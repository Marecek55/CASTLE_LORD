package Predmety.Zbroj;

import Logika.Hra;
import Predmety.Predmet;
import Predmety.Rarita;


import java.util.ArrayList;

public abstract class Brneni extends Predmet {
    protected Rarita rarita;
    protected String jmeno;
    protected double sanceUltraKryt;
    protected int kryt;
    protected int ultraKryt;

    public Brneni(String jmeno ,Rarita rarita, int kryt, int ultraKryt) {
        super(jmeno);
        this.jmeno  = jmeno;
        this.rarita = rarita;
        this.kryt = kryt;
        this.ultraKryt = ultraKryt;
    }
    public static Brneni vytvoritBrneni(int uroven,  boolean jeZtruhly, Rarita raritaZtruhly){
        Rarita rarita = null;
        if (!jeZtruhly){
            ArrayList<Rarita> rarity = new ArrayList<>();
            rarity.add(Rarita.VZÁCNÁ);
            rarity.add(Rarita.BĚŽNÁ);
            rarity.add(Rarita.LEGENDÁRNÍ);
            int cislo = Hra.rand.nextInt(rarity.size());
            rarita = rarity.get(cislo);
        }else {
            rarita = raritaZtruhly;
        }

        int cislo2 = Hra.rand.nextInt(1,4);
        int provizorniKryt = 0;
        int provizorniUltraKryt = 0;
        switch (rarita) {
            case LEGENDÁRNÍ: provizorniKryt =Hra.rand.nextInt(2,7)*uroven;
                provizorniUltraKryt = Hra.rand.nextInt(8,12)*uroven;
                break;
            case BĚŽNÁ: provizorniKryt = uroven;
                provizorniUltraKryt = Hra.rand.nextInt(3,7)*uroven;
                break;
            case VZÁCNÁ: provizorniKryt = Hra.rand.nextInt(1,5)*uroven;
                provizorniUltraKryt = Hra.rand.nextInt(5,8)*uroven;
                break;
        }
        switch (cislo2) {
            case 1:
                Brneni z = new KozeneBrneni("Kožené Brnění",rarita, provizorniKryt, provizorniUltraKryt);
                return z;
            case 2:
                Brneni z2 = new OceloveBrneni("Ocelové Brnění",rarita, provizorniKryt, provizorniUltraKryt);
                return z2;
            case 3:
                Brneni z3 = new ListoveBrneni("Listové Brnění",rarita, provizorniKryt, provizorniUltraKryt);
                return z3;
            default:
                return null;
        }

    }
    public abstract String getNazevObrazku();
    public abstract int kryt(int utok);
    public Rarita getRarita() {
        return rarita;
    }

    public void setRarita(Rarita rarita) {
        this.rarita = rarita;
    }

    public double getSanceUltraKryt() {
        return sanceUltraKryt;
    }

    public void setSanceUltraKryt(double sanceUltraKryt) {
        this.sanceUltraKryt = sanceUltraKryt;
    }

    public int getKryt() {
        return kryt;
    }

    public void setKryt(int kryt) {
        this.kryt = kryt;
    }

    public int getUltraKryt() {
        return ultraKryt;
    }

    public void setUltraKryt(int ultraKryt) {
        this.ultraKryt = ultraKryt;
    }
}
