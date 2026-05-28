package Obrazovky;
import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Predmety.Jidlo;
import Predmety.Penize;
import Predmety.Predmet;
import Predmety.Rarita;
import Predmety.Truhly.Truhla;
import Predmety.Truhly.DrevenaTruhla;
import Predmety.Truhly.StribrnaTruhla;
import Predmety.Truhly.ZlataTruhla;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Tato obrazovka pracuje s inventarem pro truhly nastavuje jejich lokaci a vzhled a animaci otevreni
 */
public class ObrazovkaTruhel extends Obrazovka {
    private PanelNaPozadi panelTruhel;
    private JButton btnZpet;
    private ArrayList<JComponent> tlacitkaTruhel;

    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;

    /**
     * Nastavuje pozadi a spousti metody inicializace a funkcnost
     * @param nazev nezev obrazovky
     * @param malaObrazovka jeslti ma byt obrazovka mala
     */
    public ObrazovkaTruhel(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        panelTruhel = new PanelNaPozadi("/obrazkyInventar/pozadiTruhly.png");
        panelTruhel.setLayout(null);

        inicializace();
        funkcnost();

        okno.setContentPane(panelTruhel);
        okno.revalidate();
        okno.repaint();
        okno.setVisible(true);
    }

    /**
     * Tato metoda dava lokaci a velikst tlacitkam truhel a pocita grid pro rozestaveni truhel na 16 poli
     * tim ze zbytek po deleni 4 takze ve 4 je to nula a jde to od znova 1 zbytek deleni 4 je 1 atd 0 zbytek deleni 4 je 0
     * 2 zbytek deleni 4 je 2 to znamena 2. sloupec
     * i/4 pocita pozici v radku kde 0 deleno 4 je nula 1 deleno 4 je 0 2 deleno 4 je 0 atd ale pak napr 7 deleno 4 je jedna
     */
    @Override
    public void inicializace() {
        tlacitkaTruhel = new ArrayList<>();
        btnZpet = new JButton();
        int sirkaTlacitka = (int) (sirka * 0.2);
        int vzdalenostOdKraje = (int) (sirka * 0.009);
        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnZpet.setBounds((int) (sirka - (sirkaTlacitka * 0.92)), -vzdalenostOdKraje, sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        panelTruhel.add(btnZpet);

        btnZpet.addActionListener(e -> {
            if (Hra.obrazovkaHradu != null) {
                Hra.obrazovkaHradu.aktualizace();
                Hra.obrazovkaHradu.getOkno().setVisible(true);
            }
            this.okno.setVisible(false);
        });

        int velikost = (int) (sirka * 0.12);
        int mezeraX = (int) (sirka * 0.003);
        int mezeraY = (int) (sirka * -0.009);

        int xTabulky = (int) (sirka * 0.257);
        int yTabulky = (int) (vyska * 0.15);

        ArrayList<Truhla> truhly = Hra.inventarTruhel;


        for (int i = 0; i < truhly.size(); i++) {
            int x = xTabulky + (i % 4) * (velikost + mezeraX);
            int y = yTabulky + (i / 4) * (velikost + mezeraY);
            Truhla truhla = truhly.get(i);
            String obrazekBedny = "";
            if (truhla instanceof StribrnaTruhla) {
                obrazekBedny = "stribrnaTruhla.png";
            } else if (truhla instanceof ZlataTruhla) {
                obrazekBedny = "zlataTruhla.png";
            }else  if(truhla instanceof DrevenaTruhla) {
                 obrazekBedny = "drevenaTruhla.png";
            }
            IkonaVeciVInventari ikonaTruhly = new IkonaVeciVInventari(obrazekBedny, truhla.getRarita(), 0);
            ikonaTruhly.setBounds(x, y, velikost, velikost);

            ikonaTruhly.addActionListener(e -> {
                otevreniTruhly(truhla);
            });

            panelTruhel.add(ikonaTruhly);
            tlacitkaTruhel.add(ikonaTruhly);
        }

        panelTruhel.revalidate();
        panelTruhel.repaint();
    }

    /**
     * Tato metoda vizualizuje co se stane po otevreni truhly deaktivuje vsechna ostatni tlacitka
     *  a vykrasli velky otaznik a pak se hracovi bud odkryje ze to jsou surviny nebo se mu odkryje rarita
     *  a pak po dalsim kliknuti cela zbran se silou
     * @param truhla
     */
    private void otevreniTruhly(Truhla truhla) {
        for (JComponent komp : tlacitkaTruhel) {
            komp.setEnabled(false);
        }
        btnZpet.setEnabled(false);

        int velkaVelikost = (int) (sirka * 0.22);
        int x = sirka / 2 - velkaVelikost / 2;
        int y = vyska / 2 - velkaVelikost / 2;

        Predmet vec = null;
        vec = truhla.getObsah().get(0);
        Predmet finalVec = vec;

        JButton velkyOtaznik = new JButton();
        velkyOtaznik.setBounds(x, y, velkaVelikost, velkaVelikost);
        StylTlacitek.nastavJakoObrazek(velkyOtaznik, "/obrazkyInventar/zamknutyPredmet.png", velkaVelikost, velkaVelikost);
        panelTruhel.add(velkyOtaznik);
        panelTruhel.setComponentZOrder(velkyOtaznik, 0);
        velkyOtaznik.addActionListener(e -> {
            panelTruhel.remove(velkyOtaznik);
            if (finalVec == null) {
                Hra.inventarTruhel.remove(truhla);
                panelTruhel.remove(velkyOtaznik);
                panelTruhel.removeAll();
                inicializace();
                boolean plneJidlo = false;
                boolean plnePenize = false;
                if (Jidlo.getPocet() >= Jidlo.getMaxpocet()){
                    plneJidlo = true;
                } else if (Penize.getPocet() >= Penize.getMaxpocet()) {
                    plnePenize =true;
                }

                if (plneJidlo || plnePenize) {
                    JOptionPane.showMessageDialog(okno, "Truhla v sobě měla suroviny!\nAle máš plný sklad.\nPostav nebo vylepši sklady.");
                } else {
                    JOptionPane.showMessageDialog(okno, "Truhla v sobě měla suroviny! Máš je ve skladu.");
                }
            } else {
                int cisloVeci = 0;
                String nazevObrazku = "";
                Rarita raritaVeci = null;

                if (finalVec instanceof Zbran) {
                    Zbran z = (Zbran) finalVec;
                    cisloVeci = z.getSila();
                    raritaVeci = z.getRarita();
                    nazevObrazku = z.getNazevObrazku();
                } else if (finalVec instanceof Brneni) {
                    Brneni b = (Brneni) finalVec;
                    cisloVeci = b.getKryt();
                    raritaVeci = b.getRarita();
                    nazevObrazku = b.getNazevObrazku();
                } else if (finalVec instanceof Medailon) {
                    Medailon m = (Medailon) finalVec;
                    cisloVeci = m.getZlepsovac();
                    raritaVeci = m.getRarita();
                    nazevObrazku = m.getNazevObrazku();
                }

                String finalNazevObrazku = nazevObrazku;
                Rarita finalRaritaVeci = raritaVeci;
                int finalCisloVeci = cisloVeci;

                IkonaVeciVInventari ikonaRarity = new IkonaVeciVInventari("", finalRaritaVeci, 0);
                ikonaRarity.setBounds(x, y, velkaVelikost, velkaVelikost);
                panelTruhel.add(ikonaRarity);
                panelTruhel.setComponentZOrder(ikonaRarity, 0);

                ikonaRarity.addActionListener(e2 -> {
                    panelTruhel.remove(ikonaRarity);
                    IkonaVeciVInventari konecnaIkona = new IkonaVeciVInventari(finalNazevObrazku, finalRaritaVeci, finalCisloVeci);
                    konecnaIkona.setBounds(x, y, velkaVelikost, velkaVelikost);
                    panelTruhel.add(konecnaIkona);
                    panelTruhel.setComponentZOrder(konecnaIkona, 0);
                    konecnaIkona.addActionListener(e3 -> {
                        Hra.inventar.pridejPredmet(finalVec);
                        Hra.inventarTruhel.remove(truhla);
                        panelTruhel.remove(konecnaIkona);
                        panelTruhel.removeAll();
                        inicializace();
                    });
                    panelTruhel.revalidate();
                    panelTruhel.repaint();
                });
            }
            panelTruhel.revalidate();
            panelTruhel.repaint();
        });

        panelTruhel.revalidate();
        panelTruhel.repaint();
    }


    @Override
    public void funkcnost() {

    }
}