
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
        FooterView footerView = new FooterView(utils);
        FooterController footerController = new FooterController(footerView, songManager);

        footerView.initializeButtons();
        footerView.addFooterController(footerController);

        FooterView footerView2 = new FooterView(utils);
        FooterController footerController2 = new FooterController(footerView2, songManager);
        footerView2.initializeButtons();
        footerView2.addFooterController(footerController2);

        FooterView footerView3 = new FooterView(utils);
        FooterController footerController3 = new FooterController(footerView3, songManager);
        footerView3.initializeButtons();
        footerView3.addFooterController(footerController3);

        FooterView footerView4 = new FooterView(utils);
        FooterController footerController4 = new FooterController(footerView4, songManager);
        footerView4.initializeButtons();
        footerView4.addFooterController(footerController4);

        FooterView footerView5 = new FooterView(utils);
        FooterController footerController5 = new FooterController(footerView5, songManager);
        footerView5.initializeButtons();
        footerView5.addFooterController(footerController5);

        FooterView footerView6 = new FooterView(utils);
        FooterController footerController6 = new FooterController(footerView6, songManager);
        footerView6.initializeButtons();
        footerView6.addFooterController(footerController6);

        FooterView footerView7 = new FooterView(utils);
        FooterController footerController7 = new FooterController(footerView7, songManager);
        footerView7.initializeButtons();
        footerView7.addFooterController(footerController7);

        FooterView footerView8 = new FooterView(utils);
        FooterController footerController8 = new FooterController(footerView8, songManager);
        footerView8.initializeButtons();
        footerView8.addFooterController(footerController8);

        HeaderView headerView = new HeaderView(utils);
        HeaderController headerController = new HeaderController(headerView, userManager);
        headerView.initilizeButtons();
        headerView.addHeaderController(headerController);

        HeaderView headerView2 = new HeaderView(utils);
        headerView2.initilizeButtons();
        headerView2.addHeaderController(headerController);

        HeaderView headerView3 = new HeaderView(utils);
        headerView3.initilizeButtons();
        headerView3.addHeaderController(headerController);

        HeaderView headerView4 = new HeaderView(utils);
        headerView4.initilizeButtons();
        headerView4.addHeaderController(headerController);

        HeaderView headerView5 = new HeaderView(utils);
        headerView5.initilizeButtons();
        headerView5.addHeaderController(headerController);

        HeaderView headerView6 = new HeaderView(utils);
        headerView6.initilizeButtons();
        headerView6.addHeaderController(headerController);

        HeaderView headerView7 = new HeaderView(utils);
        headerView7.initilizeButtons();
        headerView7.addHeaderController(headerController);

        HeaderView headerView8 = new HeaderView(utils);
        headerView8.initilizeButtons();
        headerView8.addHeaderController(headerController);

        RegisterView registerView = new RegisterView(utils);
        LoginView loginView = new LoginView(utils);
        AddSongView addSongView = new AddSongView(utils, headerView, footerView);
        DeleteSongView deleteSongView = new DeleteSongView(headerView2, utils, footerView2);
        DetailedPlaylistView detailedPlaylistView = new DetailedPlaylistView(headerView8, footerView8, utils);
        DetailedSongView detailedSongView = new DetailedSongView(utils, headerView3, footerView3);
        GeneralPlaylistView generalPlaylistView = new GeneralPlaylistView(headerView4, footerView4, utils);
        GeneralSongListView generalSongListView = new GeneralSongListView(utils, headerView5, footerView5);
        MainMenuView mainMenuView = new MainMenuView(footerView6, utils, headerView6);
        StatisticsView statisticsView = new StatisticsView(headerView7, utils, footerView7, songManager);

        MainView mainView = new MainView(initialView, deleteSongView, statisticsView, mainMenuView, generalPlaylistView, generalSongListView, detailedSongView, detailedPlaylistView, registerView, loginView, addSongView, footerView, headerView);
        headerController.addMainView(mainView);

        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView, userManager,footerView);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView, userManager);
        AddSongViewController addSongViewController = new AddSongViewController(addSongView, mainView, songManager, userManager);
        DeleteSongViewController deleteSongViewController = new DeleteSongViewController(deleteSongView, mainView, songManager, userManager);
        DetailedPlaylistViewController detailedPlaylistViewController = new DetailedPlaylistViewController(detailedPlaylistView, mainView);
        DetailedSongViewController detailedSongViewController = new DetailedSongViewController(detailedSongView, mainView, songManager);
        GeneralSongListViewController generalSongListViewController = new GeneralSongListViewController(generalSongListView, mainView, songManager,detailedSongView);
        GeneralPlaylistViewController generalPlaylistViewController = new GeneralPlaylistViewController(generalPlaylistView, mainView);
        MainMenuViewController mainMenuViewController = new MainMenuViewController(mainMenuView, mainView, songManager, userManager, deleteSongView, generalSongListView);
        StatisticsViewController statisticsViewController = new StatisticsViewController(statisticsView, mainView);

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
