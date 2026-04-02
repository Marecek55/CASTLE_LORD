package Predmety;

import Logika.Hra;

public class Jidlo extends Predmet {

    private static int pocet = 0;
    private static int maxpocet = 0;

    public Jidlo(String nazev) {
        super(nazev);
    }


    public static int dostaneJidlo(int level, Rarita raritaTruhly, double nasobitelPodleTruhly) {
        int zaklad = 0;
        switch (raritaTruhly) {
            case BĚŽNÁ: zaklad = 200;
            break;
            case VZÁCNÁ: zaklad = 500;
            break;
            case LEGENDÁRNÍ: zaklad = 1000;
            break;
        }
        return (int) (zaklad * level * nasobitelPodleTruhly);
    }


    public static void pridaniDoHromadyJidlaZTruhly(int jidlo, int levelHrace) {
        maxpocet = levelHrace * 500;

        pocet = pocet + jidlo;

        if (pocet > maxpocet) {
            pocet = maxpocet;
        }

    }


    public static int getPocet() { return pocet; }
    public static int getMaxpocet() { return maxpocet; }
}