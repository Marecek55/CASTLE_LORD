package Obrazovky;

import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;
import java.awt.*;

public class NacitaciObrazovka extends Obrazovka {


    private StylTlacitek btnPokracovat;
    private StylTlacitek btnNovaHra;
    PanelNaPozadi nacitaciPanel;


    public NacitaciObrazovka(String nazev) {
        super(nazev, false);
        nacitaciPanel = new PanelNaPozadi("/Obrazky/pozadiNacitaciObrazovky.png");

    }

    @Override
    public void inicializace() {

        nacitaciPanel.setLayout(new GridBagLayout());

        this.okno.setContentPane(nacitaciPanel);


        this.okno.setVisible(true);
    }

    @Override
    public void funkcnost() {


        btnPokracovat.addActionListener(e -> {

            this.okno.dispose();


        });

        btnNovaHra.addActionListener(e -> {
            this.okno.dispose();
        });
    }
}