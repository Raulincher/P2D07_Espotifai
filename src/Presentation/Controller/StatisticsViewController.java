package Presentation.Controller;

import Business.SongManager;
import Presentation.View.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StatisticsViewController implements ActionListener {

    // Declarem atributs
    private final StatisticsView statisticsView;
    private final SongManager songManager;

    /**
     * Funció que servirà com a constructor de la StatisticsViewController
     *
     * @param statisticsView, view de la classe
     * @param songManager, manager per a controlar les songs de les estadístiques
     */
    public StatisticsViewController(StatisticsView statisticsView, SongManager songManager) {
        this.statisticsView = statisticsView;
        this.songManager = songManager;
    }

    /**
     * Funció que servirà com a resposta quan els usuaris premin el botó
     * i s'activin els listeners
     *
     * @param e, resposta de l'usuari
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // En cas que es premi l'únic botó, es rebrà el Map amb els gèneres i nombres
            // i es passarà la informació
            case StatisticsView.BTN_STATISTICS:
                Map<String, Integer> genreMap = songManager.createGenreMap();
                statisticsView.setGenreMap(genreMap);
                statisticsView.repaint();
        }
    }
}
