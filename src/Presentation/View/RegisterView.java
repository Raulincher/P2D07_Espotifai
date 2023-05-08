package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.RegisterViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class RegisterView extends JPanel {

    // Action commands
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    // Texts
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



    // JTextFields
    private JTextField jtfUsername;
    private JTextField jtfEmail;
    private JPasswordField jtfPassword;
    private JPasswordField jtfRepeatPassword;

    // JButtons
    private JButton jbBack;
    private JButton jbRegister;

    private final Utils utils;

    public RegisterView(Utils utils){
        this.utils = utils;
    }

    public void configureRegisterView() {
        setBackground(Color.black);
        setLayout(new BorderLayout());

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(north, BorderLayout.NORTH);


        ImageIcon labelIcon = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_LABEL));
        JLabel signUpLabel = new JLabel(labelIcon);
        north.add(signUpLabel);

        // CENTER
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.black);
        add(center, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;


        JLabel label1 = utils.label(USERNAME_LABEL);
        jtfUsername = utils.textField();

        JLabel label2 = utils.label(EMAIL_LABEL);
        jtfEmail = utils.textField();

        JLabel label3 = utils.label(PASSWORD_LABEL);
        jtfPassword = utils.passwordField();

        JLabel label4 = utils.label(REPEAT_PASSWORD_LABEL);
        jtfRepeatPassword = utils.passwordField();

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

        JPanel south = new JPanel();
        south.setBackground(Color.black);
        south.setBorder(createEmptyBorder(0, 0, 50, 0));

        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_BUTTON_IMG));
        jbBack = utils.buttonImg(backBtn);
        jbBack.setActionCommand(BTN_BACK);

        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jbRegister = utils.buttonImg(registerBtn);
        jbRegister.setActionCommand(BTN_REGISTER);

        south.add(jbBack);
        south.add(jbRegister);
        add(south, BorderLayout.SOUTH);
    }

    // TODO: Es pot fer amb getters o no?
    public JTextField getJtfUsername() {
        return jtfUsername;
    }

    public JTextField getJtfEmail() {
        return jtfEmail;
    }

    public JPasswordField getJtfPassword() {
        return jtfPassword;
    }

    public JPasswordField getJtfRepeatPassword() {
        return jtfRepeatPassword;
    }

    public void emptyFields() {
        JOptionPane.showMessageDialog(this, EMPTY_FIELDS_MESSAGE);
    }

    public void differentPassword() {
        JOptionPane.showMessageDialog(this, DIFFERENT_PASSWORD_MESSAGE);
    }

    public void userExistence() {
        JOptionPane.showMessageDialog(this, USER_EXISTENCE_MESSAGE);
    }

    public void mailError() {
        JOptionPane.showMessageDialog(this, MAIL_ERROR);
    }

    public void passwordError() {
        JOptionPane.showMessageDialog(this, PASSWORD_ERROR);
    }


    public void addRegisterController(RegisterViewController registerController) {
        jbBack.addActionListener(registerController);
        jbRegister.addActionListener(registerController);
    }
}