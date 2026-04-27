import Hrad.Hrad;
import Logika.Hudba;
import Obrazovky.NacitaciObrazovka;
import Obrazovky.Obrazovka;
import Obrazovky.ObrazovkaHradu;
import Obrazovky.SoubojovaObrazovka;

import static Logika.Hra.hudbaPozadi;

public class Main {
    public static void main(String[] args) {
//        SoubojovaObrazovka arena = new SoubojovaObrazovka("Bitva", false);
//        arena.inicializace();
//        arena.funkcnost();
          NacitaciObrazovka n = new NacitaciObrazovka("");
          n.inicializace();
          hudbaPozadi = new Hudba("/Hudba/HudbaBitva.wav");

//        ObrazovkaHradu h = new ObrazovkaHradu("hrad", false);
//        h.inicializace();



    }
}
