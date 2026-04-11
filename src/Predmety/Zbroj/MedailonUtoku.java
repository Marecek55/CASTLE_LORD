package Predmety.Zbroj;

import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Rarita;

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
    public void vylepsitVlastnost(Postava b) {
        if (b.getZbran()!=null){
            b.getZbran().setSanceCritical(b.getZbran().getSanceCritical() - zlepsovac);
        }

    }
}
