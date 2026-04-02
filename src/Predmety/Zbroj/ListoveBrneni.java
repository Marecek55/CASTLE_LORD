package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Rarita;

public class ListoveBrneni extends Brneni{
    public ListoveBrneni(String jmeno,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno, rarita, kryt, ultraKryt);
        this.sanceUltraKryt = 8;
    }
    @Override
    public int kryt(int utok) {

        if (Hra.rand.nextInt(1,11)>=sanceUltraKryt){
            return  utok/3*2;
        }else {
            return kryt;
        }
    }


}
