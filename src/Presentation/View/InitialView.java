package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.InitialViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class InitialView extends JPanel {

    private Utils utils;

    private JButton jLogin;
    private JButton jRegister;
    private JButton jTest;

    private JPanel logo;
    private JPanel buttons;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    public InitialView(Utils utils){
        this.utils = utils;
    }

    public void configureInitialView(){

        JPanel exterior = new JPanel();

        buttonsZone();
        logoZone();

        exterior.setLayout(new BoxLayout(exterior, BoxLayout.PAGE_AXIS));
        exterior.add(logo);
        exterior.add(buttons);
        setBackground(Color.BLACK);

        add(exterior);
    }


    public void logoZone(){
        logo = new JPanel();

        logo.setBackground(Color.black);
        ImageIcon logoLbl = new ImageIcon(String.valueOf(AssetsFiles.LOGO_LABEL));
        JLabel jLogo = new JLabel(logoLbl);
        jLogo.setMinimumSize(new Dimension(500,150));
        logo.setBorder(createEmptyBorder(150, 0, 0, 0));

        logo.add(jLogo);
    }

    public void buttonsZone(){
        buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setBorder(createEmptyBorder(200, 0, 0, 0));

        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jRegister = utils.buttonImg(registerBtn);
        jRegister.setActionCommand(BTN_REGISTER);
        buttons.add(jRegister);

        ImageIcon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        jLogin = utils.buttonImg(loginBtn);
        jLogin.setActionCommand(BTN_LOGIN);
        buttons.add(jLogin);
    }


    public void addInitialViewController(InitialViewController initialViewController){
        jLogin.addActionListener(initialViewController);
        jRegister.addActionListener(initialViewController);
    }
}
