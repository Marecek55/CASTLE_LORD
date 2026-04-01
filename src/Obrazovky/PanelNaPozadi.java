package Obrazovky;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelNaPozadi extends JPanel {

    private final Image bg;


    public PanelNaPozadi(String nazevObrazku) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        bg = nactiObrazek(nazevObrazku);
    }


    public PanelNaPozadi() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        bg = null;
    }

    public Image getImage() {
        return bg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private Image nactiObrazek(String obrazek) {
        URL url = getClass().getResource(obrazek);
        if (url == null) {
            System.out.println(obrazek + " nenalezen. Používám černé pozadí.");
            return null;
        }
        return new ImageIcon(url).getImage();
    }
}