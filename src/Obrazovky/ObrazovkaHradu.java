package Obrazovky;

import Hrad.Hrad;
import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.awt.*;

public class ObrazovkaHradu extends Obrazovka{
    private Hrad hrad;
    private PanelNaPozadi panelHradu;
    private StylTlacitek btnKasarna;
    private StylTlacitek btnStavba;
    public ObrazovkaHradu(String nazev, boolean malaObrazovka, Hrad hrad) {
        super(nazev, malaObrazovka);
        this.hrad = hrad;
        btnKasarna = new StylTlacitek("Kasárna");
        btnStavba = new StylTlacitek("Stavba");
        btnKasarna.nastavJakoObrazek("/Obrazky/ObrazkyVHradu/Barracks.png", 400, 225);
        btnStavba.nastavJakoObrazek("/Obrazky/ObrazkyVHradu/StavbaTlacitko.png", 280, 80);
    }

    @Override
    public void inicializace() {
        panelHradu = new PanelNaPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
        panelHradu.setLayout(new BorderLayout());
        JLabel nadpis = new JLabel("Vítej ve svém novém hradě", SwingConstants.CENTER);
        nadpis.setFont(new Font("Georgia", Font.BOLD, 40));
        nadpis.setForeground(Color.WHITE);
        panelHradu.add(nadpis, BorderLayout.NORTH);

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
