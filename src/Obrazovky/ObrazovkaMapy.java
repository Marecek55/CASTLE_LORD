package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;

public class ObrazovkaMapy extends Obrazovka{
    private PanelNaPozadi mapa;
    private JButton btnLes;
    private JButton btnArena;
    public ObrazovkaMapy(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        mapa = new PanelNaPozadi("/Obrazky/ObrazkyBoje/bojovaMapa.png");
        mapa.setLayout(null);
        btnLes = new JButton();
        btnArena = new JButton();
        inicializace();
        funkcnost();
        okno.setContentPane(mapa);
        okno.setVisible(true);
    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;
    int sirkaTlacitek = (int) (sirka * 0.3);
    int vyskaTlacitek = (int) (sirkaTlacitek * (371.0 / 673.0));
    int yTlacitek = (int) (vyska * 0.7);

    @Override
    public void inicializace() {

        StylTlacitek.nastavJakoObrazek(btnArena, "/Obrazky/ObrazkyBoje/tlacitkoArena.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnLes, "/Obrazky/ObrazkyBoje/tlacitkoLesGoblinu.png", sirkaTlacitek, vyskaTlacitek);
        btnArena.setLocation((int) (sirka * 0.70), yTlacitek);
        btnLes.setLocation((int) (sirka * 0.05), yTlacitek);
        mapa.add(btnArena);
        mapa.add(btnLes);

    }

    @Override
    public void funkcnost() {

    }
}
