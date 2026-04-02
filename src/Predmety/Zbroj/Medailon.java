
package Predmety.Zbroj;
import Logika.Hra;
import Postavy.Bojovnik;
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

    public static Medailon vytvoritMedailon( boolean jeZtruhly, Rarita raritaZtruhly){
        ArrayList<Rarita> rarity = new ArrayList<>();
        rarity.add(Rarita.VZÁCNÁ);
        rarity.add(Rarita.BĚŽNÁ);
        rarity.add(Rarita.LEGENDÁRNÍ);
        int cislo = Hra.rand.nextInt(rarity.size());
        Rarita rarita = rarity.get(cislo);
        int cislo2 = Hra.rand.nextInt(1,3);
        switch (cislo2) {
            case 1:
                Medailon z = new MedailonObrany("Kožené Brnění",rarita);
                return z;
            case 2:
                Medailon z2 = new MedailonUtoku("Ocelové Brnění",rarita);
                return z2;
            default:
                return null;
        }
    }

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

    public abstract void vylepsitVlastnost(Bojovnik b);
}
