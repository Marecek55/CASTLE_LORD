package Predmety.Zbrane;

import Logika.Hra;
import Predmety.Rarita;

public class Luk extends Zbran {


    public Luk(String nazev, int sila, int silaCritical, Rarita rarita) {
        super(nazev, sila, silaCritical, rarita);
        this.sanceCritical = 8;
    }

    @Override
    public String getNazevObrazku() {
        return "luk.png";
    }

    @Override
    public int zautoc() {

        if (Hra.rand.nextInt(1,11)>=sanceCritical){
            return silaCritical+sila;
        }else {
            return sila;
        }
    }
}
