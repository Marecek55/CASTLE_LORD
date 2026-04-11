package Obrazovky;

import java.awt.Color;

public class LetajiciText {
    public int poziceX;
    public int poziceY;
    public String zobrazovanyText;
    public Color barvaTextu;
    public int zbyvajiciCasZobrazeni;

    public LetajiciText(int poziceX, int poziceY, String zobrazovanyText, Color barvaTextu) {
        this.poziceX = poziceX;
        this.poziceY = poziceY;
        this.zobrazovanyText = zobrazovanyText;
        this.barvaTextu = barvaTextu;
        this.zbyvajiciCasZobrazeni = 40;
    }
}