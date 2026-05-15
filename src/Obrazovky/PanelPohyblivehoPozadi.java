package Obrazovky;

import java.awt.*;

public class PanelPohyblivehoPozadi extends PanelNaPozadi {
    private int xKamery = 0;
    private int yKamery = 0;
    private double meritko = 1.0;

    public PanelPohyblivehoPozadi(String nazevObrazku) {
        super(nazevObrazku);
        setPohyblivePozadi(true);
        setLayout(null);
    }

    public void posunKamerou(int x, int y) {
        int noveX = xKamery + x;
        int noveY = yKamery + y;
        int aktualniSirkaObrazku = (int) (bg.getWidth(null) * meritko);
        int aktualniVyskaObrazku = (int) (bg.getHeight(null) * meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < getWidth() - aktualniSirkaObrazku) {
            noveX = getWidth() - aktualniSirkaObrazku;
        }
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < getHeight() - aktualniVyskaObrazku) {
            noveY = getHeight() - aktualniVyskaObrazku;
        }

        xKamery = noveX;
        yKamery = noveY;
        repaint();
    }

    public void zmenaOddaleni(int smerKolecka, int xMysi, int yMysi) {
        double stareMeritko = meritko;
        if (smerKolecka > 0) {
            meritko = meritko - 0.05;
        } else {
            meritko = meritko + 0.05;
        }

        double minimalniSirka = (double) getWidth() / bg.getWidth(null);
        double minimalniVyska = (double) getHeight() / bg.getHeight(null);
        double minimalniMeritko;

        if (minimalniSirka > minimalniVyska) {
            minimalniMeritko = minimalniSirka;
        } else {
            minimalniMeritko = minimalniVyska;
        }

        if (meritko < minimalniMeritko) {
            meritko = minimalniMeritko;
        }
        if (meritko > 2.5) {
            meritko = 2.5;
        }
        double procentoRozdilu = meritko / stareMeritko;
        int noveX = (int) (xMysi - (xMysi - xKamery) * procentoRozdilu);
        int noveY = (int) (yMysi - (yMysi - yKamery) * procentoRozdilu);
        int sirkaPoPriblizeni =(int) (bg.getWidth(null) * meritko);
        int vyskaPoPriblizeni = (int) (bg.getHeight(null) * meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < getWidth() - sirkaPoPriblizeni) {
            noveX = getWidth() - sirkaPoPriblizeni;}
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < getHeight() - vyskaPoPriblizeni) {
            noveY = getHeight() - vyskaPoPriblizeni;
        }
        xKamery = noveX;
        yKamery = noveY;
        repaint();
    }

    @Override
    protected void paintChildren(Graphics grafika) {
        Graphics2D grafika2D = (Graphics2D) grafika.create();
        for (Component component : getComponents()) {
            if (component.getName() == null || !component.getName().equals("pevneTlacitka")) {
                Graphics2D grafikaSveta = (Graphics2D) grafika2D.create();
                grafikaSveta.translate(xKamery, yKamery);
                grafikaSveta.scale(meritko, meritko);
                grafikaSveta.translate(component.getX(), component.getY());
                component.paint(grafikaSveta);
                grafikaSveta.dispose();


            }
        }

        for (Component component : getComponents()) {
            if (component.getName() != null && component.getName().equals("pevneTlacitka")) {
                Graphics2D grafikaUI = (Graphics2D) grafika2D.create();
                grafikaUI.translate(component.getX(), component.getY());
                component.paint(grafikaUI);

                grafikaUI.dispose();
            }
        }
        grafika2D.dispose();
    }

    @Override
    public Component getComponentAt(int x, int y) {
        for (Component component : getComponents()) {
            if (component.getName() != null && component.getName().equals("pevneTlacitka")) {
                if (component.getBounds().contains(x, y)) {

                    return component;
                }
            }
        }


        int xMapy = (int) ((x - xKamery) / meritko);
        int yMapy = (int)((y - yKamery) / meritko);


        for (Component component : getComponents()) {
            if (component.getName() == null || !component.getName().equals("pevneTlacitka")) {
                if (component.getBounds().contains(xMapy, yMapy)) {
                    return component;
                }
            }
        }

        return super.getComponentAt(x, y);
    }

    @Override
    protected void paintComponent(Graphics grafika) {
        super.paintComponent(grafika);
        Graphics2D grafika2D = (Graphics2D) grafika;
        if (bg != null) {
            int sirkaObrazku = (int) (bg.getWidth(null) * meritko);
            int vyskaObrazku = (int) (bg.getHeight(null) * meritko);
            grafika2D.drawImage(bg, xKamery, yKamery, sirkaObrazku, vyskaObrazku, null);
        }
    }
}