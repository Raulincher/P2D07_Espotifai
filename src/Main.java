
import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Persistance.dao.PlaylistDao;
import Persistance.dao.SongDao;
import Persistance.dao.UserDao;
import Persistance.dao.sql.DatabaseConnector;
import Persistance.dao.sql.SQLPlaylistDao;
import Persistance.dao.sql.SQLSongDao;
import Persistance.dao.sql.SQLUserDao;
import Presentation.Controller.*;
import Presentation.View.*;

import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);
        PlaylistDao playlistDao = new SQLPlaylistDao(remoteConnection);

        UserManager userManager = new UserManager(userDao);
        SongManager songManager = new SongManager(songDao);
        PlaylistManager playlistManager = new PlaylistManager(playlistDao);


        InitialView initialView = new InitialView();
        RegisterView registerView = new RegisterView();
        LoginView loginView = new LoginView();
        TestView testView = new TestView();
        AddSongView addSongView = new AddSongView();
        DeleteSongView deleteSongView = new DeleteSongView();
        DetailedPlaylistView detailedPlaylistView = new DetailedPlaylistView();
        DetailedSongView detailedSongView = new DetailedSongView();
        GeneralPlaylistView generalPlaylistView = new GeneralPlaylistView();
        GeneralSongListView generalSongListView = new GeneralSongListView();
        MainMenuView mainMenuView = new MainMenuView();
        StatisticsView statisticsView = new StatisticsView();


        MainView mainView = new MainView(initialView, deleteSongView, statisticsView, mainMenuView, generalPlaylistView, generalSongListView, detailedSongView, detailedPlaylistView, registerView, loginView, testView, addSongView);

        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView, userManager);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView, userManager);
        AddSongViewController addSongViewController = new AddSongViewController(addSongView);
        DeleteSongViewController deleteSongViewController = new DeleteSongViewController(deleteSongView);
        DetailedPlaylistViewController detailedPlaylistViewController = new DetailedPlaylistViewController(detailedPlaylistView);
        DetailedSongViewController detailedSongViewController = new DetailedSongViewController(detailedSongView);
        GeneralSongListViewController generalSongListViewController = new GeneralSongListViewController(generalSongListView);
        GeneralPlaylistViewController generalPlaylistViewController = new GeneralPlaylistViewController(generalPlaylistView);
        MainMenuViewController mainMenuViewController = new MainMenuViewController(mainMenuView);
        TestViewController testViewController = new TestViewController(mainView, userManager);


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
        testView.addTestController(testViewController);

        mainView.start();
    }
}
