package Predmety.Truhly;

import Logika.Hra;
import Predmety.Jidlo;
import Predmety.Penize;
import Predmety.Predmet;
import Predmety.Rarita;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public abstract class Truhla {
    protected Rarita rarita;
    protected int sanceBezna;
    protected int sanceVzacna;
    protected int sanceLegendarni;
    protected double nasobitelPenezPodleTypu;

    public Truhla(String nazev, Rarita rarita) {
        this.rarita = rarita;

    }

    public Predmet generovaniPredmetu(int uroven) {
        int nahoda = Hra.rand.nextInt(1, 101);
        Rarita raritaVeci;

        if (nahoda <= sanceLegendarni) {
            raritaVeci = Rarita.LEGENDÁRNÍ;
        } else if (nahoda <= (sanceLegendarni + sanceVzacna)) {
            raritaVeci = Rarita.VZÁCNÁ;
        } else {
            raritaVeci = Rarita.BĚŽNÁ;
        }

        int nahodaVeci = Hra.rand.nextInt(1, 6);
        switch (nahodaVeci) {
            case 1:
                Zbran z = Zbran.vytvoritZbran(uroven, true, raritaVeci);
                return z;
            case 2:
                Brneni b = Brneni.vytvoritBrneni(uroven, true, raritaVeci);

                return b;

            case 3:
                Medailon m = Medailon.vytvoritMedailon(true, raritaVeci);
                return m;

            case 4:
                int pocet = Penize.dostanePenize(uroven, rarita, nasobitelPenezPodleTypu);
                Penize.pridaniDoHromadyPenezZTruhly(pocet, uroven);
                return null;
                case 5:
                    int pocet2 = Jidlo.dostaneJidlo(uroven,rarita,nasobitelPenezPodleTypu);
                    Jidlo.pridaniDoHromadyJidlaZTruhly(pocet2, uroven);
                    return null;


        }
        return null;
    }

    public Rarita getRarita() {
        return rarita;
    }
}
