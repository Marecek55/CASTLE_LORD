package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;

public class NacitaciObrazovka extends Obrazovka {


    private JButton btnHrat;
    private JButton btnNastaveni;
    private JButton btnKonec;
    PanelNaPozadi nacitaciPanel;


    public NacitaciObrazovka(String nazev) {
        super(nazev, false);
        btnHrat = new JButton("");
        btnNastaveni = new JButton("");


    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;

    @Override
    public void inicializace() {
        nacitaciPanel = new PanelNaPozadi("/Obrazky/ObrazkyNaNacitaciObrazovce/pozadiNacitaciObrazovky.png");
        btnNastaveni.setBounds(40,100,100,100);
        nacitaciPanel.setLayout(null);

        nacitaciPanel.add(btnNastaveni);


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


//        btnKonec.addActionListener(e -> {
//            this.okno.dispose();
//        });
    }
}