package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.InitialViewController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

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
        setBackground(Color.BLACK);

        add(exterior);
    }


    public void logoZone(){
        logo = new JPanel();

        logo.setBackground(Color.black);
        ImageIcon logoLbl = new ImageIcon(String.valueOf(AssetsFiles.LOGO_LABEL));
        Image scaledImageLabel = logoLbl.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIconLabel = new ImageIcon(scaledImageLabel);
        JLabel jLogo = new JLabel(scaledIconLabel);
        jLogo.setMinimumSize(new Dimension(500,150));
        logo.setBorder(createEmptyBorder(150, 0, 0, 0));

        logo.add(jLogo);
    }

    public void buttonsZone(){
        buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setBorder(createEmptyBorder(200, 0, 0, 0));

        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jRegister = new JButton(registerBtn);
        jRegister.setActionCommand(BTN_REGISTER);
        jRegister.setBackground(Color.decode("#00000000"));
        buttons.add(jRegister);

        ImageIcon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        Image imageScaledLogin = loginBtn.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
        ImageIcon iconScaleLogin = new ImageIcon(imageScaledLogin);
        jLogin = new JButton(iconScaleLogin);
        jLogin.setActionCommand(BTN_LOGIN);
        jLogin.setBackground(Color.decode("#00000000"));
        jLogin.setPreferredSize(new Dimension(250,100));
        jLogin.setMinimumSize(new Dimension(250,100));
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
