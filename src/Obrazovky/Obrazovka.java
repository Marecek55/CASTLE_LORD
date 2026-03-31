package Obrazovky;

import javax.swing.*;

public abstract class Obrazovka {
    protected JFrame okno;

    public  Obrazovka(String nazev, boolean malaObrazovka) {
        this.okno = new JFrame(nazev);
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!malaObrazovka){
            this.okno.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            this.okno.setSize(500,500);
            this.okno.setLocationRelativeTo(null);
        }

    }
    public abstract void inicializace();
    public abstract void funkcnost();

}
