package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class Bojovnik extends Postava{
    private int maxZivoty;
    private int pocetTreninku;
    private int bonusZTreninku;
    public Bojovnik(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, int uroven) {
        super(jmeno, zbran, brneni, medailon);
        this.zivoty = uroven*100;
        this.maxZivoty = uroven*100;

    }


    @Override
    public int utok() {
        return zbran.zautoc() + bonusZTreninku ;

    }

    @Override
    public int obrana(int utok) {
        if (brneni != null) {
            return brneni.kryt(utok);
        } else {
            return 0;
        }

    }
    public boolean trenuje(int bonus){
        if (pocetTreninku<2) {
            bonusZTreninku = bonusZTreninku + bonus;
            pocetTreninku++;
            return true;
        }
        return false;

    }
    public void resetTreninkuPoBoji(){
        bonusZTreninku = 0;
        pocetTreninku= 0;
    }


    public int getMaxZivoty() {
        return maxZivoty;
    }

    public void setMaxZivoty(int maxZivoty) {
        this.maxZivoty = maxZivoty;
    }

}