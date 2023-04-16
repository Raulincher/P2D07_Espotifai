package Presentation.View;

import Presentation.Controller.InitialViewController;
import Presentation.Controller.LoginViewController;

import javax.swing.*;

public class LoginView extends JPanel {
    private JButton jSubmitLogin;

    public void configureLoginView() {
        jSubmitLogin = new JButton("Submit");
    }


    public void addLoginController(LoginViewController loginController){
        jSubmitLogin.addActionListener(loginController);
    }

}
