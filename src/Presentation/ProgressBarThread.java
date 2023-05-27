package Presentation;

import javax.swing.*;

public class ProgressBarThread implements Runnable{
    private boolean isPlaying = false;
    private boolean isLooping;
    private JProgressBar progressBar;
    private JLabel actualTime;
    private int i = 0;

    public ProgressBarThread(JProgressBar progressBar, JLabel actualTime) {
        this.progressBar = progressBar;
        this.actualTime = actualTime;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        if (isPlaying) {
            synchronized (this) {
                notify();
            }
        }
    }

    public void setLoop(boolean isLooping) {
        this.isLooping = isLooping;
        if (isLooping) {
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
                    int minutes = i / 60;
                    int seconds = i % 60;

                    String minutesString = String.format("%02d", minutes);
                    String secondsString = String.format("%02d", seconds);

                    String time = minutesString + ":" + secondsString;

                    actualTime.setText(time);
                    i++;
                    Thread.sleep(1000);
                }else{
                    break;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
