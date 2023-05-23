package Presentation.Controller;

import Business.SongManager;
import Presentation.View.DetailedPlaylistView;
import Presentation.View.MainView;
import Presentation.View.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StatisticsViewController implements ActionListener {
    private final StatisticsView statisticsView;
    private final MainView mainView;

    private final SongManager songManager;


    public StatisticsViewController(StatisticsView statisticsView, MainView mainView, SongManager songManager) {
        this.statisticsView = statisticsView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StatisticsView.BTN_STATISTICS:
                Map<String, Integer> genreMap = songManager.createGenreMap();
                statisticsView.setGenreMap(genreMap);
                statisticsView.repaint();
        }
    }
}
