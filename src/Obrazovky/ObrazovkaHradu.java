package Obrazovky;

import Hrad.Hrad;
import Hrad.TypMistnosti;
import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Predmety.Penize;

import javax.swing.*;
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
        Penize p = new Penize("");
        p.setPocet(1000000);
        inicializace();
        funkcnost();

        okno.setVisible(true);



    }
    int sirka = Hra.sirkaObrazovky;
    int vyska = Hra.vyskaObrazovky;

@Override
public void inicializace() {
    int sirkaTlacitek = (int) (sirka * 0.25);
    int vyskaTlacitek = (int) (sirkaTlacitek * (263.0 / 948.0));
    int ctverec = (int) (sirka * 0.075);
    int vzdalenostOdKraje = (int) (sirka * 0.009);
    int yTlacitek1 = vyska - vyskaTlacitek - vzdalenostOdKraje;
    int yTlacitek2 = yTlacitek1 - ctverec- vzdalenostOdKraje;

    panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
    okno.setContentPane(panelHradu);
    hrad = new Hrad(panelHradu, kasarna);
    hrad.nacteniLokaci();

    StylTlacitek.nastavJakoObrazek(stavbaTlacitko, "/Obrazky/ObrazkyVHradu/stavbaTlacitko.png", sirkaTlacitek, vyskaTlacitek);
    StylTlacitek.nastavJakoObrazek(mapaTlacitko,"/Obrazky/ObrazkyVHradu/mapaTlacitko.png",sirkaTlacitek,vyskaTlacitek );
    StylTlacitek.nastavJakoObrazek(exitTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoExit.png", ctverec, ctverec);
    StylTlacitek.nastavJakoObrazek(truhlaTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoTruhly.png", ctverec, ctverec);
    StylTlacitek.nastavJakoObrazek(inventarTlacitko,"/Obrazky/ObrazkyVHradu/tlacitkoInventar.png", ctverec, ctverec);
    stavbaTlacitko.setName("pevneTlacitka");
    mapaTlacitko.setName("pevneTlacitka");
    exitTlacitko.setName("pevneTlacitka");
    truhlaTlacitko.setName("pevneTlacitka");
    inventarTlacitko.setName("pevneTlacitka");

    stavbaTlacitko.setLocation(vzdalenostOdKraje,yTlacitek1);
    mapaTlacitko.setLocation((sirka -sirkaTlacitek- vzdalenostOdKraje), yTlacitek1);
    exitTlacitko.setLocation((sirka - ctverec - vzdalenostOdKraje),vzdalenostOdKraje);
    truhlaTlacitko.setLocation(vzdalenostOdKraje,yTlacitek2);
    inventarTlacitko.setLocation(vzdalenostOdKraje,yTlacitek2- ctverec-vzdalenostOdKraje );
    panelHradu.add(stavbaTlacitko);
    panelHradu.add(mapaTlacitko);
    panelHradu.add(exitTlacitko);
    panelHradu.add(truhlaTlacitko);
    panelHradu.add(inventarTlacitko);
    hrad.postavitMistnost(TypMistnosti.LEKARNA, 1, lekarna);
    hrad.postavitMistnost(TypMistnosti.SKLAD_JIDLA, 14, skladJidla);
    hrad.postavitMistnost(TypMistnosti.SKLAD_PENEZ, 10, skladPenez);
    hrad.postavitMistnost(TypMistnosti.TRENINKOVA_HALA, 9, trenink);
}

    @Override
    public void funkcnost() {
        MouseAdapter ovladaniMysi = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMysStart = e.getX();
                yMysStart = e.getY();

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int xMysKonecRozdil = e.getX() - xMysStart;
                int yMysKonecRozdil = e.getY() - yMysStart;
                panelHradu.posunKamerou(xMysKonecRozdil, yMysKonecRozdil);
                xMysStart = e.getX();
                yMysStart = e.getY();

            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panelHradu.zmenaOddaleni(e.getWheelRotation(), e.getX(), e.getY());
            }
        };
        panelHradu.addMouseListener(ovladaniMysi);
        panelHradu.addMouseMotionListener(ovladaniMysi);
        panelHradu.addMouseWheelListener(ovladaniMysi);
    }
}