package Hrad;

import Logika.Hra;
import Postavy.Bojovnik;
import Postavy.Postava;
import Predmety.Penize;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Trida hrad nacita souradnice lokaci stavi se v ni mistnosti a resi se v ni kliknuti a dej v mistnostech
 */

public class Hrad implements Serializable {

    private Kasarna kasarna;
    private ArrayList<Lekarna> lekarny = new ArrayList<>();
    private ArrayList<TreninkovaHala> treninkoveHaly = new ArrayList<>();
    private ArrayList<SkladPenez> skladyPenez = new ArrayList<>();
    private ArrayList<SkladJidla> skladyJidla = new ArrayList<>();
    private ArrayList<Integer> pozicePostavenych = new ArrayList<>();
    private transient JPanel okno;
    private HashMap<Integer, Integer[]> lokaceMistnosti;
    private HashMap<Integer, TypMistnosti> postavene = new HashMap<>();

    public Hrad(JPanel okno) {
        this.okno = okno;
        try {
            kasarna = new Kasarna("Kasárna", 0, 100, 1, 1);
        } catch (Exception e) {
            System.out.println("Kasarna nejde vytvorit");
        }
        lokaceMistnosti = new HashMap<>();
        nacteniLokaci();
    }

    /**
     * Tato metoda nacita do HashMapy lokace jednotlivych mistnosti a dava jim cislo
     */

    public void nacteniLokaci() {
        lokaceMistnosti.put(1, new Integer[]{787, 616});
        lokaceMistnosti.put(2, new Integer[]{1537, 616});
        lokaceMistnosti.put(3, new Integer[]{2287, 616});
        lokaceMistnosti.put(4, new Integer[]{3037, 616});
        lokaceMistnosti.put(5, new Integer[]{3787, 616});
        lokaceMistnosti.put(6, new Integer[]{2287, 1114});
        lokaceMistnosti.put(7, new Integer[]{3037, 1114});
        lokaceMistnosti.put(8, new Integer[]{3787, 1114});
        lokaceMistnosti.put(9, new Integer[]{787, 1612});
        lokaceMistnosti.put(10, new Integer[]{1537, 1612});
        lokaceMistnosti.put(11, new Integer[]{2287, 1612});
        lokaceMistnosti.put(12, new Integer[]{3037, 1612});
        lokaceMistnosti.put(13, new Integer[]{3787, 1612});
        lokaceMistnosti.put(14, new Integer[]{787, 2110});
        lokaceMistnosti.put(15, new Integer[]{1537, 2110});
        lokaceMistnosti.put(16, new Integer[]{2287, 2110});
        lokaceMistnosti.put(17, new Integer[]{3037, 2110});
        lokaceMistnosti.put(18, new Integer[]{3787, 2110});
    }

    /**
     * Tato metoda stavi mistnost podle vyberu a pridava ji do listu mistnosti a prida se do jeji cislo postaveni do listu pozicePostavenych
     * aby se vedela jaka je obsazena a prida se samotna mistnost do HashMapy postavene kde pak hrac interaguje s postavenymi mistnostmi
     *
     * @param typ    typ mistnosti
     * @param pozice pozice na jake je mistnost postavena
     */

    public void postavitMistnost(TypMistnosti typ, int pozice) {
        try {
            if (pozicePostavenych.contains(pozice)) {
                JOptionPane.showMessageDialog(okno, "Tahle pozice je obsazená jinou budovou!");
            } else {
                switch (typ) {
                    case LEKARNA:
                        Lekarna l = new Lekarna("Lékárna", 1000, 1000, 1, 1);
                        lekarny.add(l);
                        break;
                    case TRENINKOVA_HALA:
                        TreninkovaHala h = new TreninkovaHala("Tréninková hala", 1500, 800, 1, 1);
                        treninkoveHaly.add(h);
                        break;
                    case SKLAD_PENEZ:
                        SkladPenez s = new SkladPenez("Sklad Peněz", 2000, 1500, 1, 1);
                        skladyPenez.add(s);
                        break;
                    case SKLAD_JIDLA:
                        SkladJidla sklad = new SkladJidla("Sklad Jídla", 3000, 2000, 1, 1);
                        skladyJidla.add(sklad);
                        break;
                }
                pozicePostavenych.add(pozice);
                postavene.put(pozice, typ);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Tato metoda kontroluje na jake sourednice kliknul uzivatel a z toho se zapne prislusna metoda
     *
     * @param xKurzor x kurzuru mysi
     * @param yKurzor y kurzuru mysi
     */


    public void kliknutiNaSouradnice(int xKurzor, int yKurzor) {
        if (xKurzor >= 787 && xKurzor <= (787 + 1494) && yKurzor >= 1114 && yKurzor <= (1114 + 498)) {
            kliknutiKasarna();
            return;
        }

        for (int pozice : pozicePostavenych) {
            int x = lokaceMistnosti.get(pozice)[0];
            int y = lokaceMistnosti.get(pozice)[1];
            if (xKurzor >= x && xKurzor <= (x + 747) && yKurzor >= y && yKurzor <= (y + 498)) {
                TypMistnosti typ = postavene.get(pozice);
                if (typ == TypMistnosti.LEKARNA && !lekarny.isEmpty()) {
                    kliknutiLekarna(lekarny.get(0));
                } else if (typ == TypMistnosti.TRENINKOVA_HALA && !treninkoveHaly.isEmpty()) {
                    kliknutiTrenink(treninkoveHaly.get(0));
                }
                break;
            }
        }
    }

    /**
     * Tato metoda vyhodnocuje co se stane po kliknuti na kasarnu
     */

    private void kliknutiKasarna() {
        if (Hra.hrac.getUroven() < 2) {
            JOptionPane.showMessageDialog(okno, "Kasárna je zamčená Musíš mít alespoň úroveň 2.");
        } else if (Hra.hracuvTym.size() >= 3) {
            JOptionPane.showMessageDialog(okno, "Máš plný tým Můžeš mít maximálně 3 bojovníky.");
        } else {
            int cenaVojaka = 500;
            int volba = JOptionPane.showConfirmDialog(okno, "Chceš koupit nového bojovníka? \nCena: " + cenaVojaka + " zlaťáků", "Koupení", JOptionPane.YES_NO_OPTION);
            if (volba == JOptionPane.YES_OPTION) {
                if (Penize.getPocet() >= cenaVojaka) {
                    String jmeno = JOptionPane.showInputDialog(okno, "Zadej jméno pro nového bojovníka:");
                    if (jmeno != null) {
                        Bojovnik b = Logika.TvorbaPostav.tvorbaHracovaBojovnika(jmeno.toUpperCase(), Hra.hrac.getUroven(), new Predmety.Zbrane.Mec("Meč", 5, 10, Predmety.Rarita.BĚŽNÁ));
                        kasarna.pridatBojovnika(b);
                        Hra.hracuvTym.add(b);
                        JOptionPane.showMessageDialog(okno, jmeno.toUpperCase() + " byl úspěšně přidán!");
                        Hra.obrazovkaHradu.aktualizace();
                    }
                } else {
                    JOptionPane.showMessageDialog(okno, "Nemáš dost peněz!");
                }
            }


        }
    }

    /**
     * Tato metoda vyhodnocuje co se stane po kliknuti na lekarnu
     */

    private void kliknutiLekarna(Lekarna l) {
        ArrayList<String> jmena = new ArrayList<>();
        for (Postava p : Hra.hracuvTym) {
            if (p instanceof Bojovnik) {
                Bojovnik bojovnik = (Bojovnik) p;
                if (bojovnik.getZivoty() < bojovnik.getMaxZivoty()) {
                    jmena.add(bojovnik.getJmeno());
                }
            }
        }
        if (jmena.isEmpty()) {
            JOptionPane.showMessageDialog(okno, "Všichni tvoji bojovníci jsou zdraví!");
            return;
        }
        String vybrane = (String) JOptionPane.showInputDialog(okno, "Vyber hrdinu na léčení: \nCena: 200 jídla", "Lékárna", JOptionPane.QUESTION_MESSAGE, null, jmena.toArray(), jmena.get(0));

        if (vybrane != null) {

            if (Predmety.Jidlo.getPocet() >= 200) {
                for (Postava p : Hra.hracuvTym) {
                if (p.getJmeno().equals(vybrane) && p instanceof Bojovnik) {
                    Bojovnik bojovnik = (Bojovnik) p;
                    bojovnik.setZivoty(bojovnik.getMaxZivoty());
                    Predmety.Jidlo.setPocet(Predmety.Jidlo.getPocet() - 200);
                        JOptionPane.showMessageDialog(okno, vybrane + " byl úspěšně vyléčen za 200 jídla!");
                        Hra.obrazovkaHradu.aktualizace();
                        break;
                    }
                }

            } else {
                JOptionPane.showMessageDialog(okno, "Nemáš dost jídla! Potřebuješ 200 jídla.");
            }
        }

    }
    /**
     * Tato metoda vyhodnocuje co se stane po kliknuti na treninkovou halu
     */

    private void kliknutiTrenink(TreninkovaHala h) {
        ArrayList<String> jmena = new ArrayList<>();
        for (Postava p : Hra.hracuvTym) {
            jmena.add(p.getJmeno());
        }
        if (jmena.isEmpty()) {
            JOptionPane.showMessageDialog(okno, "Nemáš žádné bojovniky na trénování.");
        } else {
            String jmeno = (String) JOptionPane.showInputDialog(okno, "Vyber hrdinu na trénink:", "Trénink", JOptionPane.QUESTION_MESSAGE, null, jmena.toArray(), jmena.get(0));
            if (jmeno != null) {
                for (Postava p : Hra.hracuvTym) {
                    if (p.getJmeno().equals(jmeno) && p instanceof Bojovnik) {
                        Bojovnik bojovnik = (Bojovnik) p;
                        int cena = 50 * h.getUroven();
                        if (Predmety.Jidlo.getPocet() >= cena) {
                            boolean uspech = h.trenovaniBojovnika(bojovnik);
                            if (uspech) {
                                JOptionPane.showMessageDialog(okno, bojovnik.getJmeno() + " si zvedl útok!");
                                Hra.obrazovkaHradu.aktualizace();
                            } else {
                                JOptionPane.showMessageDialog(okno, bojovnik.getJmeno() + " už trénoval 2x! Musí jít nejdřív do bitvy.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(okno, "Nemáš dostatek jídla!");
                        }
                    }
                }
            }
        }
    }


    public Kasarna getKasarna() {
        return kasarna;
    }

    public ArrayList<Lekarna> getLekarny() {
        return lekarny;
    }

    public ArrayList<TreninkovaHala> getTreninkoveHaly() {
        return treninkoveHaly;
    }

    public ArrayList<SkladPenez> getSkladyPenez() {
        return skladyPenez;
    }

    public ArrayList<SkladJidla> getSkladyJidla() {
        return skladyJidla;
    }

    public ArrayList<Integer> getPozicePostavenych() {
        return pozicePostavenych;
    }

    public JPanel getOkno() {
        return okno;
    }

    public HashMap<Integer, Integer[]> getLokaceMistnosti() {
        return lokaceMistnosti;
    }

    public HashMap<Integer, TypMistnosti> getPostavene() {
        return postavene;
    }
}