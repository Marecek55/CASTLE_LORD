package Obrazovky;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLOutput;

/**
 * Tato trida kresli obrazek na pozadi
 */
public class PanelNaPozadi extends JPanel {

    protected Image bg;
    public int x = 0;
    public int y = 0;
    private boolean pohyblivePozadi = false;

    /**
     * Kdyz se nevykrelsli nastavi se barva na cernou
     * @param nazevObrazku
     */
    public PanelNaPozadi(String nazevObrazku) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        bg = nactiObrazek(nazevObrazku);

    }


    /**
     * Konstruktor kdyz nechceme obrazek
     */

    public PanelNaPozadi() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        bg = null;
    }

    public void setPohyblivePozadi(boolean pohyblivePozadi) {
        this.pohyblivePozadi = pohyblivePozadi;
    }

    public Image getImage() {
        return bg;
    }

    public void setBg(Image bg) {
        this.bg = bg;
    }

    /**
     * Tato metoda prekresluje obrazek a kresli ho na souradnice
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) {
            if (pohyblivePozadi) {
                g.drawImage(bg, x, y, this);
            } else {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    /**
     * Tato metoda ho nacita pomoci url z res
     * @param obrazek cesta k obrazku
     * @return
     */
    public Image nactiObrazek(String obrazek) {
        URL url = getClass().getResource(obrazek);
        if (url == null) {
            System.out.println(obrazek + " nejde najit.");
            return null;
        }

        return new ImageIcon(url).getImage();
    }
}