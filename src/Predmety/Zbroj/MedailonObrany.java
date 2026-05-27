package Predmety.Zbroj;

import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Rarita;

/**
 * Tato trida urcuje vylepseni medailonu obrany
 */
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
    public String getNazevObrazku() {
        return "medailonObrany.png";
    }

    /**
     * Tato metoda vylepsuje vlastnost brneni dane postavy medailonem
     * @param b postava
     */
    @Override
    public void vylepsitVlastnost(Postava b) {
        if (b.getBrneni()!= null){
            b.getBrneni().setSanceUltraKryt(b.getBrneni().getSanceUltraKryt() - zlepsovac);
        }

    }
}
