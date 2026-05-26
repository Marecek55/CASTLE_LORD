package Hrad;

import java.io.Serializable;

public abstract class Mistnost implements Serializable {
    protected String nazev;
    protected int cenaZaPostaveni;
    protected int cenaZaVylepseni;
    protected int uroven;
    protected int velikost;
    protected int maxPostaveni;
    protected int aktualniPocetPostaveni;

    public Mistnost(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) {
        this.nazev = nazev;
        this.cenaZaPostaveni = cenaZaPostaveni;
        this.cenaZaVylepseni = cenaZaVylepseni;
        this.uroven = uroven;
        this.velikost = velikost;

    }

    public abstract void vylepsitMistnost();
    public int getCenaZaDalsiVylepseni() {
        return cenaZaVylepseni * (uroven + 1);
    }

    public String getNazev() {
        return nazev;
    }

    public int getUroven() {
        return uroven;
    }
    public int getCenaZaVylepseni() {
        return cenaZaVylepseni;
    }
    public int getCenaZaPostaveni() {
        return cenaZaPostaveni;
    }
    public int getMaxPostaveni() {
        return maxPostaveni;
    }



}
