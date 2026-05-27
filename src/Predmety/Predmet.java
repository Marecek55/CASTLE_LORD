package Predmety;

import java.io.Serializable;

/**
 * Toto je abstraktni trida pro predmet
 */
public abstract class Predmet implements Serializable {
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

