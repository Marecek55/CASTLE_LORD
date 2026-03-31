package Obrazovky.Tlacitka;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class StylTlacitek {
    public static void zmenitStylUvod(JButton b){
         b.setBackground(Color.black);
         b.setForeground(Color.white);
         b.setFont(new Font("Georgia", Font.BOLD, 20));
         b.setFocusPainted(false);
         b.setBorderPainted(false);
         nastavitZaoblene(b,20);

    }
    public static void nastavitZaoblene(JButton b, int kulatost) {
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setBorderPainted(false);

        b.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), kulatost, kulatost);

                g2.dispose();
                super.paint(g, c);
            }
        });
    }
}
