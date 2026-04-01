package Obrazovky.Tlacitka;

import javax.swing.*;
import java.awt.*;


public class StylTlacitek extends JButton {

    public StylTlacitek(String text) {
        super(text);

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(new Font("Georgia", Font.BOLD, 20));
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

    public static void zmenitStylUvod(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Georgia", Font.BOLD, 20));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}