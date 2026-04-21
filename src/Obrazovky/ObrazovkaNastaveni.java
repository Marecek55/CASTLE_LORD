package Obrazovky;

public class ObrazovkaNastaveni extends Obrazovka {
    private PanelNaPozadi pozadiNastaveni;
    public ObrazovkaNastaveni(String nazev, boolean malaObrazovka) {
        super(nazev, malaObrazovka);
        pozadiNastaveni = new PanelNaPozadi("/Obrazky/obrazekNastaveni.png");
    }

    @Override
    public void inicializace() {
        okno.setContentPane(pozadiNastaveni);
        okno.setVisible(true);
    }

    @Override
    public void funkcnost() {

    }
}
