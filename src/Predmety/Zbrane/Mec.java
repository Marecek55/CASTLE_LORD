package Predmety.Zbrane;

public class Mec extends Zbran {

    public Mec(String nazev, int sila, int silaCritical) {
        super(nazev, sila, silaCritical);
    }

    @Override
    public int zautoc() {
        return 0;
    }
}
