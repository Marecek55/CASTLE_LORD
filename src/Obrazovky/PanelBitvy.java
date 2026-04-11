package Obrazovky;

import java.awt.*;

public class PanelBitvy extends PanelNaPozadi{
    private SoubojovaObrazovka soubojovaObrazovka;

    public PanelBitvy(String nazevObrazku, SoubojovaObrazovka soubojovaObrazovka) {
        super(nazevObrazku);
        this.soubojovaObrazovka = soubojovaObrazovka;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        soubojovaObrazovka.vykresliPostavyATexty(g);
    }
}
