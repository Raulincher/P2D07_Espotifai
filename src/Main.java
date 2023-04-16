
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
import Presentation.View.InitialView;
import Presentation.View.LoginView;
import Presentation.View.MainView;
import Presentation.View.RegisterView;

import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);
        PlaylistDao playlistDao = new SQLPlaylistDao(remoteConnection);

        InitialView initialView = new InitialView();
        RegisterView registerView = new RegisterView();
        LoginView loginView = new LoginView();
        MainView mainView = new MainView(initialView, registerView, loginView);

        InitialViewController initialViewController = new InitialViewController(mainView);
        LoginViewController loginViewController = new LoginViewController(mainView, loginView);
        RegisterViewController registerViewController = new RegisterViewController(mainView, registerView);

        initialView.addInitialViewController(initialViewController);
        loginView.addLoginController(loginViewController);
        registerView.addRegisterController(registerViewController);


        mainView.start();
    }
}
