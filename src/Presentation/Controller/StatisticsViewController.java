package Presentation.Controller;

import Presentation.View.DeleteSongView;
import Presentation.View.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsViewController implements ActionListener {
    private final StatisticsView statisticsView;

    public StatisticsViewController(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
