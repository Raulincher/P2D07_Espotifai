package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.HeaderController;
import Presentation.Controller.StatisticsViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;

    private JButton juan;
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    public StatisticsView(HeaderView headerView, Utils utils, FooterView footerView){
        this.headerView = headerView;
        this.footerView = footerView;
        this.utils = utils;
    }

    public void addStatisticsController(StatisticsViewController statisticsViewController){
        //set action command
        //jback.addActionListener(statisticsViewController);
    }


    public void configureStatisticsView() {
        setLayout(new BorderLayout());
        // NORTH
        JPanel north =  new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon statsImg = new ImageIcon(String.valueOf(AssetsFiles.STATISTICS_LABEL));
        north.add(headerView.configureHeader(statsImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        //CENTER
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(Color.BLACK);
        add(center, BorderLayout.CENTER);

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

}
