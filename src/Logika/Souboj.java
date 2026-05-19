package Logika;
import Obrazovky.PanelGobliniStezky;
import Obrazovky.SoubojovaObrazovka;
import Postavy.Bojovnik;
import Postavy.Postava;
import javax.swing.*;
import java.util.ArrayList;



public class Souboj {
    private ArrayList<Postava> tymHrace;
    private ArrayList<Postava> tymNepratel;
    private SoubojovaObrazovka obrazovka;
    public Souboj(ArrayList<Postava> hrac, ArrayList<Postava> nepratele, SoubojovaObrazovka obrazovka) {
        this.tymHrace = hrac;
        this.tymNepratel = nepratele;
        this.obrazovka = obrazovka;
    }



    public void vyhodnotRanu(Postava utocici, Postava branici){
        int utok = utocici.utok();
        int obrana = branici.obrana(utok);
        int utokZa = utok - obrana;

        if (utokZa >0){
            branici.setZivoty(branici.getZivoty() - utokZa);
            branici.setPosledniZasah("-" + utokZa + " HP");

        }
    }
    public void smrtPostavy(Postava obrana , ArrayList<Postava> tym){
        if (obrana.getZivoty() <= 0) {
            tym.remove(obrana);
            zkontrolujKonecBitvy();
        }
    }
    private void zkontrolujKonecBitvy() {

        if (tymHrace.isEmpty()) {
            ukonciBitvu(false);
        }
        else if (tymNepratel.isEmpty()) {
            ukonciBitvu(true);
        }
    }

    private void ukonciBitvu(boolean vyhra) {
        obrazovka.konecBitvy(vyhra);
    }
    public void spousteniCasovace(Postava utocnik, ArrayList<Postava> jejiNepratele) {
        int rychlost = 1000 + Hra.rand.nextInt(500);

        Timer casovac = new Timer(rychlost, null);
        casovac.addActionListener(e -> {

            if (utocnik.getZivoty() > 0 && !jejiNepratele.isEmpty()) {

                int nahodnyIndex = Hra.rand.nextInt(jejiNepratele.size());
                Postava obrana = jejiNepratele.get(nahodnyIndex);

                vyhodnotRanu(utocnik, obrana);
                smrtPostavy(obrana, jejiNepratele);

            } else {
                casovac.stop();
            }
        });
        casovac.start();
    }
    public void startBitvy() {

        for (Postava hrdina : tymHrace) {
            spousteniCasovace(hrdina, tymNepratel);
        }


        for (Postava nepritel : tymNepratel) {
            spousteniCasovace(nepritel, tymHrace);
        }
    }
    }
