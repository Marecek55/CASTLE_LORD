package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Rarita;
/**
 * Tato trida urcuje kryt listoveho brneni
 */
public class ListoveBrneni extends Brneni{
    public ListoveBrneni(String jmeno,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno, rarita, kryt, ultraKryt);
        this.sanceUltraKryt = 8;
    }

    @Override
    public String getNazevObrazku() {
        return "listoveBrneni.png";
    }
    /**
     * Tato metoda vraci kryt listoveho brneni
     */
    @Override
    public int kryt(int utok) {

        if (Hra.rand.nextInt(1,11)>=sanceUltraKryt){
            return  utok/3*2;
        }else {
            return kryt;
        }
    }


}
