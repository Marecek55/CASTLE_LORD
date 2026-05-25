package Obrazovky;

import Hrad.Hrad;
import Hrad.Mistnost;
import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Hrad.TypMistnosti;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ObrazovkaStavby extends Obrazovka {
    private PanelNaPozadi panelStavby;
    private JButton btnZpet;
    private JButton btnPostavitLekarna;
    private JButton btnVylepsitLekarna;
    private JButton btnVylepsitSkladPenez;
    private JButton btnPostavitSkladPenez;
    private JButton btnVylepsitSkladJidla;
    private JButton btnPostavitSkladJidla;
    private JButton btnPostavitTrenink;
    private JButton btnVylepsitTrenink;
    private Hrad hrad;

    public ObrazovkaStavby(String nazev, boolean malaObrazovka, Hrad hrad) {
        super(nazev, malaObrazovka);
        this.hrad = hrad;
        btnPostavitLekarna = new JButton();
        btnVylepsitLekarna = new JButton();
        btnPostavitTrenink = new JButton();
        btnVylepsitTrenink = new JButton();
        btnPostavitSkladJidla = new JButton();
        btnVylepsitSkladJidla = new JButton();
        btnPostavitSkladPenez = new JButton();
        btnVylepsitSkladPenez = new JButton();
        btnZpet = new JButton();

        panelStavby = new PanelNaPozadi("/Obrazky/ObrazkyVHradu/stavbaPozadi.png");
        panelStavby.setLayout(null);

        inicializace();
        funkcnost();

        okno.setContentPane(panelStavby);
        okno.revalidate();
        okno.repaint();
        okno.setVisible(true);
    }

    int sirka = Hra.sirkaObrazovky;
    int vyska = Hra.vyskaObrazovky;
    int sirkaTlacitka = (int) (sirka * 0.15);
    int vzdalenostOdKraje = (int) (sirka * 0.009);
    int sirkaTlacitka2 = (int) (vyska * 0.4);
    int vyskaTlacitka2 = (int) (sirkaTlacitka2 *  (272.0 / 917.0));

    @Override
    public void inicializace() {
        StylTlacitek.nastavJakoObrazek(btnPostavitLekarna, "/Obrazky/ObrazkyVHradu/postavitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnVylepsitLekarna, "/Obrazky/ObrazkyVHradu/vylepsitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnPostavitTrenink, "/Obrazky/ObrazkyVHradu/postavitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnVylepsitTrenink, "/Obrazky/ObrazkyVHradu/vylepsitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnPostavitSkladJidla, "/Obrazky/ObrazkyVHradu/postavitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnVylepsitSkladJidla, "/Obrazky/ObrazkyVHradu/vylepsitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnPostavitSkladPenez, "/Obrazky/ObrazkyVHradu/postavitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnVylepsitSkladPenez, "/Obrazky/ObrazkyVHradu/vylepsitTlacitko.png",sirkaTlacitka2,vyskaTlacitka2);
        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));

        btnZpet.setBounds((int) (sirka - (sirkaTlacitka * 0.92)), -vzdalenostOdKraje, sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnPostavitLekarna.setLocation((int) (sirka*0.04), (int) (vyska*0.345));
        btnVylepsitLekarna.setLocation((int) (sirka*0.26), (int) (vyska*0.345));
        btnPostavitTrenink.setLocation((int) ((int) (sirka*0.04)+sirka*0.48), (int) (vyska*0.78));
        btnVylepsitTrenink.setLocation((int) ((int) (sirka*0.26)+sirka*0.48), (int) (vyska*0.78));
        btnPostavitSkladJidla.setLocation((int) ((int) (sirka*0.04)+sirka*0.48), (int) (vyska*0.345));
        btnVylepsitSkladJidla.setLocation((int) ((int) (sirka*0.26)+sirka*0.48), (int) (vyska*0.345));
        btnPostavitSkladPenez.setLocation((int) (sirka*0.04), (int) (vyska*0.78));
        btnVylepsitSkladPenez.setLocation((int) (sirka*0.26), (int) (vyska*0.78));
        panelStavby.add(btnZpet);
        panelStavby.add(btnVylepsitLekarna);
        panelStavby.add(btnPostavitLekarna);
        panelStavby.add(btnVylepsitTrenink);
        panelStavby.add(btnPostavitTrenink);
        panelStavby.add(btnVylepsitSkladJidla);
        panelStavby.add(btnPostavitSkladJidla);
        panelStavby.add(btnVylepsitSkladPenez);
        panelStavby.add(btnPostavitSkladPenez);
        for (Component c : panelStavby.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).setEnabled(true);
            }
        }
        int maxUroven = 5;
        budovaTlacitkaKontrola(hrad.getLekarny(), 2, maxUroven, btnVylepsitLekarna, btnPostavitLekarna);
        budovaTlacitkaKontrola(hrad.getSkladyPenez(), 4, maxUroven, btnVylepsitSkladPenez, btnPostavitSkladPenez);
        budovaTlacitkaKontrola(hrad.getSkladyJidla(), 4, maxUroven, btnVylepsitSkladJidla, btnPostavitSkladJidla);
        budovaTlacitkaKontrola(hrad.getTreninkoveHaly(), 3, maxUroven, btnVylepsitTrenink, btnPostavitTrenink);
        vytvortexty(hrad.getLekarny(), 200, 100, 2, (int)(sirka * 0.4), (int)(vyska * 0.14));
        vytvortexty(hrad.getSkladyPenez(), 200, 300, 4, (int)(sirka * 0.4), (int)(vyska * 0.56));
        vytvortexty(hrad.getSkladyJidla(), 150, 200, 4, (int)(sirka * 0.89), (int)(vyska * 0.14));
        vytvortexty(hrad.getTreninkoveHaly(), 300, 200, 3, (int)(sirka * 0.89), (int)(vyska * 0.56));
    }
    private void budovaTlacitkaKontrola(ArrayList<? extends Mistnost> mistnosti, int maxPostaveni, int maxUroven, JButton vylepsit, JButton postavit) {
        int aktualniPocet = mistnosti.size();

        if (aktualniPocet == 0) {
            vylepsit.setEnabled(false);
            postavit.setEnabled(true);
        } else {
            int aktualniUroven = mistnosti.get(0).getUroven();
            if (aktualniUroven >= maxUroven) {
                vylepsit.setEnabled(false);
            } else {
                vylepsit.setEnabled(true);
            }

            if (aktualniPocet >= maxPostaveni) {
                postavit.setEnabled(false);
            } else {
                postavit.setEnabled(true);
            }
        }
    }

    private void vytvortexty(ArrayList<? extends Mistnost> mistnosti, int cenaZaPostaveni, int cenaZaVylepseni, int maxPostaveni, int x, int y) {
        int pocet = mistnosti.size();
        boolean jePostaveno;
        if (pocet > 0) {
            jePostaveno = true;
        }else {
            jePostaveno = false;
        }
        int uroven;
        if (jePostaveno == true) {
            uroven = mistnosti.get(0).getUroven();
        } else {
            uroven = 0;
        }

        String cena = "";
        if (jePostaveno) {
            int cenaVylepseni = cenaZaVylepseni * (uroven + 1);
            cena = String.valueOf(cenaVylepseni);
        } else {
            cena = String.valueOf(cenaZaPostaveni);
        }

        pridatText(cena, x, y, new Color(150, 255, 150));
        pridatText(String.valueOf(maxPostaveni), x, y + (int)(vyska * 0.04), new Color(245, 240, 210));
        pridatText(String.valueOf(pocet), x, y + (int)(vyska * 0.085), new Color(245, 240, 210));
        pridatText(String.valueOf(uroven), x, y + (int)(vyska * 0.125), new Color(255, 215, 0));
    }

    private void pridatText(String text, int x, int y, Color barva) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Georgia", Font.BOLD, (int)(vyska * 0.03)));
        label.setForeground(barva);
        label.setBounds(x, y, (int)(sirka * 0.2), (int)(vyska * 0.06));
        panelStavby.add(label);
    }

    @Override
    public void funkcnost() {
        btnZpet.addActionListener(e -> {
            if (Hra.obrazovkaHradu != null) {
                Hra.obrazovkaHradu.aktualizace();
                Hra.obrazovkaHradu.getOkno().setVisible(true);
            }
            this.okno.setVisible(false);
        });

        btnPostavitLekarna.addActionListener(e -> {
            staveni(TypMistnosti.LEKARNA);
        });

        btnPostavitSkladPenez.addActionListener(e -> {
            staveni(TypMistnosti.SKLAD_PENEZ);
        });

        btnPostavitSkladJidla.addActionListener(e -> {
            staveni(TypMistnosti.SKLAD_JIDLA);
        });

        btnPostavitTrenink.addActionListener(e -> {
            staveni(TypMistnosti.TRENINKOVA_HALA);
        });

        btnVylepsitLekarna.addActionListener(e -> {
            if (hrad.getLekarny().isEmpty() == false) {
                try {
                    hrad.getLekarny().get(0).vylepsitMistnost();
                    obnovitObrazovku();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(okno, exception.getMessage());
                }
            }
        });

        btnVylepsitSkladPenez.addActionListener(e -> {
            if (hrad.getSkladyPenez().isEmpty() == false) {
                try {
                    hrad.getSkladyPenez().get(0).vylepsitMistnost();
                    obnovitObrazovku();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(okno, exception.getMessage());
                }
            }
        });

        btnVylepsitSkladJidla.addActionListener(e -> {
            if (hrad.getSkladyJidla().isEmpty() == false) {
                try {
                    hrad.getSkladyJidla().get(0).vylepsitMistnost();
                    obnovitObrazovku();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(okno, exception.getMessage());
                }
            }
        });

        btnVylepsitTrenink.addActionListener(e -> {
            if (hrad.getTreninkoveHaly().isEmpty() == false) {
                try {
                    hrad.getTreninkoveHaly().get(0).vylepsitMistnost();
                    obnovitObrazovku();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(okno, exception.getMessage());
                }
            }
        });
    }
    private void staveni(TypMistnosti typ) {
        String odpoved = JOptionPane.showInputDialog(okno, "Na jakou pozici (1-18) chceš budovu postavit?");
        if (odpoved != null) {
            try {
                int pozice = Integer.parseInt(odpoved.trim());

                if (pozice >= 1 && pozice <= 18) {
                    hrad.postavitMistnost(typ, pozice, new JButton());
                    obnovitObrazovku();
                } else {
                    JOptionPane.showMessageDialog(okno, "Pozice musí být mezi 1 až 18!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(okno, "Musíš zadat číslo!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(okno, e.getMessage());
            }
        }
    }

    private void obnovitObrazovku() {
        panelStavby.removeAll();
        inicializace();
        panelStavby.revalidate();
        panelStavby.repaint();
    }
}