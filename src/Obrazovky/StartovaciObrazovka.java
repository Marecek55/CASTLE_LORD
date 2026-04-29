package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;

public class StartovaciObrazovka extends Obrazovka {
    private JButton btnHrat;
    private JButton btnNastaveni;
    private JButton btnKonec;
    PanelNaPozadi nacitaciPanel;


    public StartovaciObrazovka(String nazev) {
        super(nazev, false);
        btnHrat = new JButton();
        btnNastaveni = new JButton();
        btnKonec = new JButton();
    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;

    @Override
    public void inicializace() {
        nacitaciPanel = new PanelNaPozadi("/Obrazky/ObrazkyNaNacitaciObrazovce/pozadiNacitaciObrazovky.png");
        int sirkaTlacitek = (int) (sirka*0.2);
        int vyskaTlacitek  = (int) (sirkaTlacitek* (368.0 / 679.0));
        StylTlacitek.nastavJakoObrazek(btnHrat, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoHrat.png", sirkaTlacitek,vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnNastaveni, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoNastaveni.png", sirkaTlacitek,vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnKonec, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoKonec.png", sirkaTlacitek,vyskaTlacitek);
        int xTlacitek = sirka/2 - sirkaTlacitek/2;

        btnHrat.setLocation(xTlacitek, (int) (vyska*0.5));
        btnNastaveni.setLocation(xTlacitek, (int) (vyska*0.65));
        btnKonec.setLocation(xTlacitek, (int) (vyska*0.80));
        nacitaciPanel.setLayout(null);
        nacitaciPanel.add(btnNastaveni);
        nacitaciPanel.add(btnHrat);
        nacitaciPanel.add(btnKonec);
        this.okno.setContentPane(nacitaciPanel);
        funkcnost();
        this.okno.setVisible(true);
    }

    @Override
    public void funkcnost() {


        btnHrat.addActionListener(e -> {


            new ObrazovkaHradu("Hrad", false).inicializace();
            this.okno.dispose();


        });
        btnNastaveni.addActionListener(e -> {

            new ObrazovkaNastaveni("Nastaveni", false).inicializace();

        });


        btnKonec.addActionListener(e -> {
            this.okno.dispose();
        });
    }
}