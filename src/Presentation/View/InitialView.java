package Presentation.View;

import Presentation.Controller.InitialViewController;

import javax.swing.*;
import java.awt.*;

public class InitialView extends JPanel {

    private JButton jLogin;
    private JButton jRegister;

    private JPanel logo;
    private JPanel buttons;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_REGISTER = "BTN_REGISTER";


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

        ImageIcon space0 = new ImageIcon("Media/placeholder.png");
        JLabel jLogo = new JLabel("", space0, JLabel.CENTER);

        logo.add(jLogo);
    }

    public void buttonsZone(){
        buttons = new JPanel();

        jLogin = new JButton("Login");
        jRegister = new JButton("Register");

        jLogin.setActionCommand(BTN_LOGIN);
        jLogin.setFont(new Font("Helvetica", Font.ITALIC, 20));

        jRegister.setActionCommand(BTN_REGISTER);
        jRegister.setFont(new Font("Helvetica", Font.ITALIC, 20));

        jLogin.setMaximumSize(new Dimension(200,100));
        jRegister.setMaximumSize(new Dimension (200,100));

        buttons.add(jRegister);
        buttons.add(jLogin);
    }


    public void addInitialViewController(InitialViewController initialViewController){
        jLogin.addActionListener(initialViewController);
        jRegister.addActionListener(initialViewController);
    }


}
