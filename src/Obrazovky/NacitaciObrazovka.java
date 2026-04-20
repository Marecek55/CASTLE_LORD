package Obrazovky;

import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;
import java.awt.*;

public class NacitaciObrazovka extends Obrazovka {


    private StylTlacitek btnPokracovat;
    private StylTlacitek btnNovaHra;
    PanelNaPozadi  nacitaciPanel;


    public NacitaciObrazovka(String nazev) {
        super(nazev, true);
        nacitaciPanel = new PanelNaPozadi("Obrazky/pozadiNacitacihoPanelu.jpg");
        btnPokracovat = new StylTlacitek("Pokračovat");
        btnNovaHra = new StylTlacitek("Nová Hra");
        StylTlacitek.zmenitStylUvod(btnPokracovat);
        StylTlacitek.zmenitStylUvod(btnNovaHra);
    }

    @Override
    public void inicializace() {

        nacitaciPanel.setLayout(new GridBagLayout());

        this.okno.setContentPane(nacitaciPanel);


        nacitaciPanel.setOpaque(false);
        nacitaciPanel.setPreferredSize(new Dimension(400, 600));
        nacitaciPanel.setLayout(new GridLayout(3, 1, 0, 20));

        JLabel nazevHry = new JLabel("CASTLE LORD", SwingConstants.CENTER);
        nazevHry.setFont(new Font("Georgia", Font.BOLD, 50));
        nazevHry.setForeground(Color.ORANGE);
        nacitaciPanel.add(nazevHry);


        nacitaciPanel.add(btnPokracovat);
        nacitaciPanel.add(btnNovaHra);

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