package Hrad;

import Predmety.Penize;
/**
 * Trida SkladPenez nastavuje co sklad bude delat a co se odecte uzivateli po postaveni
 */
public class SkladPenez extends Mistnost{


    public SkladPenez(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni = 4;

            if (Penize.getPocet() >= cenaZaPostaveni) {
                Penize.setMaxpocet(Penize.getMaxpocet() + 10000);
                aktualniPocetPostaveni++;
                Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
            }else {
                throw new Exception("Nemáš peníze");
            }


    }
    /**
     * Tato metoda vylepsuje a odecita penize za vylepseni skladu
     */


    @Override
    public void vylepsitMistnost() {

        int cena = getCenaZaDalsiVylepseni();
        if (Penize.getPocet() >= cena) {

            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            Penize.setMaxpocet(Penize.getMaxpocet() + 10000 * uroven);

        }else {
            throw new RuntimeException("Nemáš dostatek peněz na vylepšení!");
        }


    }
}
