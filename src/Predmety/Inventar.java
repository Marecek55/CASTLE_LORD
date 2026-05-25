package Predmety;

import Predmety.Truhly.Truhla;
import java.util.ArrayList;

public class Inventar {
    private ArrayList<Predmet> predmety;


    public Inventar() {
        predmety = new ArrayList<>();

    }

    public void pridejPredmet(Predmet predmet) {
        if (predmet != null) {
            predmety.add(predmet);
        }
    }


    public ArrayList<Predmet> getPredmety() {
        return predmety;
    }

}