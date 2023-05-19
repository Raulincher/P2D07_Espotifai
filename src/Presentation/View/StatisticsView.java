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
import java.util.Collections;
import java.util.Map;

import static javax.swing.BorderFactory.createEmptyBorder;

public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
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

        // CENTER
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(Color.BLACK);
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Map<String, Integer> genreMap = songManager.createGenreMap();

                int maxCount = Collections.max(genreMap.values());
                int barWidth = getWidth() / (genreMap.size() * 2);
                int maxHeight = getHeight();
                int x = 0;

                for (Map.Entry<String, Integer> entry : genreMap.entrySet()) {
                    String genre = entry.getKey();
                    int count = entry.getValue();

                    int barHeight = (int) ((double) count / maxCount * maxHeight);

                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(x, getHeight() - barHeight, barWidth, barHeight);

                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Gotham", Font.BOLD, 12));
                    g2d.drawString(genre, x, getHeight() - 5);

                    x += barWidth * 2;
                }
            }
        };
        graphPanel.setPreferredSize(new Dimension(400, 300));
        graphPanel.setBackground(Color.BLACK);
        center.add(graphPanel);

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
