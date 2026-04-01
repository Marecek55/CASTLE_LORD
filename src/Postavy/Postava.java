package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

public abstract class Postava {
    protected String jmeno;
    protected int zivoty;
    protected Zbran zbran;
    protected Brneni brneni;
    protected Medailon medailon;

    public Postava(String jmeno, int zivoty, Zbran zbran, Brneni brneni, Medailon medailon) {
        this.jmeno = jmeno;
        this.zivoty = zivoty;
        this.zbran = zbran;
        this.brneni = brneni;
        this.medailon = medailon;
    }

    public abstract int utok();
    public abstract int obrana();
}
