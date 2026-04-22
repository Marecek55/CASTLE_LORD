package Obrazovky;

import Logika.Hra;

import javax.swing.*;

public class ObrazovkaNastaveni extends Obrazovka {
    private JButton btnHudba;
    private JButton btnExit;
    private JButton btnUlozit;
    private JButton btnKonec;
    private JDialog dialog;
    private PanelNaPozadi pozadiNastaveni;
    public ObrazovkaNastaveni(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        this.dialog = new JDialog(okno, nazev, true);

        pozadiNastaveni = new PanelNaPozadi("/Obrazky/ObrazkyNaNacitaciObrazovce/obrazekNastaveni.png");

        inicializace();
        funkcnost();
    }

    @Override
    public void inicializace() {
        dialog.setUndecorated(true);
        dialog.setContentPane(pozadiNastaveni);
        dialog.setSize(Hra.sirkaObrazovky, Hra.vyskaObrazovky);
        dialog.setVisible(true);

    }

    @Override
    public void funkcnost() {

    }
}
