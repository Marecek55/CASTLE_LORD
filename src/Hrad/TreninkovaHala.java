package Hrad;

import Postavy.Bojovnik;
import Predmety.Jidlo;
import Predmety.Penize;
/**
 * Trida TreninkovaHala nastavuje co hala bude delat a co se odecte uzivateli po postaveni
 */
public class TreninkovaHala extends Mistnost{
    public TreninkovaHala(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni = 3;
        if (aktualniPocetPostaveni <=maxPostaveni){
            if (Penize.getPocet() >= cenaZaPostaveni) {
                Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
                aktualniPocetPostaveni++;
            } else {
                throw new Exception("Nemáš peníze");
            }
        }



    }
    /**
     * Tato metoda vylepsuje a odecita penize za vylepseni haly
     */

    @Override
    public void vylepsitMistnost() {
        int cena = getCenaZaDalsiVylepseni();
        if (Penize.getPocet() >= cena) {
            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
        }else {
            throw new RuntimeException("Nemáš dostatek peněz na vylepšení!");
        }
    }
    /**
     * Tato metoda uskutecnuje trenink bojovniku za jidlo a podava cislo k bonusu utokua pocita ho
     */
    public boolean trenovaniBojovnika(Bojovnik b) {
        int cenaTreninku = 100 * uroven;
        int bonusKUtoku = uroven * 3;

        if (Jidlo.getPocet() >= cenaTreninku) {
            if (b.trenuje(bonusKUtoku)) {
                Jidlo.setPocet(Jidlo.getPocet() - cenaTreninku);
                return true;
            }
        }
        return false;
    }
}
