package Predmety;

import Logika.Hra;

/**
 * Tato trida v sobe ma instanci jidla
 */
public class Jidlo extends Predmet {

    private static int pocet = 0;
    private static int maxpocet = 0;

    public Jidlo(String nazev) {
        super(nazev);
    }

    public static void setPocet(int pocet) {
        Jidlo.pocet = pocet;
    }

    public static void setMaxpocet(int maxpocet) {
        Jidlo.maxpocet = maxpocet;
    }

    public static int getPocet() { return pocet; }
    public static int getMaxpocet() { return maxpocet; }
}