//package Logika;
//
//import Obrazovky.ObrazovkaHradu;
//
//import javax.swing.*;
//import java.util.List;
//import java.util.Random;
//
//public class PostupNacitani extends SwingWorker<Void, int[]> {
//    private JLabel status;
//    private JProgressBar postup;
//    private JFrame frame;
//
//    public PostupNacitani(JLabel status, JProgressBar postup, JFrame frame) {
//        this.status = status;
//        this.postup = postup;
//        this.frame = frame;
//    }
//
//    private String[] kroky = {"Načítám bojovníky", "Načítám hrad",
//            "Načítám peníze", "Načítám Jídlo"};
//
//    @Override
//    protected Void doInBackground() throws Exception {
//        Random random = new Random();
//        for (int i = 0; i <= 100; i++) {
//            int pauza = random.nextInt(20, 100);
//            if (i % 30 == 0) pauza = pauza+ random.nextInt(500, 1500);
//            Thread.sleep(pauza);
//            int indexTextu = Math.min(i / 25, kroky.length - 1);
//            publish(new int[]{i, indexTextu});
//        }
//        return null;
//    }
//
//    @Override
//    protected void process(List<int[]> chunks) {
//        int[] last = chunks.get(chunks.size() - 1);
//        int procenta = last[0];
//        int indexTextu = last[1];
//        postup.setValue(procenta);
//        status.setText(kroky[indexTextu] + "... " + procenta + "%");
//    }
//
//    @Override
//    protected void done() {
//        frame.dispose();
//        new ObrazovkaHradu("Hrad", false).inicializace();
//    }
//}
