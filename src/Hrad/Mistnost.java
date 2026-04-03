package Hrad;

public abstract class Mistnost {
    protected String nazev;
    protected int cenaZaPostaveni;
    protected int cenaZaVylepseni;
    protected int uroven;
    protected int velikost;

    public Mistnost(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) {
        this.nazev = nazev;
        this.cenaZaPostaveni = cenaZaPostaveni;
        this.cenaZaVylepseni = cenaZaVylepseni;
        this.uroven = uroven;
        this.velikost = velikost;
    }

    public abstract void vylepsitMistnost();



}
