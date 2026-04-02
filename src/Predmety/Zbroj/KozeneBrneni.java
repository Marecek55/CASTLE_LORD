package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Rarita;

public class KozeneBrneni extends Brneni{
    public KozeneBrneni(String jmeno,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno, rarita, kryt, ultraKryt);
        this.sanceUltraKryt = 9;
    }

    @Override
    public int kryt(int utok) {

        if (Hra.rand.nextInt(1,11)>=sanceUltraKryt){
            return  utok;
        }else {
            return kryt;
        }
    }


}
