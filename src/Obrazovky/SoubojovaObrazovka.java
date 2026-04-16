package Obrazovky;

import Logika.Souboj;
import Postavy.Goblin;
import Postavy.Postava;
import Logika.TvorbaPostav;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;


public class SoubojovaObrazovka extends Obrazovka {
    private PanelNaPozadi arenaPanel;
    private ArrayList<Postava> hracuvTym;
    private ArrayList<Postava> nepratelskyTym;
    private ArrayList<LetajiciText> seznamLetajicichTextu;
    private Timer casovac;
    private int maxHPTymu;
    private int maxHPnepratelskehoTymu;

    public SoubojovaObrazovka(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        hracuvTym = new ArrayList<>();
        nepratelskyTym = new ArrayList<>();
    }

    @Override
    public void inicializace() {
        seznamLetajicichTextu = new ArrayList<>();
        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Hrdina 1", 1));
        nepratelskyTym.add(TvorbaPostav.tvorbaGoblina(1));

        maxHPTymu = 0;
        for (Postava postava : hracuvTym) {
            maxHPTymu = maxHPTymu + postava.getZivoty();
        }
        for (Postava postava : nepratelskyTym) {
            maxHPnepratelskehoTymu = maxHPnepratelskehoTymu + postava.getZivoty();
        }

        arenaPanel = new PanelBitvy("/Obrazky/PozadiBoje.png", this);
        arenaPanel.setLayout(null);
        this.okno.add(arenaPanel);

        casovac = new Timer(30, e -> {
            aktualizujAnimaceCisel();
            arenaPanel.repaint();
        });
        casovac.start();
        okno.setVisible(true);
    }


    public void kresleniZasahu(int poskozeni, boolean jeToHrac) {
        int sirka = arenaPanel.getWidth();
        int vyska = arenaPanel.getHeight();
        int poziceX;
        Color barvaTextu;

        if (jeToHrac == true) {
            poziceX = (int)(sirka * 0.25);
            barvaTextu = Color.RED;
        } else {
            poziceX = (int)(sirka * 0.75);
            barvaTextu = Color.GREEN;
        }

        int poziceY = (int)(vyska * 0.5);

        String text = "-" + poskozeni + " HP";
        LetajiciText letajici = new LetajiciText(poziceX, poziceY, text, barvaTextu);
        seznamLetajicichTextu.add(letajici);
    }

    private void aktualizujAnimaceCisel() {
        for (int i = 0; i < seznamLetajicichTextu.size(); i = i + 1) {
            LetajiciText text = seznamLetajicichTextu.get(i);

            text.poziceY = text.poziceY - 2;
            text.zbyvajiciCasZobrazeni = text.zbyvajiciCasZobrazeni - 1;

            if (text.zbyvajiciCasZobrazeni <= 0) {
                seznamLetajicichTextu.remove(i);
                i = i - 1;
            }
        }
    }

    public void vykresliPostavyATexty(Graphics g) {
        Graphics2D grafika = (Graphics2D) g;
        grafika.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int sirkaMonitoru = arenaPanel.getWidth();
        int vyskaMonitoru = arenaPanel.getHeight();

        int sirkaPostavy = (int)(sirkaMonitoru * 0.15);
        int vyskaPostavy = sirkaPostavy;
        int barSirka = (int)(sirkaMonitoru * 0.30);
        int barVyska = (int)(vyskaMonitoru * 0.03);

        Font fontJmeno = new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.012));
        int YPostav = (int)(vyskaMonitoru * 0.35);

        int XHrdiny = (int)(sirkaMonitoru * 0.15);
        for (Postava bojovnik : hracuvTym) {
            grafika.setColor(Color.BLACK);
            grafika.fillRect(XHrdiny, YPostav, sirkaPostavy, vyskaPostavy);

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(bojovnik.getJmeno(), XHrdiny, YPostav + vyskaPostavy + 30);

            XHrdiny = XHrdiny + sirkaPostavy + (int)(sirkaMonitoru * 0.05);
        }

        int XNepratel = sirkaMonitoru - (int)(sirkaMonitoru * 0.15) - sirkaPostavy;
        for (Postava nepritel : nepratelskyTym) {
            grafika.setColor(Color.BLUE);
            grafika.fillRect(XNepratel, YPostav, sirkaPostavy, vyskaPostavy);

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(nepritel.getJmeno(), XNepratel, YPostav + vyskaPostavy + 30);

            XNepratel = XNepratel - (sirkaPostavy + (int)(sirkaMonitoru * 0.05));
        }

        int aktualniHPTymu = 0;
        int aktualniHPNepratelskyTymu = 0;

        for (Postava p : hracuvTym) {
            aktualniHPTymu = aktualniHPTymu + p.getZivoty();
        }
        for (Postava p : nepratelskyTym) {
            aktualniHPNepratelskyTymu = aktualniHPNepratelskyTymu + p.getZivoty();
        }

        float pomerZivotuHrac = (float)aktualniHPTymu / maxHPTymu;
        int hracBarX = (int)(sirkaMonitoru * 0.1);
        int barY = (int)(vyskaMonitoru * 0.1);

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(hracBarX, barY, barSirka, barVyska);
        grafika.setColor(new Color(0, 200, 0));
        grafika.fillRect(hracBarX, barY, (int)(barSirka * pomerZivotuHrac), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(hracBarX, barY, barSirka, barVyska);
        grafika.setFont(fontJmeno);
        grafika.drawString(aktualniHPTymu + " / " + maxHPTymu, hracBarX + 10, barY + barVyska - 10);

        float pomerZivotuNepritel = (float)aktualniHPNepratelskyTymu / maxHPnepratelskehoTymu;
        int nepritelBarX = sirkaMonitoru - (int)(sirkaMonitoru * 0.1) - barSirka;

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.setColor(new Color(200, 0, 0));
        grafika.fillRect(nepritelBarX, barY, (int)(barSirka * pomerZivotuNepritel), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.drawString(aktualniHPNepratelskyTymu + " / " + maxHPnepratelskehoTymu, nepritelBarX + 10, barY + barVyska - 10);

        for (int j = 0; j < seznamLetajicichTextu.size(); j = j + 1) {
            LetajiciText text = seznamLetajicichTextu.get(j);
            float pruhlednost = text.zbyvajiciCasZobrazeni / 40.0f;

            if (pruhlednost < 0) {
                pruhlednost = 0;
            }
            if (pruhlednost > 1) {
                pruhlednost = 1;
            }

            Color c = text.barvaTextu;
            grafika.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(pruhlednost * 255)));
            grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.02)));
            grafika.drawString(text.zobrazovanyText, text.poziceX, text.poziceY);
        }
    }

    @Override
    public void funkcnost() {
        JButton tlacitkoStart = new JButton("ZAČÍT BITVU!");
        tlacitkoStart.setBounds(10, 10, 300, 60);

        tlacitkoStart.setBackground(Color.YELLOW);
        tlacitkoStart.setOpaque(true);
        tlacitkoStart.setFont(new Font("Arial", Font.BOLD, 24));

        tlacitkoStart.addActionListener(e -> {
            tlacitkoStart.setVisible(false);
            Souboj arena = new Souboj(hracuvTym, nepratelskyTym, this);
            arena.startBitvy();
        });

        arenaPanel.add(tlacitkoStart);
        arenaPanel.repaint();
    }
}