package Predmety.Zbroj;

import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Rarita;
/**
 * Tato trida urcuje vylepseni medailonu utoku
 */
public class MedailonUtoku extends Medailon{
    public MedailonUtoku(String nazev, Rarita rarita) {
        super(nazev,rarita);
        switch (rarita){
            case BĚŽNÁ -> zlepsovac = 1;
            case VZÁCNÁ -> zlepsovac = 2;
            case LEGENDÁRNÍ -> zlepsovac = 3;
        }
    }

    @Override
    public String getNazevObrazku() {
        return "medailonUtoku.png";
    }
    /**
     * Tato metoda vylepsuje vlastnost zbrane dane postavy medailonem
     * @param b postava
     */
    @Override
    public void vylepsitVlastnost(Postava b) {
        if (b.getZbran()!=null){
            b.getZbran().setSanceCritical(b.getZbran().getSanceCritical() - zlepsovac);
        }

    }
}
