package Presentation;

import javax.swing.*;

public class ProgressBarThread implements Runnable{
    private boolean isPlaying = false;
    private JProgressBar progressBar;
    private int i = 0;
    private int progressOfBar;

    public ProgressBarThread(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        if (isPlaying) {
            synchronized (this) {
                notify();
            }
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this) {
                    while (!isPlaying) {
                        wait();
                    }
                }

                if (progressBar.getValue() != progressBar.getMaximum()) {

                    progressBar.setValue(i);
                    System.out.println(i);
                    progressOfBar = progressBar.getValue();
                    i++;
                    Thread.sleep(1000);
                } else {
                    break;  // Exit the loop when the progress reaches the maximum
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
