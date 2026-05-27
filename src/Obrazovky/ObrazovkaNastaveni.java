package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;
import PraceSeSoubory.UlozenaHra;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Tato obrazovka v sobe ma nastaveni hudby a ulozeni a nacteni hry
 */
public class ObrazovkaNastaveni extends Obrazovka {
    private JButton btnHudba;
    private JButton btnZpet;
    private JButton btnUlozit;
    private JButton btnUlozitHru;
    private JButton btnNacistHru;
    private JDialog dialog;
    private PanelNaPozadi pozadiNastaveni;
    private boolean stavHudbyVNastaveni;

    /**
     * Nastavuje tlacitka a tvori je
     * @param nazev nazev obrazovky
     * @param malaObrazovka jeslti ma byt mala obrazovka
     */
    public ObrazovkaNastaveni(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        this.dialog = new JDialog(okno, nazev, true);
        pozadiNastaveni = new PanelNaPozadi("/Obrazky/ObrazkyNaNacitaciObrazovce/obrazekNastaveni.png");
        btnZpet = new JButton();
        btnUlozit = new JButton();
        btnHudba = new JButton();
        btnUlozitHru = new JButton();
        btnNacistHru = new JButton();
        stavHudbyVNastaveni = Hra.hudbaPozadi.hraje();

        inicializace();
        funkcnost();
        dialog.setVisible(true);
    }

    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;
    int sirkaTlacitek = (int) (sirka * 0.2);
    int sirkaTlacitek2 =  (int) (sirka * 0.2);
    int vyskaTlacitek2 = (int) (sirkaTlacitek2 * (331.0 / 754.0));
    int vyskaTlacitek = (int) (sirkaTlacitek * (368.0 / 679.0));

    int yTlacitek = (int) (vyska * 0.7);

    /**
     * Tato metoda nastavuje lokaci tlacitkum a urcuje tlacitko hudby
     */
    @Override
    public void inicializace() {
        pozadiNastaveni.setLayout(null);
        String cesta = "";

        if (Hra.hudbaPozadi.hraje()) {
            cesta = "tlacitkoHudbaOn";

        } else {
            cesta = "tlacitkoHudbaOff";

        }
        StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/" + cesta + ".png", sirkaTlacitek2, vyskaTlacitek2);

        StylTlacitek.nastavJakoObrazek(btnUlozit, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoUlozit.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnNacistHru, "/Obrazky/ObrazkyNaNacitaciObrazovce/nacistHru.png", sirkaTlacitek2, vyskaTlacitek2);
        StylTlacitek.nastavJakoObrazek(btnUlozitHru, "/Obrazky/ObrazkyNaNacitaciObrazovce/ulozitHru.png", sirkaTlacitek2, vyskaTlacitek2);

        btnHudba.setLocation((int) (sirka * 0.4), (int) (vyska * 0.33));
        btnUlozit.setLocation((int) (sirka * 0.5), yTlacitek);
        btnZpet.setLocation((int) (sirka * 0.3), yTlacitek);
        btnNacistHru.setLocation((int) (sirka * 0.4), (int) (vyska*0.455));
        btnUlozitHru.setLocation((int) (sirka * 0.4), (int) (vyska*0.58));
        pozadiNastaveni.add(btnHudba);
        pozadiNastaveni.add(btnUlozit);
        pozadiNastaveni.add(btnZpet);
        pozadiNastaveni.add(btnUlozitHru);
        pozadiNastaveni.add(btnNacistHru);
        dialog.setUndecorated(true);
        dialog.setContentPane(pozadiNastaveni);
        dialog.setSize(Hra.sirkaObrazovky, Hra.vyskaObrazovky);


    }

    /**
     * Tato metoda uklada parametry do tridy na ulozeni hry a prepina tlacitko hudby
     */
    @Override
    public void funkcnost() {
        btnHudba.addActionListener(e -> {
            if (stavHudbyVNastaveni == true) {
                stavHudbyVNastaveni = false;
            } else {
                stavHudbyVNastaveni = true;
            }
            if (stavHudbyVNastaveni == true) {
                StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoHudbaOn.png", sirkaTlacitek2, vyskaTlacitek2);
            } else {
                StylTlacitek.nastavJakoObrazek(btnHudba, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoHudbaOff.png", sirkaTlacitek2, vyskaTlacitek2);
            }

            btnHudba.revalidate();
            btnHudba.repaint();
        });
        btnUlozitHru.addActionListener(e -> {
            try {
                UlozenaHra h = new UlozenaHra();
                h.hracuvTym = Hra.hracuvTym;
                h.inventarTruhel = Hra.inventarTruhel;
                h.inventar = Hra.inventar.getPredmety();
                h.hrac = Hra.hrac;
                h.penize = Predmety.Penize.getPocet();
                h.jidlo = Predmety.Jidlo.getPocet();
                h.maxPenize = Predmety.Penize.getMaxpocet();
                h.maxJidlo = Predmety.Jidlo.getMaxpocet();
                h.urovenGobliniStezky = Hra.urovenGobliniStezky;

                if (Hra.obrazovkaHradu != null) {
                    h.hrad = Hra.obrazovkaHradu.getHrad();
                }

                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.txt"));
                out.writeObject(h);
                out.close();

                JOptionPane.showMessageDialog(dialog, "Hra byla uložena!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Chyba");
            }
        });

        btnNacistHru.addActionListener(e -> {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.txt"));
                UlozenaHra h = (UlozenaHra) in.readObject();
                in.close();

                Hra.hracuvTym = h.hracuvTym;
                for (Postava p : Hra.hracuvTym) {
                    p.obnoveni();
                }

                Hra.inventarTruhel = h.inventarTruhel;
                Hra.inventar.getPredmety().clear();
                Hra.inventar.getPredmety().addAll(h.inventar);
                Hra.hrac = h.hrac;
                Predmety.Penize.setPocet(h.penize);
                Predmety.Jidlo.setPocet(h.jidlo);
                Predmety.Penize.setMaxpocet(h.maxPenize);
                Predmety.Jidlo.setMaxpocet(h.maxJidlo);
                Hra.urovenGobliniStezky = h.urovenGobliniStezky;

                if (h.hrad != null) {
                    if (Hra.obrazovkaHradu != null) {
                        Hra.obrazovkaHradu.getOkno().dispose();
                    }
                    Hra.obrazovkaHradu = new ObrazovkaHradu("Hrad", false);
                    Hra.obrazovkaHradu.setHrad(h.hrad);
                    Hra.obrazovkaHradu.aktualizace();
                }

                JOptionPane.showMessageDialog(dialog, "Hra načtena!");
                dialog.dispose();

                if (h.hrad != null) {
                    if (Hra.startovaciObrazovka != null) {
                        Hra.startovaciObrazovka.getOkno().setVisible(false);
                    }
                    if (Hra.obrazovkaHradu != null) {
                        Hra.obrazovkaHradu.getOkno().setVisible(true);
                    }
                } else {
                    if (Hra.startovaciObrazovka != null) {
                        Hra.startovaciObrazovka.getOkno().setVisible(true);
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Chyba");
            }
        });

        btnUlozit.addActionListener(e -> {
            if (stavHudbyVNastaveni == true) {
                Hra.hudbaPozadi.hraj(true);
            } else {
                Hra.hudbaPozadi.zastav();
            }
        });

        btnZpet.addActionListener(e -> {
            dialog.dispose();
        });
    }
}
