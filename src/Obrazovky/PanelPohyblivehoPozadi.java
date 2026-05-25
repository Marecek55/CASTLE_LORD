package Obrazovky;

import Hrad.TypMistnosti;
import java.awt.*;
import java.util.HashMap;

public class PanelPohyblivehoPozadi extends PanelNaPozadi {
    private int xKamery = 0;
    private int yKamery = 0;
    private double meritko = 1.0;
    private ObrazovkaHradu obrazovka;

    private Image kasarna = nactiObrazek("/Obrazky/ObrazkyVHradu/kasarnaMistnost.png");
    private Image lekarna = nactiObrazek("/Obrazky/ObrazkyVHradu/lekarnaMistnost.png");
    private Image trenink = nactiObrazek("/Obrazky/ObrazkyVHradu/treninkovaMistnost.png");
    private Image skladPenez = nactiObrazek("/Obrazky/ObrazkyVHradu/skladPenez.png");
    private Image skladJidla = nactiObrazek("/Obrazky/ObrazkyVHradu/skladJidla.png");

    public PanelPohyblivehoPozadi(String nazevObrazku, ObrazovkaHradu obrazovka) {
        super(nazevObrazku);
        this.obrazovka = obrazovka;
        setPohyblivePozadi(true);
        setLayout(null);
    }

    public int getXKamery() {
        return xKamery;
    }
    public int getYKamery() {
        return yKamery;
    }
    public double getMeritko() {
        return meritko;
    }

    public void posunKamerou(int x, int y) {
        int noveX = xKamery + x;
        int noveY = yKamery + y;
        int aktualniSirkaObrazku = (int) (bg.getWidth(null) * meritko);
        int aktualniVyskaObrazku = (int) (bg.getHeight(null) * meritko);
        if (noveX > 0) noveX = 0;
        if (noveX < getWidth() - aktualniSirkaObrazku) noveX = getWidth() - aktualniSirkaObrazku;
        if (noveY > 0) noveY = 0;
        if (noveY < getHeight() - aktualniVyskaObrazku) noveY = getHeight() - aktualniVyskaObrazku;

        xKamery = noveX;
        yKamery = noveY;
        repaint();
    }

    public void zmenaOddaleni(int smerKolecka, int xMysi, int yMysi) {
        double stareMeritko = meritko;
        if (smerKolecka > 0) meritko -= 0.05;
        else meritko += 0.05;
        double minimalniSirka = (double) getWidth() / bg.getWidth(null);
        double minimalniVyska = (double) getHeight() / bg.getHeight(null);
        double minimalniMeritko = Math.max(minimalniSirka, minimalniVyska);
        if (meritko < minimalniMeritko) meritko = minimalniMeritko;
        if (meritko > 2.5) meritko = 2.5;
        double procentoRozdilu = meritko / stareMeritko;
        int noveX = (int) (xMysi - (xMysi - xKamery) * procentoRozdilu);
        int noveY = (int) (yMysi - (yMysi - yKamery) * procentoRozdilu);
        if (noveX > 0) noveX = 0;
        if (noveX < getWidth() - (int)(bg.getWidth(null) * meritko)) noveX = getWidth() - (int)(bg.getWidth(null) * meritko);
        if (noveY > 0) noveY = 0;
        if (noveY < getHeight() - (int)(bg.getHeight(null) * meritko)) noveY = getHeight() - (int)(bg.getHeight(null) * meritko);
        xKamery = noveX;
        yKamery = noveY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (bg != null) {
            g2.drawImage(bg, xKamery, yKamery, (int)(bg.getWidth(null) * meritko), (int)(bg.getHeight(null) * meritko), null);
        }
        if (obrazovka.getHrad() == null) return;

        Graphics2D gMapy = (Graphics2D) g2.create();
        gMapy.translate(xKamery, yKamery);
        gMapy.scale(meritko, meritko);
        if (kasarna != null){
            gMapy.drawImage(kasarna, 787, 1114, 1494, 498, null);
        }

        HashMap<Integer, Integer[]> lokace = obrazovka.getHrad().getLokaceMistnosti();
        HashMap<Integer, TypMistnosti> stavby = obrazovka.getHrad().getPostavene();
        for (int pozice : obrazovka.getHrad().getPozicePostavenych()) {
            int x = lokace.get(pozice)[0];
            int y = lokace.get(pozice)[1];
            TypMistnosti typ = stavby.get(pozice);
            Image obrazek = null;
            if (typ == TypMistnosti.LEKARNA) {
                obrazek = lekarna;
            }
            else if (typ == TypMistnosti.TRENINKOVA_HALA){
                obrazek = trenink;
            }
            else if (typ == TypMistnosti.SKLAD_PENEZ){
                obrazek = skladPenez;
            }
            else if (typ == TypMistnosti.SKLAD_JIDLA) {
                obrazek = skladJidla;
            }
            if (obrazek != null) {
                gMapy.drawImage(obrazek, x, y, 747, 498, null);
            }
        }
        gMapy.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D pozadi = (Graphics2D) g.create();
        for (Component c : getComponents()) {
            if (c.getName() != null && (c.getName().equals("pevneTlacitka") || c.getName().equals("pevneTlacitkaText"))) {
                Graphics2D gTlacitka = (Graphics2D) pozadi.create();
                gTlacitka.translate(c.getX(), c.getY());
                c.paint(gTlacitka);
                gTlacitka.dispose();
            }
        }
        pozadi.dispose();
    }

    @Override
    public Component getComponentAt(int x, int y) {
        for (Component component : getComponents()) {
            if (component.getName() != null && (component.getName().equals("pevneTlacitka") || component.getName().equals("pevneTlacitkaText"))) {
                if (component.getBounds().contains(x, y)) {
                    return component;
                }
            }
        }
        return this;
    }

    public Image getSkladPenez() {
        return skladPenez;
    }

    public void setSkladPenez(Image skladPenez) {
        this.skladPenez = skladPenez;
    }
}