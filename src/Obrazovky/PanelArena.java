package Obrazovky;

import Logika.Hra;
import Logika.Souboj;
import Logika.TvorbaPostav;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelArena extends PanelNaPozadi {
    private JFrame okno;

    private JButton btnZpet;
    private JButton bitvaTlacitko;
    private ObrazovkaMapy predchoziObrazovka;

    public PanelArena(String obrazek, JFrame okno, ObrazovkaMapy predchoziObrazovka) {
        super("/Obrazky/ObrazkyBoje/arena.png");
        this.okno = okno;
        btnZpet = new JButton();
        bitvaTlacitko = new JButton();
        this.predchoziObrazovka = predchoziObrazovka;


        int sirkaTlacitka = (int) (sirka * 0.2);
        int vzdalenostOdKraje = (int) (sirka * 0.009);

        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnZpet.setLocation((int) (sirka - (sirkaTlacitka * 0.92)), -vzdalenostOdKraje);
        add(btnZpet);

        btnZpet.addActionListener(e -> {
            okno.setContentPane(predchoziObrazovka.getMapa());
            okno.revalidate();
            okno.repaint();
        });

        inicializace();
    }

    int sirka = Hra.sirkaObrazovky;
    int vyska = Hra.vyskaObrazovky;

    private void inicializace() {
        int sirkaTlacitka = (int) (sirka * 0.4);
        int vyskaTlacitka = sirkaTlacitka * 273 / 914;

        Font font = new Font("Georgia", Font.BOLD, (int) (vyska * 0.06));
        Color barvaTextu = new Color(60, 30, 10);


        int urovenHradu = Hra.urovenHradu;
        ArrayList<Postava> nepratelskyTym = new ArrayList<>();
        if (Hra.hracuvTym.size() == 1){
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
        } else if (Hra.hracuvTym.size() == 2) {
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
        }else {
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
            nepratelskyTym.add(TvorbaPostav.tvorbaProtihracovaBojovnika("Nepřítel", urovenHradu));
        }





        int silaNepratel = 0;
        for (Postava nepritel : nepratelskyTym) {
            silaNepratel = silaNepratel + nepritel.getSilaPostavy();
        }
        int pocetProtivniku = nepratelskyTym.size();
        ArrayList<Postava> hracuvTym = Hra.hracuvTym;
        JLabel silaProtivniku = new JLabel(String.valueOf(silaNepratel));
        silaProtivniku.setFont(font);
        silaProtivniku.setForeground(barvaTextu);
        silaProtivniku.setHorizontalAlignment(SwingConstants.CENTER);
        silaProtivniku.setBounds((int) (sirka * 0.57), (int) (vyska * 0.303), (int) (sirka * 0.18), (int) (vyska * 0.07));
        add(silaProtivniku);
        JLabel pocetProtivnikuText = new JLabel(String.valueOf(pocetProtivniku));
        pocetProtivnikuText.setFont(font);
        pocetProtivnikuText.setForeground(barvaTextu);
        pocetProtivnikuText.setHorizontalAlignment(SwingConstants.CENTER);
        pocetProtivnikuText.setBounds((int) (sirka * 0.57), (int) (vyska * 0.447), (int) (sirka * 0.18), (int) (vyska * 0.07));
        add(pocetProtivnikuText);

        StylTlacitek.nastavJakoObrazek(bitvaTlacitko, "/Obrazky/ObrazkyBoje/bitvaArenaTlacitko.png", sirkaTlacitka, vyskaTlacitka);
        bitvaTlacitko.setLocation(sirka / 2 - sirkaTlacitka / 2, (int) (vyska * 0.60));
        add(bitvaTlacitko);

        bitvaTlacitko.addActionListener(e1 -> {
            if (okno != null) {
                SoubojovaObrazovka soubojovaObrazovka = new SoubojovaObrazovka("Souboj", false, okno, "arena", predchoziObrazovka);
                soubojovaObrazovka.setHracuvTym(hracuvTym);
                soubojovaObrazovka.setNepratelskyTym(nepratelskyTym);
                soubojovaObrazovka.inicializace();

                okno.setContentPane(soubojovaObrazovka.getArenaPanel());

                Souboj arena = new Souboj(hracuvTym, nepratelskyTym, soubojovaObrazovka);
                okno.revalidate();
                okno.repaint();
                arena.startBitvy();
            }
        });
    }
}