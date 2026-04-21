package Obrazovky.Tlacitka;

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
    public void nastavJakoObrazek(String cestaKObrazku, int sirka, int vyska) {
        try {
            java.net.URL url = getClass().getResource(cestaKObrazku);
            if (url != null) {
                java.awt.Image original = javax.imageio.ImageIO.read(url);
                java.awt.Image zmensenyObrazek = original.getScaledInstance(sirka, vyska, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new javax.swing.ImageIcon(zmensenyObrazek));
            } else {
                System.out.println(cestaKObrazku);
            }



            this.setBorderPainted(false);
            this.setFocusPainted(false);


            this.setContentAreaFilled(false);
            this.setOpaque(false);
            this.setPreferredSize(new java.awt.Dimension(sirka, vyska));

        } catch (Exception e) {
            System.out.println(  e.getMessage());
        }
    }

}