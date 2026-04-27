package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;

public class ObrazovkaNastaveni extends Obrazovka {
    private JButton btnHudba;
    private JButton btnZpet;
    private JButton btnUlozit;
    private JDialog dialog;
    private PanelNaPozadi pozadiNastaveni;

    public ObrazovkaNastaveni(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        this.dialog = new JDialog(okno, nazev, true);
        pozadiNastaveni = new PanelNaPozadi("/Obrazky/ObrazkyNaNacitaciObrazovce/obrazekNastaveni.png");
        btnZpet = new JButton();
        btnUlozit = new JButton();
        btnHudba = new JButton();

        inicializace();
        funkcnost();
        dialog.setVisible(true);
    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;
    int sirkaTlacitek = (int) (sirka*0.2);
    int vyskaTlacitek  = (int) (sirkaTlacitek* (368.0 / 679.0));
    int yTlacitek = (int) (vyska*0.7);
    @Override
    public void inicializace() {
        pozadiNastaveni.setLayout(null);
        String cesta = "";

        if (Hra.hudbaPozadi.hraje()) {
             cesta = "tlacitkoHudbaOn";
        }else {
             cesta = "tlacitkoHudbaOff";
        }
        StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/"+cesta+".png", sirkaTlacitek,vyskaTlacitek);

        StylTlacitek.nastavJakoObrazek(btnUlozit, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoUlozit.png", sirkaTlacitek,vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitek,vyskaTlacitek);

        btnHudba.setLocation((int) (sirka*0.4), (int) (vyska* 0.5));
        btnUlozit.setLocation((int) (sirka*0.5), yTlacitek);
        btnZpet.setLocation((int) (sirka*0.3),yTlacitek);
        pozadiNastaveni.add(btnHudba);
        pozadiNastaveni.add(btnUlozit);
        pozadiNastaveni.add(btnZpet);
        dialog.setUndecorated(true);
        dialog.setContentPane(pozadiNastaveni);
        dialog.setSize(Hra.sirkaObrazovky, Hra.vyskaObrazovky);


    }

    @Override
    public void funkcnost() {
        btnHudba.addActionListener(e -> {
                if (Hra.hudbaPozadi.hraje()) {
                    Hra.hudbaPozadi.zastav();
                    StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoHudbaOff.png", sirkaTlacitek, vyskaTlacitek);
                } else {
                    Hra.hudbaPozadi.hraj(true);
                    StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoHudbaOn.png", sirkaTlacitek, vyskaTlacitek);
                }

                btnHudba.revalidate();
                btnHudba.repaint();
        });


        btnZpet.addActionListener(e ->{
            dialog.dispose();
        });
    }
}
