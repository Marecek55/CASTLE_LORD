package Predmety.Zbrane;

import Logika.Hra;
import Predmety.Rarita;
/**
 * Tato trida pridava hodnotu silu mece
 */
public class Mec extends Zbran {

    public Mec(String nazev, int sila, int silaCritical, Rarita rarita) {
        super(nazev, sila, silaCritical, rarita);
        this.sanceCritical = 7;
    }

    @Override
    public String getNazevObrazku() {
        return "mec.png";
    }
    /**
     * Tato metoda nastavuje a vraci silu mece
     * @return
     */
    @Override
    public int zautoc() {

        if (Hra.rand.nextInt(1,11)>=sanceCritical){
            return silaCritical;
        }else {
            return sila;
        }
    }
}


