package Hrad;

import Postavy.Bojovnik;
import Predmety.Penize;

import java.util.ArrayList;

public class Lekarna extends Mistnost{
    private ArrayList<Bojovnik> leceniBojovnici;
    private int maxKapacita;
    public Lekarna(String nazev, int cenaZaPostaveni, int cenaZaVylepseni, int uroven, int velikost) throws Exception {
        super(nazev, cenaZaPostaveni, cenaZaVylepseni, uroven, velikost);
        if (Penize.getPocet() >= cenaZaPostaveni) {
            Penize.setPocet(Penize.getPocet() - cenaZaPostaveni);
            this.leceniBojovnici = new ArrayList<>();
            this.maxKapacita = 1;
        } else {
            throw new Exception("Nemáš peníze");
        }

    }

    @Override
    public void vylepsitMistnost() {
        int cena = cenaZaVylepseni * (uroven+1);
        if (Penize.getPocet() >= cena) {
            Penize.setPocet(Penize.getPocet() - cena);
            this.uroven++;
            this.maxKapacita = uroven;
        }
    }
    public  void pridatBojovnikyDoLekarny(Bojovnik b){
        if (leceniBojovnici.size() <maxKapacita){
            if (b.getMaxZivoty()>b.getZivoty()){
                leceniBojovnici.add(b);
            }
        }


    }
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
