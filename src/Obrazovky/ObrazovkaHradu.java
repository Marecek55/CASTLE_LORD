package Obrazovky;

import Hrad.Hrad;
import Hrad.TypMistnosti;
import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Predmety.Jidlo;
import Predmety.Penize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ObrazovkaHradu extends Obrazovka {
    private PanelPohyblivehoPozadi panelHradu;
    private JButton kasarna;
    private JButton lekarna;
    private JButton trenink;
    private JButton skladPenez;
    private JButton skladJidla;
    private JButton stavbaTlacitko;
    private JButton mapaTlacitko;
    private JButton exitTlacitko;
    private JButton truhlaTlacitko;
    private JButton inventarTlacitko;
    private JLabel pocetPenez;
    private JLabel pocetJidla;
    private JButton penize;
    private JButton jidlo;
    private int xMysStart;
    private int yMysStart;
    private Hrad hrad;


    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        kasarna = new JButton();
        lekarna = new JButton();
        trenink = new JButton();
        skladJidla = new JButton();
        stavbaTlacitko = new JButton();
        mapaTlacitko = new JButton();
        skladPenez = new JButton();
        exitTlacitko = new JButton();
        truhlaTlacitko = new JButton();
        inventarTlacitko = new JButton();
        Penize.setPocet(1000);
        Jidlo.setPocet(500);
        pocetPenez = new JLabel(String.valueOf(Penize.getPocet()));
        pocetJidla = new JLabel(String.valueOf(Jidlo.getPocet()));
        penize = new JButton();
        jidlo = new JButton();



        inicializace();
        funkcnost();

        okno.setVisible(true);


    }
    public void aktualizace() {
        if (pocetPenez != null && pocetJidla != null) {
            pocetPenez.setText(String.valueOf(Penize.getPocet()));
            pocetJidla.setText(String.valueOf(Jidlo.getPocet()));
        }
    }
    int sirka = Hra.sirkaObrazovky;
    int vyska = Hra.vyskaObrazovky;

@Override
public void inicializace() {
    int sirkaTlacitek = (int) (sirka * 0.25);
    int sirkaTlacitek2 = (int) (sirka * 0.2);
    int vyskaTlacitek = (int) (sirkaTlacitek * (263.0 / 948.0));
    int vyskaTlacitek2 = (int) (sirkaTlacitek2 * (369.0 / 677.0));
    int ctverec = (int) (sirka * 0.075);
    int vzdalenostOdKraje = (int) (sirka * 0.009);
    int yTlacitek1 = vyska - vyskaTlacitek - vzdalenostOdKraje;
    int yTlacitek2 = yTlacitek1 - ctverec- vzdalenostOdKraje;
    int xExit = sirka - ctverec - vzdalenostOdKraje;
    exitTlacitko.setLocation(xExit, vzdalenostOdKraje);
    int yTlacitek3 = vzdalenostOdKraje + (ctverec / 2) - (vyskaTlacitek2 / 2);
    int xJidla = xExit - sirkaTlacitek2 - vzdalenostOdKraje;
    int xPenez = xJidla - sirkaTlacitek2 - vzdalenostOdKraje;



    panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
    okno.setContentPane(panelHradu);
    hrad = new Hrad(panelHradu, kasarna);
    hrad.nacteniLokaci();

    Font font = new Font("Georgia", Font.BOLD, 50);
    pocetJidla.setFont(font);
    pocetPenez.setFont(font);
    Color barva = new Color(60, 30, 10);
    pocetJidla.setForeground(barva);
    pocetPenez.setForeground(barva);
    pocetJidla.setHorizontalAlignment(SwingConstants.CENTER);
    pocetPenez.setHorizontalAlignment(SwingConstants.CENTER);


    pocetJidla.setBounds((int) (xJidla + sirkaTlacitek2 * 0.25), yTlacitek3, (int) (sirkaTlacitek2 * 0.7), vyskaTlacitek2);
    pocetPenez.setBounds((int) (xPenez + sirkaTlacitek2 * 0.25), yTlacitek3, (int) (sirkaTlacitek2 * 0.7), vyskaTlacitek2);


    StylTlacitek.nastavJakoObrazek(stavbaTlacitko, "/Obrazky/ObrazkyVHradu/stavbaTlacitko.png", sirkaTlacitek, vyskaTlacitek);
    StylTlacitek.nastavJakoObrazek(mapaTlacitko,"/Obrazky/ObrazkyVHradu/mapaTlacitko.png",sirkaTlacitek,vyskaTlacitek );
    StylTlacitek.nastavJakoObrazek(exitTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoExit.png", ctverec, ctverec);
    StylTlacitek.nastavJakoObrazek(truhlaTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoTruhly.png", ctverec, ctverec);
    StylTlacitek.nastavJakoObrazek(inventarTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoInventar.png", ctverec, ctverec);
    StylTlacitek.nastavJakoObrazek(jidlo,"/Obrazky/ObrazkyVHradu/jidlo.png", sirkaTlacitek2, vyskaTlacitek2);
    StylTlacitek.nastavJakoObrazek(penize,"/Obrazky/ObrazkyVHradu/penize.png", sirkaTlacitek2, vyskaTlacitek2);
    stavbaTlacitko.setName("pevneTlacitka");
    mapaTlacitko.setName("pevneTlacitka");
    exitTlacitko.setName("pevneTlacitka");
    truhlaTlacitko.setName("pevneTlacitka");
    inventarTlacitko.setName("pevneTlacitka");
    penize.setName("pevneTlacitka");
    jidlo.setName("pevneTlacitka");

    stavbaTlacitko.setLocation(vzdalenostOdKraje,yTlacitek1);
    mapaTlacitko.setLocation((sirka -sirkaTlacitek- vzdalenostOdKraje), yTlacitek1);
    exitTlacitko.setLocation(xExit,vzdalenostOdKraje);
    truhlaTlacitko.setLocation(vzdalenostOdKraje,yTlacitek2);
    inventarTlacitko.setLocation(vzdalenostOdKraje,yTlacitek2- ctverec-vzdalenostOdKraje );
    jidlo.setLocation(xJidla, yTlacitek3);
    penize.setLocation( xPenez, yTlacitek3);
    panelHradu.add(stavbaTlacitko);
    panelHradu.add(mapaTlacitko);
    panelHradu.add(exitTlacitko);
    panelHradu.add(truhlaTlacitko);
    panelHradu.add(inventarTlacitko);
    panelHradu.add(penize);
    panelHradu.add(jidlo);
    panelHradu.add(pocetJidla);
    panelHradu.add(pocetPenez);
    pocetJidla.setName("pevneTlacitkaText");
    pocetPenez.setName("pevneTlacitkaText");
    hrad.postavitMistnost(TypMistnosti.LEKARNA, 1, lekarna);
    hrad.postavitMistnost(TypMistnosti.SKLAD_JIDLA, 14, skladJidla);
    hrad.postavitMistnost(TypMistnosti.SKLAD_PENEZ, 10, skladPenez);
    hrad.postavitMistnost(TypMistnosti.TRENINKOVA_HALA, 9, trenink);
    panelHradu.setComponentZOrder(stavbaTlacitko, 0);
    panelHradu.setComponentZOrder(mapaTlacitko, 0);
    panelHradu.setComponentZOrder(exitTlacitko, 0);
    panelHradu.setComponentZOrder(truhlaTlacitko, 0);
    panelHradu.setComponentZOrder(inventarTlacitko, 0);

    panelHradu.repaint();


}

@Override
public void funkcnost() {
    stavbaTlacitko.addActionListener(e -> {
        new ObrazovkaStavby("Stavba", false, hrad);
        this.okno.setVisible(false);
    });
    truhlaTlacitko.addActionListener(e -> {
        new ObrazovkaTruhel("Sklad truhel", false);
        this.okno.setVisible(false);
    });
    inventarTlacitko.addActionListener(e -> {

        new ObrazovkaInventare("Inventar", false);
        this.okno.setVisible(false);
    });
    exitTlacitko.addActionListener(e -> {
        Hra.startovaciObrazovka.getOkno().setVisible(true);
        this.okno.setVisible(false);
    });
    mapaTlacitko.addActionListener(e -> {
        if (Hra.obrazovkaMapy == null) {
            Hra.obrazovkaMapy = new ObrazovkaMapy("Mapa", false);
        } else {

            Hra.obrazovkaMapy.getOkno().setVisible(true);
        }

        this.okno.setVisible(false);
    });

    MouseAdapter ovladaniMysi = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            xMysStart = e.getLocationOnScreen().x;
            yMysStart = e.getLocationOnScreen().y;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int xMysKonecRozdil = e.getLocationOnScreen().x - xMysStart;
            int yMysKonecRozdil = e.getLocationOnScreen().y - yMysStart;

            panelHradu.posunKamerou(xMysKonecRozdil, yMysKonecRozdil);

            xMysStart = e.getLocationOnScreen().x;
            yMysStart = e.getLocationOnScreen().y;
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            panelHradu.zmenaOddaleni(e.getWheelRotation(), e.getX(), e.getY());
        }
    };

    panelHradu.addMouseListener(ovladaniMysi);
    panelHradu.addMouseMotionListener(ovladaniMysi);
    panelHradu.addMouseWheelListener(ovladaniMysi);

    for (int i = 0; i < panelHradu.getComponentCount(); i++) {
        java.awt.Component komponent = panelHradu.getComponent(i);

        komponent.addMouseListener(ovladaniMysi);
        komponent.addMouseMotionListener(ovladaniMysi);
        komponent.addMouseWheelListener(ovladaniMysi);
    }
}
}