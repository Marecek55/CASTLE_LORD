package Logika;

import Postavy.Bojovnik;
import Postavy.Goblin;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class TvorbaPostav {
    public static Goblin tvorbaGoblina(int urovenStezky){
        Zbran z = Zbran.vytvoritZbran(urovenStezky,false, null);
        Brneni b = null;
        if (urovenStezky>3){
             b = Brneni.vytvoritBrneni(urovenStezky,false, null);
        }
        Medailon m = null;
        if (urovenStezky>6){
             m = Medailon.vytvoritMedailon(false,null);
        }
        return new Goblin("Goblin", z,b,m,urovenStezky);
    }
    public static Bojovnik tvorbaHracovaBojovnika(String jmeno , int urovenHradu){
        Zbran z = Zbran.vytvoritZbran(urovenHradu,false, null);
        return new Bojovnik(jmeno, z, null, null, urovenHradu);
    }
    public static Bojovnik tvorbaProtihracovaBojovnika(String jmeno , int urovenHradu){
        Zbran z = Zbran.vytvoritZbran(urovenHradu,false, null);
        int nahoda = Hra.rand.nextInt(1,11);
        Brneni b = null;
        if (nahoda<=7){
            b = Brneni.vytvoritBrneni(urovenHradu,false, null);
        }
        int nahoda2 = Hra.rand.nextInt(1,11);
        Medailon m = null;
        if (nahoda2<=5){
            m = Medailon.vytvoritMedailon(false, null);
        }


        return new Bojovnik(jmeno, z, b, m, urovenHradu);
    }
}
