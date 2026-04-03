package Hrad;

import Predmety.Penize;

public class SkladPenez extends Mistnost{


    public SkladPenez(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        if (Penize.getPocet() >= cenaZaPostaveni) {
            Penize.setMaxpocet(Penize.getMaxpocet() + 10000);
            Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
        }else {
            throw new Exception("Nema penize");
        }
    }

    @Override
    public void vylepsitMistnost() {

        int cena = cenaZaVylepseni * (uroven + 1);
        if (Penize.getPocet() >= cena) {

            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            Penize.setMaxpocet(Penize.getMaxpocet() + 10000 * uroven);

        }


    }
}
