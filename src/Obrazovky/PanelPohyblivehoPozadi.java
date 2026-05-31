package Obrazovky;

import Hrad.TypMistnosti;
import Logika.Hra;
import Postavy.Postava;

import java.awt.*;
import java.util.HashMap;

/**
 * Tato trida vytvari pohyblive pozadi po hradu s pohybem mysi a oddaleni
 * Matematicke posuny kamery jsem delal za pomoci internetu
 */
public class PanelPohyblivehoPozadi extends PanelNaPozadi {
    private int xKamery =0;
    private int yKamery= 0;
    private double meritko =1.0;
    private ObrazovkaHradu obrazovka;

    private Image kasarna=nactiObrazek("/Obrazky/ObrazkyVHradu/kasarnaMistnost.png");
    private Image lekarna= nactiObrazek("/Obrazky/ObrazkyVHradu/lekarnaMistnost.png");
    private Image trenink = nactiObrazek("/Obrazky/ObrazkyVHradu/treninkovaMistnost.png");
    private Image skladPenez=nactiObrazek("/Obrazky/ObrazkyVHradu/skladPenez.png");
    private Image skladJidla= nactiObrazek("/Obrazky/ObrazkyVHradu/skladJidla.png");
    private Image mec=nactiObrazek("/Obrazky/ObrazkyPostav/bojovnikMecKlidny.png");
    private Image luk = nactiObrazek("/Obrazky/ObrazkyPostav/bojovnikLukKlidny.png");
    private Image mag= nactiObrazek("/Obrazky/ObrazkyPostav/bojovnikMagKlidny.png");


    /**
     * Tato metoda spousti pohyblive pozadi
     * @param nazevObrazku nazev pozadi
     * @param obrazovka obrazovka hradu
     */
    public PanelPohyblivehoPozadi(String nazevObrazku,ObrazovkaHradu obrazovka) {
        super(nazevObrazku);
        this.obrazovka =obrazovka;
        setPohyblivePozadi(true);
        setLayout(null);
    }

    public int getXKamery() {
        return xKamery;
    }
    public int getYKamery() {
        return yKamery;
    }
    public double getMeritko() {
        return meritko;
    }

    /**
     * Tato metoda vypocita pozici kamery a ulozi aktualni sirka a vysku obrazu
     * a pohlida jestli hrac nevyjel za obrazovku a to ohranici
     * @param x lokace x
     * @param y lokace y
     */
    public void posunKamerou(int x,int y) {
        int noveX= xKamery+x;
        int noveY=yKamery+y;
        int aktualniSirkaObrazku=(int)(bg.getWidth(null)*meritko);
        int aktualniVyskaObrazku = (int)(bg.getHeight(null)*meritko);

        if(noveX >0) noveX= 0;
        if(noveX < getWidth() -aktualniSirkaObrazku) noveX = getWidth()-aktualniSirkaObrazku;
        if(noveY> 0) noveY=0;
        if(noveY < getHeight()-aktualniVyskaObrazku) noveY=getHeight() -aktualniVyskaObrazku;

        xKamery=noveX;
        yKamery=noveY;
        repaint();
    }

    /**
     *Tato metoda zajistuje oddalovani obrazu podle smeru kolecka a hlida aby se nevyjelo za plochu
     * stare meritko urcuje aktualni zvetseni mapy minimalni meritko je minimalni hodnota pri ktere
     * je to jeste viditelne to stejne pro max a procento rozdilu je rozdil mezi novym a starym meritkem
     * aby se to priblizilo tam kde je mys
     * @param smerKolecka
     * @param xMysi
     * @param yMysi
     */
    public void zmenaOddaleni(int smerKolecka,int xMysi,int yMysi) {
        double stareMeritko= meritko;
        if (smerKolecka> 0) {
            meritko = meritko-0.05;
        } else {
            meritko= meritko+0.05;
        }
        double minimalniSirka=(double)getWidth() /bg.getWidth(null);
        double minimalniVyska=(double) getHeight()/bg.getHeight(null);
        double minimalniMeritko=Math.max(minimalniSirka,minimalniVyska);
        if (meritko< minimalniMeritko) {
            meritko=minimalniMeritko;
        }
        if (meritko>2.5) {
            meritko= 2.5;
        }
        double procentoRozdilu =meritko/stareMeritko;
        int noveX = (int)(xMysi-(xMysi-xKamery)* procentoRozdilu);
        int noveY = (int)(yMysi-(yMysi-yKamery)*procentoRozdilu);
        if (noveX>0) {
            noveX=0;
        }
        if (noveY> 0) {
            noveY=0;
        }
        if (noveX<getWidth()-(int)(bg.getWidth(null)*meritko)) {
            noveX= getWidth()-(int)(bg.getWidth(null)*meritko);
        }
        if (noveY<getHeight()-(int)(bg.getHeight(null)*meritko)) {
            noveY= getHeight()-(int)(bg.getHeight(null)*meritko);
        }
        xKamery=noveX;
        yKamery= noveY;
        repaint();
    }

    /**
     * Tato metoda vykresli mapu na pozici kam ji hrac posunul a rekne pomoci translate
     * mistnostem aby se hybaly stejne jako kamera jako hrad a vykresli mistnsoti ve velikosti meritka
     * a zepta se na jakych souradnicich jaka mistnost je a tu vykresli take vykresluje bojovniky
     * ktere ma hrac v tymu do kasarny
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        if (bg!=null) {
            g2.drawImage(bg,xKamery,yKamery,(int)(bg.getWidth(null)*meritko),(int)(bg.getHeight(null)*meritko),null);
        }
        if (obrazovka.getHrad()==null) return;

        Graphics2D gMapy=(Graphics2D) g2.create();
        gMapy.translate(xKamery,yKamery);
        gMapy.scale(meritko,meritko);
        if (kasarna!=null){
            gMapy.drawImage(kasarna,787,1114,1494,498,null);
            int xBojovnika= 1000;
            int yBojovnika= 1190;
            int sirkaBojovnika= (int)(1494*0.18);
            int vyskaBojovnika= sirkaBojovnika*612/408;

            for (Postava p: Hra.hracuvTym) {
                Image obrazek=null;
                switch (p.getTyp()) {
                    case "Bojovnik":obrazek= mec;
                        break;
                    case "Lukostrelec":obrazek =luk;
                        break;
                    case "Mag": obrazek= mag;
                        break;
                }
                gMapy.drawImage(obrazek,xBojovnika,yBojovnika,sirkaBojovnika,vyskaBojovnika,null);
                xBojovnika= xBojovnika+sirkaBojovnika+ 100;

            }
        }

        HashMap<Integer,Integer[]> lokace = obrazovka.getHrad().getLokaceMistnosti();
        HashMap<Integer,TypMistnosti> stavby = obrazovka.getHrad().getPostavene();

        for (int pozice : obrazovka.getHrad().getPozicePostavenych()) {
            int x = lokace.get(pozice)[0];
            int y= lokace.get(pozice)[1];
            TypMistnosti typ= stavby.get(pozice);
            Image obrazek= null;
            if (typ== TypMistnosti.LEKARNA) {
                obrazek= lekarna;
            } else if (typ== TypMistnosti.TRENINKOVA_HALA){
                obrazek= trenink;
            } else if (typ==TypMistnosti.SKLAD_PENEZ){
                obrazek= skladPenez;
            } else if (typ==TypMistnosti.SKLAD_JIDLA) {
                obrazek= skladJidla;
            }

            if (obrazek!= null) {
                gMapy.drawImage(obrazek,x,y,747,498,null);
            }
        }
        gMapy.dispose();
    }

    /**
     *Vytvori se kopie plochy pozadi a projde vsechny komponenty ktere maji jmeno
     * pevnych tlacitek ktere maji zustat n amiste tak se a vykresli se tam a
     * fixne tam zustane
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D pozadi= (Graphics2D) g.create();
        for (Component c: getComponents()) {
            if (c.getName()!=null && (c.getName().equals("pevneTlacitka") || c.getName().equals("pevneTlacitkaText"))) {
                Graphics2D gTlacitka=(Graphics2D) pozadi.create();
                gTlacitka.translate(c.getX(),c.getY());
                c.paint(gTlacitka);
                gTlacitka.dispose();
            }
        }
        pozadi.dispose();
    }

    /**
     *Tahle metoda dela to aby slo na pevne tlacitka kliknout i kdyz mapa odjela pryc
     * metoda veme souradnice kam hrac klikl a koukne se jestli tam je pevne tlacitko a kdyz ano tak ho zavola
     * @param x the <i>x</i> coordinate
     * @param y the <i>y</i> coordinate
     * @return
     */
    @Override
    public Component getComponentAt(int x,int y) {
        for (Component component: getComponents()) {
            if (component.getName()!=null && (component.getName().equals("pevneTlacitka") || component.getName().equals("pevneTlacitkaText"))) {
                if (component.getBounds().contains(x,y)) {
                    return component;
                }
            }
        }
        return this;
    }

    public Image getSkladPenez() {
        return skladPenez;
    }

    public void setSkladPenez(Image skladPenez) {
        this.skladPenez= skladPenez;
    }
}