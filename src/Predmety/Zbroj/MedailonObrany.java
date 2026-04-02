package Predmety.Zbroj;

import Postavy.Bojovnik;
import Predmety.Rarita;

public class MedailonObrany extends Medailon{
    public MedailonObrany(String nazev , Rarita rarita) {
        super(nazev,rarita);
        switch (rarita){
            case BĚŽNÁ -> zlepsovac = 1;
            case VZÁCNÁ -> zlepsovac = 2;
            case LEGENDÁRNÍ -> zlepsovac = 3;
        }
    }

    @Override
    public void vylepsitVlastnost(Bojovnik b) {
        b.getBrneni().setSanceUltraKryt(b.getBrneni().getSanceUltraKryt() - zlepsovac);
    }
}
