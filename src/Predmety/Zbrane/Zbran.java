    package Predmety.Zbrane;

    import Logika.Hra;
    import Predmety.Rarita;

    import java.util.ArrayList;

    public abstract class Zbran {
        protected String nazev;
        protected int sila;
        protected int silaCritical;
        protected Rarita rarita;
        protected double sanceCritical;

        public Zbran(String nazev, int sila, int silaCritical, Rarita rarita ) {
            this.nazev = nazev;
            this.sila = sila;
            this.silaCritical = silaCritical;
            this.rarita = rarita;

        }
        public static Zbran vytvoritZbran(int uroven){
            ArrayList<Rarita> rarity = new ArrayList<>();
                rarity.add(Rarita.VZÁCNÁ);
                rarity.add(Rarita.BĚŽNÁ);
                rarity.add(Rarita.LEGENDÁRNÍ);
            int cislo = Hra.rand.nextInt(rarity.size());
            Rarita rarita = rarity.get(cislo);
            int cislo2 = Hra.rand.nextInt(1,4);
            int provizorniSila = 0;
            int provizorniCriticalSila = 0;
            switch (rarita) {
                case LEGENDÁRNÍ: provizorniSila =Hra.rand.nextInt(8,12)*uroven;
                    provizorniCriticalSila = Hra.rand.nextInt(18,21)*uroven;
                break;
                case BĚŽNÁ: provizorniSila = Hra.rand.nextInt(3,8)*uroven;
                    provizorniCriticalSila = Hra.rand.nextInt(8,11)*uroven;
                    break;
                case VZÁCNÁ: provizorniSila = Hra.rand.nextInt(5,10)*uroven;
                    provizorniCriticalSila = Hra.rand.nextInt(13,17)*uroven;
                    break;
            }
            switch (cislo2) {
                case 1:
                    Zbran z = new Mec("Meč", provizorniSila, provizorniCriticalSila, rarita);
                    return z;
                case 2:
                    Zbran z2 = new Luk("Luk", provizorniSila, provizorniCriticalSila, rarita);
                    return z2;
                case 3:
                    Zbran z3 = new MagickaHul("Magická Hůl", provizorniSila, provizorniCriticalSila, rarita);
                    return z3;
                    default:
                        return null;
            }

        }

        public abstract int zautoc();

        public String getNazev() {
            return nazev;
        }

        public void setNazev(String nazev) {
            this.nazev = nazev;
        }

        public int getSila() {
            return sila;
        }

        public void setSila(int sila) {
            this.sila = sila;
        }

        public int getSilaCritical() {
            return silaCritical;
        }

        public void setSilaCritical(int silaCritical) {
            this.silaCritical = silaCritical;
        }

        public Rarita getRarita() {
            return rarita;
        }

        public void setRarita(Rarita rarita) {
            this.rarita = rarita;
        }

        public double getSanceCritical() {
            return sanceCritical;
        }

        public void setSanceCritical(double sanceCritical) {
            this.sanceCritical = sanceCritical;
        }
    }
