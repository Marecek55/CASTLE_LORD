
package Predmety.Zbroj;
import Logika.Hra;
import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Penize;
import Predmety.Predmet;
import Predmety.Rarita;


import java.util.ArrayList;

public abstract class Medailon extends Predmet {
    protected Rarita rarita;
    protected String nazev;
    protected int zlepsovac;

    public Medailon(String nazev, Rarita rarita) {
        super(nazev);
        this.rarita = rarita;
        this.nazev = nazev;


    }

    public static Medailon vytvoritMedailon(boolean jeZtruhly, Rarita raritaZtruhly){
        Rarita rarita = null;
        if (!jeZtruhly){
            ArrayList<Rarita> rarity = new ArrayList<>();
            rarity.add(Rarita.VZÁCNÁ);
            rarity.add(Rarita.BĚŽNÁ);
            rarity.add(Rarita.LEGENDÁRNÍ);
            int cislo = Hra.rand.nextInt(rarity.size());
            rarita = rarity.get(cislo);
        } else {
            rarita = raritaZtruhly;
        }

        int cislo2 = Hra.rand.nextInt(1, 3);
        switch (cislo2) {
            case 1:
                return new MedailonObrany("Medailon Obrany", rarita);
            case 2:
                return new MedailonUtoku("Medailon Útoku", rarita);
            default:
                return null;
        }
    }
    public abstract String getNazevObrazku();

    public Rarita getRarita() {
        return rarita;
    }

    public void setRarita(Rarita rarita) {
        this.rarita = rarita;
    }

    public int getZlepsovac() {
        return zlepsovac;
    }

    public void setZlepsovac(int zlepsovac) {
        this.zlepsovac = zlepsovac;
    }

    public abstract void vylepsitVlastnost(Postava b);
}
