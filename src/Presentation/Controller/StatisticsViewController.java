package Presentation.Controller;

import Presentation.View.MainView;
import Presentation.View.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsViewController implements ActionListener {
    private final StatisticsView statisticsView;
    private final MainView mainView;


    public StatisticsViewController(StatisticsView statisticsView, MainView mainView) {
        this.statisticsView = statisticsView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StatisticsView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }
}
