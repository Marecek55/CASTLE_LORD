package Obrazovky;

import Obrazovky.Tlacitka.StylTlacitek;

import javax.swing.*;
import java.awt.*;

public class NacitaciObrazovka extends Obrazovka {
    JButton btnPokracovat;
    JButton btnNovaHra;

    public NacitaciObrazovka(String nazev) {
        super(nazev, true);
        btnPokracovat = new JButton("Pokračovat");
        btnNovaHra = new JButton("Nová Hra");

    }
    @Override
    public void inicializace(){
        okno.setLayout(new GridBagLayout());
        okno.setBackground(Color.BLACK);
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setPreferredSize(new Dimension(400, 600));
        menuPanel.setLayout(new GridLayout(3, 1, 0, 20));
        JLabel nazevHry = new JLabel("CASTLE LORD", SwingConstants.CENTER);
        nazevHry.setFont(new Font("Georgia", Font.BOLD, 50));
        nazevHry.setForeground(Color.ORANGE);

        menuPanel.add(nazevHry);
        StylTlacitek.zmenitStylUvod(btnPokracovat);
        menuPanel.add(btnPokracovat);
        StylTlacitek.zmenitStylUvod(btnNovaHra);
        menuPanel.add(btnNovaHra);

        okno.add(menuPanel, new GridBagConstraints());

        okno.setVisible(true);
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
