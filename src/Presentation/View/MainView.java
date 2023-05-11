package Presentation.View;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {

    private final InitialView initialView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final AddSongView addSongView;
    private final DeleteSongView deleteSongView;
    private final DetailedPlaylistView detailedPlaylistView;
    private final DetailedSongView detailedSongView;
    private final GeneralSongListView generalSongListView;
    private final GeneralPlaylistView generalPlaylistView;
    private final MainMenuView mainMenuView;
    private final StatisticsView statisticsView;

    private static final String CARD_MAIN = "CARD_MAIN";
    private static final String CARD_ADD_SONG = "CARD_ADD_SONG";
    private static final String CARD_DELETE_SONG = "CARD_DELETE_SONG";
    private static final String CARD_DETAILED_PLAYLIST = "CARD_DETAILED_PLAYLIST";
    private static final String CARD_DETAILED_SONG = "CARD_DETAILED_SONG";
    private static final String CARD_GENERAL_SONG = "CARD_GENERAL_SONG";
    private static final String CARD_GENERAL_PLAYLIST = "CARD_GENERAL_PLAYLIST";
    private static final String CARD_MAIN_MENU = "CARD_MAIN_MENU";
    private static final String CARD_STATISTICS = "CARD_STATISTICS";
    private static final String CARD_LOGIN = "CARD_LOGIN";
    private static final String CARD_REGISTER = "CARD_REGISTER";


    private final CardLayout cardManager;

    public MainView(InitialView initialView, DeleteSongView deleteSongView, StatisticsView statisticsView, MainMenuView mainMenuView, GeneralPlaylistView generalPlaylistView, GeneralSongListView generalSongListView, DetailedSongView detailedSongView, DetailedPlaylistView detailedPlaylistView, RegisterView registerView, LoginView loginView, AddSongView addSongView) {
        this.initialView = initialView;
        this.registerView = registerView;
        this.loginView = loginView;
        this.addSongView = addSongView;
        this.deleteSongView = deleteSongView;
        this.statisticsView = statisticsView;
        this.mainMenuView = mainMenuView;
        this.generalPlaylistView = generalPlaylistView;
        this.generalSongListView = generalSongListView;
        this.detailedPlaylistView = detailedPlaylistView;
        this.detailedSongView = detailedSongView;

        cardManager = new CardLayout();
        getContentPane().setLayout(cardManager);
        configureWindow();

        configureInitial();
        configureLogin();
        configureRegister();
        configureDeleteSong();
        configureDetailedPlaylist();
        configureStatisticsView();
        configureDetailedSong();
        configureGeneralPlaylist();
        configureGeneralSongList();
        configureAddSong();
        configureMainMenuView();
    }

    private void configureWindow(){
        setTitle("Espotifai");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
    }

    private void configureInitial(){
        this.initialView.configureInitialView();
        this.getContentPane().add(initialView, CARD_MAIN);
    }

    private void configureLogin(){
        this.loginView.configureLoginView();
        this.getContentPane().add(loginView, CARD_LOGIN);
    }

    private void configureRegister(){
        this.registerView.configureRegisterView();
        this.getContentPane().add(registerView, CARD_REGISTER);
    }
    private void configureAddSong(){
        this.addSongView.configureAddSong();
        this.getContentPane().add(addSongView, CARD_ADD_SONG);
    }
    private void configureDeleteSong(){
        this.deleteSongView.configureDeleteSongView();
        this.getContentPane().add(deleteSongView, CARD_DELETE_SONG);
    }
    private void configureDetailedPlaylist(){
        this.detailedPlaylistView.configureDetailedPlaylistView();
        this.getContentPane().add(detailedPlaylistView, CARD_DETAILED_PLAYLIST);
    }
    private void configureDetailedSong(){
        this.detailedSongView.configureDetailedSongView();
        this.getContentPane().add(detailedSongView, CARD_DETAILED_SONG);
    }
    private void configureGeneralPlaylist(){
        this.generalPlaylistView.configureGeneralPlaylistView();
        this.getContentPane().add(generalPlaylistView, CARD_GENERAL_PLAYLIST);
    }
    private void configureGeneralSongList(){
        this.generalSongListView.configureGeneralSonglistView();
        this.getContentPane().add(generalSongListView, CARD_GENERAL_SONG);
    }
    private void configureMainMenuView(){
        this.mainMenuView.configureMainMenuView();
        this.getContentPane().add(mainMenuView, CARD_MAIN_MENU);
    }
    private void configureStatisticsView(){
        this.statisticsView.configureStatisticsView();
        this.getContentPane().add(statisticsView, CARD_STATISTICS);
    }


    public void showMainCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showLoginCard(){
        cardManager.show(getContentPane(), CARD_LOGIN);
    }
    public void showRegisterCard(){
        cardManager.show(getContentPane(), CARD_REGISTER);
    }
    public void showAddSongCard(){
        cardManager.show(getContentPane(), CARD_ADD_SONG);
    }
    public void showDeleteSongCard(){
        cardManager.show(getContentPane(), CARD_DELETE_SONG);
    }
    public void showDetailedPlaylistCard(){
        cardManager.show(getContentPane(), CARD_DETAILED_PLAYLIST);
    }
    public void showDetailedSongCard(){
        cardManager.show(getContentPane(), CARD_DETAILED_SONG);
    }
    public void showGeneralPlaylistCard(){
        cardManager.show(getContentPane(), CARD_GENERAL_PLAYLIST);
    }
    public void showGeneralSongListCard(){
        cardManager.show(getContentPane(), CARD_GENERAL_SONG);
    }
    public void showMainMenuCard(){
        cardManager.show(getContentPane(), CARD_MAIN_MENU);
    }
    public void showStatisticsCard(){
        cardManager.show(getContentPane(), CARD_STATISTICS);
    }


    public void start() {
        showMainCard();
        setVisible(true);
    }
}
