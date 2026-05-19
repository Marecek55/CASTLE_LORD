package Obrazovky.Tlacitka;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class StylTlacitek extends JButton {

    public StylTlacitek(String text) {
        super(text);

    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();

        super.paintComponent(g);
    }

    public static void zmenitNaNeviditelneTlacitko(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setText(null);
    }
    public static void nastavJakoObrazek(JButton tlacitko, String cesta, int sirka, int vyska) {
        try {
            URL url = StylTlacitek.class.getResource(cesta);
            if (url != null) {
                Image original = ImageIO.read(url);
                Image zmenseny = original.getScaledInstance(sirka, vyska, Image.SCALE_SMOOTH);
                tlacitko.setIcon(new ImageIcon(zmenseny));
            } else {
                System.err.println("Chyba" + cesta);
            }
            tlacitko.setContentAreaFilled(false);
            tlacitko.setBorderPainted(false);
            tlacitko.setFocusPainted(false);
            tlacitko.setOpaque(false);
            tlacitko.setBorder(null);

            tlacitko.setText(null);
            tlacitko.setPreferredSize(new Dimension(sirka, vyska));
            tlacitko.setSize(sirka, vyska);

        } catch (Exception e) {
            System.out.println("Chyba" + cesta);
        }
    }
}