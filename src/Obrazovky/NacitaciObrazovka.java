package Obrazovky;

import Obrazovky.Tlacitka.StylTlacitek;
import javax.swing.*;
import java.awt.*;

public class NacitaciObrazovka extends Obrazovka {


    private StylTlacitek btnPokracovat;
    private StylTlacitek btnNovaHra;

    public NacitaciObrazovka(String nazev) {
        super(nazev, true);

        btnPokracovat = new StylTlacitek("Pokračovat");
        btnNovaHra = new StylTlacitek("Nová Hra");
    }

    @Override
    public void inicializace() {

        PanelNaPozadi bgPanel = new PanelNaPozadi();
        bgPanel.setLayout(new GridBagLayout());
        this.okno.setContentPane(bgPanel);


        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setPreferredSize(new Dimension(400, 600));
        menuPanel.setLayout(new GridLayout(3, 1, 0, 20));

        JLabel nazevHry = new JLabel("CASTLE LORD", SwingConstants.CENTER);
        nazevHry.setFont(new Font("Georgia", Font.BOLD, 50));
        nazevHry.setForeground(Color.ORANGE);
        menuPanel.add(nazevHry);


        menuPanel.add(btnPokracovat);
        menuPanel.add(btnNovaHra);


        bgPanel.add(menuPanel, new GridBagConstraints());

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