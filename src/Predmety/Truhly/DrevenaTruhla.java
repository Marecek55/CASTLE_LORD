package Predmety.Truhly;

import Predmety.Rarita;

public class DrevenaTruhla extends Truhla{
    public DrevenaTruhla(String nazev, Rarita rarita) {
        super(nazev, rarita);
        this.nasobitelPenezPodleTypu = 1;
        this.sanceBezna = 80;
        this.sanceVzacna = 15;
        this.sanceLegendarni = 5;
    }


}
