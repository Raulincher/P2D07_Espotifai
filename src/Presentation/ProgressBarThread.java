package Presentation;

import javax.swing.*;

public class ProgressBarThread implements Runnable{

    // Preparem atributs
    private boolean isPlaying = false;
    private JProgressBar progressBar;
    private JLabel actualTime;
    private int i = 0;

    /**
     * Funció que servirà per com a constructor del ProgressBarThread
     *
     * @param progressBar, progress bar que s'actualitzarà amb el threat
     * @param actualTime, jlabel que mostrarà el temps actual i el cual actualitzarem amb el Threat
     *
     */
    public ProgressBarThread(JProgressBar progressBar, JLabel actualTime) {
        this.progressBar = progressBar;
        this.actualTime = actualTime;
    }
    /**
     *
     * Funció que servirà per parar l'execució del Threat en cas de rebre que la song s'ha parat
     *
     * @param isPlaying, progress bar que s'actualitzarà amb el threat
     *
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        if (isPlaying) {
            synchronized (this) {
                notify();
            }
        }
    }
    /**
     *
     * Funció que servirà per executar el nostre Threat
     *
     * no te params ni returns
     *
     */
    @Override
    public void run() {
        //mentre que el nostre Threat no sigui interrumpt
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //mirem de forma sincrona si l'usuari ha parat la reproducció de la canço
                //així podem parar el Threat i deixar d'actualitzar la barra i el temps actual durant la pausa
                synchronized (this) {

                    while (!isPlaying) {
                        wait();
                    }
                }

                //dins la condició dem l'actualització del valor de la barra i del temps actual
                if (progressBar.getValue() != progressBar.getMaximum()) {
                    progressBar.setValue(i);
                    int minutes = i / 60;
                    int seconds = i % 60;

                    String minutesString = String.format("%02d", minutes);
                    String secondsString = String.format("%02d", seconds);

                    String time = minutesString + ":" + secondsString;

                    actualTime.setText(time);
                    i++;
                    //cada segon
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
