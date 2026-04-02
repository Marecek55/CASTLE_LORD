package Postavy;

import Logika.Hra;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class Goblin extends Postava {
    public Goblin(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, int urovenGobliniStezky) {
        super(jmeno, zbran, brneni, medailon);
        double nasobitel = Hra.rand.nextInt(10, 20)/10.0;
        this.zivoty = (int) (80 * urovenGobliniStezky * nasobitel);
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
