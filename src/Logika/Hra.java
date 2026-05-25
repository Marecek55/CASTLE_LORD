package Logika;

import Obrazovky.ObrazovkaHradu;
import Obrazovky.ObrazovkaMapy;
import Obrazovky.ObrazovkaNastaveni;
import Obrazovky.StartovaciObrazovka;
import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Inventar;
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
public class Hra {
    public static  Random rand = new Random();
    public static int vyskaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int sirkaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static Hudba hudbaPozadi;
    public static Hudba hudbaBitva;
    public static int urovenGobliniStezky = 1;
    public static int urovenHradu = 1;
    public static StartovaciObrazovka startovaciObrazovka;
    public static ObrazovkaHradu obrazovkaHradu;
    public static ObrazovkaMapy obrazovkaMapy;
    public static ArrayList<Postava> hracuvTym = new ArrayList<>();
    public static ArrayList<Truhla> inventarTruhel = new ArrayList<>();
    public static Inventar inventar = new Inventar();
    public static void hracuvTym(String jmeno) {
        if (hracuvTym.isEmpty()) {
            int sila = rand.nextInt(3, 8);
            int crit = rand.nextInt(8, 11);
            Zbran m = new Mec("Meč", sila, crit, Rarita.BĚŽNÁ);
            Brneni b = new ListoveBrneni("", Rarita.LEGENDÁRNÍ, 2,2);
            Medailon me = new MedailonObrany("" , Rarita.VZÁCNÁ);
            Postava p = TvorbaPostav.tvorbaHracovaBojovnika(jmeno, 1, m);
            p.setBrneni(b);
            p.setMedailon(me);
            Zbran m2 = new MagickaHul("Magická Hůl", sila, crit, Rarita.BĚŽNÁ);
            Postava p2 = TvorbaPostav.tvorbaHracovaBojovnika(jmeno, 1, m2);
            p2.setBrneni(b);
            p2.setMedailon(me);
            p2.setJmeno("Bobis");
            Zbran m3 = new Luk("Luk", sila, crit, Rarita.BĚŽNÁ);
            Postava p3 = TvorbaPostav.tvorbaHracovaBojovnika(jmeno, 1, m3);
            p3.setBrneni(b);
            p3.setMedailon(me);
            p3.setJmeno("KUBA");
            hracuvTym.add(p);
            hracuvTym.add(p2);
            hracuvTym.add(p3);
        }
    }
    public Hra() {
        hudbaPozadi = new Hudba("/Hudba/HudbaPozadi.wav");
        hudbaBitva = new Hudba("/Hudba/HudbaBitva.wav");
        Hra.hudbaPozadi.hraj(true);

        startovaciObrazovka = new StartovaciObrazovka("Start");

   }


}