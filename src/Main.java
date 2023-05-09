
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
import Presentation.Utils;
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

        Utils utils = new Utils();

        songManager.getSong();

        InitialView initialView = new InitialView(utils);
        FooterView footerView = new FooterView(utils);
        HeaderView headerView = new HeaderView(utils);
        RegisterView registerView = new RegisterView(utils);
        LoginView loginView = new LoginView(utils);
        TestView testView = new TestView();
        AddSongView addSongView = new AddSongView(utils, headerView, footerView);
        DeleteSongView deleteSongView = new DeleteSongView(headerView, utils);
        DetailedPlaylistView detailedPlaylistView = new DetailedPlaylistView(utils);
        DetailedSongView detailedSongView = new DetailedSongView(utils, headerView, footerView);
        GeneralPlaylistView generalPlaylistView = new GeneralPlaylistView(headerView, footerView, utils);
        GeneralSongListView generalSongListView = new GeneralSongListView(utils, headerView, footerView);
        MainMenuView mainMenuView = new MainMenuView(footerView, utils, headerView);
        StatisticsView statisticsView = new StatisticsView(headerView, utils);

        MainView mainView = new MainView(initialView, deleteSongView, statisticsView, mainMenuView, generalPlaylistView, generalSongListView, detailedSongView, detailedPlaylistView, registerView, loginView, testView, addSongView);

        FooterController footerController = new FooterController(footerView, songManager);
        HeaderController headerController = new HeaderController(headerView, userManager, mainView);
        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView, userManager);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView, userManager);
        AddSongViewController addSongViewController = new AddSongViewController(addSongView, mainView, songManager);
        DeleteSongViewController deleteSongViewController = new DeleteSongViewController(deleteSongView, mainView, songManager);
        DetailedPlaylistViewController detailedPlaylistViewController = new DetailedPlaylistViewController(detailedPlaylistView, mainView);
        DetailedSongViewController detailedSongViewController = new DetailedSongViewController(detailedSongView, mainView);
        GeneralSongListViewController generalSongListViewController = new GeneralSongListViewController(generalSongListView, mainView);
        GeneralPlaylistViewController generalPlaylistViewController = new GeneralPlaylistViewController(generalPlaylistView, mainView);
        MainMenuViewController mainMenuViewController = new MainMenuViewController(mainMenuView, mainView);
        StatisticsViewController statisticsViewController = new StatisticsViewController(statisticsView, mainView);

        headerView.addHeaderController(headerController);
        footerView.addFooterController(footerController);
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
        statisticsView.addStatisticsController(statisticsViewController);

        mainView.start();
    }
}
