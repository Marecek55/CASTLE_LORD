package Predmety;

import Predmety.Truhly.Truhla;
import java.util.ArrayList;

public class Inventar {
    private ArrayList<Predmet> predmety;
    private ArrayList<Truhla> truhly;

    public Inventar() {
        predmety = new ArrayList<>();
        truhly = new ArrayList<>();
    }

    public void pridejPredmet(Predmet predmet) {
        if (predmet != null) {
            predmety.add(predmet);
        }
    }

    public void pridejTruhlu(Truhla truhla) {
        if (truhla != null) {
            truhly.add(truhla);
        }
    }

    public ArrayList<Predmet> getPredmety() {
        return predmety;
    }

    public ArrayList<Truhla> getTruhly() {
        return truhly;
    }
}