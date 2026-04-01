package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class Goblin extends Postava {
    public Goblin(String jmeno, int zivoty, Zbran zbran, Brneni brneni, Medailon medailon) {
        super(jmeno, zivoty, zbran, brneni, medailon);
    }

    @Override
    public int utok() {
        return 0;
    }

    @Override
    public int obrana() {
        return 0;
    }
}
