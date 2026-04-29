//package Obrazovky;
//import Logika.Hra;
//import Logika.PostupNacitani;
//import javax.swing.*;
//import java.awt.*;
//
//public class NacitaciObrazovka extends Obrazovka {
//
//    private JProgressBar progressBar;
//    private JLabel status;
//    private PanelNaPozadi panelPozadi;
//
//    public NacitaciObrazovka() {
//        super("Načítání hry...", false);
//        panelPozadi = new PanelNaPozadi("/Obrazky/pozadi_nacitani.png");
//        panelPozadi.setLayout(null);
//        inicializace();
//        funkcnost();
//        okno.setContentPane(panelPozadi);
//        okno.setVisible(true);
//    }
//
//    @Override
//    public void inicializace() {
//        int sirkaOkna = Hra.sirkaObrazovky;
//        int vyskaOkna = Hra.vyskaObrazovky;
//
//        progressBar = new JProgressBar(0, 100);
//        int sirkaBaru = (int) (sirkaOkna * 0.6);
//        int vyskaBaru = 30;
//        progressBar.setBounds((sirkaOkna - sirkaBaru) / 2, vyskaOkna - 100, sirkaBaru, vyskaBaru);
//
//        progressBar.setForeground(new Color(218, 165, 32));
//        progressBar.setBackground(new Color(0, 0, 0, 150));
//        progressBar.setBorderPainted(false);
//        progressBar.setStringPainted(true);
//
//
//        status = new JLabel("Nastavování hradu...", SwingConstants.CENTER);
//        status.setBounds(0, vyskaOkna - 140, sirkaOkna, 30);
//        status.setForeground(Color.WHITE);
//        status.setFont(new Font("Serif", Font.BOLD, 20));
//
//
//        panelPozadi.add(progressBar);
//        panelPozadi.add(status);
//    }
//
//    @Override
//    public void funkcnost() {
//
//        PostupNacitani worker = new PostupNacitani(status, progressBar, okno);
//        worker.execute();
//    }
//}