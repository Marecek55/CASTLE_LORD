package Hrad;

import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Hrad {


    private Kasarna kasarna;
    private ArrayList<Lekarna> lekarny = new ArrayList<>();
    private ArrayList<TreninkovaHala> treninkoveHaly = new ArrayList<>();
    private ArrayList<SkladPenez> skladyPenez = new ArrayList<>();
    private ArrayList<SkladJidla> skladyJidla = new ArrayList<>();
    private JPanel okno;


    public Hrad(JPanel okno, JButton b)  {
        this.okno = okno;
        try {
            kasarna = new Kasarna("Kasárna", 0, 100, 1, 1);
            StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/kasarnaMistnost.png", 1494,498);
            b.setLocation(787,1114);
            okno.add(b);
        } catch (Exception e) {
            System.out.println("Kasarna nejde vytvorit");
        }
        lokaceMistnosti = new HashMap<>();
    }
    HashMap<Integer, Integer[]> lokaceMistnosti;
    public void nacteniLokaci(){
        lokaceMistnosti.put(1,new Integer[]{787, 616});
        lokaceMistnosti.put(2,new Integer[]{1537, 616});
        lokaceMistnosti.put(3,new Integer[]{2287, 616});
        lokaceMistnosti.put(4,new Integer[]{3037, 616});
        lokaceMistnosti.put(5,new Integer[]{3787, 616});
        lokaceMistnosti.put(6,new Integer[]{2287, 1114});
        lokaceMistnosti.put(7,new Integer[]{3037, 1114});
        lokaceMistnosti.put(8,new Integer[]{3787, 1114});
        lokaceMistnosti.put(9,new Integer[]{787, 1612});
        lokaceMistnosti.put(10,new Integer[]{1537, 1612});
        lokaceMistnosti.put(11,new Integer[]{2287, 1612});
        lokaceMistnosti.put(12,new Integer[]{3037, 1612});
        lokaceMistnosti.put(13,new Integer[]{3787, 1612});
        lokaceMistnosti.put(14,new Integer[]{787, 2110});
        lokaceMistnosti.put(15,new Integer[]{1537, 2110});
        lokaceMistnosti.put(16,new Integer[]{2287, 2110});
        lokaceMistnosti.put(17,new Integer[]{3037, 2110});
        lokaceMistnosti.put(18,new Integer[]{3787, 2110});
    }
    public void postavitMistnost(TypMistnosti typ, int pozice, JButton b) {
        try {

            switch (typ) {
                case LEKARNA:
                        Lekarna l = new Lekarna("Lékárna", 200, 100, 1, 1);
                        lekarny.add(l);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/lekarnaMistnost.png", 747,498);
                        break;

                case TRENINKOVA_HALA:
                        TreninkovaHala h = new TreninkovaHala("Tréninková hala", 300, 200, 1, 1);
                        treninkoveHaly.add(h);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/treninkovaMistnost.png", 747,498);
                        break;

                case SKLAD_PENEZ:
                        SkladPenez s = new SkladPenez("Sklad Peněz", 200, 300, 1, 1);
                        skladyPenez.add(s);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/skladPenez.png", 747,498);
                        break;

                case SKLAD_JIDLA:
                        SkladJidla sklad = new SkladJidla("Sklad Jídla", 150, 200, 1, 1);
                        skladyJidla.add(sklad);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/skladJidla.png", 747,498);
                        break;


            }
            b.setLocation(lokaceMistnosti.get(pozice)[0],lokaceMistnosti.get(pozice)[1]);
            okno.add(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Kasarna getKasarna() {
        return kasarna;
    }

    public ArrayList<Lekarna> getLekarny() {
        return lekarny;
    }

    public ArrayList<TreninkovaHala> getTreninkoveHaly() {
        return treninkoveHaly;
    }

    public ArrayList<SkladPenez> getSkladyPenez() {
        return skladyPenez;
    }

    public ArrayList<SkladJidla> getSkladyJidla() {
        return skladyJidla;
    }

    public JPanel getOkno() {
        return okno;
    }

    public HashMap<Integer, Integer[]> getLokaceMistnosti() {
        return lokaceMistnosti;
    }
}
