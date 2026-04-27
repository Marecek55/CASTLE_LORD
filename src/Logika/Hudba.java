package Logika;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Hudba {
    private Clip clip;

    public Hudba(String cesta) {
        try {
            InputStream is = getClass().getResourceAsStream(cesta);
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            System.err.println("Chyba"+ e.getMessage());
        }
    }
    public boolean hraje() {
        return clip != null && clip.isRunning();
    }

    public void hraj(boolean dokola) {
        if (clip != null) {
            new Thread(() -> {
                if (clip.isRunning()) {
                    clip.stop();
                }
                clip.setFramePosition(0);
                if (dokola) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                clip.start();
            }).start();
        }
    }

    public void zastav() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}