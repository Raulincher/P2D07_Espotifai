
import Business.Entities.User;
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
import Presentation.Controller.InitialViewController;
import Presentation.Controller.LoginViewController;
import Presentation.Controller.RegisterViewController;
import Presentation.Controller.TestViewController;
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
        MainView mainView = new MainView(initialView, registerView, loginView, testView);

        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView);
        TestViewController testViewController = new TestViewController(mainView, userManager);


        initialView.addInitialViewController(initialViewController);
        loginView.addLoginController(loginViewController);
        registerView.addRegisterController(registerViewController);
        testView.addTestController(testViewController);

        if(userManager.UserExistence()){

            System.out.println("Existo 1");

        }

        userManager.Delete();

        if(userManager.UserExistence()){

            System.out.println("Existo 2");

        }
        mainView.start();
    }
}
