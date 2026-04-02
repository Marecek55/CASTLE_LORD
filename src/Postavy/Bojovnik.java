package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class Bojovnik extends Postava{
    public Bojovnik(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, int uroven) {
        super(jmeno, zbran, brneni, medailon);
        this.zivoty = uroven*100;
    }


    @Override
    public int utok() {
        return zbran.zautoc();
    }

    @Override
    public int obrana(int utok) {
        if (brneni != null) {
            return brneni.kryt(utok);
        } else {
            return 0;
        }

    }

}