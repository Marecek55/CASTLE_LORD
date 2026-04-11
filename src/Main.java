import Hrad.Hrad;
import Obrazovky.NacitaciObrazovka;
import Obrazovky.Obrazovka;
import Obrazovky.ObrazovkaHradu;
import Obrazovky.SoubojovaObrazovka;

public class Main {
    public static void main(String[] args) {
        SoubojovaObrazovka arena = new SoubojovaObrazovka("Bitva", false);
        arena.inicializace();
        arena.funkcnost();

//        ObrazovkaHradu h = new ObrazovkaHradu("hrad", false, new Hrad());
//        h.inicializace();



    }
}
