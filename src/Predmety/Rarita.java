package Predmety;

/**
 * Tato trida v sobe me rarity predmetu
 */
public enum Rarita {
    BĚŽNÁ("/Obrazky/obrazkyInventar/rarePozadi.png"),
    VZÁCNÁ("/Obrazky/obrazkyInventar/epicPozadi.png"),
    LEGENDÁRNÍ("/Obrazky/obrazkyInventar/legendaryPozadi.png");

    private final String cesta;

    Rarita(String cesta) {
        this.cesta = cesta;
    }

    public String getCesta() {
        return cesta;
    }
}