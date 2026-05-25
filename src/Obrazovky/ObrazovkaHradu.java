package Obrazovky;

import Hrad.Hrad;
import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Predmety.Jidlo;
import Predmety.Penize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ObrazovkaHradu extends Obrazovka {
    private PanelPohyblivehoPozadi panelHradu;
    private JButton stavbaTlacitko;
    private JButton mapaTlacitko;
    private JButton exitTlacitko;
    private JButton truhlaTlacitko;
    private JButton inventarTlacitko;
    private JLabel pocetPenez;
    private JLabel pocetJidla;
    private JButton penize;
    private JButton jidlo;
    private JButton levelUkazatel;
    private JLabel level;
    private int xMysStart;
    private int yMysStart;
    private Hrad hrad;

    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        stavbaTlacitko = new JButton();
        mapaTlacitko = new JButton();
        exitTlacitko = new JButton();
        truhlaTlacitko = new JButton();
        inventarTlacitko = new JButton();
        levelUkazatel = new JButton();
        level = new JLabel(String.valueOf(Hra.hrac.getUroven()));
        pocetPenez = new JLabel(String.valueOf(Penize.getPocet()));
        pocetJidla = new JLabel(String.valueOf(Jidlo.getPocet()));
        penize = new JButton();
        jidlo = new JButton();

        inicializace();
        funkcnost();
        okno.setVisible(true);
    }

    public void aktualizace() {
        pocetPenez.setText(String.valueOf(Penize.getPocet()));
        pocetJidla.setText(String.valueOf(Jidlo.getPocet()));
        level.setText(String.valueOf(Hra.hrac.getUroven()));
    }

    public Hrad getHrad() { return hrad; }

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
        int yTlacitek2 = yTlacitek1 - ctverec - vzdalenostOdKraje;
        int xExit = sirka - ctverec - vzdalenostOdKraje;
        exitTlacitko.setLocation(xExit, vzdalenostOdKraje);
        int yTlacitek3 = vzdalenostOdKraje + (ctverec / 2) - (vyskaTlacitek2 / 2);
        int xJidla = xExit - sirkaTlacitek2 - vzdalenostOdKraje;
        int xPenez = xJidla - sirkaTlacitek2 - vzdalenostOdKraje;

        panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png", this);
        okno.setContentPane(panelHradu);

        hrad = new Hrad(panelHradu);

        Font font = new Font("Georgia", Font.BOLD, 50);
        pocetJidla.setFont(font);
        pocetPenez.setFont(font);
        Color barva = new Color(60, 30, 10);
        pocetJidla.setForeground(barva);
        pocetPenez.setForeground(barva);
        pocetJidla.setHorizontalAlignment(SwingConstants.CENTER);
        pocetPenez.setHorizontalAlignment(SwingConstants.CENTER);
        Font font2 = new Font("Georgia", Font.BOLD, (int) (vyska * 0.065));
        level.setFont(font2);
        level.setForeground(new Color(255, 235, 150));
        level.setHorizontalAlignment(SwingConstants.CENTER);
        level.setVerticalAlignment(SwingConstants.CENTER);

        pocetJidla.setBounds((int) (xJidla + sirkaTlacitek2 * 0.25), yTlacitek3, (int) (sirkaTlacitek2 * 0.7), vyskaTlacitek2);
        pocetPenez.setBounds((int) (xPenez + sirkaTlacitek2 * 0.25), yTlacitek3, (int) (sirkaTlacitek2 * 0.7), vyskaTlacitek2);
        level.setBounds((int) (sirka*0.046), (int) (sirka*0.043), ctverec, ctverec);

        StylTlacitek.nastavJakoObrazek(stavbaTlacitko, "/Obrazky/ObrazkyVHradu/stavbaTlacitko.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(mapaTlacitko, "/Obrazky/ObrazkyVHradu/mapaTlacitko.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(exitTlacitko, "/Obrazky/ObrazkyVHradu/tlacitkoExit.png", ctverec, ctverec);
        StylTlacitek.nastavJakoObrazek(truhlaTlacitko, "/Obrazky/ObrazkyVHradu/tlacitkoTruhly.png", ctverec, ctverec);
        StylTlacitek.nastavJakoObrazek(inventarTlacitko, "/Obrazky/ObrazkyVHradu/tlacitkoInventar.png", ctverec, ctverec);
        StylTlacitek.nastavJakoObrazek(jidlo, "/Obrazky/ObrazkyVHradu/jidlo.png", sirkaTlacitek2, vyskaTlacitek2);
        StylTlacitek.nastavJakoObrazek(penize, "/Obrazky/ObrazkyVHradu/penize.png", sirkaTlacitek2, vyskaTlacitek2);
        StylTlacitek.nastavJakoObrazek(levelUkazatel, "/Obrazky/ObrazkyVHradu/ukazatelLevelu.png", (int) (sirka*0.15), (int) (sirka*0.15));

        levelUkazatel.setName("pevneTlacitka");
        stavbaTlacitko.setName("pevneTlacitka");
        mapaTlacitko.setName("pevneTlacitka");
        exitTlacitko.setName("pevneTlacitka");
        truhlaTlacitko.setName("pevneTlacitka");
        inventarTlacitko.setName("pevneTlacitka");
        penize.setName("pevneTlacitka");
        jidlo.setName("pevneTlacitka");
        level.setName("pevneTlacitka");

        stavbaTlacitko.setLocation(vzdalenostOdKraje, yTlacitek1);
        mapaTlacitko.setLocation((sirka - sirkaTlacitek - vzdalenostOdKraje), yTlacitek1);
        exitTlacitko.setLocation(xExit, vzdalenostOdKraje);
        truhlaTlacitko.setLocation(vzdalenostOdKraje, yTlacitek2);
        inventarTlacitko.setLocation(vzdalenostOdKraje, yTlacitek2 - ctverec - vzdalenostOdKraje);
        jidlo.setLocation(xJidla, yTlacitek3);
        penize.setLocation(xPenez, yTlacitek3);
        levelUkazatel.setLocation(vzdalenostOdKraje, vzdalenostOdKraje);

        panelHradu.add(stavbaTlacitko);
        panelHradu.add(mapaTlacitko);
        panelHradu.add(exitTlacitko);
        panelHradu.add(truhlaTlacitko);
        panelHradu.add(inventarTlacitko);
        panelHradu.add(penize);
        panelHradu.add(jidlo);
        panelHradu.add(levelUkazatel);
        panelHradu.add(pocetJidla);
        panelHradu.add(pocetPenez);
        panelHradu.add(level);

        pocetJidla.setName("pevneTlacitkaText");
        pocetPenez.setName("pevneTlacitkaText");

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
            private boolean mapaSePosunula = false;

            @Override
            public void mousePressed(MouseEvent e) {
                xMysStart = e.getLocationOnScreen().x;
                yMysStart = e.getLocationOnScreen().y;
                mapaSePosunula = false;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mapaSePosunula = true;
                int xMysKonecRozdil = e.getLocationOnScreen().x - xMysStart;
                int yMysKonecRozdil = e.getLocationOnScreen().y - yMysStart;
                panelHradu.posunKamerou(xMysKonecRozdil, yMysKonecRozdil);
                xMysStart = e.getLocationOnScreen().x;
                yMysStart = e.getLocationOnScreen().y;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!mapaSePosunula && e.getSource() == panelHradu) {
                    int xSvet = (int) ((e.getX() - panelHradu.getXKamery()) / panelHradu.getMeritko());
                    int ySvet = (int) ((e.getY() - panelHradu.getYKamery()) / panelHradu.getMeritko());
                    if (hrad != null) {
                        hrad.kliknutiNaSouradnice(xSvet, ySvet);
                    }
                }
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