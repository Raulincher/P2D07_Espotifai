package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.InitialViewController;
import Presentation.Controller.LoginViewController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Presentation.Utils;

import static javax.swing.BorderFactory.createEmptyBorder;

public class LoginView extends JPanel {

    // Afegim atributs i components
    private JButton jSubmitLogin;
    private JButton jSubmitBack;
    private JTextField jUsername;
    private JPasswordField jPassword;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_BACK = "BTN_BACK";

    private final Utils utils;

    /**
     * Funció que servirà com a constructor de LoginView
     *
     * @param utils, per a vincular amb els mètodes d'utils
     */
    public LoginView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a configurar la vista i
     * generar totes les funcions de Swing
     *
     * No tindrà ni param ni return
     */
    public void configureLoginView() {

        // Creem el BorderLayout
        setLayout(new BorderLayout());

        // NORTH
        // Iniciem el JPanel del nord i el configurem amb el FlowLayout
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(north, BorderLayout.NORTH);

        // Afegim el JLabel del logo
        ImageIcon labelIcon = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_LABEL));
        JLabel loginLabel = new JLabel(labelIcon);
        north.add(loginLabel);

        add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center i el configurem amb el FlowLayout
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.black);
        add(center, BorderLayout.CENTER);

        // Calculem amb el GridBag les mesures per als components que afegirem
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        // Creem el JLabel del Username i el seu respectiu JTextField
        JLabel label1 = utils.label("USERNAME");
        jUsername = utils.textField();

        // Creem el JLabel de la Password i el seu respectiu JPasswordField
        JLabel label2 = utils.label("PASSWORD");
        jPassword = utils.passwordField();

        // Calculem les constraints i anem afegint per ordre
        center.add(label1, constraints);
        constraints.gridy++;
        center.add(jUsername, constraints);
        constraints.gridy++;
        center.add(label2, constraints);
        constraints.gridy++;
        center.add(jPassword, constraints);

        // SOUTH
        // Creem el JPanel del sud i el configurem amb el FlowLayout
        JPanel south = new JPanel();
        south.setBackground(Color.black);
        south.setBorder(createEmptyBorder(0, 0, 100, 0));

        // Afegim la icona del back amb el seu JButton
        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_BUTTON_IMG));
        jSubmitBack = utils.buttonImg(backBtn);
        jSubmitBack.setActionCommand(BTN_BACK);

        // Afegim la icona de login amb el seu JButton
        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        jSubmitLogin = utils.buttonImg(loginBtn);
        jSubmitLogin.setActionCommand(BTN_LOGIN);

        // Afegim tots els components al JPanel
        south.add(jSubmitBack);
        south.add(jSubmitLogin);
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Funció que servirà per a vincular la vista amb el seu
     * controller, i configurar els listeners
     *
     * @param loginController, per a vincular amb el seu controller
     */
    public void addLoginController(LoginViewController loginController){
        jSubmitBack.addActionListener(loginController);
        jSubmitLogin.addActionListener(loginController);
    }

    /**
     * Funció que servirà per a recollir la informació que s'hagi
     * afegit en el JTextField
     *
     * @return jUsername, usuari que haurà introduït el propi usuari
     */
    public JTextField getJUsername() {
        return jUsername;
    }

    /**
     * Funció que servirà per a recollir la informació que s'hagi
     * afegit en el JPasswordField
     *
     * @return jPassword, contrasenya que haurà introduït l'usuari
     */
    public JPasswordField getJPassword() {
        return jPassword;
    }


    /**
     * Funció que servirà per a configurar tots els pop-ups que tindrà
     * la vista
     *
     * @param error, missatge a mostrar
     */
    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }


    public void clearInfo() {
        jUsername.setText("");
        jPassword.setText("");}
}