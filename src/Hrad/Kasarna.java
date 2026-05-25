package Hrad;

import Postavy.Bojovnik;
import Predmety.Penize;

import java.util.ArrayList;

public class Kasarna extends Mistnost{
    private ArrayList<Bojovnik> bojovnici;
    private int maxKapacita;
    public Kasarna(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni = 1;
        if (Penize.getPocet() >= cenaZaPostaveni) {
            Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
            aktualniPocetPostaveni++;
            this.bojovnici = new ArrayList<>();
            this.maxKapacita = 1;
        } else {
            throw new Exception("Nemáš peníze");
        }


    }

    @Override
    public void vylepsitMistnost() {
        int cena = getCenaZaDalsiVylepseni();
        if (Penize.getPocet() >= cena) {
            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            this.maxKapacita = uroven;
        }

    }
    public void pridatBojovnika(Bojovnik novy){
        int cenaZaBojovnika = 500 * uroven;
        if (Penize.getPocet()>= cenaZaBojovnika){
            if (bojovnici.size()<maxKapacita){
                bojovnici.add(novy);
                Penize.setPocet(Penize.getPocet()-cenaZaBojovnika);

            }
        }

    }

}
