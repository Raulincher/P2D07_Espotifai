package Presentation.View;

import Business.SongManager;
import Presentation.AssetsFiles;
import Presentation.Controller.HeaderController;
import Presentation.Controller.StatisticsViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;

    private JButton juan;
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    private final SongManager songManager;

    public StatisticsView(HeaderView headerView, Utils utils, FooterView footerView, SongManager songManager){
        this.headerView = headerView;
        this.footerView = footerView;
        this.utils = utils;
        this.songManager = songManager;
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

        ArrayList<Integer> genreCounts = songManager.countSongsByGenre();

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Num cançons");

        for (int i = 0; i < genreCounts.size(); i++) {
            int count = genreCounts.get(i);
            tableModel.addRow(new Object[]{count});
        }
        table.setModel(tableModel);
        center.add(table);

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

}
