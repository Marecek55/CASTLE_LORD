package PraceSeSoubory;

import Hrad.Hrad;
import Logika.Hrac;
import Postavy.Postava;
import Predmety.Predmet;
import Predmety.Truhly.Truhla;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Tato trida uchovava hru po nacteni
 * a jeji vsechny promene
 */
public class UlozenaHra implements Serializable {
    public ArrayList<Postava> hracuvTym;
    public ArrayList<Truhla> inventarTruhel;
    public ArrayList<Predmet> inventar;
    public Hrac hrac;
    public Hrad hrad;
    public int penize;
    public int jidlo;
    public int maxPenize;
    public int maxJidlo;
    public int urovenGobliniStezky;
}