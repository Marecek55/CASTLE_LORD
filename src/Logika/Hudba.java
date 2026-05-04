package Logika;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Hudba implements Runnable {
    private Clip clip;
    private boolean dokola;

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

    public boolean hraje() {
        return clip != null && clip.isRunning();
    }

    public void hraj(boolean dokola) {
        if (clip != null) {
            this.dokola = dokola;
            Thread vlakno = new Thread(this);
            vlakno.start();
        }
    }

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

    public void zastav() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}