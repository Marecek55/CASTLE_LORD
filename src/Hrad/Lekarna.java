package Hrad;

import Postavy.Bojovnik;
import Predmety.Penize;

import java.util.ArrayList;
/**
 * Trida Lekarna nastavuje co lekarna bude delat a co se odecte uzivateli po postaveni
 */
public class Lekarna extends Mistnost{
    private ArrayList<Bojovnik> leceniBojovnici;
    private int maxKapacita;
    public Lekarna(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        maxPostaveni =2;

        if (Penize.getPocet() >= cenaZaPostaveni) {
                Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
                aktualniPocetPostaveni++;
                this.leceniBojovnici = new ArrayList<>();
                this.maxKapacita = 1;
            } else {
                throw new Exception("Nemáš peníze");
            }



    }
    /**
     * Tato metoda vylepsuje a odecita penize za vylepseni Lekarny
     */

    @Override
    public void vylepsitMistnost() {
        int cena = getCenaZaDalsiVylepseni();
        if (Penize.getPocet() >= cena) {
            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            this.maxKapacita = uroven;
        }else {
            throw new RuntimeException("Nemáš dost peněz!");
        }
    }

    /**
     * Tato metoda posila bojovnika na leceni a kontroluje jestli uz neni na max zivotech
     * @param b bojovnik co jde na leceni
     */
    public  void pridatBojovnikyDoLekarny(Bojovnik b){
        if (leceniBojovnici.size() <maxKapacita){
            if (b.getMaxZivoty()>b.getZivoty()){
                leceniBojovnici.add(b);
            }
        }


    }

    /**
     * Tato metoda uskutecnuje leceni a leci bojovniky za penize
     */
    public void leceni(){
        int cenaZaLeceni = 50*uroven*leceniBojovnici.size();
        if (Penize.getPocet() >= cenaZaLeceni){
            Penize.setPocet(Penize.getPocet() - cenaZaLeceni);
            for (int i = 0; i < leceniBojovnici.size(); i++) {
                leceniBojovnici.get(i).setZivoty(leceniBojovnici.get(i).getMaxZivoty());
            }
            leceniBojovnici.clear();

        }
    }
}
