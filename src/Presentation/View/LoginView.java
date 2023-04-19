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

        buttonsZoneLogin();
        add(buttonsLogin);
        jSubmitLogin = new JButton("Submit");
        jSubmitBack = new JButton("Back");


    }

    public void buttonsZoneLogin () {
        buttonsLogin = new JPanel();

        jUsername = new JTextField();
        jUsername.setPreferredSize(new Dimension(300, 50));
        buttonsLogin.add(jUsername);

        jPassword = new JPasswordField();
        jUsername.setPreferredSize(new Dimension(300, 50));
        buttonsLogin.add(jPassword);

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
    }

    public void addLoginController(LoginViewController loginController){
        //set action command
        jSubmitLogin.addActionListener(loginController);
        jSubmitBack.addActionListener(loginController);
    }

    public ArrayList<String> getLoginData() {
        ArrayList<String> loginData = new ArrayList<>();
        loginData.add(jUsername.getText());
        loginData.add(String.valueOf(jPassword.getPassword()));
        return loginData;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

}
