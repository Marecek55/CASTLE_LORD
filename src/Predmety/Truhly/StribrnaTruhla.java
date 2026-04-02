package Predmety.Truhly;

import Logika.Hra;
import Predmety.Rarita;
import Predmety.Zbrane.Zbran;

public class StribrnaTruhla extends Truhla{
    public StribrnaTruhla(String nazev, Rarita rarita) {
        super(nazev, rarita);
        this.nasobitelPenezPodleTypu = 1.5;
        this.sanceBezna = 50;
        this.sanceVzacna = 40;
        this.sanceLegendarni = 10;
    }



}
