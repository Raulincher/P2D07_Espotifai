package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Utils;
import Presentation.View.FooterView;
import Presentation.Controller.MainMenuViewController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class MainMenuView extends JPanel {

    FooterView footerView;

    public MainMenuView(FooterView footerView){
        this.footerView = footerView;
    }

    private JButton jSongList;
    private JButton jAddSong;
    private JButton jDeleteSong;
    private JButton jStatistics;
    private JButton jManage;

    private JButton jSubmitBack2 = new JButton();


    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_SONG_LIST = "BTN_SONG_LIST";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_DELETE_SONG = "BTN_DELETE_SONG";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_MANAGE = "BTN_MANAGE";

    public void configureMainMenuView() {

        setLayout(new BorderLayout());

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon menuImg = new ImageIcon(String.valueOf(AssetsFiles.MENU_LABEL));
        Utils.header(north, jSubmitBack2, menuImg);
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(Color.black);
        center.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(center, BorderLayout.CENTER);

        Icon songListBtn = new ImageIcon(String.valueOf(AssetsFiles.SONGLIST_BUTTON_IMG));
        jSongList = new JButton(songListBtn);
        jSongList.setActionCommand(BTN_SONG_LIST);
        jSongList.setBackground(Color.decode("#00000000"));

        Icon addSongBtn = new ImageIcon(String.valueOf(AssetsFiles.ADDSONG_BUTTON_IMG));
        jAddSong = new JButton(addSongBtn);
        jAddSong.setActionCommand(BTN_ADD_SONG);
        jAddSong.setBackground(Color.decode("#00000000"));

        Icon deleteSongBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETESONG_BUTTON_IMG));
        jDeleteSong = new JButton(deleteSongBtn);
        jDeleteSong.setActionCommand(BTN_DELETE_SONG);
        jDeleteSong.setBackground(Color.decode("#00000000"));

        Icon statisticsBtn = new ImageIcon(String.valueOf(AssetsFiles.STATISTICS_BUTTON_IMG));
        jStatistics = new JButton(statisticsBtn);
        jStatistics.setActionCommand(BTN_STATISTICS);
        jStatistics.setBackground(Color.decode("#00000000"));

        Icon manageBtn = new ImageIcon(String.valueOf(AssetsFiles.MANAGELISTS_BUTTON_IMG));
        jManage = new JButton(manageBtn);
        jManage.setActionCommand(BTN_MANAGE);
        jManage.setBackground(Color.decode("#00000000"));

        center.add(jSongList);
        center.add(jAddSong);
        center.add(jDeleteSong);
        center.add(jStatistics);
        center.add(jManage);

        // SOUTH
        JPanel south = new JPanel();
        south.setBackground(Color.black);
        south.setBorder(createEmptyBorder(0, 0, 100, 0));

        // Aquí anirà el reproductor


        add(south, BorderLayout.SOUTH);
    }


    public void addMainMenuController(MainMenuViewController mainMenuController){
        //set action command
        jSubmitBack2.addActionListener(mainMenuController);
        jSongList.addActionListener(mainMenuController);
        jAddSong.addActionListener(mainMenuController);
        jDeleteSong.addActionListener(mainMenuController);
        jStatistics.addActionListener(mainMenuController);
        jManage.addActionListener(mainMenuController);
    }

}
