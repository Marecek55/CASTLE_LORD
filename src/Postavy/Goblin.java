package Postavy;

import Logika.Hra;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

/**
 * Tato trida resi utok obranu a zivoty goblina
 */
public class Goblin extends Postava {

    /**
     *Tato metoda pocita nasobitel zivotu
     */
    public Goblin(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, String nazevObrazkuVKlidu, String nazevObrazkuVUtoku, String typ, int urovenGobliniStezky) {
        super(jmeno, zbran, brneni, medailon, nazevObrazkuVKlidu, nazevObrazkuVUtoku, typ);
        double nasobitel = Hra.rand.nextInt(10, 20)/10.0;
        this.zivoty = (int) (70 * urovenGobliniStezky * nasobitel);
    }

    /**
     * Tato metoda vraci hodnotu utoku
     * @return
     */
    @Override
    public int utok() {
        setUtoci(true);
        return zbran.zautoc();

    }


    /**
     * Tato metoda vraci utok odectenou od obrany
     * @param utok utok
     * @return
     */
    @Override
    public int obrana(int utok) {
        setUtoci(false);
        if (brneni!=null){
            return brneni.kryt(utok);
        }
            return 0;

    }
}
