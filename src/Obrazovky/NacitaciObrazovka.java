package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;
import java.awt.*;

public class NacitaciObrazovka extends Obrazovka {


    private JButton btnStart;
    private JButton btnNastaveni;
    private JButton btnKonec;
    PanelNaPozadi nacitaciPanel;


    public NacitaciObrazovka(String nazev) {
        super(nazev, false);
        nacitaciPanel = new PanelNaPozadi("/Obrazky/pozadiNacitaciObrazovky.png");

    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;

    @Override
    public void inicializace() {
        this.okno.setContentPane(nacitaciPanel);
        btnStart = new JButton();
        btnNastaveni = new JButton();
        btnStart.setBounds((int) ( sirka*0.30), (int) ( vyska*0.439), (int) ( sirka*0.14), (int) (vyska*0.25));
        btnNastaveni.setBounds((int) ( sirka*0.58), (int) ( vyska*0.5), (int) ( sirka*0.12), (int) (vyska*0.22));
        StylTlacitek.zmenitNaNeviditelneTlacitko(btnStart);
        StylTlacitek.zmenitNaNeviditelneTlacitko(btnNastaveni);
        okno.add(btnStart);
        okno.add(btnNastaveni);

        funkcnost();
        okno.setLayout(null);
        this.okno.setVisible(true);


    }

    @Override
    public void funkcnost() {


        btnStart.addActionListener(e -> {

            this.okno.dispose();
            new ObrazovkaHradu("Hrad", false).inicializace();


        });
        btnNastaveni.addActionListener(e -> {

            new ObrazovkaNastaveni("Nastaveni", false).inicializace();

        });


//        btnKonec.addActionListener(e -> {
//            this.okno.dispose();
//        });
    }
}