package Presentation.View;

import Presentation.Controller.StatisticsViewController;

import javax.swing.*;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";

    public void addStatisticsController(StatisticsViewController statisticsViewController){
        //set action command

    }

    public void configureStatisticsView() {

        JLabel jLogo = new JLabel("statistics");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
