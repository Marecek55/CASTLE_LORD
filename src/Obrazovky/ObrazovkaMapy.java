package Obrazovky;

import Logika.Hra;
import Obrazovky.Tlacitka.StylTlacitek;
import Postavy.Postava;

import javax.swing.*;

/**
 * ObrazovkaMpay vytvari pozadi mapy a dava funkcnost tlactikum
 */
public class ObrazovkaMapy extends Obrazovka{
    private PanelNaPozadi mapa;

    private JButton btnLes;
    private JButton btnArena;
    private JButton btnZpet;

    /**
     * Konstruktor nacita a inicializuje promene
     * @param nazev nazev obrazovky
     * @param malaObrazovka jestli ma byt mala
     */
    public ObrazovkaMapy(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        mapa = new PanelNaPozadi("/Obrazky/ObrazkyBoje/bojovaMapa.png");
        mapa.setLayout(null);
        btnLes = new JButton();
        btnArena = new JButton();
        btnZpet = new JButton();
        okno.setContentPane(mapa);

        inicializace();
        funkcnost();
        okno.setVisible(true);

    }
    int vyska = Hra.vyskaObrazovky;
    int sirka = Hra.sirkaObrazovky;
    int sirkaTlacitek = (int) (sirka * 0.3);
    int vyskaTlacitek = (int) (sirkaTlacitek * (371.0 / 673.0));
    int yTlacitek = (int) (vyska * 0.7);

    /**
     * V inicializaci se nacitaji tlacitka a jejich souradnice
     */
    @Override
    public void inicializace() {

        StylTlacitek.nastavJakoObrazek(btnArena, "/Obrazky/ObrazkyBoje/tlacitkoArena.png", sirkaTlacitek, vyskaTlacitek);
        StylTlacitek.nastavJakoObrazek(btnLes, "/Obrazky/ObrazkyBoje/tlacitkoLesGoblinu.png", sirkaTlacitek, vyskaTlacitek);
        btnArena.setLocation((int) (sirka * 0.70), yTlacitek);
        btnLes.setLocation((int) (sirka * 0.05), yTlacitek);
        mapa.add(btnArena);
        mapa.add(btnLes);

        int sirkaTlacitka = (int) (sirka * 0.2);
        int vzdalenostOdKraje = (int) (sirka * 0.009);

        StylTlacitek.nastavJakoObrazek(btnZpet, "/Obrazky/ObrazkyNaNacitaciObrazovce/tlacitkoZpet.png", sirkaTlacitka, (int) (sirkaTlacitka * (368.0 / 679.0)));
        btnZpet.setLocation((int) (sirka - (sirkaTlacitka * 0.92)),-vzdalenostOdKraje);
        mapa.add(btnZpet);


    }
    public PanelNaPozadi getMapa() {
        return this.mapa;
    }

    /**
     * V teto metode se spousti funkcnost jednotlivych tlacitek
     *
     */
    @Override
    public void funkcnost() {
         btnLes.addActionListener(e -> {
             PanelGobliniStezky panelMapy = new PanelGobliniStezky("/Obrazky/ObrazkyBoje/gobliniStezka.png", okno, this);
             panelMapy.setLayout(null);
             okno.setContentPane(panelMapy);
             okno.revalidate();
             okno.repaint();

         });
        btnArena.addActionListener(e -> {
            int zivoty = 0;
            for (Postava hrdina : Hra.hracuvTym) {
                zivoty = zivoty + hrdina.getZivoty();
            }
            if (zivoty <= 0) {
                JOptionPane.showMessageDialog(okno, "Nemůžeš bojovat! Tvoji hrdinové jsou mrtví.");
                return;
            }
            PanelArena panelAreny = new PanelArena("/Obrazky/ObrazkyBoje/arena.png", okno, this);
            panelAreny.setLayout(null);
            okno.setContentPane(panelAreny);
            okno.revalidate();
            okno.repaint();
        });
        btnZpet.addActionListener(e -> {
            if (Hra.obrazovkaHradu != null) {
                Hra.obrazovkaHradu.getOkno().setVisible(true);
                this.okno.setVisible(false);
            }
        });
    }
}
