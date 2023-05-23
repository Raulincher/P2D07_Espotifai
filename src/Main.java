
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

    public static void main(String[] args) throws IOException {

        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);
        PlaylistDao playlistDao = new SQLPlaylistDao(remoteConnection);
        ApiHelper apiHelper = new ApiHelper();
        SongLyricsApi songLyricsApi = new SongLyricsApi(apiHelper);

        UserManager userManager = new UserManager(userDao);
        SongManager songManager = new SongManager(songDao,songLyricsApi);
        PlaylistManager playlistManager = new PlaylistManager(playlistDao);

        Utils utils = new Utils();


        songManager.getSong("");

        InitialView initialView = new InitialView(utils);


        HeaderView headerView = new HeaderView(utils);
        HeaderController headerController = new HeaderController(headerView, userManager);
        headerView.initilizeButtons();
        headerView.addHeaderController(headerController);


        FooterView footerView = new FooterView(utils);
        RegisterView registerView = new RegisterView(utils);
        LoginView loginView = new LoginView(utils);
        AddSongView addSongView = new AddSongView(utils);
        DeleteSongView deleteSongView = new DeleteSongView(utils);
        DetailedPlaylistView detailedPlaylistView = new DetailedPlaylistView(utils);
        DetailedSongView detailedSongView = new DetailedSongView(utils);
        GeneralPlaylistView generalPlaylistView = new GeneralPlaylistView(utils);
        GeneralSongListView generalSongListView = new GeneralSongListView(utils);
        MainMenuView mainMenuView = new MainMenuView(utils);
        StatisticsView statisticsView = new StatisticsView(utils);

        MainView mainView = new MainView(initialView, deleteSongView, statisticsView, mainMenuView, generalPlaylistView, generalSongListView, detailedSongView, detailedPlaylistView, registerView, loginView, addSongView, footerView, headerView);
        headerController.addMainView(mainView);



        FooterController footerController = new FooterController(footerView, songManager);
        footerView.addFooterController(footerController);

        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView, userManager,footerView);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView, userManager);
        AddSongViewController addSongViewController = new AddSongViewController(addSongView, mainView, songManager, userManager);
        DeleteSongViewController deleteSongViewController = new DeleteSongViewController(deleteSongView, mainView, songManager, userManager);
        DetailedPlaylistViewController detailedPlaylistViewController = new DetailedPlaylistViewController(detailedPlaylistView, mainView);
        DetailedSongViewController detailedSongViewController = new DetailedSongViewController(detailedSongView, mainView, songManager);
        GeneralSongListViewController generalSongListViewController = new GeneralSongListViewController(generalSongListView, mainView, songManager,detailedSongView);
        GeneralPlaylistViewController generalPlaylistViewController = new GeneralPlaylistViewController(generalPlaylistView, mainView, playlistManager, userManager, detailedPlaylistView);
        MainMenuViewController mainMenuViewController = new MainMenuViewController(mainMenuView, mainView, songManager, userManager, deleteSongView, generalSongListView, generalPlaylistView, playlistManager);
        StatisticsViewController statisticsViewController = new StatisticsViewController(statisticsView, mainView, songManager);

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
