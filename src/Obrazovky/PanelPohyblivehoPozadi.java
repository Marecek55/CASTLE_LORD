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
        int sirka= (int) (bg.getWidth(null)* meritko);
        int vyska =(int) (bg.getHeight(null)* meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < (getWidth() - sirka)) {
            noveX =getWidth() - sirka;
        }
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < (getHeight() - vyska)) {
            noveY = getHeight() - vyska;
        }
        int realnyPosunX = noveX - this.xKamery;
        int realnyPosunY = noveY - this.yKamery;
        this.xKamery = noveX;
        this.yKamery = noveY;

        for (Component component : getComponents()) {
            int novaPoziceTlacitkaX = component.getX() + realnyPosunX;
            int novaPoziceTlacitkaY = component.getY() + realnyPosunY;
            component.setLocation(novaPoziceTlacitkaX, novaPoziceTlacitkaY);
        }
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
        double max;
        if (minimalniSirka > minimalniVyska) {
            max = minimalniSirka;
        } else {
            max = minimalniVyska;
        }
        if (meritko < max) {
            meritko = max;
        }
        if (meritko > 3.0) {
            meritko = 3.0;
        }
        double zmena = meritko/stareMeritko;

        int noveX = (int) (xMysi - (xMysi - xKamery) * zmena);
        int noveY = (int) (yMysi - (yMysi - yKamery) * zmena);
        int novaSirka = (int) (bg.getWidth(null) * meritko);
        int novaVyska= (int) (bg.getHeight(null) * meritko);

        if (noveX > 0) {
            noveX = 0;
        }
        if (noveX < (getWidth() - novaSirka)) {
            noveX = getWidth() - novaSirka;
        }
        if (noveY > 0) {
            noveY = 0;
        }
        if (noveY < (getHeight() - novaVyska)) {
            noveY = getHeight() - novaVyska;
        }

        for (Component component : getComponents()) {
            int tlacitkoX = (int) (xMysi -(xMysi - component.getX()) * zmena);
            int tlacitkoY = (int) (yMysi - (yMysi - component.getY())* zmena);
            int spravneX = noveX- (int) (xMysi - (xMysi - xKamery) * zmena);
            int spravneY = noveY -(int) (yMysi - (yMysi - yKamery) * zmena);
            component.setLocation(tlacitkoX + spravneX, tlacitkoY + spravneY);
        }
        this.xKamery = noveX;
        this.yKamery = noveY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grafika2D = (Graphics2D) g;

        if (bg != null) {
            int novaSirka = (int) (bg.getWidth(null) * meritko);
            int novaVyska = (int) (bg.getHeight(null) * meritko);
            grafika2D.drawImage(bg, xKamery, yKamery, novaSirka, novaVyska, null);
        }
    }
}