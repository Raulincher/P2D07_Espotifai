package Presentation.View;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {

    private final InitialView initialView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final TestView testView;
    private final AddSongView addSongView;
    private final DeleteSongView deleteSongView;
    private final DetailedPlaylistView detailedPlaylistView;
    private final DetailedSongView detailedSongView;
    private final GeneralSongListView generalSongListView;
    private final GeneralPlaylistView generalPlaylistView;
    private final MainMenuView mainMenuView;
    private final StatisticsView statisticsView;



    private static final String CARD_MAIN = "CARD_MAIN";
    private static final String CARD_LOGIN = "CARD_LOGIN";
    private static final String CARD_REGISTER = "CARD_REGISTER";
    private static final String CARD_TEST = "CARD_TEST";


    private final CardLayout cardManager;

    public MainView(InitialView initialView, DeleteSongView deleteSongView, StatisticsView statisticsView, MainMenuView mainMenuView, GeneralPlaylistView generalPlaylistView, GeneralSongListView generalSongListView, DetailedSongView detailedSongView, DetailedPlaylistView detailedPlaylistView, RegisterView registerView, LoginView loginView, TestView testView, AddSongView addSongView) {
        this.initialView = initialView;
        this.registerView = registerView;
        this.loginView = loginView;
        this.testView = testView;
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
        configureTestView();
        showAddSongCard();
        showDeleteSongCard();
        showDetailedPlaylistCard();
        showDetailedSongCard();
        showGeneralPlaylistCard();
        showGeneralSongListCard();
        showMainMenuCard();
        showStatisticsCard();
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

    private void configureTestView(){
        this.testView.configureTestView();
        this.getContentPane().add(testView, CARD_TEST);
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
    public void showTestCard(){
        cardManager.show(getContentPane(), CARD_TEST);
    }
    public void showAddSongCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showDeleteSongCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showDetailedPlaylistCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showDetailedSongCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showGeneralPlaylistCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showGeneralSongListCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showMainMenuCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }
    public void showStatisticsCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }



    /*public void showPopUpsLogin(String error) {
        this.loginView.showPopUps(error);
    }*/

    public void start() {
        showMainCard();
        setVisible(true);
    }
}
