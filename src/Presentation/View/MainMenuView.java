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


    public void addMainMenuController(MainMenuViewController mainMenuController){
        //set action command

    }

    public void configureMainMenuView() {

        JLabel jLogo = new JLabel("general song list");
        JButton jSongList = new JButton("song list");
        jSongList.setActionCommand(BTN_SONG_LIST);
        JButton jAddSong = new JButton("add song");
        jAddSong.setActionCommand(BTN_ADD_SONG);
        JButton jDeleteSong = new JButton("back");
        jDeleteSong.setActionCommand(BTN_DELETE_SONG);
        JButton jStatistics = new JButton("statistics");
        jStatistics.setActionCommand(BTN_STATISTICS);
        JButton jPlaylist = new JButton("playlist");
        jPlaylist.setActionCommand(BTN_PLAYLIST);
        JButton jback = new JButton("back");
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
