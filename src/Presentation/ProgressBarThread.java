package Presentation;

import javax.swing.*;

public class ProgressBarThread implements Runnable{
    private boolean isPlaying = false;
    private JProgressBar progressBar;
    private int i = 0;

    public ProgressBarThread(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    public void run() {
        while (isPlaying && progressBar.getValue() != progressBar.getMaximum()) {
            progressBar.setValue(i);
            System.out.println(i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
