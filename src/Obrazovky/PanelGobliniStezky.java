package Obrazovky;

import Logika.Hra;
import Logika.Souboj;
import Logika.TvorbaPostav;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Bojovnik;
import Postavy.Goblin;
import Postavy.Postava;
import Predmety.Rarita;
import Predmety.Zbrane.Luk;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;
import Predmety.Zbroj.OceloveBrneni;

import javax.swing.*;
import java.util.ArrayList;

public class PanelGobliniStezky extends PanelNaPozadi {
    private PanelNaPozadi urovneStezky;
    ArrayList<JButton> seznamTlacitek;
    private JButton bitvaTlacitko;
    private JFrame okno;

    public PanelGobliniStezky(String obrazek, JFrame okno) {
        super(obrazek);
        seznamTlacitek = new ArrayList<>();
        this.okno = okno;
        for (int i = 0; i < 20  ; i++) {
            JButton tlacitkoUrovne = new JButton();
            seznamTlacitek.add(tlacitkoUrovne);

        }

        tvorbaTlacitka(0, 0.09, 0.730);
        tvorbaTlacitka(1, 0.19, 0.620);
        tvorbaTlacitka(2, 0.31, 0.7);
        tvorbaTlacitka(3, 0.50, 0.75);
        tvorbaTlacitka(4, 0.65, 0.72);
        tvorbaTlacitka(5, 0.76, 0.78);
        tvorbaTlacitka(6, 0.77, 0.57);
        tvorbaTlacitka(7, 0.81, 0.445);
        tvorbaTlacitka(8, 0.63, 0.47);
        tvorbaTlacitka(9, 0.52, 0.45);
        tvorbaTlacitka(10, 0.36, 0.49);
        tvorbaTlacitka(11, 0.26, 0.46);
        tvorbaTlacitka(12, 0.16, 0.34);
        tvorbaTlacitka(13, 0.18, 0.16);
        tvorbaTlacitka(14, 0.30, 0.19);
        tvorbaTlacitka(15, 0.43, 0.14);
        tvorbaTlacitka(16, 0.53, 0.16);
        tvorbaTlacitka(17, 0.65, 0.14);
        tvorbaTlacitka(18, 0.75, 0.19);
        tvorbaTlacitka(19, 0.85, 0.19);
        bitvaTlacitko = new JButton();

    }
    int sirka = Hra.sirkaObrazovky;
    int vyska = Hra.vyskaObrazovky;
    int sirkaTlacitek = (int) (sirka * 0.07);
    int vyskaTlacitek = sirkaTlacitek * 495/504;
    public void tvorbaTlacitka(int tlacitko, double x , double y) {
        JButton button = seznamTlacitek.get(tlacitko);
        int upravenaUroven = Hra.urovenGobliniStezky - 1;

        if (tlacitko > upravenaUroven) {
            StylTlacitek.nastavJakoObrazek(button, "/Obrazky/ObrazkyBoje/levelZamcen.png", sirkaTlacitek, vyskaTlacitek);
            Icon krizek = button.getIcon();
            button.setDisabledIcon(krizek);
            button.setEnabled(false);
        }else if (tlacitko == upravenaUroven) {
            button.setIcon(null);
            StylTlacitek.zmenitNaNeviditelneTlacitko(button);
            button.setEnabled(true);
        }else if (tlacitko < upravenaUroven) {
            StylTlacitek.nastavJakoObrazek(button, "/Obrazky/ObrazkyBoje/levelDokoncen.png", sirkaTlacitek, vyskaTlacitek);
            button.setEnabled(true);
        }
        int poziceX = (int) (sirka * x);
        int poziceY = (int) (vyska * y);
        button.setBounds(poziceX, poziceY, sirkaTlacitek, vyskaTlacitek);

        add(button);
        button.addActionListener(e -> {
            int sirkaTlacitka = (int) (sirka * 0.4);
            int vyskaTlacitka = sirkaTlacitka * 273/914;
            if (okno != null) {
                urovneStezky = new PanelNaPozadi("/Obrazky/ObrazkyBoje/gobliniUrovne.png");
                StylTlacitek.nastavJakoObrazek(bitvaTlacitko,"/Obrazky/ObrazkyBoje/bitvaTlacitko.png", sirkaTlacitka,vyskaTlacitka);
                bitvaTlacitko.setLocation(sirka/2 - sirkaTlacitka/2, (int) (vyska* 0.58));
                urovneStezky.add(bitvaTlacitko);
                for (int i = 0; i < seznamTlacitek.size(); i++) {
                    seznamTlacitek.get(i).setVisible(false);

                }
                bitvaTlacitko.addActionListener(e1 -> {
                    if (okno != null) {
                        SoubojovaObrazovka soubojovaObrazovka = new SoubojovaObrazovka("Souboj", false, okno ,"arena");
                        ArrayList<Postava> hracuvTym = new ArrayList<>();
                        ArrayList<Postava> goblin = new ArrayList<>();
                        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Ahoj", 1));
                        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Ahoj", 1));
                        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Ahoj", 1));
                        goblin.add(TvorbaPostav.tvorbaGoblina(upravenaUroven));
                        goblin.add(TvorbaPostav.tvorbaGoblina(upravenaUroven));
                        goblin.add(TvorbaPostav.tvorbaGoblina(upravenaUroven));
                        soubojovaObrazovka.setHracuvTym(hracuvTym);
                        soubojovaObrazovka.setNepratelskyTym(goblin);
                        soubojovaObrazovka.inicializace();
                        okno.setContentPane(soubojovaObrazovka.getArenaPanel());
                        Souboj arena = new Souboj(hracuvTym, goblin, soubojovaObrazovka);
                        okno.revalidate();
                        okno.repaint();
                        arena.startBitvy();
                    }
                });
                urovneStezky.setLayout(null);
                urovneStezky.setBounds(0, 0, okno.getWidth(), okno.getHeight());
                okno.add(urovneStezky);
                okno.revalidate();
                okno.repaint();
            }
        });

        }





}
