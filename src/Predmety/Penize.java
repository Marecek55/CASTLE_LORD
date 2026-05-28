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

    /**
     * Tato metoda pocita kolik penez dostane podle truhly
     * @param level uroven hrace
     * @param raritaTruhly rarita truhly
     * @param nasobitelPenezPodleTruhly nasobitel dane truhly
     * @return
     */
    public static int  dostanePenize(int level, Rarita raritaTruhly,double nasobitelPenezPodleTruhly) {
        int pocetPenez = 0;
        switch (raritaTruhly){
            case VZÁCNÁ:
                pocetPenez = 1000;
                break;
            case BĚŽNÁ:
                pocetPenez = 500;
                break;
            case LEGENDÁRNÍ:
                pocetPenez = 2000;
                break;
        }
        return (int) (pocetPenez * level* nasobitelPenezPodleTruhly);

    }

    /**
     * Tato metoda pridava penize do poctu z truhly
     * @param penize pocet penez
     * @param levelHrace uroven hrace
     */
    public static void  pridaniDoHromadyPenezZTruhly(int penize,int levelHrace){
        pocet  = pocet + penize;


        if (pocet > maxpocet) {
            pocet = maxpocet;
        }
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

