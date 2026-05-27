package Postavy;

import Predmety.Zbrane.Zbran;
import Predmety.Zbroj.Brneni;
import Predmety.Zbroj.Medailon;

/**
 * Tato trida urcuje utok a obranu bojovnika a nastavuje mu zivoty a resi jeho trenink
 */
public class Bojovnik extends Postava{
    private int maxZivoty;
    private int pocetTreninku;
    private int bonusZTreninku;

    public Bojovnik(String jmeno, Zbran zbran, Brneni brneni, Medailon medailon, String nazevObrazkuVKlidu, String nazevObrazkuVUtoku, String typ, int uroven) {
        super(jmeno, zbran, brneni, medailon, nazevObrazkuVKlidu, nazevObrazkuVUtoku, typ);
        this.zivoty = uroven*100;
        this.maxZivoty = uroven*100;
    }

    /**
     * Tato metoda secte utok hrace a vraci hodnotu utoku
     * @return
     */
    @Override
    public int utok() {
        setUtoci(true);
        return zbran.zautoc() + bonusZTreninku ;

    }

    /**
     * Tato metoda vrati hodnotu utoku odectenou od obrany
     * @param utok utok
     * @return
     */
    @Override
    public int obrana(int utok) {
        setUtoci(false);
        if (brneni != null) {
            return brneni.kryt(utok);
        } else {
            return 0;
        }

    }

    /**
     * Tato metoda vraci silu postavy spolecne s bonusem z treninku a medailonu
     * @return
     */
    @Override
    public int getSilaPostavy() {
        int celkovaSila = super.getSilaPostavy();
        celkovaSila = celkovaSila + bonusZTreninku;
        if (this.medailon != null) {
            celkovaSila = celkovaSila + this.medailon.getZlepsovac();
        }

        return celkovaSila;
    }

    /**
     * Tato metoda resi trenink hrace a kontroluje max pocet treninku
     * @param bonus
     * @return
     */
    public boolean trenuje(int bonus){
        if (pocetTreninku<2) {
            bonusZTreninku = bonusZTreninku + bonus;
            pocetTreninku++;
            return true;
        }
        return false;

    }

    /**
     * Tato metoda resetuje trenink po boji
     */
    public void resetTreninkuPoBoji(){
        bonusZTreninku = 0;
        pocetTreninku= 0;
    }


    public int getMaxZivoty() {
        return maxZivoty;
    }

    public void setMaxZivoty(int maxZivoty) {
        this.maxZivoty = maxZivoty;
    }

}