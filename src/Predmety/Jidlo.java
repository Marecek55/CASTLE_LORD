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

    /**
     * Tato metoda pocita prijem jidla z turhly podle jeji rarity a nasobitelu
     * @param level uroven hrace
     * @param raritaTruhly rarita truhly
     * @param nasobitelPodleTruhly nasobitel jidla
     * @return
     */
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

    /**
     * Tato metoda pridava vypocitane jidlo do hromady jidla hrace
     * @param jidlo pocet jidla
     * @param levelHrace uroven hrace
     */
    public static void pridaniDoHromadyJidlaZTruhly(int jidlo, int levelHrace) {

        pocet = pocet + jidlo;

        if (pocet > maxpocet) {
            pocet = maxpocet;
        }

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