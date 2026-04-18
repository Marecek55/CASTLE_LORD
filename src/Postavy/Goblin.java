package Postavy;

import Logika.Hra;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public class Goblin extends Postava {


    public Goblin(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, String nazevObrazkuVKlidu, String nazevObrazkuVUtoku, int urovenGobliniStezky) {
        super(jmeno, zbran, brneni, medailon, nazevObrazkuVKlidu, nazevObrazkuVUtoku);
        double nasobitel = Hra.rand.nextInt(10, 20)/10.0;
        this.zivoty = (int) (70 * urovenGobliniStezky * nasobitel);
    }

    @Override
    public int utok() {
        setUtoci(true);
        return zbran.zautoc();

    }

    @Override
    public int obrana(int utok) {
        setUtoci(false);
        if (brneni!=null){
            return brneni.kryt(utok);
        }
            return 0;

    }
}
