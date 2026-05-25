package Hrad;

import Predmety.Jidlo;
import Predmety.Penize;


public class SkladJidla extends Mistnost{
    public SkladJidla(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost ) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni = 4;

            if (Penize.getPocet() >= cenaZaPostaveni) {
                aktualniPocetPostaveni++;
                Jidlo.setMaxpocet(Jidlo.getMaxpocet() + 5000);
                Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
            }else {
                throw new Exception("Nema penize");
            }


    }

    @Override
    public void vylepsitMistnost() {
        int cena = getCenaZaDalsiVylepseni();
        if (Penize.getPocet() >= cena) {

            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            Jidlo.setMaxpocet(Jidlo.getMaxpocet() + 5000 * uroven);

        }else {
            throw new RuntimeException("Nemáš dostatek peněz na vylepšení!");
        }
    }
}
