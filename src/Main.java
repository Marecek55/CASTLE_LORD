import Hrad.Hrad;
import Obrazovky.NacitaciObrazovka;
import Obrazovky.Obrazovka;
import Obrazovky.ObrazovkaHradu;
import Obrazovky.SoubojovaObrazovka;

public class Main {
    public static void main(String[] args) {
//        Obrazovka o = new NacitaciObrazovka("Obrazovka");
//        o.inicializace();
//        SoubojovaObrazovka a = new SoubojovaObrazovka("Boj", false);
//        a.inicializace();
        ObrazovkaHradu h = new ObrazovkaHradu("hrad", false, new Hrad());
        h.inicializace();


    }
}
