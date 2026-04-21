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
        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Hrdina 1", 1));//TODO
        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Hrdina 2", 1));//TODO
        hracuvTym.add(TvorbaPostav.tvorbaHracovaBojovnika("Hrdina 3", 1));//TODO
        nepratelskyTym.add(TvorbaPostav.tvorbaGoblina(1));
        nepratelskyTym.add(TvorbaPostav.tvorbaGoblina(1));
        nepratelskyTym.add(TvorbaPostav.tvorbaGoblina(1));

        maxHPTymu = 0;
        for (Postava postava : hracuvTym) {
            maxHPTymu = maxHPTymu + postava.getZivoty();
        }
        for (Postava postava : nepratelskyTym) {
            maxHPnepratelskehoTymu = maxHPnepratelskehoTymu + postava.getZivoty();
        }

        arenaPanel = new PanelBitvy("/Obrazky/PozadiBoje.png", this);
        this.okno.add(arenaPanel);
        casovac = new Timer(30, e -> {
            arenaPanel.repaint();
        });
        casovac.start();
        okno.setVisible(true);
    }

    public void vykresliPostavyATexty(Graphics g) {
        Graphics2D grafika = (Graphics2D) g;
        grafika.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int sirkaMonitoru = arenaPanel.getWidth();
        int vyskaMonitoru = arenaPanel.getHeight();

        int sirkaPostavy = (int)(sirkaMonitoru * 0.15);
        int vyskaPostavy = (int)(vyskaMonitoru * 0.30);
        int barSirka = (int)(sirkaMonitoru * 0.30);
        int barVyska = (int)(vyskaMonitoru * 0.04);

        Font fontJmeno = new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.012));
        int YPostav = (int)(vyskaMonitoru * 0.35);

        int XHrdiny = (sirkaMonitoru / 2) - sirkaPostavy - (int)(sirkaMonitoru * 0.02);

        for (Postava bojovnik : hracuvTym) {

            if (bojovnik.isUtoci()) {
                grafika.drawImage(bojovnik.getObrazekVUtoku(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            } else {
                grafika.drawImage(bojovnik.getObrazekVKlidu(), XHrdiny, YPostav, sirkaPostavy, vyskaPostavy, null);
            }
            if (bojovnik.getPosledniZasah() != null){
                grafika.setColor(Color.RED);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.015)));
                grafika.drawString(bojovnik.getPosledniZasah(), XHrdiny, YPostav - 15);
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(bojovnik.getJmeno(), XHrdiny, YPostav + vyskaPostavy + 30);


            XHrdiny = XHrdiny - sirkaPostavy - (int)(sirkaMonitoru * 0.01);
        }


        int XNepratel = (sirkaMonitoru / 2) + (int)(sirkaMonitoru * 0.02);

        for (Postava nepritel : nepratelskyTym) {
            if (nepritel.isUtoci()) {
                grafika.drawImage(nepritel.getObrazekVUtoku(), XNepratel, YPostav, sirkaPostavy, vyskaPostavy, null);
            } else {
                grafika.drawImage(nepritel.getObrazekVKlidu(), XNepratel, YPostav, sirkaPostavy, vyskaPostavy, null);
            }

            if (nepritel.getPosledniZasah()!= null){
                grafika.setColor(Color.GREEN);
                grafika.setFont(new Font("Arial", Font.BOLD, (int)(sirkaMonitoru * 0.015)));
                grafika.drawString(nepritel.getPosledniZasah(), XNepratel, YPostav - 15);
            }

            grafika.setColor(Color.WHITE);
            grafika.setFont(fontJmeno);
            grafika.drawString(nepritel.getJmeno(), XNepratel, YPostav + vyskaPostavy + 30);


            XNepratel = XNepratel + sirkaPostavy + (int)(sirkaMonitoru * 0.01);
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
        grafika.setColor(Color.GREEN);
        grafika.fillRect(hracBarX, barY, (int)(barSirka * pomerZivotuHrac), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(hracBarX, barY, barSirka, barVyska);
        grafika.setFont(fontJmeno);
        grafika.drawString(aktualniHPTymu + " / " + maxHPTymu, hracBarX + 10, barY + barVyska - 10);

        float pomerZivotuNepritel = (float)aktualniHPNepratelskyTymu / maxHPnepratelskehoTymu;
        int nepritelBarX = sirkaMonitoru - (int)(sirkaMonitoru * 0.1) - barSirka;

        grafika.setColor(new Color(50, 50, 50));
        grafika.fillRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.setColor(Color.RED);
        grafika.fillRect(nepritelBarX, barY, (int)(barSirka * pomerZivotuNepritel), barVyska);
        grafika.setColor(Color.WHITE);
        grafika.drawRect(nepritelBarX, barY, barSirka, barVyska);
        grafika.drawString(aktualniHPNepratelskyTymu + " / " + maxHPnepratelskehoTymu, nepritelBarX + 10, barY + barVyska - 10);

    }

    @Override
    public void funkcnost() {
        JButton tlacitkoStart = new JButton("START!");
        tlacitkoStart.setLocation(10, 10);
        tlacitkoStart.setSize(300,60);

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