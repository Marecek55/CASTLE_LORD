package Predmety.Truhly;

import Logika.Hra;
import Predmety.Rarita;
import Predmety.Zbrane.Zbran;
/**
 * Tato trida dava sance stribrne truhle
 */
public class StribrnaTruhla extends Truhla{
    public StribrnaTruhla(String nazev, Rarita rarita, int uroven) {
        super(nazev, rarita, uroven);

    }
    /**
     * Tato metoda nastavuje sance stribrne truhle
     */
    @Override
    protected void nastavSance() {
        this.nasobitelPenezPodleTypu = 1.5;
        this.sanceBezna = 50;
        this.sanceVzacna = 40;
        this.sanceLegendarni = 10;
    }
}
