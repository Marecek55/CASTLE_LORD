package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;
import Predmety.Predmet;
import Predmety.Rarita;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ObrazovkaInventare sklada inventar a urcuje fungovani inventare a jeho komponent
 */
public class ObrazovkaInventare extends Obrazovka {
    private PanelNaPozadi panelInventare;
    private JButton sipka;
    private JButton sipkaOtocena;
    private JButton sipkaNahoru;
    private JButton sipkaDolu;
    private JLabel obrazekBojovnika;
    private JLabel jmenoBojovnika;
    private JButton btnZpet;
    int poziceBojovnika = 0;
    private JPanel panelZbran;
    private JPanel panelBrneni;
    private JPanel panelMedailon;
    private JPanel inventarVeci;
    private int aktualniStranka = 0;

    /**
     * Konstruktor inventare nastavuje tlacitka a zakladni inicializaci
     * @param nazev nazev
     * @param malaObrazovka jestli ma byt mala obrazovka
     */
    public ObrazovkaInventare(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        panelInventare = new PanelNaPozadi("/obrazkyInventar/pozadiInventare.png");
        panelInventare.setLayout(null);
        sipka = new JButton();
        sipkaOtocena = new JButton();
        sipkaNahoru = new JButton();
        sipkaDolu = new JButton();
        inventarVeci = new JPanel();
        obrazekBojovnika = new JLabel();
        btnZpet = new JButton();
        panelInventare.add(obrazekBojovnika);
        inventarVeci.setLayout(null);
        inventarVeci.setOpaque(false);
        panelInventare.add(inventarVeci);

        jmenoBojovnika = new JLabel("", SwingConstants.CENTER);
        jmenoBojovnika.setFont(new Font("Georgia", Font.BOLD, (int) (Hra.vyskaObrazovky * 0.04)));
        Color color = new Color(245, 240, 210);
        jmenoBojovnika.setForeground(color);
        panelInventare.add(jmenoBojovnika);
        panelZbran = new JPanel();
        panelZbran.setOpaque(false);
        panelBrneni = new JPanel();
        panelBrneni.setOpaque(false);
        panelMedailon = new JPanel();
        panelMedailon.setOpaque(false);
        panelInventare.add(panelZbran);
        panelInventare.add(panelBrneni);
        panelInventare.add(panelMedailon);

        inicializace();
        funkcnost();
        okno.setContentPane(panelInventare);
        okno.setVisible(true);
    }

    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;
    int sirkaTlacitek = (int) (sirka * 0.08);
    int vyskaTlacitek = (int) (sirkaTlacitek * (369.0 / 677.0));
    int vyskaTlacitek2 = (int) (sirkaTlacitek * (677.0 / 369.0));

    /**
     * Inicializace urcuje lokaci a pozadi tlacitek
     */
    @Override
    public void inicializace() {
        StylTlacitek.nastavJakoObrazek(sipka, "/obrazkyInventar/sipky/sipka.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(sipkaOtocena, "/obrazkyInventar/sipky/sipkaOtocena.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(sipkaNahoru, "/obrazkyInventar/sipky/sipkaNahoru.png", sirkaTlacitek, vyskaTlacitek2);
        StylTlacitek.nastavJakoObrazek(sipkaDolu, "/obrazkyInventar/sipky/sipkaDolu.png", sirkaTlacitek, vyskaTlacitek2);
        inventarVeci.setBounds((int)(sirka * 0.485), (int)(vyska * 0.145), (int)(sirka * 0.45), (int)(vyska * 0.75));

        int sirkaTlacitka = (int) (sirka * 0.2);
        int vzdalenostOdKraje = (int) (sirka * 0.009);

        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnZpet.setLocation((int) (sirka - (sirkaTlacitka * 0.92)),-vzdalenostOdKraje);
        panelInventare.add(btnZpet);


        sipka.setLocation((int) (sirka*0.232), (int) (vyska*0.065));
        sipkaOtocena.setLocation((int) (sirka*0.182), (int) (vyska*0.065));
        sipkaNahoru.setLocation((int) (sirka*0.4), (int) (vyska*0.3));
        sipkaDolu.setLocation((int) (sirka*0.4), (int) (vyska*0.5));

        int velikost = (int)(sirka * 0.17);
        panelZbran.setBounds((int)(sirka * 0.0665), (int)(vyska * 0.715), velikost, velikost);
        panelBrneni.setBounds((int)(sirka * 0.165), (int)(vyska * 0.715), velikost, velikost);
        panelMedailon.setBounds((int)(sirka * 0.264), (int)(vyska * 0.715), velikost, velikost);

        panelInventare.add(sipka);
        panelInventare.add(sipkaOtocena);
        panelInventare.add(sipkaNahoru);
        panelInventare.add(sipkaDolu);
        obrazekBojovnika(0);
        dalsiStranka();
    }

    /**
     * Tato metoda dava pozici bojovnikovi a zobrazuje jeho i jeho zbrane pomoci
     * @param pozice
     */
    public void obrazekBojovnika(int pozice) {
        if (pozice >= Hra.hracuvTym.size()) {
            JOptionPane.showMessageDialog(okno, "Nemáš už další bojovníky.");
            return;
        }
        if (pozice < 0) {
            JOptionPane.showMessageDialog(okno, "Toto je tvůj první bojovník.");
            return;
        }
        Postava bojovnik = Hra.hracuvTym.get(pozice);


            ImageIcon icon = null;
            switch (bojovnik.getTyp()) {
                case "Bojovnik":
                    icon = new ImageIcon(getClass().getResource("/Obrazky/ObrazkyPostav/bojovnikMecKlidny.png"));
                    break;
                case "Lukostrelec":
                    icon = new ImageIcon(getClass().getResource("/Obrazky/ObrazkyPostav/bojovnikLukKlidny.png"));
                    break;
                case "Mag":
                    icon = new ImageIcon(getClass().getResource("/Obrazky/ObrazkyPostav/bojovnikMagKlidny.png"));
                    break;
            }
            int sirkaBojovnika = (int) (sirka * 0.3);
            int vyskaBojovnika  = sirkaBojovnika * 612/408;

            if (icon != null) {
                obrazekBojovnika.setIcon(icon);
                obrazekBojovnika.setBounds((int) (sirka * 0.17), (int) (vyska * 0.06), sirkaBojovnika, vyskaBojovnika);
            }


        String jmeno = Hra.hracuvTym.get(pozice).getJmeno();
        jmenoBojovnika.setText(jmeno);
        jmenoBojovnika.setBounds((int) (sirka*0.15), (int) (vyska*0.15), (int) (sirka*0.2), (int) (vyska*0.1));
        panelZbran.removeAll();
        panelBrneni.removeAll();
        panelMedailon.removeAll();
        Zbran z = Hra.hracuvTym.get(pozice).getZbran();
        Brneni b = Hra.hracuvTym.get(pozice).getBrneni();
        Medailon m = Hra.hracuvTym.get(pozice).getMedailon();

        if (z != null) {
            IkonaVeciVInventari zbran = new IkonaVeciVInventari(z.getNazevObrazku(), z.getRarita(), z.getSila());
            zbran.addActionListener(e ->{
                Hra.hracuvTym.get(poziceBojovnika).setZbran(null);
                Hra.inventar.pridejPredmet(z);
                obrazekBojovnika(poziceBojovnika);
                dalsiStranka();
            });
            panelZbran.add(zbran);
        }
        if (b != null) {
            IkonaVeciVInventari brneni = new IkonaVeciVInventari(b.getNazevObrazku(), b.getRarita(), b.getKryt());
            brneni.addActionListener(e -> {
                Hra.hracuvTym.get(poziceBojovnika).setBrneni(null);
                Hra.inventar.pridejPredmet(b);
                obrazekBojovnika(poziceBojovnika);
                dalsiStranka();

            });
            panelBrneni.add(brneni);
        }
        if (m != null) {
            IkonaVeciVInventari medailon = new IkonaVeciVInventari(m.getNazevObrazku(), m.getRarita(), m.getZlepsovac());
            medailon.addActionListener(e ->{
                Hra.hracuvTym.get(poziceBojovnika).setMedailon(null);
                Hra.inventar.pridejPredmet(m);
                obrazekBojovnika(poziceBojovnika);
                dalsiStranka();
            });
            panelMedailon.add(medailon);
        }

        poziceBojovnika = pozice;
        panelInventare.revalidate();
        panelInventare.repaint();

    }

    /**
     * Tato metoda nasazuje predmet bojovnikovi a kontroluje jestli si muze tuto zbran nasadit a odebere ho z inventare
     * @param p predmet ktery se nasazuje
     */
    public void nasadit(Predmet p){
        Postava postava = Hra.hracuvTym.get(poziceBojovnika);
        if (p instanceof Zbran) {
            Zbran z = (Zbran) p;
            if (z.getNazev().contains("Luk") && !postava.getTyp().equals("Lukostrelec")) {
                JOptionPane.showMessageDialog(okno, "Tento hrdina neumí používat luk!");
                return;
            }
            if (z.getNazev().contains("Meč") && !postava.getTyp().equals("Bojovnik")) {
                JOptionPane.showMessageDialog(okno, "Tento hrdina neumí používat meč!");
                return;
            }
            if (z.getNazev().contains("Hůl") && !postava.getTyp().equals("Mag")) {
                JOptionPane.showMessageDialog(okno, "Tento hrdina neumí používat magickou hůl!");
                return;
            }
        }
        if (p instanceof Zbran) {
            Zbran z = (Zbran) p;
            Zbran stara = postava.getZbran();
            postava.setZbran(z);
            Hra.inventar.getPredmety().remove(z);
            if (stara != null) {
                Hra.inventar.pridejPredmet(stara);
            }
        } else if (p instanceof Brneni) {
            Brneni b = (Brneni) p;
            Brneni stare = postava.getBrneni();
            postava.setBrneni(b);
            Hra.inventar.getPredmety().remove(b);
            if (stare != null) {
                Hra.inventar.pridejPredmet(stare);
            }
        } else if (p instanceof Medailon) {
            Medailon m = (Medailon) p;
            Medailon stary = postava.getMedailon();
            postava.setMedailon(m);
            Hra.inventar.getPredmety().remove(m);
            if (stary != null) {
                Hra.inventar.pridejPredmet(stary);
            }
        }
        obrazekBojovnika(poziceBojovnika);
        dalsiStranka();
    }

    /**
     * Tato metoda prekresluje inventar po kliknuti na dalsi stranku a zpracovava funkci vymazani predmetu
     */
    public void dalsiStranka() {
        inventarVeci.removeAll();
        int velikost = (int)(sirka * 0.11);
        int mezeraX = (int)(sirka * -0.009);
        int mezeraY = (int)(vyska * -0.015);

        int startX = aktualniStranka * 16;

        for (int i = 0; i < 16; i++) {
            int indexVInventari = startX + i;

            if (indexVInventari < Hra.inventar.getPredmety().size()) {
                Predmet p = Hra.inventar.getPredmety().get(indexVInventari);

                int cisloVeci = 0;
                String nazevObrazku = "";
                Rarita rarita = null;

                if (p instanceof Zbran) {
                    Zbran z = (Zbran) p;
                    cisloVeci = z.getSila();
                    rarita = z.getRarita();
                    nazevObrazku = z.getNazevObrazku();
                } else if (p instanceof Brneni) {
                    Brneni b = (Brneni) p;
                    cisloVeci = b.getKryt();
                    rarita = b.getRarita();
                    nazevObrazku = b.getNazevObrazku();
                } else if (p instanceof Medailon) {
                    Medailon m = (Medailon) p;
                    cisloVeci = m.getZlepsovac();
                    rarita = m.getRarita();
                    nazevObrazku = m.getNazevObrazku();
                }

                IkonaVeciVInventari ikona = new IkonaVeciVInventari(nazevObrazku, rarita, cisloVeci);

                int x = (i % 4) * (velikost + mezeraX);
                int y = (i / 4) * (velikost + mezeraY);
                ikona.setBounds(x, y, velikost, velikost);
                ikona.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            int odpoved = JOptionPane.showConfirmDialog(okno, "Opravdu chceš vyhodit předmět: " + p.getNazev() + "?", "Zahodit předmět", JOptionPane.YES_NO_OPTION);
                            if (odpoved == JOptionPane.YES_OPTION) {
                                Hra.inventar.getPredmety().remove(p);
                                dalsiStranka();
                            }
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            nasadit(p);
                        }
                    }
                });
                inventarVeci.add(ikona);
            }
        }
        inventarVeci.revalidate();
        inventarVeci.repaint();
    }

    /**
     * Tato metoda dava funkcnost tlacitkum a sipkam
     */
    @Override
    public void funkcnost() {

        btnZpet.addActionListener(e -> {
            if (Hra.obrazovkaHradu != null) {
                Hra.obrazovkaHradu.getOkno().setVisible(true);
            }
            this.okno.setVisible(false);
        });
        sipka.addActionListener(e -> {
            obrazekBojovnika(poziceBojovnika + 1);
        });
        sipkaOtocena.addActionListener(e -> {
            obrazekBojovnika(poziceBojovnika - 1);
        });
        sipkaDolu.addActionListener(e -> {
            if ((aktualniStranka + 1) * 16 < Hra.inventar.getPredmety().size()) {
                aktualniStranka++;
                dalsiStranka();
            }
        });

        sipkaNahoru.addActionListener(e -> {
            if (aktualniStranka > 0) {
                aktualniStranka--;
                dalsiStranka();
            }
        });
    }
}