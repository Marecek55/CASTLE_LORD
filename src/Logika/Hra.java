package Logika;

import Obrazovky.ObrazovkaHradu;
import Obrazovky.ObrazovkaMapy;
import Obrazovky.StartovaciObrazovka;
import Postavy.Postava;
import Predmety.Inventar;
import Predmety.Jidlo;
import Predmety.Penize;
import Predmety.Rarita;
import Predmety.Truhly.Truhla;
import Predmety.Zbrane.Luk;
import Predmety.Zbrane.MagickaHul;
import Predmety.Zbrane.Mec;
import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.ListoveBrneni;
import Predmety.Zbroj.Medailon;
import Predmety.Zbroj.MedailonObrany;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Toolkit;
/**
 * Trida Hra v sobe drzi hlavni tridy a staticke promene potrebne pro chod hry
 */
public class Hra {
    public static  Random rand = new Random();
    public static int vyskaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int sirkaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static Hudba hudbaPozadi;
    public static Hudba hudbaBitva;
    public static int urovenGobliniStezky = 1;
    public static StartovaciObrazovka startovaciObrazovka;
    public static ObrazovkaHradu obrazovkaHradu;
    public static ObrazovkaMapy obrazovkaMapy;
    public static ArrayList<Postava> hracuvTym = new ArrayList<>();
    public static ArrayList<Truhla> inventarTruhel = new ArrayList<>();
    public static Inventar inventar = new Inventar();
    public static Hrac hrac;

    /**
     * Tato metoda vytvari hracova prvniho hrace na start hry
     * se zakladnim vybavenim
     * @param jmeno
     */
    public static void hracuvTym(String jmeno) {
        if (hracuvTym.isEmpty()) {
            int sila = rand.nextInt(3, 8);
            int crit = rand.nextInt(8, 11);
            Zbran m = new Mec("Meč", sila, crit, Rarita.BĚŽNÁ);

            Postava p = TvorbaPostav.tvorbaHracovaBojovnika(jmeno, 1, m);
            hracuvTym.add(p);

        }
    }

    /**
     * Konstruktor tridy hra spousti hudbu a prvni startovaciObrazovku
     * a nastavi limit a pocet penez a jidla na zacatek
     */
    public Hra() {
        hrac = new Hrac(1,Penize.getPocet(), Jidlo.getPocet(),1,0);
        hudbaPozadi = new Hudba("/Hudba/HudbaPozadi.wav");
        hudbaBitva = new Hudba("/Hudba/HudbaBitva.wav");
        Hra.hudbaPozadi.hraj(true);
        Penize.setPocet(3000);
        Jidlo.setPocet(3000);
        Penize.setMaxpocet(5000);
        Jidlo.setMaxpocet(5000);

        startovaciObrazovka = new StartovaciObrazovka("Start");

   }


}