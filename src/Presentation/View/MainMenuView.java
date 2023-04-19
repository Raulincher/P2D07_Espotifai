package Presentation.View;

import Presentation.Controller.LoginViewController;
import Presentation.Controller.MainMenuViewController;

import javax.swing.*;
import java.awt.*;


public class MainMenuView extends JPanel {


    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_SONG_LIST = "BTN_SONG_LIST";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_DELETE_SONG = "BTN_DELETE_SONG";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_PLAYLIST = "BTN_PLAYLIST";


    private JButton jback = new JButton("back");
    private JButton jSongList = new JButton("song list");
    private JButton jAddSong = new JButton("add song");
    private JButton jDeleteSong = new JButton("Delete song");
    private JButton jStatistics = new JButton("statistics");
    private JButton jPlaylist = new JButton("playlist");



    public void addMainMenuController(MainMenuViewController mainMenuController){
        //set action command
        jback.addActionListener(mainMenuController);
        jSongList.addActionListener(mainMenuController);
        jAddSong.addActionListener(mainMenuController);
        jDeleteSong.addActionListener(mainMenuController);
        jStatistics.addActionListener(mainMenuController);
        jPlaylist.addActionListener(mainMenuController);


    }

    public void configureMainMenuView() {

        JLabel jLogo = new JLabel("general song list");
        jSongList.setActionCommand(BTN_SONG_LIST);
        jAddSong.setActionCommand(BTN_ADD_SONG);
        jDeleteSong.setActionCommand(BTN_DELETE_SONG);
        jStatistics.setActionCommand(BTN_STATISTICS);
        jPlaylist.setActionCommand(BTN_PLAYLIST);
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jSongList);
        add(jAddSong);
        add(jDeleteSong);
        add(jStatistics);
        add(jPlaylist);
        add(jback);

    }
}
