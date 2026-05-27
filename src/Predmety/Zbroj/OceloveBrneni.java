package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Rarita;
/**
 * Tato trida urcuje kryt oceloveho brneni
 */
public class OceloveBrneni extends Brneni{
    public OceloveBrneni(String jmeno,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno, rarita, kryt, ultraKryt);
        this.sanceUltraKryt = 7;
    }

    @Override
    public String getNazevObrazku() {
        return "oceloveBrneni.png";
    }
    /**
     * Tato metoda vraci kryt oceloveho brneni
     */
    @Override
    public int kryt(int utok) {

        if (Hra.rand.nextInt(1,11)>=sanceUltraKryt){
            return  utok/3;
        }else {
            return kryt;
        }
    }


}
