package Logika;

import Postavy.Bojovnik;
import Postavy.Goblin;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

/**
 * Trida TvorbaPostav tvori postavy a prirazuje jim obrazky
 */
public class TvorbaPostav {
    /**
     * Tato metoda tvori goblina dava mu obrazek a vybavu
     * @param urovenStezky uroven goblini stezky
     * @return
     */
    public static Goblin tvorbaGoblina(int urovenStezky ){
        Zbran z = Zbran.vytvoritZbran(urovenStezky,false, null);
        String utociciObrazek = "";
        String klidnyObrazek = "";

        switch ( z.getNazev()){
            case "Meč": utociciObrazek =  "/Obrazky/ObrazkyPostav/goblinMecUtok.png";
                klidnyObrazek = "/Obrazky/ObrazkyPostav/goblinMecKlidny.png";

                break;
            case "Luk":  utociciObrazek =  "/Obrazky/ObrazkyPostav/goblinLukKlidny.png";
                klidnyObrazek = "/Obrazky/ObrazkyPostav/goblinLukUtok.png";

                break;
            case "Magická Hůl":  utociciObrazek =  "/Obrazky/ObrazkyPostav/goblinMagKlidny.png";
                klidnyObrazek = "/Obrazky/ObrazkyPostav/goblinMagUtok.png";

        }
        Brneni b = null;
        if (urovenStezky>3){
             b = Brneni.vytvoritBrneni(urovenStezky,false, null);
        }
        Medailon m = null;
        if (urovenStezky>6){
             m = Medailon.vytvoritMedailon(false,null);
        }
        Goblin novyGoblin = new Goblin("Goblin", z,b,m, klidnyObrazek,utociciObrazek, "Goblin", urovenStezky);
        if (m!=null){
            m.vylepsitVlastnost(novyGoblin);
        }
        return novyGoblin;
    }
     static String bojovnikMecKlidny = "/Obrazky/ObrazkyPostav/bojovnikMecKlidny.png";
     static String bojovnikMecUtok = "/Obrazky/ObrazkyPostav/bojovnikMecUtok.png";
     static String bojovnikLukUtok = "/Obrazky/ObrazkyPostav/bojovnikLukUtok.png";
     static String bojovnikLukKlidny = "/Obrazky/ObrazkyPostav/bojovnikLukKlidny.png";
     static String bojovnikMagKlidny = "/Obrazky/ObrazkyPostav/bojovnikMagKlidny.png";
     static String bojovnikMagUtok = "/Obrazky/ObrazkyPostav/bojovnikMagUtok.png";

    /**
     * Tato metoda tvori hracoveho bojovnika dava mu obrazek a vybavu
     * @param jmeno jmeno bojovnika
     * @param urovenHradu uroven hradu
     * @param z zbran bojovnika
     * @return
     */
    public  static Bojovnik tvorbaHracovaBojovnika(String jmeno , int urovenHradu, Zbran z){
        String utociciObrazek = "";
        String klidnyObrazek = "";
        String typ = "";

        switch (z.getNazev()) {
            case "Meč":
                utociciObrazek = bojovnikMecUtok;
                klidnyObrazek = bojovnikMecKlidny;
                typ = "Bojovnik";
                break;
            case "Luk":
                utociciObrazek = bojovnikLukUtok;
                klidnyObrazek = bojovnikLukKlidny;
                typ = "Lukostrelec";
                break;
            case "Magická Hůl":
                utociciObrazek = bojovnikMagUtok;
                klidnyObrazek = bojovnikMagKlidny;
                typ = "Mag";
                break;
        }
        return new Bojovnik(jmeno, z, null, null, klidnyObrazek, utociciObrazek,typ, urovenHradu);
    }

    /**
     * Tato metoda tvori protihracoveho bojovnika dava mu obrazek a vybavu
     * @param jmeno jmeno bojovnika
     * @param urovenHradu uroven hradu
     * @return
     */
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
        String utociciObrazek = "";
        String klidnyObrazek = "";
        switch ( z.getNazev()){
            case "Meč": utociciObrazek =  bojovnikMecUtok;
                klidnyObrazek = bojovnikMecKlidny;
                break;
            case "Luk":  utociciObrazek =  bojovnikLukUtok;
                klidnyObrazek = bojovnikLukKlidny;
                break;
            case "Magická Hůl":  utociciObrazek =  bojovnikMagUtok;
                klidnyObrazek = bojovnikMagKlidny;
        }
        Bojovnik novyBojovnik = new Bojovnik(jmeno, z, b, m,klidnyObrazek, utociciObrazek,"", urovenHradu);


        if (m != null) {
            m.vylepsitVlastnost(novyBojovnik);
        }

        return novyBojovnik;
    }
}
