package Predmety.Zbrane;

import Logika.Hra;
import Predmety.Rarita;

/**
 * Tato trida pridava hodnotu silu luku
 */
public class Luk extends Zbran {


    public Luk(String nazev, int sila, int silaCritical, Rarita rarita) {
        super(nazev, sila, silaCritical, rarita);
        this.sanceCritical = 8;
    }

    @Override
    public String getNazevObrazku() {
        return "luk.png";
    }

    /**
     * Tato metoda nastavuje a vraci silu luku
     * @return
     */
    @Override
    public int zautoc() {

        if (Hra.rand.nextInt(1,11)>=sanceCritical){
            return silaCritical+sila;
        }else {
            return sila;
        }
    }


}
