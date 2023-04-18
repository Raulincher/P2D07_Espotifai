package Presentation.View;

import Presentation.Controller.InitialViewController;
import Presentation.Controller.LoginViewController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginView extends JPanel {
    private JButton jSubmitLogin;

    private JButton jSubmitBack;

    private JTextField jUsername;

    private JPasswordField jPassword;

    private JPanel buttonsLogin;

    public static final String BTN_LOGIN = "BTN_LOGIN";

    public static final String BTN_BACK = "BTN_BACK";
    public void configureLoginView() {

        JPanel layout = new JPanel();

        buttonsZoneLogin();
        layout.add(buttonsLogin);
        add(layout);
    }

    public void buttonsZoneLogin () {
        buttonsLogin = new JPanel();

        jSubmitLogin = new JButton("Login");
        jSubmitLogin.setActionCommand(BTN_LOGIN);
        jSubmitLogin.setFont(new Font("Helvetica", Font.ITALIC, 20));
        jSubmitLogin.setMaximumSize(new Dimension(200,100));
        buttonsLogin.add(jSubmitLogin);

        jSubmitBack = new JButton("Back");
        jSubmitBack.setActionCommand(BTN_BACK);
        jSubmitBack.setFont(new Font("Helvetica", Font.ITALIC, 20));
        jSubmitBack.setMaximumSize(new Dimension(200,100));
        buttonsLogin.add(jSubmitBack);

        jUsername = new JTextField();
        jUsername.setMaximumSize(new Dimension(300, 50));

        jPassword = new JPasswordField();
        jUsername.setMaximumSize(new Dimension(300, 50));
    }

    public void addLoginController(LoginViewController loginController){
        jSubmitLogin.addActionListener(loginController);
        jSubmitBack.addActionListener(loginController);
    }

    public ArrayList<String> getLoginData() {
        ArrayList<String> loginData = new ArrayList<>();
        loginData.add(jUsername.getText());
        loginData.add(String.valueOf(jPassword.getPassword()));
        return loginData;
    }

}
