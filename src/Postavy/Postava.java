package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Tato trida je abstraktni trida postava ktera resi silu a obrazek bojovnika
 */
public abstract class Postava implements Serializable {
    protected String jmeno;
    protected int zivoty;
    protected Zbran zbran;
    protected Brneni brneni;
    protected Medailon medailon;
    protected String posledniZasah;
    protected transient Image obrazekVKlidu;
    protected transient Image obrazekVUtoku;
    private boolean utoci = false;
    protected String typ;
    protected String cestaVKlidu;
    protected String cestaVUtoku;

    public Postava(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, String nazevObrazkuVKlidu, String nazevObrazkuVUtoku, String typ) {
        this.jmeno = jmeno;
        this.zbran = zbran;
        this.brneni = brneni;
        this.medailon = medailon;
        this.cestaVKlidu = nazevObrazkuVKlidu;
        this.cestaVUtoku = nazevObrazkuVUtoku;
        this.obrazekVKlidu = new ImageIcon(getClass().getResource(nazevObrazkuVKlidu)).getImage();
        this.obrazekVUtoku = new ImageIcon(getClass().getResource(nazevObrazkuVUtoku)).getImage();
        this.typ = typ;
        obnoveni();
    }
    public String getTyp() {
        return typ;
    }

    /**
     * Tato metoda obnovoje obrazek po nacteni hry
     */
    public void obnoveni() {
        if (cestaVKlidu != null) {
            this.obrazekVKlidu = new ImageIcon(getClass().getResource(cestaVKlidu)).getImage();
        }
        if (cestaVUtoku != null) {
            this.obrazekVUtoku = new ImageIcon(getClass().getResource(cestaVUtoku)).getImage();
        }
    }

    /**
     * Tato metoda vraci silu postavy podle zbrane
     * @return
     */
    public int getSilaPostavy() {
        int celkovaSila = 0;

        if (this.zbran != null) {
            celkovaSila =celkovaSila+ this.zbran.getSila();
        }

        if (this.brneni != null) {
            celkovaSila = celkovaSila+ this.brneni.getKryt();
        }

        if (this.medailon != null) {
            celkovaSila =celkovaSila+ this.medailon.getZlepsovac();
        }

        return celkovaSila;
    }


    public boolean isUtoci() {
        return utoci;
    }

    public void setUtoci(boolean utoci) {
        this.utoci = utoci;
    }

    public abstract int utok();
    public abstract int obrana(int utok);

    public String getPosledniZasah() {
        return posledniZasah;
    }

    public void setPosledniZasah(String posledniZasah) {
        this.posledniZasah = posledniZasah;
    }

    public Image getObrazekVKlidu() {
        return obrazekVKlidu;
    }

    public void setObrazekVKlidu(Image obrazekVKlidu) {
        this.obrazekVKlidu = obrazekVKlidu;
    }

    public Image getObrazekVUtoku() {
        return obrazekVUtoku;
    }

    public void setObrazekVUtoku(Image obrazekVUtoku) {
        this.obrazekVUtoku = obrazekVUtoku;
    }

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
