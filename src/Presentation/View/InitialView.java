package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.InitialViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class InitialView extends JPanel {

    // Afegim atributs i components
    private Utils utils;

    private JButton jLogin;
    private JButton jRegister;

    private JPanel logo;
    private JPanel buttons;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    /**
     * Funció que servirà com a constructor d'InitialView
     *
     * @param utils, per connectar amb la classe utils i els seus mètodes
     */
    public InitialView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a configurar la vista i
     * generar totes les funcions de Swing
     *
     * No tindrà ni param ni return
     */
    public void configureInitialView(){

        // Creem el JPanel general
        JPanel exterior = new JPanel();

        // Afegim els dos sub-JPanels creats amb diferents funcions
        buttonsZone();
        logoZone();

        // Creem el boxLayout i afegim els sub-JPanels al gran JPanel
        exterior.setLayout(new BoxLayout(exterior, BoxLayout.PAGE_AXIS));
        exterior.add(logo);
        exterior.add(buttons);

        // Configurem background i ho afegim a la vista
        setBackground(Color.BLACK);
        add(exterior);
    }

    /**
     * Funció que servirà per a representar el JPanel
     * superior de la vista d'Initial View
     *
     * No tindrà ni param ni return
     */
    public void logoZone(){

        // Creem el JPanel i el configurem
        logo = new JPanel();
        logo.setBackground(Color.black);
        logo.setBorder(createEmptyBorder(150, 0, 0, 0));

        // Creem una ImageIcon per afegir el JLabel
        ImageIcon logoLbl = new ImageIcon(String.valueOf(AssetsFiles.LOGO_LABEL));
        JLabel jLogo = new JLabel(logoLbl);
        jLogo.setMinimumSize(new Dimension(500,150));

        // Li afegim el JLabel
        logo.add(jLogo);
    }

    /**
     * Funció que servirà per a representar el JPanel
     * inferior de la vista d'Initial View
     *
     * No tindrà ni param ni return
     */
    public void buttonsZone(){

        // Creem el JPanel i el configurem
        buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setBorder(createEmptyBorder(200, 0, 0, 0));

        // Afegim com a icona el JButton de Sign Up
        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jRegister = utils.buttonImg(registerBtn);
        jRegister.setActionCommand(BTN_REGISTER);
        buttons.add(jRegister);

        // Afegim com a icona el JButton de Log In
        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        jLogin = utils.buttonImg(loginBtn);
        jLogin.setActionCommand(BTN_LOGIN);
        buttons.add(jLogin);
    }

    /**
     * Funció que servirà per a afegir els listeners als components
     * de la vista que ho necessitin
     *
     * @param initialViewController, que servirà per a vincular-ho amb el seu controller
     */
    public void addInitialViewController(InitialViewController initialViewController){
        jLogin.addActionListener(initialViewController);
        jRegister.addActionListener(initialViewController);
    }
}
