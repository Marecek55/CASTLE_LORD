package Obrazovky;

import javax.swing.*;
import java.awt.event.*;

public class ObrazovkaHradu extends Obrazovka {
    private PanelPohyblivehoPozadi panelHradu;
    private JButton kasarna;
    private int xMysStart;
    private int yMysStart;

    public ObrazovkaHradu(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        kasarna = new JButton("Kasarna");
        inicializace();
        funkcnost();
        okno.setVisible(true);
    }

    @Override
    public void inicializace() {
        panelHradu = new PanelPohyblivehoPozadi("/Obrazky/ObrazkyVHradu/PozadiHradu.png");

        kasarna.setBounds(500, 300, 150, 50);
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