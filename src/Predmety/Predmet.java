package Predmety;

public abstract class Predmet {
    protected String nazev;
    protected String typ;

    public Predmet(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public String getTyp() {
        return typ;
    }
}

