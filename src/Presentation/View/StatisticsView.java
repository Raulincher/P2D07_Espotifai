package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.StatisticsViewController;
import Presentation.Utils;

import javax.swing.*;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;

    private final Utils utils;
    private final HeaderView headerView;

    public StatisticsView(HeaderView headerView, Utils utils){
        this.headerView = headerView;
        this.utils = utils;
    }

    public void addStatisticsController(StatisticsViewController statisticsViewController){
        //set action command
        //jback.addActionListener(statisticsViewController);
    }

    public void configureStatisticsView() {
        Icon statsImg = new ImageIcon(String.valueOf(AssetsFiles.STATISTICS_LABEL));
        add(headerView.configureHeader(statsImg));
    }

}
