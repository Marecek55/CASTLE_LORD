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

    public Postava(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon) {
        this.jmeno = jmeno;
        this.zbran = zbran;
        this.brneni = brneni;
        this.medailon = medailon;
    }

    public abstract int utok();
    public abstract int obrana(int utok);

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public Zbran getZbran() {
        return zbran;
    }

    public void setZbran(Zbran zbran) {
        this.zbran = zbran;
    }

    public Brneni getBrneni() {
        return brneni;
    }

    public void setBrneni(Brneni brneni) {
        this.brneni = brneni;
    }

    public Medailon getMedailon() {
        return medailon;
    }

    public void setMedailon(Medailon medailon) {
        this.medailon = medailon;
    }
}
