package Predmety.Zbrane;

public class Luk extends Zbran {

    public Luk(String nazev, int sila, int silaCritical) {
        super(nazev, sila, silaCritical);
    }

    @Override
    public int zautoc() {
        return 0;
    }
}
