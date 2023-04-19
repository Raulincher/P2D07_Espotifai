package Presentation.Controller;

import Presentation.View.InitialView;
import Presentation.View.LoginView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {

    private final MainView mainView;
    private final LoginView loginView;

    public LoginViewController(MainView mainView, LoginView loginView) {
        this.mainView = mainView;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LoginView.BTN_BACK -> mainView.showLoginCard();
            case LoginView.BTN_LOGIN -> System.out.println("log in");
        }
    }
}
