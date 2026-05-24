package Predmety.Truhly;

import Predmety.Rarita;

public class ZlataTruhla extends Truhla{
    public ZlataTruhla(String nazev, Rarita rarita, int uroven) {
        super(nazev, rarita, uroven);

    }

    @Override
    protected void nastavSance() {
        super.nastavSance();
        this.nasobitelPenezPodleTypu = 2;
        this.sanceBezna = 20;
        this.sanceVzacna = 50;
        this.sanceLegendarni = 30;
    }
}
