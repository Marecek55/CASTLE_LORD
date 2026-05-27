package Predmety;

import Logika.Hra;

import java.util.ArrayList;
/**
 * Tato trida v sobe ma instanci penez
 */
public class Penize extends Predmet{
    private static int pocet = 0;
    private static int maxpocet = 0;
    public Penize(String nazev) {
        super(nazev);

    }

    public static int getPocet() {
        return pocet;
    }

    public static void setPocet(int pocet) {
        Penize.pocet = pocet;
    }

    public static int getMaxpocet() {
        return maxpocet;
    }

    public static void setMaxpocet(int maxpocet) {
        Penize.maxpocet = maxpocet;
    }
}

