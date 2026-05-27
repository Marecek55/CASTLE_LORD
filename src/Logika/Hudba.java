package Logika;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Trida hudba spousti hudbu a pracuje s ni
 */
public class Hudba implements Runnable {
    private Clip clip;
    private boolean dokola;

    /**
     * Konstruktor hudby ji nacita z resources
     * @param cesta cesta slozky
     */
    public Hudba(String cesta) {
        try {
            InputStream is = getClass().getResourceAsStream(cesta);
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            audioStream.close();
            bufferedIn.close();
        } catch (Exception e) {
            System.out.println("Chyba" + e.getMessage());
        }
    }

    /**
     * Tato metoda kontroluje jestli hraje
     * @return
     */
    public boolean hraje() {
        return clip != null && clip.isRunning();
    }

    /**
     * Tato metod spousti vlakno hudby
     * @param dokola
     */
    public void hraj(boolean dokola) {
        if (clip != null) {
            this.dokola = dokola;
            Thread vlakno = new Thread(this);
            vlakno.start();
        }
    }

    /**
     * Tato metoda spousti hudbu
     */

    @Override
    public void run() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);

        if (this.dokola) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        clip.start();
    }

    /**
     * Tato metoda zastavuje hudbu
     */

    public void zastav() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}