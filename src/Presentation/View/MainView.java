package Presentation.View;

import Business.Entities.Song;
import Presentation.AssetsFiles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;


public class MainView extends JFrame {

    // Afegirem totes les vistes com a atributs final
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

    // Afegim els strings finals per a referir-se a cada View
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
    private JPanel main;
    private JPanel center;
    private CardLayout cardMain;


    /**
     * Funció que servirà per a configurar el programa
     * i servirà com a constructor i punt de referència
     * de les vistes
     *
     * @param initialView, vista inicial
     * @param registerView, vista del sign up
     * @param loginView, vista del log in
     * @param addSongView, vista per afegir una cançó
     * @param deleteSongView, vista d'esborrar la cançó
     * @param statisticsView, vista de les estadístiques
     * @param mainMenuView, vista del menú principal
     * @param generalPlaylistView, vista de totes les playlists
     * @param generalSongListView, vista de la song list
     * @param detailedPlaylistView, vista d'una playlist en concret
     * @param detailedSongView, vista d'una song en concret
     */
    public MainView(InitialView initialView, DeleteSongView deleteSongView, StatisticsView statisticsView, MainMenuView mainMenuView, GeneralPlaylistView generalPlaylistView, GeneralSongListView generalSongListView, DetailedSongView detailedSongView, DetailedPlaylistView detailedPlaylistView, RegisterView registerView, LoginView loginView, AddSongView addSongView, FooterView footerView, HeaderView headerView) {
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


        // Crearem el cardLayout
        cardManager = new CardLayout();
        getContentPane().setLayout(cardManager);
        configureWindow();
        configureInitial();
        configureLogin();
        configureRegister();

        // Preparem el JPanel general amb el BorderLayout
        main = new JPanel(new BorderLayout());

        // Creem el JPanel del nord que servirà com a Header i el configurem
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon menuImg = new ImageIcon(String.valueOf(AssetsFiles.SONG_LABEL));
        north.add(headerView.configureHeader(menuImg));
        north.setBackground(Color.black);

        // Afegim el JPanel al BorderLayout
        main.add(north, BorderLayout.NORTH);

        // Creem el JPanel del sud que servirà com a Footer i el configurem
        JPanel south = new JPanel();
        Color gris = new Color(26, 26, 26);
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());

        // Afegim el JPanel al BorderLayout
        main.add(south, BorderLayout.SOUTH);

        // Creem el JPanel del center que servirà per canviar de vistes només al centre
        center = new JPanel();
        cardMain = new CardLayout();
        center.setLayout(cardMain);

        // Activem totes les vistes
        configureDetailedSong();
        configureDetailedPlaylist();
        configureMainMenuView();
        configureGeneralSongList();
        configureDeleteSong();
        configureStatisticsView();
        configureGeneralSongList();
        configureGeneralPlaylist();
        configureAddSong();
        main.add(center, BorderLayout.CENTER);
    }


    /**
     * Funció que servirà per a configurar pantalla amb la
     * qual es veurà el nostre programa
     *
     * No tindrà ni param ni return
     */

    private void configureWindow(){
        // Afegim títol, funció de tancat i mides
        setTitle("Espotifai");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocationRelativeTo(null);
    }


    /**
     * Funció que servirà per a activar la InitialView
     *
     * No tindrà ni param ni return
     */
    private void configureInitial(){
        this.initialView.configureInitialView();
        this.getContentPane().add(initialView, CARD_MAIN);
    }

    /**
     * Funció que servirà per a activar la LoginView
     *
     * No tindrà ni param ni return
     */
    private void configureLogin(){
        this.loginView.configureLoginView();
        this.getContentPane().add(loginView, CARD_LOGIN);
    }

    /**
     * Funció que servirà per a activar la RegisterView
     *
     * No tindrà ni param ni return
     */
    private void configureRegister(){
        this.registerView.configureRegisterView();
        this.getContentPane().add(registerView, CARD_REGISTER);
    }

    /**
     * Funció que servirà per a activar la AddSongView
     *
     * No tindrà ni param ni return
     */
    private void configureAddSong(){
        this.addSongView.configureAddSong();
        this.center.add(addSongView, CARD_ADD_SONG);
    }

    /**
     * Funció que servirà per a activar la DeleteSongView
     *
     * No tindrà ni param ni return
     */
    private void configureDeleteSong(){
        this.deleteSongView.configureDeleteSongView();
        this.center.add(deleteSongView, CARD_DELETE_SONG);
    }

    /**
     * Funció que servirà per a activar la MainMenuView
     *
     * No tindrà ni param ni return
     */
    private void configureMainMenuView(){
        this.mainMenuView.configureMainMenuView();
        this.center.add(mainMenuView, CARD_MAIN_MENU);
        this.getContentPane().add(main, CARD_MAIN_MENU);
    }

    /**
     * Funció que servirà per a activar la GeneralSongListView
     *
     * No tindrà ni param ni return
     */
    private void configureGeneralSongList(){
        this.generalSongListView.configureGeneralSonglistView();
        this.center.add(generalSongListView, CARD_GENERAL_SONG);
    }


    /**
     * Funció que servirà per a activar la DetailedPlaylistView
     *
     * No tindrà ni param ni return
     */
    private void configureDetailedPlaylist(){
        this.detailedPlaylistView.configureDetailedPlaylistView();
        this.center.add(detailedPlaylistView, CARD_DETAILED_PLAYLIST);
    }

    /**
     * Funció que servirà per a activar la DetailedSongView
     *
     * No tindrà ni param ni return
     */
    private void configureDetailedSong(){
        this.detailedSongView.configureDetailedSongView();
        this.center.add(detailedSongView, CARD_DETAILED_SONG);
    }



    /**
     * Funció que servirà per a activar la GeneralPlaylistView
     *
     * No tindrà ni param ni return
     */
    private void configureGeneralPlaylist(){
        this.generalPlaylistView.configureGeneralPlaylistView();
        this.center.add(generalPlaylistView, CARD_GENERAL_PLAYLIST);
    }




    /**
     * Funció que servirà per a activar la StatisticsView
     *
     * No tindrà ni param ni return
     */
    private void configureStatisticsView(){
        this.statisticsView.configureStatisticsView();
        this.center.add(statisticsView, CARD_STATISTICS);
    }

    /**
     * Funció que servirà per mostrar el main principal
     *
     * No tindrà ni param ni return
     */
    public void showMainCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }

    /**
     * Funció que servirà per mostrar el login
     *
     * No tindrà ni param ni return
     */
    public void showLoginCard(){
        cardManager.show(getContentPane(), CARD_LOGIN);
    }

    /**
     * Funció que servirà per mostrar el register
     *
     * No tindrà ni param ni return
     */
    public void showRegisterCard(){
        cardManager.show(getContentPane(), CARD_REGISTER);
    }

    /**
     * Funció que servirà per mostrar el l'add song
     *
     * No tindrà ni param ni return
     */
    public void showAddSongCard(){
        cardManager.show(getContentPane(), CARD_ADD_SONG);
        cardMain.show(center, CARD_ADD_SONG);

    }

    /**
     * Funció que servirà per mostrar la detailed song
     *
     * No tindrà ni param ni return
     */
    public void showDeleteSongCard(){
        cardManager.show(getContentPane(), CARD_DELETE_SONG);
        cardMain.show(center, CARD_DELETE_SONG);

    }

    /**
     * Funció que servirà per mostrar la playlist detallada
     *
     * No tindrà ni param ni return
     */
    public void showDetailedPlaylistCard(){
        cardManager.show(getContentPane(), CARD_DETAILED_PLAYLIST);
        cardMain.show(center, CARD_DETAILED_PLAYLIST);

    }

    /**
     * Funció que servirà per mostrar la song detallada
     *
     * No tindrà ni param ni return
     */
    public void showDetailedSongCard(){
        cardManager.show(getContentPane(), CARD_DETAILED_SONG);
        cardMain.show(center, CARD_DETAILED_SONG);
    }

    /**
     * Funció que servirà per mostrar el main Menú
     *
     * No tindrà ni param ni return
     */
    public void showMainMenuCard(){
        cardManager.show(getContentPane(), CARD_MAIN_MENU);
        cardMain.show(center, CARD_MAIN_MENU);
    }

    /**
     * Funció que servirà per mostrar les Playlists
     *
     * No tindrà ni param ni return
     */
    public void showGeneralPlaylistCard(){
        cardManager.show(getContentPane(), CARD_GENERAL_PLAYLIST);
        cardMain.show(center, CARD_GENERAL_PLAYLIST);

    }

    /**
     * Funció que servirà per mostrar la Song List
     *
     * No tindrà ni param ni return
     */
    public void showGeneralSongListCard(){
        cardManager.show(getContentPane(), CARD_GENERAL_SONG);
        cardMain.show(center, CARD_GENERAL_SONG);
    }



    /**
     * Funció que servirà per mostrar les statistics
     *
     * No tindrà ni param ni return
     */
    public void showStatisticsCard(){
        cardManager.show(getContentPane(), CARD_STATISTICS);
        cardMain.show(center, CARD_STATISTICS);

    }

    /**
     * Funció que servirà per a iniciar el programa
     *
     * No tindrà ni param ni return
     */
    public void start() {
        showMainCard();
        setVisible(true);
    }
}