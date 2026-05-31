package Obrazovky;

import Logika.Hra;
import Logika.Souboj;
import Logika.TvorbaPostav;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Tato metoda spousti souboj vytvari tlacitka a urovne goblini stezky
 */
public class PanelGobliniStezky extends PanelNaPozadi {
    private PanelNaPozadi urovneStezky;
    ArrayList<JButton> seznamTlacitek;
    private JButton bitvaTlacitko;
    private JFrame okno;
    private JButton btnZpet;
    private ObrazovkaMapy predchoziObrazovka;

    /**
     * Tato metoda vytvari seznam tlacitek a definuje jejich lokaci na obrazovce
     * je jich 20 na 20 urovni a kazdy je postupne tezsi
     * @param obrazek
     * @param okno
     * @param predchoziObrazovka
     */
    public PanelGobliniStezky(String obrazek, JFrame okno, ObrazovkaMapy predchoziObrazovka) {
        super(obrazek);
        btnZpet = new JButton();
        seznamTlacitek = new ArrayList<>();
        this.okno = okno;
        this.predchoziObrazovka = predchoziObrazovka;

        int sirkaTlacitka = (int) (sirka * 0.2);
        int vzdalenostOdKraje = (int) (sirka * 0.009);

        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnZpet.setLocation((int) (sirka - (sirkaTlacitka * 0.92)),-vzdalenostOdKraje);
        add(btnZpet);
        for (int i = 0; i < 20  ; i++) {
            JButton tlacitkoUrovne = new JButton();
            seznamTlacitek.add(tlacitkoUrovne);

        }
        btnZpet.addActionListener(e -> {
            if (urovneStezky != null && urovneStezky.getParent() != null) {
                okno.remove(urovneStezky);
                for (JButton button : seznamTlacitek) {
                    button.setVisible(true);
                }
                okno.revalidate();
                okno.repaint();
            } else{
                okno.setContentPane(predchoziObrazovka.getMapa());
                okno.revalidate();
                okno.repaint();
            }
        });

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

    /**
     * Tato metoda tvori tlacitko na obrazovce podle toho na jake je hrac urovni tak se vykresli vzhled jestli je zamcena
     * ta uroven  a po vyhre se prekresli na dokonceny a nastavuje obrazovku s tlacitkem bitvy po kliknuti na uroven a
     * nasledny start bitvy do ktere preda tymy do soubojoveObrazovky a kontroluje jestli hrac muze vstoupit do souboje
     * @param tlacitko tlacitko
     * @param x souradnice x
     * @param y souradnice y
     */
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
                Font font = new Font("Georgia", Font.BOLD, (int)(vyska * 0.06));
                Color barvaTextu = new Color(240, 210, 150);
                JLabel uroven = new JLabel(String.valueOf(tlacitko + 1));
                uroven.setFont(font);
                uroven.setForeground(barvaTextu);
                uroven.setHorizontalAlignment(SwingConstants.CENTER);
                uroven.setBounds((int)(sirka * 0.545), (int)(vyska * 0.12), (int)(sirka * 0.1), (int)(vyska * 0.1));
                urovneStezky.add(uroven);


                ArrayList<Postava> gobliniTym = new ArrayList<>();
                int urovenCoVybral = tlacitko + 1;
                if (tlacitko<5){
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                } else if (tlacitko<15) {
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                }else {
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                    gobliniTym.add(TvorbaPostav.tvorbaGoblina(urovenCoVybral));
                }


                int silaNepratel = 0;
                for (Postava goblin : gobliniTym) {
                    silaNepratel = silaNepratel+ goblin.getSilaPostavy();
                }

                ArrayList<Postava> hracuvTym =Hra.hracuvTym;


                int silaHrace = 0;
                for (Postava hrdina : hracuvTym) {
                    silaHrace = silaHrace+ hrdina.getSilaPostavy();
                }

                JLabel textSilyProtivniku = new JLabel(String.valueOf(silaNepratel));
                textSilyProtivniku.setFont(font);
                textSilyProtivniku.setForeground(barvaTextu);
                textSilyProtivniku.setHorizontalAlignment(SwingConstants.CENTER);
                textSilyProtivniku.setBounds((int)(sirka * 0.57), (int)(vyska * 0.305), (int)(sirka * 0.18), (int)(vyska * 0.07));
                urovneStezky.add(textSilyProtivniku);

                JLabel textSily = new JLabel(String.valueOf(silaHrace));
                textSily.setFont(font);
                textSily.setForeground(barvaTextu);
                textSily.setHorizontalAlignment(SwingConstants.CENTER);
                textSily.setBounds((int)(sirka * 0.57), (int)(vyska * 0.445), (int)(sirka * 0.18), (int)(vyska * 0.07));
                urovneStezky.add(textSily);

                bitvaTlacitko.setLocation(sirka/2 - sirkaTlacitka/2, (int) (vyska* 0.58));
                urovneStezky.add(bitvaTlacitko);
                for (int i = 0; i < seznamTlacitek.size(); i++) {
                    seznamTlacitek.get(i).setVisible(false);

                }
                /**
                 * Tento cyklus jsem vytvoril pomoci ai
                 */
                for (java.awt.event.ActionListener al : bitvaTlacitko.getActionListeners()) {
                    bitvaTlacitko.removeActionListener(al);
                }
                bitvaTlacitko.addActionListener(e1 -> {
                    int zivoty = 0;
                    for (Postava hrdina : Hra.hracuvTym) {
                        zivoty = zivoty + hrdina.getZivoty();
                    }
                    if (zivoty <= 0) {
                        JOptionPane.showMessageDialog(okno, "Tvoji hrdinové zemřeli.");
                        return;
                    }
                    if (okno != null) {
                        SoubojovaObrazovka soubojovaObrazovka = new SoubojovaObrazovka("Souboj", false, okno ,"gobliniStezka", predchoziObrazovka);
                        soubojovaObrazovka.setHracuvTym(hracuvTym);
                        soubojovaObrazovka.setNepratelskyTym(gobliniTym);
                        soubojovaObrazovka.setVybranaUrovenStezky(tlacitko + 1);
                        soubojovaObrazovka.inicializace();
                        okno.setContentPane(soubojovaObrazovka.getArenaPanel());
                        Souboj arena = new Souboj(hracuvTym, gobliniTym, soubojovaObrazovka);
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
