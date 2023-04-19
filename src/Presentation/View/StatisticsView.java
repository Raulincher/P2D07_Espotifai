package Presentation.View;

import Presentation.Controller.StatisticsViewController;

import javax.swing.*;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback = new JButton("back");


    public void addStatisticsController(StatisticsViewController statisticsViewController){
        //set action command
        jback.addActionListener(statisticsViewController);
    }

    public void configureStatisticsView() {

        JLabel jLogo = new JLabel("statistics");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
