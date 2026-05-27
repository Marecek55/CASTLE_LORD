package Predmety.Truhly;

import Logika.Hra;
import Predmety.Jidlo;
import Predmety.Penize;
import Predmety.Predmet;
import Predmety.Rarita;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Tato trida urcuje sance truhly a generuje predmet
 */
public abstract class Truhla implements Serializable {
    protected Rarita rarita;
    protected int sanceBezna;
    protected int sanceVzacna;
    protected int sanceLegendarni;
    protected double nasobitelPenezPodleTypu;
    private ArrayList<Predmet> obsah = new ArrayList<>();
    private boolean otevreno = false;

    /**
     * Tato metoda nastavuje sance truhle
     */
    protected void nastavSance() {}

    public Truhla(String nazev, Rarita rarita, int uroven) {
        this.rarita = rarita;
        nastavSance();
        if (otevreno) return;
        otevreno = true;

    }

    public ArrayList<Predmet> getObsah() {
        return obsah;
    }


    public Rarita getRarita() {
        return rarita;
    }
}
