package Obrazovky;
import javax.swing.*;
import java.awt.*;


public class ObrazovkaHradu extends Obrazovka {
    private PanelNaPozadi panelHradu;
    private int kameraX = 0;
    private int kameraY = 0;
    private int rychlostKamery = 30;

    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        inicializace();
        funkcnost();
    }

    @Override
    public void inicializace() {
        panelHradu = new PanelNaPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
        panelHradu.setPohyblivePozadi(true);
        okno.setContentPane(panelHradu);
        okno.revalidate();
        okno.setVisible(true);
    }

    @Override
    public void funkcnost() {

    }

}