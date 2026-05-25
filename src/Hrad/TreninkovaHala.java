package Hrad;

import Postavy.Bojovnik;
import Predmety.Jidlo;
import Predmety.Penize;

public class TreninkovaHala extends Mistnost{
    public TreninkovaHala(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni = 3;
        if (aktualniPocetPostaveni >maxPostaveni){
            if (Penize.getPocet() >= cenaZaPostaveni) {
                Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
                aktualniPocetPostaveni++;
            } else {
                throw new Exception("Nemáš peníze");
            }
        }



    }

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
    public void trenovaniBojovnika(Bojovnik b ){
        int cenaTreninku = 50*uroven;
        int bonusKUtoku = uroven;

        if (Jidlo.getPocet() >= cenaTreninku){
            if (b.trenuje(bonusKUtoku)== true){
                Jidlo.setPocet(Jidlo.getPocet() - cenaTreninku);
            }

        }

    }
}
