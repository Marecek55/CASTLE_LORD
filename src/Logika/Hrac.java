package Logika;

import Postavy.Bojovnik;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Trida hrac urcuje atributy hrace penez a urovne hradu a pracuje s xp
 */
public class Hrac implements Serializable {
    private int uroven;
    private int zlataky;
    private int jidlo;
    private int urovenGobliniStezky;
    private int xp;

    public Hrac(int uroven, int zlataky, int jidlo, int urovenGobliniStezky, int xp) {
        this.uroven = uroven;
        this.zlataky = zlataky;
        this.jidlo = jidlo;
        this.urovenGobliniStezky = urovenGobliniStezky;
        this.xp = xp;
    }

    /**
     * Tato metoda pridava xp a pridava uroven a nastavuje xp potrebne na dalsi level
     */
    public void pridejxp() {
        this.xp = this.xp + 50;
        int xpNaDalsi = this.uroven * 100;
        while (this.xp >= xpNaDalsi) {
            this.uroven++;
            this.xp = this.xp - xpNaDalsi;
            xpNaDalsi = this.uroven * 100;
        }
    }

    /**
     * Odebira xp po prohre
     */
    public void odeberxp(){
        if (xp>0){
            this.xp = xp - 25;
        }

    }

    public int getUroven() {
        return uroven;
    }

    public int getZlataky() {
        return zlataky;
    }

    public int getJidlo() {
        return jidlo;
    }

    public int getUrovenGobliniStezky() {
        return urovenGobliniStezky;
    }

    public int getXp() {
        return xp;
    }
}
