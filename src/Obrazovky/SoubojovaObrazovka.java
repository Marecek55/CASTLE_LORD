package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Bojovnik;
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

/**
 * Tato trida nastavuje zivota tymu a pozadi souboje spousti hudbu pro bitvu resi konec
 * bitvy a vykresluje konecnou obrazovku a odmeny a bojovniky s bary a animace
 */
public class SoubojovaObrazovka extends Obrazovka {
    private PanelNaPozadi arenaPanel;
    private ArrayList<Postava> hracuvTym;
    private ArrayList<Postava> nepratelskyTym;
    private Timer casovac;
    private int maxHPTymu;
    private int maxHPnepratelskehoTymu;
    private String lokace;
    private ObrazovkaMapy predchoziObrazovkaMapy;
    private int vybranaUrovenStezky;

    public SoubojovaObrazovka(String nazev, boolean malaObrazovka, JFrame okno, String lokace, ObrazovkaMapy predchoziObrazovkaMapy) {
        super(nazev, malaObrazovka);
        this.okno = okno;
        this.lokace = lokace;
        this.predchoziObrazovkaMapy = predchoziObrazovkaMapy;

    }

    public void setVybranaUrovenStezky(int vybranaUrovenStezky) {
        this.vybranaUrovenStezky = vybranaUrovenStezky;
    }

    /**
     * Tato metoda pocita HP tymu a spousti pozadi podle typu a casovac a hudby pozadi
     */
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
        if (Hra.hudbaPozadi != null) {
            Hra.hudbaPozadi.zastav();
        }
        if (Hra.hudbaBitva != null) {
            Hra.hudbaBitva.hraj(true);
        }
    }
    private boolean bitvaSkoncila = false;

    /**
     * Tato metoda vyhodnoti kdo vyhral stopne prekreslovani animaci a vybere vyherne nebo pro herni pozadi
     * kazdemu bojovnikovi resetuje trenink a vytvori panel odmen kam se vykresli vyhra
     * @param hracVyhral boolen jestli hrac vyhral
     */
    public void konecBitvy(boolean hracVyhral) {
        bitvaSkoncila = true;
        if (casovac != null) {
            casovac.stop();
        }
        for (Postava p : hracuvTym) {
            if (p instanceof Bojovnik) {
                ((Bojovnik) p).resetTreninkuPoBoji();
            }
        }
        if (Hra.obrazovkaHradu != null && Hra.obrazovkaHradu.getHrad() != null) {
            Hra.obrazovkaHradu.getHrad().resetLeceni();
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
        for (Component c : panelOdmen.getComponents()) {
            c.setEnabled(false);
        }
        arenaPanel.add(panelOdmen);
        panelOdmen.setVisible(true);
        if (hracVyhral) {
            Hra.hrac.pridejxp();
            if (lokace.equals("gobliniStezka")) {
                if (vybranaUrovenStezky == Hra.urovenGobliniStezky) {
                    Hra.urovenGobliniStezky++;
                }
            }
        }else {
            Hra.hrac.odeberxp();
        }
        JButton zpetTlacitko = new JButton();
        int sirkaTlacitka = (int) (Hra.sirkaObrazovky * 0.2);
        int vyskaTlacitka = (int) (sirkaTlacitka * (368.0 / 679.0));

        StylTlacitek.nastavJakoObrazek(zpetTlacitko, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, vyskaTlacitka);

        int vzdalenostOdKraje = (int) (Hra.sirkaObrazovky * 0.009);
        zpetTlacitko.setLocation((int) (Hra.sirkaObrazovky - (sirkaTlacitka * 0.92)), -vzdalenostOdKraje);

        zpetTlacitko.addActionListener(e -> {
            if(predchoziObrazovkaMapy != null) {
                if (Hra.hudbaBitva != null) {
                    Hra.hudbaBitva.zastav();
                }
                if (Hra.hudbaPozadi != null) {
                    Hra.hudbaPozadi.hraj(true);
                }
                if (Hra.obrazovkaHradu != null) {
                    Hra.obrazovkaHradu.aktualizace();
                }
                okno.setContentPane(predchoziObrazovkaMapy.getMapa());
                okno.revalidate();
                okno.repaint();
            }
        });

        arenaPanel.add(zpetTlacitko);
        arenaPanel.revalidate();
        arenaPanel.repaint();
    }

    /**
     * Tato metoda vytvari sance na ruzne predmety pomoci IkonyVeciVInventari ktera prida urovne a sance na truhlu
     * a prida ho na panel s pozadim textem sily a raritou
     * @param panelOdmen panel kam se pridavaji odmeny
     * @param hracVyhral jeslti hrac vyhral
     */
    private void odmena(JPanel panelOdmen, boolean hracVyhral) {
        int uroven;
        if (lokace.equals("arena")) {
            uroven = Hra.hrac.getUroven();
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
            IkonaVeciVInventari ikona = new IkonaVeciVInventari(z.getNazevObrazku(), rarita, z.getSila());
            ikona.deaktivujKlikani();
            panelOdmen.add(ikona);
        } else if (nahodnyPredmet == 2) {
            Brneni b = Brneni.vytvoritBrneni(uroven, true, rarita);
            Hra.inventar.pridejPredmet(b);
            IkonaVeciVInventari ikona = new IkonaVeciVInventari(b.getNazevObrazku(), rarita, b.getKryt());
            ikona.deaktivujKlikani();
            panelOdmen.add(ikona);
        } else {
            Medailon m = Medailon.vytvoritMedailon(true, rarita);
            Hra.inventar.pridejPredmet(m);
            IkonaVeciVInventari ikona = new IkonaVeciVInventari(m.getNazevObrazku(), rarita, m.getZlepsovac());
            ikona.deaktivujKlikani();
            panelOdmen.add(ikona);
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
                truhla = new DrevenaTruhla("Dřevěná Truhla", raritaTruhly, uroven);
                nazevObrazkuTruhly = "drevenaTruhla.png";
            } else if (truhlaTyp == 1) {
                truhla = new StribrnaTruhla("Stříbrná Truhla", raritaTruhly, uroven);
                nazevObrazkuTruhly = "stribrnaTruhla.png";
            } else {
                truhla = new ZlataTruhla("Zlatá Truhla", raritaTruhly, uroven);
                nazevObrazkuTruhly = "zlataTruhla.png";
            }

            Hra.inventarTruhel.add(truhla);
            panelOdmen.add(new IkonaVeciVInventari(nazevObrazkuTruhly, raritaTruhly, 0));
        }

    }

    /**
     * Tato metoda nejdriv vyhladi grafiku a vykresluje hrdiny na levou stranu a protivniky na druhou
     * dava jim lokaci a velikost a otaci nepratele pro boj v arene a vykresluje baner zivotu podle pomeru zivotu
     * take vykresluje nad hlavy hodnotu zasahu cervenou a zelenou barvou
     * @param g grafika panelu
     */
    public void vykresliPostavyATexty(Graphics g) {
        if (bitvaSkoncila) {
            return;
        }
        Graphics2D grafika = (Graphics2D) g;
        grafika.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int sirka = arenaPanel.getWidth();
        int vyska = arenaPanel.getHeight();
        int sirkaPostavy = (int)(sirka * 0.15);
        int vyskaPostavy = (int)(vyska * 0.30);
        int sirkaBaru = (int)(sirka * 0.30);
        int vyskaBaru = (int)(vyska * 0.04);

        Font fontJmena = new Font("Arial", Font.BOLD, (int)(sirka * 0.012));
        int YPostav = (int)(vyska * 0.45);
        int XHrdiny = (sirka / 2) - sirkaPostavy - (int)(sirka * 0.02);
        for (Postava bojovnik : hracuvTym) {
            if (bojovnik.isUtoci()) {
                grafika.drawImage(bojovnik.getObrazekVUtoku(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            } else {
                grafika.drawImage(bojovnik.getObrazekVKlidu(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            }
            if (bojovnik.getPosledniZasah() != null){
                grafika.setColor(Color.RED);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirka * 0.015)));
                grafika.drawString(bojovnik.getPosledniZasah(), XHrdiny, YPostav -(int)(vyska * 0.02));
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmena);
            grafika.drawString(bojovnik.getJmeno(), XHrdiny, YPostav + vyskaPostavy + (int)(vyska * 0.04));
            XHrdiny = XHrdiny - sirkaPostavy - (int)(sirka * 0.01);
        }


        int XNepratel = (sirka / 2) + (int)(sirka * 0.02);

        for (Postava nepritel : nepratelskyTym) {
            Image obrazekNepritele;
            if (nepritel.isUtoci()) {
                obrazekNepritele = nepritel.getObrazekVUtoku();
            } else {
                obrazekNepritele = nepritel.getObrazekVKlidu();
            }

            if (lokace.equals("arena")) {
                g.drawImage(obrazekNepritele, XNepratel + sirkaPostavy, YPostav, XNepratel, YPostav + vyskaPostavy, 0, 0, obrazekNepritele.getWidth(null), obrazekNepritele.getHeight(null), null);
            } else {
                grafika.drawImage(obrazekNepritele, XNepratel, YPostav, sirkaPostavy, vyskaPostavy, null);
            }
            if (nepritel.getPosledniZasah()!= null){
                grafika.setColor(Color.GREEN);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirka * 0.015)));
                grafika.drawString(nepritel.getPosledniZasah(), XNepratel, YPostav - 15);
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmena);
            grafika.drawString(nepritel.getJmeno(), XNepratel, YPostav + vyskaPostavy + 30);
            XNepratel = XNepratel + sirkaPostavy + (int)(sirka * 0.01);
        }

        int aktualniHPTymu = 0;
        int aktualniHPNepratelskehoTymu = 0;

        for (Postava p : hracuvTym) {
            aktualniHPTymu = aktualniHPTymu + p.getZivoty();
        }
        for (Postava p : nepratelskyTym) {
            aktualniHPNepratelskehoTymu = aktualniHPNepratelskehoTymu + p.getZivoty();
        }

        float pomerZivotuHrac = (float)aktualniHPTymu / maxHPTymu;
        int xBaruHrace = (int)(sirka * 0.1);
        int yBaru = (int)(vyska * 0.2);

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(xBaruHrace, yBaru, sirkaBaru, vyskaBaru);
        grafika.setColor(Color.GREEN);
        grafika.fillRect(xBaruHrace, yBaru, (int)(sirkaBaru * pomerZivotuHrac), vyskaBaru);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(xBaruHrace, yBaru, sirkaBaru, vyskaBaru);
        grafika.setFont(fontJmena);
        grafika.drawString(aktualniHPTymu + " / " + maxHPTymu, xBaruHrace + 10, yBaru + vyskaBaru - 10);
        float pomerZivotuNepritel = (float) aktualniHPNepratelskehoTymu / maxHPnepratelskehoTymu;
        int xBaruNepritele = sirka - (int)(sirka * 0.1) - sirkaBaru;
        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(xBaruNepritele, yBaru, sirkaBaru, vyskaBaru);
        grafika.setColor(Color.RED);
        grafika.fillRect(xBaruNepritele, yBaru, (int)(sirkaBaru * pomerZivotuNepritel), vyskaBaru);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(xBaruNepritele, yBaru, sirkaBaru, vyskaBaru);
        grafika.drawString(aktualniHPNepratelskehoTymu + " / " + maxHPnepratelskehoTymu, xBaruNepritele + 10, yBaru + vyskaBaru - 10);

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