package Hrad;

import java.util.ArrayList;

public class Hrad {


    private Kasarna kasarna;
    private Lekarna lekarna;
    private TreninkovaHala treninkovaHala;
    private SkladPenez skladPenez;
    private SkladJidla skladJidla;


    public Hrad()  {
        try {
            kasarna = new Kasarna("Kasárna", 0, 100, 1, 1);
        } catch (Exception e) {
            System.out.println("Kasarna nejde vytvorit");
        }
    }

    public void postavitLekarnu(){
        if (lekarna == null){
            try {
                lekarna = new Lekarna("Lékárna", 200, 100 ,1,1 );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            return;
        }
    }
    public void postavitTreninkovouHalu(){
        if (treninkovaHala == null){
            try {
                treninkovaHala = new TreninkovaHala("Tréninková hala", 300, 200 ,1,1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            return;
        }
    }
    public void postavitSkladPenez(){
        if (skladPenez == null){
            try {
                skladPenez = new SkladPenez("Sklad Peněz", 200, 300 ,1,1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            return;
        }
    }
    public void postavitSkladJidla(){
        if (skladJidla == null){
            try {
                skladJidla = new SkladJidla("Sklad Jídla", 150, 200 ,1,1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            return;
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
