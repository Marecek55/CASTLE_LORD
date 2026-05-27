package Predmety.Truhly;

import Predmety.Rarita;

/**
 * Tato trida dava sance drevene truhle
 */
public class DrevenaTruhla extends Truhla{
    public DrevenaTruhla(String nazev, Rarita rarita, int uroven) {
        super(nazev, rarita, uroven);

    }

    /**
     * Tato metoda nastavuje sance drevene truhle
     */
    @Override
    protected void nastavSance() {
        this.nasobitelPenezPodleTypu = 1;
        this.sanceBezna = 80;
        this.sanceVzacna = 15;
        this.sanceLegendarni = 5;
    }
}
