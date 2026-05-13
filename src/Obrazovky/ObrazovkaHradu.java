package Obrazovky;

import Hrad.Hrad;
import Hrad.TypMistnosti;
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
    private int xMysStart;
    private int yMysStart;
    private Hrad hrad;


    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        kasarna = new JButton();
        lekarna = new JButton();
        trenink = new JButton();
        skladJidla = new JButton();
        skladPenez = new JButton();
        Penize p = new Penize("");
        p.setPocet(1000000);
        inicializace();
        funkcnost();

        okno.setVisible(true);



    }

    @Override
    public void inicializace() {

        panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");
        okno.setContentPane(panelHradu);
        hrad = new Hrad(panelHradu, kasarna);
        hrad.nacteniLokaci();
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