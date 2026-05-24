package Obrazovky;

import Logika.Hra;
import Predmety.Rarita;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IkonaVeciVInventari extends JButton {
    private Image pozadi;
    private Image obrazek;
    private String textSily = "";

    public IkonaVeciVInventari(String nazevObrazkuVeci, Rarita rarita, int sila) {
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        int velikost = (int) (Hra.sirkaObrazovky * 0.1);
        this.setPreferredSize(new Dimension(velikost, velikost));
        this.setOpaque(false);

        String cestaPozadi = rarita.getCesta();
        URL adresaPozadi = getClass().getResource(cestaPozadi);
        pozadi = new ImageIcon(adresaPozadi).getImage();

        String cestaVeci = "/obrazkyInventar/" + nazevObrazkuVeci;
        URL adresaVeci = getClass().getResource(cestaVeci);
        if (adresaVeci != null) {
            obrazek = new ImageIcon(adresaVeci).getImage();
        }

        if (sila == 0) {
            textSily = "";
        } else {
            textSily = String.valueOf(sila);
        }


    }
    public void deaktivujKlikani() {
        this.setEnabled(false);
        this.setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(pozadi, 0, 0, getWidth(), getHeight(), this);

        int vzddalenostOdKraje = (int) (getWidth() * 0.2);
        int sirkaObrazku = getWidth() - (2 * vzddalenostOdKraje);
        int vyskaObrazku = getHeight() - (2 * vzddalenostOdKraje);

        g2.drawImage(obrazek, vzddalenostOdKraje, vzddalenostOdKraje, sirkaObrazku, vyskaObrazku, this);


        if (!textSily.equals("")) {
            Font font = new Font("Segoe UI", Font.BOLD, (int) (Hra.vyskaObrazovky * 0.03));
            g2.setFont(font);
            Color barva = new Color(225, 210, 195);
            g2.setColor(barva);
            g2.drawString(textSily, (int)(getWidth() * 0.65), (int)(getHeight() * 0.83));
        }
    }
}