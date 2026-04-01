package Predmety.Zbrane;

public abstract class Zbran {
    protected String nazev;
    protected int sila;
    protected int silaCritical;

    public Zbran(String nazev, int sila, int silaCritical) {
        this.nazev = nazev;
        this.sila = sila;
        this.silaCritical = silaCritical;
    }
    public abstract int zautoc();
}
