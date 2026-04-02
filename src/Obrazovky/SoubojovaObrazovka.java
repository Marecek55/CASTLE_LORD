package Obrazovky;

import Postavy.Postava;

import java.util.ArrayList;

import Logika.TvorbaPostav;

import javax.swing.*;

public class SoubojovaObrazovka extends Obrazovka{
    private PanelNaPozadi arenaPanel;
    private ArrayList<Postava> hracuvTym;
    private ArrayList<Postava> nepratelskyTym;
    private Timer Timer;
    private int indexUtocnika = 0;
    public SoubojovaObrazovka(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
    }

    @Override
    public void inicializace() {
        arenaPanel = new PanelNaPozadi("/PozadiBoje.png");
        arenaPanel.setLayout(null);
        this.okno.add(arenaPanel);

        hracuvTym = new ArrayList<>();
        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Hrdina 1", 1));


        nepratelskyTym = new ArrayList<>();
        nepratelskyTym.add(TvorbaPostav.tvorbaGoblina(1));
        okno.setVisible(true);



    }

    @Override
    public void funkcnost() {

    }
}
