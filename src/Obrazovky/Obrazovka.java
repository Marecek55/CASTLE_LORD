package Obrazovky;

import javax.swing.*;

/**
 * Tato abstraktni trida Obrazovka dela zaklady pro ostatni obrazovky a nastavuje parametry
 */
public abstract class Obrazovka {
    protected JFrame okno;

    /**
     * Konstruktor nastavuje zakladni parametry
     * @param nazev nazev okna
     * @param malaObrazovka jestli ma byt obrazovka mala nebo fullscreen
     */
    public  Obrazovka(String nazev, boolean malaObrazovka) {
        this.okno = new JFrame(nazev);
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!malaObrazovka){
            this.okno.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.okno.setUndecorated(true);
        }else {
            this.okno.setSize(500,500);
            this.okno.setLocationRelativeTo(null);
        }

    }
    public Obrazovka(JFrame okno) {
        this.okno = okno;
    }

    public JFrame getOkno() {
        return okno;
    }

    public abstract void inicializace();
    public abstract void funkcnost();

}
