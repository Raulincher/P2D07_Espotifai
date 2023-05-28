package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.RegisterViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class RegisterView extends JPanel {

    // Activem els action commands
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    // Preparem els texts
    public static final String USERNAME_LABEL = "USERNAME";
    public static final String EMAIL_LABEL = "EMAIL";
    public static final String PASSWORD_LABEL = "PASSWORD";
    public static final String REPEAT_PASSWORD_LABEL = "REPEAT PASSWORD";
    public static final String EMPTY_FIELDS_MESSAGE = "Information missing! Please enter it\n" +
            "before registering.";
    public static final String DIFFERENT_PASSWORD_MESSAGE = "The passwords entered do not match!";
    public static final String USER_EXISTENCE_MESSAGE = "User already exists!";
    public static final String MAIL_ERROR = "Error in mail";
    public static final String PASSWORD_ERROR = "Error in password! Must contain a number, a capital letter and a lower case letter ";

    // Preparem els components de Swing
    private JTextField jtfUsername;
    private JTextField jtfEmail;
    private JPasswordField jtfPassword;
    private JPasswordField jtfRepeatPassword;

    // Iniciem els JButtons
    private JButton jbBack;
    private JButton jbRegister;

    private final Utils utils;

    /**
     * Funció que servirà com a constructor de RegisterView
     *
     * @param utils, per a utilitzar els seus mètodes
     */
    public RegisterView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a configurar la RegisterView
     * i tots els seus components de Swing
     *
     * No tindrà ni param ni return
     */
    public void configureRegisterView() {
        // Preparem el BorderLayout i configurem background
        setBackground(Color.black);
        setLayout(new BorderLayout());

        // NORTH
        // Iniciem JPanel del nord i el configurem amb el FlowLayout
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(north, BorderLayout.NORTH);

        // Activem el JLabel del register i l'afegim
        ImageIcon labelIcon = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_LABEL));
        JLabel signUpLabel = new JLabel(labelIcon);
        north.add(signUpLabel);

        // CENTER
        // Iniciem el JPanel del center i el configurem amb el GridBagLayout
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.black);
        add(center, BorderLayout.CENTER);

        // Preparem les GridBagConstraints per a ordenar els elements
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        // Afegim el JLabel del username i el seu respectiu JTextField
        JLabel label1 = utils.label(USERNAME_LABEL);
        jtfUsername = utils.textField();

        // Afegim el JLabel del email i el seu respectiu JTextField
        JLabel label2 = utils.label(EMAIL_LABEL);
        jtfEmail = utils.textField();

        // Afegim el JLabel de la password i el seu respectiu JPasswordField
        JLabel label3 = utils.label(PASSWORD_LABEL);
        jtfPassword = utils.passwordField();

        // Afegim el JLabel de la repetició de la password i el seu JPasswordField
        JLabel label4 = utils.label(REPEAT_PASSWORD_LABEL);
        jtfRepeatPassword = utils.passwordField();

        // Afegim per ordre els components
        center.add(label1, constraints);
        constraints.gridy++;
        center.add(jtfUsername, constraints);
        constraints.gridy++;
        center.add(label2, constraints);
        constraints.gridy++;
        center.add(jtfEmail, constraints);
        constraints.gridy++;
        center.add(label3, constraints);
        constraints.gridy++;
        center.add(jtfPassword, constraints);
        constraints.gridy++;
        center.add(label4, constraints);
        constraints.gridy++;
        center.add(jtfRepeatPassword, constraints);

        // SOUTH
        // Iniciem el JPanel i el configurem amb el FlowLayout
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.setBackground(Color.black);
        south.setBorder(createEmptyBorder(0, 0, 50, 0));

        // Creem el botó de back amb el seu JButton
        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_BUTTON_IMG));
        jbBack = utils.buttonImg(backBtn);
        jbBack.setActionCommand(BTN_BACK);

        // Creem el botó de sign up amb el seu JButton
        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jbRegister = utils.buttonImg(registerBtn);
        jbRegister.setActionCommand(BTN_REGISTER);

        // Afegim tots els components al JPanel i BorderLayout
        south.add(jbBack);
        south.add(jbRegister);
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * en el username
     *
     * @return jtfUsername, usuari que haurà introduït el propi usuari
     */
    public JTextField getJtfUsername() {
        return jtfUsername;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * en el email
     *
     * @return jtfEmail, email que haurà introduït l'usuari
     */
    public JTextField getJtfEmail() {
        return jtfEmail;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * en el password
     *
     * @return jtfPassword, contrasenya que haurà introduït l'usuari
     */
    public JPasswordField getJtfPassword() {
        return jtfPassword;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * en el repeat password
     *
     * @return jtfRepeatPassword, confirmació de contrasenya que haurà introduït l'usuari
     */
    public JPasswordField getJtfRepeatPassword() {
        return jtfRepeatPassword;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * en el username
     */
    public void emptyFields() {
        JOptionPane.showMessageDialog(this, EMPTY_FIELDS_MESSAGE);
    }

    /**
     * Funció que servirà per a mostrar el pop up en cas
     * que s'hagi introduït dues contrasenyes diferents
     */
    public void differentPassword() {
        JOptionPane.showMessageDialog(this, DIFFERENT_PASSWORD_MESSAGE);
    }

    /**
     * Funció que servirà per a mostrar el pop up en cas
     * que s'hagi introduït un usuari ja existent
     */
    public void userExistence() {
        JOptionPane.showMessageDialog(this, USER_EXISTENCE_MESSAGE);
    }

    /**
     * Funció que servirà per a mostrar el pop up en cas
     * que s'hagi introduït un email erroni
     */
    public void mailError() {
        JOptionPane.showMessageDialog(this, MAIL_ERROR);
    }

    /**
     * Funció que servirà per a mostrar el pop up en cas
     * que no s'hagi respectat les normes de la contrasenya
     */
    public void passwordError() {
        JOptionPane.showMessageDialog(this, PASSWORD_ERROR);
    }

    /**
     * Funció que servirà per a connectar amb el seu
     * controller i activar els listeners
     *
     * @param registerController, controller del Register
     */
    public void addRegisterController(RegisterViewController registerController) {
        jbBack.addActionListener(registerController);
        jbRegister.addActionListener(registerController);
    }

    /**
     * Mètode que neteja els camps un cop l'usuari s'ha registrat.
     */
    public void clearFields() {
        jtfEmail.setText("");
        jtfPassword.setText("");
        jtfRepeatPassword.setText("");
        jtfUsername.setText("");

    }
}