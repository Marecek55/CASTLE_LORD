package Predmety.Zbrane;

import Logika.Hra;
import Predmety.Rarita;
/**
 * Tato trida pridava hodnotu silu magicke hole
 */
public class MagickaHul extends Zbran{


    public MagickaHul(String nazev, int sila, int silaCritical, Rarita rarita) {
        super(nazev, sila, silaCritical, rarita);
        this.sanceCritical = 9;
    }

    @Override
    public String getNazevObrazku() {
        return "hul.png";
    }
    /**
     * Tato metoda nastavuje a vraci magicke hole
     * @return
     */
    @Override
    public int zautoc() {

        if (Hra.rand.nextInt(1,11)>=sanceCritical){
            return silaCritical*2;
        }else {
            return sila;
        }
    }
}
