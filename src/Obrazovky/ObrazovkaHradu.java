package Obrazovky;

import Hrad.Hrad;
import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.awt.*;

public class ObrazovkaHradu extends Obrazovka{
    private Hrad hrad;
    private PanelNaPozadi panelHradu;
    private JButton btnKasarna;
    private JButton btnStavba;
    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);

        btnKasarna = new JButton("Kasárna");
        btnStavba = new JButton("Stavba");

    }

    @Override
    public void inicializace() {
        StylTlacitek.nastavJakoObrazek(btnKasarna, "/Obrazky/ObrazkyVHradu/Barracks.png", 400, 225);
        StylTlacitek.nastavJakoObrazek(btnStavba,"/Obrazky/ObrazkyVHradu/StavbaTlacitko.png", 280, 80);
        panelHradu = new PanelNaPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
        panelHradu.setLayout(new BorderLayout());
        JPanel stredovyPanel = new JPanel(new GridBagLayout());
        stredovyPanel.setOpaque(false);
        stredovyPanel.add(btnKasarna);
        panelHradu.add(stredovyPanel, BorderLayout.CENTER);

        JPanel spodniPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        spodniPanel.setOpaque(false);
        spodniPanel.add(btnStavba);
        panelHradu.add(spodniPanel, BorderLayout.SOUTH);

        okno.setContentPane(panelHradu);
        okno.setVisible(true);
    }

    @Override
    public void funkcnost() {

    }
}
