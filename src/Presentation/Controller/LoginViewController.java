package Presentation.Controller;

import Business.UserManager;
import Presentation.View.LoginView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController implements ActionListener {

    private final MainView mainView;
    private final LoginView loginView;
    private final UserManager userManager;

    public LoginViewController(MainView mainView, LoginView loginView, UserManager userManager) {
        this.mainView = mainView;
        this.loginView = loginView;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CARD_LOGIN")){
            if (userManager.Login(loginView.getLoginData())){
                mainView.showLoginCard();
            }else{
                if (userManager.IsEmpty(loginView.getLoginData())){
                    loginView.showPopUps("It's Empty!");
                }else{
                    loginView.showPopUps("User not found!");
                }
            }
        }else{
            mainView.showMainCard();
        }
    }
}
