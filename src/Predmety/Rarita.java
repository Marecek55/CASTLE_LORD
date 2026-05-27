package Predmety;

/**
 * Tato trida v sobe me rarity predmetu
 */
public enum Rarita {
    BĚŽNÁ("/obrazkyInventar/rarePozadi.png"),
    VZÁCNÁ("/obrazkyInventar/epicPozadi.png"),
    LEGENDÁRNÍ("/obrazkyInventar/legendaryPozadi.png");

    private final String cesta;

    Rarita(String cesta) {
        this.cesta = cesta;
    }

    public String getCesta() {
        return cesta;
    }
}