package Logika;

import Postavy.Bojovnik;

import java.util.ArrayList;

public class Hrac {
    private ArrayList<Bojovnik> bojovnici;
    private int uroven;
    private int silaTymu;
    private int zlataky;
    private int jablka;
    private int urovenGobliniStezky;
    private int xp;

    public Hrac(int uroven, int silaTymu, int zlataky, int jablka, int urovenGobliniStezky, int xp) {
        this.bojovnici = new ArrayList<>();
        this.uroven = uroven;
        this.silaTymu = silaTymu;
        this.zlataky = zlataky;
        this.jablka = jablka;
        this.urovenGobliniStezky = urovenGobliniStezky;
        this.xp = xp;
    }
    public void pridatBojovnika(Bojovnik bojovnik) {
        this.bojovnici.add(bojovnik);
    }

    public void pridejxp(){
        this.xp = 50 + xp;
        if (xp>=50){
            uroven++;
            this.xp = xp-50;
        }
    }
    public void odeberxp(){
        if (xp>0){
            this.xp = xp - 25;
        }

    }




}
