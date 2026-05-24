package Predmety.Truhly;

import Predmety.Rarita;

public class DrevenaTruhla extends Truhla{
    public DrevenaTruhla(String nazev, Rarita rarita, int uroven) {
        super(nazev, rarita, uroven);

    }

    @Override
    protected void nastavSance() {
        this.nasobitelPenezPodleTypu = 1;
        this.sanceBezna = 80;
        this.sanceVzacna = 15;
        this.sanceLegendarni = 5;
    }
}
