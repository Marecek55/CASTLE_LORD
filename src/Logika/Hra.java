package Logika;

import Obrazovky.ObrazovkaHradu;
import Obrazovky.ObrazovkaMapy;
import Obrazovky.ObrazovkaNastaveni;
import Obrazovky.StartovaciObrazovka;

import java.util.Random;
import java.awt.Toolkit;
public class Hra {
    public static  Random rand = new Random();
    public static int vyskaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int sirkaObrazovky = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static Hudba hudbaPozadi;
    public static int urovenGobliniStezky = 1;
    public static ObrazovkaNastaveni obrazovkaNastaveni;
    public static StartovaciObrazovka startovaciObrazovka;
    public static ObrazovkaHradu obrazovkaHradu;
    public static ObrazovkaMapy obrazovkaMapy;

    public Hra() {
        hudbaPozadi = new Hudba("/Hudba/HudbaPozadi.wav");
        Hra.hudbaPozadi.hraj(true);

        startovaciObrazovka = new StartovaciObrazovka("Start");

   }


}
