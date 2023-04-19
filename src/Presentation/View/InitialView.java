package Presentation.View;

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

        buttonsZone();
        logoZone();


        exterior.setLayout(new BoxLayout(exterior, BoxLayout.PAGE_AXIS));
        exterior.add(logo);
        exterior.add(buttons);

        add(exterior);

    }


    public void logoZone(){
        logo = new JPanel();

        ImageIcon space0 = new ImageIcon("Media/placeholder2.png");
        JLabel jLogo = new JLabel("", space0, JLabel.CENTER);
        jLogo.setMaximumSize(new Dimension (400,300));

        logo.add(jLogo);
    }

    public void buttonsZone(){
        buttons = new JPanel();

        jRegister = new JButton("Register");
        jRegister.setActionCommand(BTN_REGISTER);
        jRegister.setFont(new Font("Helvetica", Font.ITALIC, 20));
        jRegister.setMaximumSize(new Dimension (200,100));
        buttons.add(jRegister);

        jLogin = new JButton("Login");
        jLogin.setActionCommand(BTN_LOGIN);
        jLogin.setFont(new Font("Helvetica", Font.ITALIC, 20));
        jLogin.setMaximumSize(new Dimension(200,100));
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
