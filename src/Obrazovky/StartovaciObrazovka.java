package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;

/**
 * Tato trida v sobe ma pridava zacatecnicka tlactika na spusteni hry a nastaveni a ukonceni
 */
public class StartovaciObrazovka extends Obrazovka {
    private JButton btnHrat;
    private JButton btnNastaveni;
    private JButton btnKonec;
    PanelNaPozadi nacitaciPanel;


    public StartovaciObrazovka(String nazev) {
        super(nazev, false);
        inicializace();
    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;

    /**
     * Tato metoda nastavuje lokaci velikost a obrazky tlacitek
     */
    @Override
    public void inicializace() {

        btnHrat = new JButton();
        btnNastaveni = new JButton();
        btnKonec = new JButton();
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

    /**
     * Tato metoda dava funkce tlacitkum a pta se hrace na jmeno prvniho bojovnika
     * a kdyz nezada nic zada mu zakladni jmeno
     */
    @Override
    public void funkcnost() {


        btnHrat.addActionListener(e -> {

            if (Hra.hracuvTym.isEmpty()) {
                String zadaneJmeno = JOptionPane.showInputDialog(okno, "Zadej jméno svého prvního bojovníka:");
                if (zadaneJmeno == null) {
                    zadaneJmeno = "EDVARD";
                }
                Hra.hracuvTym(zadaneJmeno.toUpperCase());
            }
            if (Hra.obrazovkaHradu == null) {
                Hra.obrazovkaHradu = new ObrazovkaHradu("Hrad", false);
            } else {
                Hra.obrazovkaHradu.getOkno().setVisible(true);
            }
            this.okno.setVisible(false);


        });

        btnNastaveni.addActionListener(e -> {

            new ObrazovkaNastaveni("Nastaveni", false)  ;

        });


        btnKonec.addActionListener(e -> {
            Hra.hudbaPozadi.zastav();
            this.okno.dispose();

        });
    }
}