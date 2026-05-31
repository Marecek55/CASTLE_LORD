package Logika;
import Obrazovky.SoubojovaObrazovka;
import Postavy.Postava;
import javax.swing.*;
import java.util.ArrayList;


/**
 * Trida Souboj vyhodnocuje vysledek a spousti bitvu
 */
public class Souboj {
    private ArrayList<Postava> tymHrace;
    private ArrayList<Postava> tymNepratel;
    private SoubojovaObrazovka obrazovka;
    private ArrayList<Timer> listCasovacu = new ArrayList<>();
    public Souboj(ArrayList<Postava> hrac, ArrayList<Postava> nepratele, SoubojovaObrazovka obrazovka) {
        this.tymHrace = hrac;
        this.tymNepratel = nepratele;
        this.obrazovka = obrazovka;
    }


    /**
     * Tato metoda vyhodnucuje silu utoku
     * a nastavuje posledni zasah hrace
     * @param utocici utocici postava
     * @param branici branici postava
     */
    public void vyhodnotRanu(Postava utocici, Postava branici){
        int utok = utocici.utok();
        int obrana = branici.obrana(utok);
        int utokZa = utok - obrana;

        if (utokZa >0){
            branici.setZivoty(branici.getZivoty() - utokZa);
            if (branici.getZivoty() < 0) {
                branici.setZivoty(0);
            }
            branici.setPosledniZasah("-" + utokZa + " HP");

        }
    }

    /**
     * Tato metoda kontroluje jestli postava zemrela
     * @param obrana postava ktera je kontrolovana
     * @param tym tym z ktereho je postava
     */
    public void smrtPostavy(Postava obrana , ArrayList<Postava> tym){
        if (obrana.getZivoty() <= 0) {
            zkontrolujKonecBitvy();
        }
    }

    /**
     * Kontroluje jestli vsichni z tymu zemreli podle toho kolik je zivych nepratel
     */
    private void zkontrolujKonecBitvy() {
        int zivi = 0;
        for (Postava p : tymHrace) {
            if (p.getZivoty() > 0) {
                zivi++;
            }
        }

        int ziviNepratele = 0;
        for (Postava p : tymNepratel) {
            if (p.getZivoty() > 0){
                ziviNepratele++;
            }
        }

        if (zivi == 0) {
            ukonciBitvu(false);
        } else if (ziviNepratele == 0) {
            ukonciBitvu(true);
        }
    }

    /**
     * Tato metoda ukoncuje casovac a predava informaci vyhry
     * @param vyhra informace vyhry
     */
    private void ukonciBitvu(boolean vyhra) {
        for (Timer t : listCasovacu) {
            t.stop();
        }
        obrazovka.konecBitvy(vyhra);
    }

    /**
     * Spusti casovac pro kazdou postavu s nahodnym intervalem a spusti ranu a zkontroluje jestli nezemrel
     * a nahodne vybira utok na jednoho z nepratel
     * Tuto metodu jsem vytvarel pomoci youtube videa na tema Timer
     * @param utocnik ten ktery utoci
     * @param jejiNepratele tym nepratel proti nemu
     */
    public void spousteniCasovace(Postava utocnik, ArrayList<Postava> jejiNepratele) {
        int rychlost = 1000 + Hra.rand.nextInt(500);

        Timer casovac = new Timer(rychlost, null);

        listCasovacu.add(casovac);
        casovac.addActionListener(e -> {

            if (utocnik.getZivoty() > 0) {
                ArrayList<Postava> ziviNepratel = new ArrayList<>();
                for (Postava p : jejiNepratele) {
                    if (p.getZivoty() > 0) ziviNepratel.add(p);
                }

                if (!ziviNepratel.isEmpty()) {
                    int nahodnyIndex = Hra.rand.nextInt(ziviNepratel.size());
                    Postava obrana = ziviNepratel.get(nahodnyIndex);
                    vyhodnotRanu(utocnik, obrana);
                    smrtPostavy(obrana, jejiNepratele);
                } else {
                    casovac.stop();
                }
            } else {
                casovac.stop();
            }
        });
        casovac.start();
    }

    /**
     * Startuje bitvu spustenim casovace pro kazdeho bojovnika
     */
    public void startBitvy() {

        for (Postava hrdina : tymHrace) {
            spousteniCasovace(hrdina, tymNepratel);
        }


        for (Postava nepritel : tymNepratel) {
            spousteniCasovace(nepritel, tymHrace);
        }
    }
    }
