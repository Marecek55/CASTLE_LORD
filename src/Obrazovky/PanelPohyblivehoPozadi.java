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
        int noveX = this.xKamery + x;
        int noveY = this.yKamery + y;
        int aktualniSirkaObrazku = (int)(bg.getWidth(null) * meritko);
        int aktualniVyskaObrazku = (int)(bg.getHeight(null) * meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < getWidth()- aktualniSirkaObrazku) {
            noveX = getWidth() -aktualniSirkaObrazku;
        }
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < getHeight() -aktualniVyskaObrazku) {
            noveY = getHeight() - aktualniVyskaObrazku;
        }

        this.xKamery = noveX;
        this.yKamery = noveY;
        repaint();
    }

    public void zmenaOddaleni(int smerKolecka, int xMysi, int yMysi) {
        double stareMeritko = meritko;

        if (smerKolecka > 0) {
            meritko = meritko - 0.05;
        } else {
            meritko = meritko + 0.05;
        }

        double minimalniSirka = (double) getWidth() /bg.getWidth(null);
        double minimalniVyska = (double) getHeight() /bg.getHeight(null);
        double nejmensiMozneMeritko;

        if (minimalniSirka > minimalniVyska) {
            nejmensiMozneMeritko = minimalniSirka;
        } else {
            nejmensiMozneMeritko = minimalniVyska;
        }

        if (meritko < nejmensiMozneMeritko) {
            meritko = nejmensiMozneMeritko;
        }
        if (meritko > 3.0) {
            meritko = 3.0;
        }

        double koeficientZmeny = meritko /stareMeritko;
        int noveX = (int)(xMysi - (xMysi - xKamery) * koeficientZmeny);
        int noveY = (int)(yMysi - (yMysi - yKamery) * koeficientZmeny);

        int novaSirkaPoZoomu = (int)(bg.getWidth(null) * meritko);
        int novaVyskaPoZoomu = (int)(bg.getHeight(null) * meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < getWidth() -novaSirkaPoZoomu) {
            noveX = getWidth() - novaSirkaPoZoomu;
        }
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < getHeight() -novaVyskaPoZoomu) {
            noveY = getHeight() - novaVyskaPoZoomu;
        }

        this.xKamery = noveX;
        this.yKamery = noveY;
        repaint();
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D grafika2D = (Graphics2D) g.create();
        grafika2D.translate(xKamery, yKamery);
        grafika2D.scale(meritko, meritko);
        super.paintChildren(grafika2D);
        grafika2D.dispose();
    }

    @Override
    public Component getComponentAt(int x, int y) {
        int stareX = (int)((x - xKamery) /meritko);
        int stareY = (int)((y - yKamery) / meritko);

        for (Component tlacitko : getComponents()) {
            if (tlacitko.getBounds().contains(stareX, stareY)) {
                return tlacitko;
            }
        }
        return super.getComponentAt(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grafika2D = (Graphics2D) g;
        if (bg != null) {
            int sirkaObrazku = (int)(bg.getWidth(null) * meritko);
            int vyskaObrazku = (int)(bg.getHeight(null) * meritko);
            grafika2D.drawImage(bg, xKamery, yKamery, sirkaObrazku, vyskaObrazku, null);
        }
    }
}