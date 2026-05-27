package Predmety;

import Predmety.Truhly.Truhla;
import java.util.ArrayList;

/**
 * Tato trida pridava predmet do inventare
 */
public class Inventar {
    private ArrayList<Predmet> predmety;


    public Inventar() {
        predmety = new ArrayList<>();


    }

    /**
     * Tato metoda pridava predmet
     * @param predmet predmet
     */
    public void pridejPredmet(Predmet predmet) {
        if (predmet != null) {
            predmety.add(predmet);
        }
    }


    public ArrayList<Predmet> getPredmety() {
        return predmety;
    }

}