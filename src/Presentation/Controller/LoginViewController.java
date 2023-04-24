package Presentation.Controller;

import Business.UserManager;
import Presentation.View.LoginView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<String> data = new ArrayList<>();

        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN :
                mainView.showLoginCard();
                String username = loginView.getJUsername().getText();
                String password = Arrays.toString(loginView.getJPassword().getPassword());
                data.add(username);
                data.add(password);
                if(userManager.isEmpty(data)){
                    loginView.showPopUps("It's Empty!");
                }else{
                    if (userManager.login(data)) {
                        //canviem vista al seguent
                        mainView.showMainMenuCard();
                    }else{
                        loginView.showPopUps("User not found!");
                    }
                }
                break;
            case LoginView.BTN_BACK:
                mainView.showMainCard();
                break;
        }
    }
}
