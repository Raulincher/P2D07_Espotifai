package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.InitialViewController;

import javax.swing.*;
import java.awt.*;

public class InitialView extends JPanel {

    private JButton jLogin;
    private JButton jRegister;
    private JButton jTest;


    private JPanel logo;
    private JPanel buttons;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_REGISTER = "BTN_REGISTER";
    public static final String BTN_TEST = "BTN_TEST";



    public void configureInitialView(){

        JPanel exterior = new JPanel();

        exterior.setBackground(Color.black);

        buttonsZone();
        logoZone();


        exterior.setLayout(new BoxLayout(exterior, BoxLayout.PAGE_AXIS));
        exterior.add(logo);
        exterior.add(buttons);

        add(exterior);

    }


    public void logoZone(){
        logo = new JPanel();

        Icon logoLbl = new ImageIcon(String.valueOf(AssetsFiles.LOGO_LABEL));
        JLabel jLogo = new JLabel(logoLbl);

        logo.add(jLogo);
    }

    public void buttonsZone(){
        buttons = new JPanel();

        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jRegister = new JButton(registerBtn);
        jRegister.setActionCommand(BTN_REGISTER);
        jRegister.setBackground(Color.decode("#00000000"));
        buttons.add(jRegister);

        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        jLogin = new JButton(loginBtn);
        jLogin.setActionCommand(BTN_LOGIN);
        jLogin.setBackground(Color.decode("#00000000"));
        buttons.add(jLogin);

        jTest = new JButton("Test");
        jTest.setActionCommand(BTN_TEST);
        jTest.setFont(new Font("Helvetica", Font.ITALIC, 20));
        jTest.setMaximumSize(new Dimension (200,100));
        buttons.add(jTest);
    }


    public void addInitialViewController(InitialViewController initialViewController){
        jLogin.addActionListener(initialViewController);
        jRegister.addActionListener(initialViewController);
        jTest.addActionListener(initialViewController);

    }


}
