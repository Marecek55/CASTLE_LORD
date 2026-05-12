package Obrazovky;

import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.awt.event.*;

public class ObrazovkaHradu extends Obrazovka {
    private PanelPohyblivehoPozadi panelHradu;
    private JButton kasarna;
    private int xMysStart;
    private int yMysStart;

    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        kasarna = new JButton();
        inicializace();
        funkcnost();
        okno.setVisible(true);
    }

    @Override
    public void inicializace() {
        panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
        StylTlacitek.nastavJakoObrazek(kasarna, "/Obrazky/ObrazkyVHradu/kasarnaMistnost.png", 1495,497);
        kasarna.setLocation(792,1120);
        kasarna.setFocusable(false);
        panelHradu.add(kasarna);

        okno.setContentPane(panelHradu);
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