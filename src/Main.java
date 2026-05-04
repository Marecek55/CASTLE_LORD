import Logika.Hra;
import Logika.Hudba;
import Obrazovky.ObrazovkaHradu;
import Obrazovky.ObrazovkaMapy;
import Obrazovky.StartovaciObrazovka;

import static Logika.Hra.hudbaPozadi;

public class Main {
    public static void main(String[] args) {
//        SoubojovaObrazovka arena = new SoubojovaObrazovka("Bitva", false);
//        arena.inicializace();
//        arena.funkcnost();
          StartovaciObrazovka n = new StartovaciObrazovka("");
          n.inicializace();
          hudbaPozadi = new Hudba("/Hudba/HudbaPozadi.wav");
         Hra.hudbaPozadi.hraj(true);
//
//        ObrazovkaMapy o = new ObrazovkaMapy("mapa", false);
//        o.inicializace();
//        ObrazovkaHradu h = new ObrazovkaHradu("hrad", false);
//        h.inicializace();



    }
}
