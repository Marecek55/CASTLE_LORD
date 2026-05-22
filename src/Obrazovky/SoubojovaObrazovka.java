package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;
import Predmety.Rarita;
import Predmety.Truhly.DrevenaTruhla;
import Predmety.Truhly.StribrnaTruhla;
import Predmety.Truhly.Truhla;
import Predmety.Truhly.ZlataTruhla;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;


public class SoubojovaObrazovka extends Obrazovka {
    private PanelNaPozadi arenaPanel;
    private ArrayList<Postava> hracuvTym;
    private ArrayList<Postava> nepratelskyTym;
    private Timer casovac;
    private int maxHPTymu;
    private int maxHPnepratelskehoTymu;
    private String lokace;
    private ObrazovkaMapy predchoziObrazovkaMapy;

    public SoubojovaObrazovka(String nazev, boolean malaObrazovka, JFrame okno, String lokace, ObrazovkaMapy predchoziObrazovkaMapy) {
        super(nazev, malaObrazovka);
        this.okno = okno;
        this.lokace = lokace;
        this.predchoziObrazovkaMapy = predchoziObrazovkaMapy;

    }

    @Override
    public void inicializace() {

        maxHPTymu = 0;
        for (Postava postava : hracuvTym) {
            maxHPTymu = maxHPTymu + postava.getZivoty();
        }
        for (Postava postava : nepratelskyTym) {
            maxHPnepratelskehoTymu = maxHPnepratelskehoTymu + postava.getZivoty();
        }
        String cesta = "/Obrazky/ObrazkyBoje/pozadiBoje.png";
        if (lokace.equals("arena")) {
            cesta = "/Obrazky/ObrazkyBoje/pozadiBojeArena.png";
        }

        arenaPanel = new PanelBitvy(cesta, this);
        arenaPanel.setLayout(null);
        casovac = new Timer(30, e -> {
            arenaPanel.repaint();
        });
        casovac.start();
    }
    private boolean bitvaSkoncila = false;

    public void konecBitvy(boolean hracVyhral) {
        bitvaSkoncila = true;
        if (casovac != null) {
            casovac.stop();
        }
        String cestaKObrazku = "";
        if (lokace.equals("arena")) {
            if (hracVyhral) {
                cestaKObrazku = "/Obrazky/ObrazkyBoje/vyhraArena.png";
            } else {
                cestaKObrazku = "/Obrazky/ObrazkyBoje/prohraArena.png";
            }
        } else {
            if (hracVyhral) {
                cestaKObrazku = "/Obrazky/ObrazkyBoje/vyhraGoblin.png";
            } else {
                cestaKObrazku = "/Obrazky/ObrazkyBoje/prohraGoblin.png";
            }
        }
        Image obrazekPozadi = arenaPanel.nactiObrazek(cestaKObrazku);
        arenaPanel.setBg(obrazekPozadi);
        JPanel panelOdmen = new JPanel();
        int mezera = (int) (Hra.sirkaObrazovky * 0.015);
        panelOdmen.setLayout(new FlowLayout(FlowLayout.CENTER, mezera, mezera));
        panelOdmen.setOpaque(false);
        panelOdmen.setBounds(0, (int) (Hra.vyskaObrazovky * 0.4), Hra.sirkaObrazovky, (int) (Hra.vyskaObrazovky * 0.25));
        odmena(panelOdmen, hracVyhral);
        arenaPanel.add(panelOdmen);
        panelOdmen.setVisible(true);
        JButton zpetTlacitko = new JButton();
        int sirkaTlacitka = (int) (Hra.sirkaObrazovky * 0.2);
        int vyskaTlacitka = (int) (sirkaTlacitka * (368.0 / 679.0));

        StylTlacitek.nastavJakoObrazek(zpetTlacitko, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, vyskaTlacitka);

        int vzdalenostOdKraje = (int) (Hra.sirkaObrazovky * 0.009);
        zpetTlacitko.setLocation((int) (Hra.sirkaObrazovky - (sirkaTlacitka * 0.92)), -vzdalenostOdKraje);

        zpetTlacitko.addActionListener(e -> {
            if(predchoziObrazovkaMapy != null) {
                okno.setContentPane(predchoziObrazovkaMapy.getMapa());
                okno.revalidate();
                okno.repaint();
            }
        });

        arenaPanel.add(zpetTlacitko);
        arenaPanel.revalidate();
        arenaPanel.repaint();
    }

    private void odmena(JPanel panelOdmen, boolean hracVyhral) {
        int uroven;

        if (lokace.equals("arena")) {
            uroven = Hra.urovenHradu;
        } else {
            uroven = Hra.urovenGobliniStezky;
        }

        int sance = Hra.rand.nextInt(100);
        Rarita rarita;
        Rarita raritaTruhly;

        int sanceLegendarni;
        int sanceVzacna;

        if (hracVyhral) {
            if (uroven <= 5) {
                sanceLegendarni = 2;
                sanceVzacna = 13;
            } else if (uroven <= 10) {
                sanceLegendarni = 5;
                sanceVzacna = 25;
            } else if (uroven <= 15) {
                sanceLegendarni = 12;
                sanceVzacna = 38;
            } else {
                sanceLegendarni = 25;
                sanceVzacna = 45;
            }
        } else {
            sanceLegendarni = 0;
            sanceVzacna = 5;
        }

        if (sance < sanceLegendarni) {
            rarita = Rarita.LEGENDÁRNÍ;
        } else if (sance < (sanceLegendarni + sanceVzacna)) {
            rarita = Rarita.VZÁCNÁ;
        } else {
            rarita = Rarita.BĚŽNÁ;
        }

        int sanceTruhly = Hra.rand.nextInt(100);
        if (sanceTruhly < sanceLegendarni) {
            raritaTruhly = Rarita.LEGENDÁRNÍ;
        } else if (sanceTruhly < (sanceLegendarni + sanceVzacna)) {
            raritaTruhly = Rarita.VZÁCNÁ;
        } else {
            raritaTruhly = Rarita.BĚŽNÁ;
        }

        int nahodnyPredmet = Hra.rand.nextInt(1, 4);
        if (nahodnyPredmet == 1) {
            Zbran z = Zbran.vytvoritZbran(uroven, true, rarita);
            Hra.inventar.pridejPredmet(z);
            panelOdmen.add(new IkonaVeciVInventari(z.getNazevObrazku(), rarita, z.getSila()));
        } else if (nahodnyPredmet == 2) {
            Brneni b = Brneni.vytvoritBrneni(uroven, true, rarita);
            Hra.inventar.pridejPredmet(b);
            panelOdmen.add(new IkonaVeciVInventari(b.getNazevObrazku(), rarita, b.getKryt()));
        } else {
            Medailon m = Medailon.vytvoritMedailon(true, rarita);
            Hra.inventar.pridejPredmet(m);
            panelOdmen.add(new IkonaVeciVInventari(m.getNazevObrazku(), rarita, m.getZlepsovac()));
        }

        int sanceNaTruhlu = 20 + (uroven * 2);
        if (sanceNaTruhlu > 60) {
            sanceNaTruhlu = 60;
        }

        if (hracVyhral == false) {
            sanceNaTruhlu = 10;
        }

        if (Hra.rand.nextInt(100) < sanceNaTruhlu) {
            Truhla truhla;
            String nazevObrazkuTruhly = "";

            int truhlaTyp = Hra.rand.nextInt(3);

            if (truhlaTyp == 0) {
                truhla = new DrevenaTruhla("Dřevěná Truhla", raritaTruhly);
                nazevObrazkuTruhly = "drevenaTruhla.png";
            } else if (truhlaTyp == 1) {
                truhla = new StribrnaTruhla("Stříbrná Truhla", raritaTruhly);
                nazevObrazkuTruhly = "stribrnaTruhla.png";
            } else {
                truhla = new ZlataTruhla("Zlatá Truhla", raritaTruhly);
                nazevObrazkuTruhly = "zlataTruhla.png";
            }

            Hra.inventar.pridejTruhlu(truhla);
            panelOdmen.add(new IkonaVeciVInventari(nazevObrazkuTruhly, raritaTruhly, 0));
        }

    }

    public void vykresliPostavyATexty(Graphics g) {
        if (bitvaSkoncila) {
            return;
        }
        Graphics2D grafika = (Graphics2D) g;
        grafika.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int sirkaMonitoru = arenaPanel.getWidth();
        int vyskaMonitoru = arenaPanel.getHeight();
        int sirkaPostavy = (int)(sirkaMonitoru * 0.15);
        int vyskaPostavy = (int)(vyskaMonitoru * 0.30);
        int barSirka = (int)(sirkaMonitoru * 0.30);
        int barVyska = (int)(vyskaMonitoru * 0.04);

        Font fontJmeno = new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.012));
        int YPostav = (int)(vyskaMonitoru * 0.45);

        int XHrdiny = (sirkaMonitoru / 2) - sirkaPostavy - (int)(sirkaMonitoru * 0.02);

        for (Postava bojovnik : hracuvTym) {

            if (bojovnik.isUtoci()) {
                grafika.drawImage(bojovnik.getObrazekVUtoku(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            } else {
                grafika.drawImage(bojovnik.getObrazekVKlidu(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            }
            if (bojovnik.getPosledniZasah() != null){
                grafika.setColor(Color.RED);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.015)));
                grafika.drawString(bojovnik.getPosledniZasah(), XHrdiny, YPostav - 15);
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(bojovnik.getJmeno(), XHrdiny, YPostav + vyskaPostavy + 30);


            XHrdiny = XHrdiny - sirkaPostavy - (int)(sirkaMonitoru * 0.01);
        }


        int XNepratel = (sirkaMonitoru / 2) + (int)(sirkaMonitoru * 0.02);

        for (Postava nepritel : nepratelskyTym) {
            Image obrNepritele;
            if (nepritel.isUtoci()) {
                obrNepritele = nepritel.getObrazekVUtoku();
            } else {
                obrNepritele = nepritel.getObrazekVKlidu();
            }

            if (lokace.equals("arena")) {
                g.drawImage(obrNepritele, XNepratel + sirkaPostavy, YPostav, XNepratel, YPostav + vyskaPostavy, 0, 0, obrNepritele.getWidth(null), obrNepritele.getHeight(null), null);
            } else {
                grafika.drawImage(obrNepritele, XNepratel, YPostav, sirkaPostavy, vyskaPostavy, null);
            }

            if (nepritel.getPosledniZasah()!= null){
                grafika.setColor(Color.GREEN);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.015)));
                grafika.drawString(nepritel.getPosledniZasah(), XNepratel, YPostav - 15);
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(nepritel.getJmeno(), XNepratel, YPostav + vyskaPostavy + 30);


            XNepratel = XNepratel + sirkaPostavy + (int)(sirkaMonitoru * 0.01);
        }

        int aktualniHPTymu = 0;
        int aktualniHPNepratelskyTymu = 0;

        for (Postava p : hracuvTym) {
            aktualniHPTymu = aktualniHPTymu + p.getZivoty();
        }
        for (Postava p : nepratelskyTym) {
            aktualniHPNepratelskyTymu = aktualniHPNepratelskyTymu + p.getZivoty();
        }

        float pomerZivotuHrac = (float)aktualniHPTymu / maxHPTymu;
        int hracBarX = (int)(sirkaMonitoru * 0.1);
        int barY = (int)(vyskaMonitoru * 0.2);

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(hracBarX, barY, barSirka, barVyska);
        grafika.setColor(Color.GREEN);
        grafika.fillRect(hracBarX, barY, (int)(barSirka * pomerZivotuHrac), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(hracBarX, barY, barSirka, barVyska);
        grafika.setFont(fontJmeno);
        grafika.drawString(aktualniHPTymu + " / " + maxHPTymu, hracBarX + 10, barY + barVyska - 10);

        float pomerZivotuNepritel = (float)aktualniHPNepratelskyTymu / maxHPnepratelskehoTymu;
        int nepritelBarX = sirkaMonitoru - (int)(sirkaMonitoru * 0.1) - barSirka;

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.setColor(Color.RED);
        grafika.fillRect(nepritelBarX, barY, (int)(barSirka * pomerZivotuNepritel), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.drawString(aktualniHPNepratelskyTymu + " / " + maxHPnepratelskehoTymu, nepritelBarX + 10, barY + barVyska - 10);

    }


    public void setHracuvTym(ArrayList<Postava> hracuvTym) {
        this.hracuvTym = hracuvTym;
    }

    public void setNepratelskyTym(ArrayList<Postava> nepratelskyTym) {
        this.nepratelskyTym = nepratelskyTym;
    }

    public PanelNaPozadi getArenaPanel() {
        return arenaPanel;
    }

    @Override
    public void funkcnost() {

    }
}