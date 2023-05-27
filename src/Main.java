
import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Persistance.dao.*;
import Persistance.dao.sql.DatabaseConnector;
import Persistance.dao.sql.SQLPlaylistDao;
import Persistance.dao.sql.SQLSongDao;
import Persistance.dao.sql.SQLUserDao;
import Presentation.Controller.*;
import Presentation.Utils;
import Presentation.View.*;

import java.io.IOException;
import java.sql.Connection;

public class Main {

    /**
     * Funció que servirà com a constructor del propi main,
     * és dir, de tot el programa
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        // Connectem amb la base de dades
        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);
        PlaylistDao playlistDao = new SQLPlaylistDao(remoteConnection);

        // Connectem amb l'API
        ApiHelper apiHelper = new ApiHelper();
        SongLyricsApi songLyricsApi = new SongLyricsApi(apiHelper);

        // Connectem amb els Managers
        UserManager userManager = new UserManager(userDao);
        SongManager songManager = new SongManager(songDao,songLyricsApi);
        PlaylistManager playlistManager = new PlaylistManager(playlistDao);

        // Iniciem utils
        Utils utils = new Utils();

        songManager.getSong("");

        // Iniciem la primera vista
        InitialView initialView = new InitialView(utils);



        // Preparem totes les vistes
        FooterView footerView = new FooterView(utils);
        // Preparem el header
        HeaderView headerView = new HeaderView(utils);
        RegisterView registerView = new RegisterView(utils);
        LoginView loginView = new LoginView(utils);
        AddSongView addSongView = new AddSongView(utils);
        DeleteSongView deleteSongView = new DeleteSongView(utils);
        DetailedPlaylistView detailedPlaylistView = new DetailedPlaylistView(utils);
        DetailedSongView detailedSongView = new DetailedSongView(utils);
        GeneralPlaylistView generalPlaylistView = new GeneralPlaylistView(utils);
        GeneralSongListView generalSongListView = new GeneralSongListView(utils);
        MainMenuView mainMenuView = new MainMenuView(utils);
        StatisticsView statisticsView = new StatisticsView();

        // Iniciem la mainView, que tindrà el mateix header i footer
        MainView mainView = new MainView(initialView, deleteSongView, statisticsView, mainMenuView, generalPlaylistView, generalSongListView, detailedSongView, detailedPlaylistView, registerView, loginView, addSongView, footerView, headerView);

        HeaderController headerController = new HeaderController(headerView, userManager, songManager);
        headerView.addHeaderController(headerController);
        headerController.addMainView(mainView);

        // Vinculem amb el controller del footer
        FooterController footerController = new FooterController(footerView, songManager);
        footerView.addFooterController(footerController);

        // Vinculem tots els controllers
        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView, userManager);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView, userManager);
        AddSongViewController addSongViewController = new AddSongViewController(addSongView, mainView, songManager, userManager);
        DeleteSongViewController deleteSongViewController = new DeleteSongViewController(deleteSongView, songManager, userManager, playlistManager);
        DetailedPlaylistViewController detailedPlaylistViewController = new DetailedPlaylistViewController(detailedPlaylistView, mainView,
                playlistManager, generalPlaylistView, userManager);
        DetailedSongViewController detailedSongViewController = new DetailedSongViewController(detailedSongView, songManager, playlistManager, userManager, footerView);
        GeneralSongListViewController generalSongListViewController = new GeneralSongListViewController(generalSongListView, mainView, songManager,detailedSongView, playlistManager, userManager, detailedSongViewController);
        GeneralPlaylistViewController generalPlaylistViewController = new GeneralPlaylistViewController(generalPlaylistView, mainView, playlistManager, userManager, detailedPlaylistView);
        MainMenuViewController mainMenuViewController = new MainMenuViewController(mainMenuView, mainView, songManager, userManager, deleteSongView, generalSongListView, generalPlaylistView, playlistManager, addSongView, statisticsView);

        // Vinculem vistes amb controllers
        initialView.addInitialViewController(initialViewController);
        loginView.addLoginController(loginViewController);
        registerView.addRegisterController(registerViewController);
        addSongView.addAddSongController(addSongViewController);
        deleteSongView.addDeleteSongController(deleteSongViewController);
        detailedPlaylistView.addDetailedPlaylistController(detailedPlaylistViewController);
        detailedSongView.addDetailedSongController(detailedSongViewController);
        generalSongListView.addGeneralSongListController(generalSongListViewController);
        generalPlaylistView.addGeneralPlaylistController(generalPlaylistViewController);
        mainMenuView.addMainMenuController(mainMenuViewController);

        mainView.start();
    }
}
