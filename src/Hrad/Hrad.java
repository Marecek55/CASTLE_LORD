package Hrad;

import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Hrad {


    private Kasarna kasarna;
    private Lekarna lekarna;
    private TreninkovaHala treninkovaHala;
    private SkladPenez skladPenez;
    private SkladJidla skladJidla;
    private JPanel okno;


    public Hrad(JPanel okno, JButton b)  {
        this.okno = okno;
        try {
            kasarna = new Kasarna("Kasárna", 0, 100, 1, 1);
            StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/kasarnaMistnost.png", 1495,497);
            b.setLocation(792,1120);
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
                    if (lekarna == null){
                        lekarna = new Lekarna("Lékárna", 200, 100, 1, 1);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/lekarnaMistnost.png", 747,498);
                        break;
                    }
                case TRENINKOVA_HALA:
                    if (treninkovaHala == null){
                        treninkovaHala = new TreninkovaHala("Tréninková hala", 300, 200, 1, 1);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/treninkovaMistnost.png", 747,498);
                        break;
                    }
                case SKLAD_PENEZ:
                    if (skladPenez == null){
                        skladPenez = new SkladPenez("Sklad Peněz", 200, 300, 1, 1);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/skladPenez.png", 747,498);
                        break;
                    }
                case SKLAD_JIDLA:
                    if (skladJidla == null){
                        skladJidla = new SkladJidla("Sklad Jídla", 150, 200, 1, 1);
                        StylTlacitek.nastavJakoObrazek(b,"/Obrazky/ObrazkyVHradu/skladJidla.png", 747,498);
                        break;
                    }

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

    public void setKasarna(Kasarna kasarna) {
        this.kasarna = kasarna;
    }

    public Lekarna getLekarna() {
        return lekarna;
    }

    public void setLekarna(Lekarna lekarna) {
        this.lekarna = lekarna;
    }

    public TreninkovaHala getTreninkovaHala() {
        return treninkovaHala;
    }

    public void setTreninkovaHala(TreninkovaHala treninkovaHala) {
        this.treninkovaHala = treninkovaHala;
    }

    public SkladPenez getSkladPenez() {
        return skladPenez;
    }

    public void setSkladPenez(SkladPenez skladPenez) {
        this.skladPenez = skladPenez;
    }

    public SkladJidla getSkladJidla() {
        return skladJidla;
    }

    public void setSkladJidla(SkladJidla skladJidla) {
        this.skladJidla = skladJidla;
    }
}
