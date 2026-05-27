package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Rarita;
/**
 * Tato trida urcuje kryt kozeneho brneni
 */
public class KozeneBrneni extends Brneni{
    public KozeneBrneni(String jmeno,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno, rarita, kryt, ultraKryt);
        this.sanceUltraKryt = 9;
    }

    @Override
    public String getNazevObrazku() {
        return "kozeneBrneni.png";
    }
    /**
     * Tato metoda vraci kryt kozeneho brneni
     */
    @Override
    public int kryt(int utok) {

        if (Hra.rand.nextInt(1,11)>=sanceUltraKryt){
            return  utok;
        }else {
            return kryt;
        }
    }


}
