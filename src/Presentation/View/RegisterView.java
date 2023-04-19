package Presentation.View;

import Presentation.Controller.RegisterViewController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {

    // Action commands
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    // Texts
    private static final String SIGNUP_MESSAGE = "Sign up on @ESPOTYFAI";
    public static final String USERNAME_LABEL = "USERNAME";
    public static final String EMAIL_LABEL = "EMAIL";
    public static final String PASSWORD_LABEL = "PASSWORD";
    public static final String REPEAT_PASSWORD_LABEL = "REPEAT PASSWORD";
    public static final String EMPTY_FIELDS_MESSAGE = "Information missing! Please enter it\n" +
            "before registering.";
    public static final String DIFFERENT_PASSWORD_MESSAGE = "The passwords entered do not match!";

    // JTextFields
    private JTextField jtfUsername;
    private JTextField jtfEmail;
    private JPasswordField jtfPassword;
    private JPasswordField jtfRepeatPassword;

    // JButtons
    private JButton jbBack;
    private JButton jbRegister;


    private JButton jSubmitRegister;

    public void configureRegisterView() {
        setBackground(Color.black);
        setLayout(new BorderLayout());

        jSubmitRegister = new JButton("Submit");

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        JLabel signUpLabel = new JLabel(SIGNUP_MESSAGE);
        signUpLabel.setForeground(Color.GREEN);
        signUpLabel.setFont(new Font("Calibri", Font.BOLD, 30));
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


        JLabel label1 = new JLabel(USERNAME_LABEL);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Graphe Alpha", Font.BOLD, 20));
        jtfUsername = new JTextField();
        jtfUsername.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfUsername.setPreferredSize(new Dimension(200,40));

        JLabel label2 = new JLabel(EMAIL_LABEL);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfEmail = new JTextField();
        jtfEmail.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfEmail.setPreferredSize(new Dimension(200,40));

        JLabel label3 = new JLabel(PASSWORD_LABEL);
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfPassword= new JPasswordField();
        jtfPassword.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfPassword.setPreferredSize(new Dimension(200,40));

        JLabel label4 = new JLabel(REPEAT_PASSWORD_LABEL);
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfRepeatPassword = new JPasswordField();
        jtfRepeatPassword.setFont(new Font("Calibri", Font.BOLD, 20));
        jtfRepeatPassword.setPreferredSize(new Dimension(200,40));

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

        jbBack = new JButton("BACK");
        jbBack.setActionCommand(BTN_BACK);
        jbBack.setPreferredSize(new Dimension(100,30));

        jbRegister = new JButton("REGISTER");
        jbRegister.setActionCommand(BTN_REGISTER);
        jbRegister.setPreferredSize(new Dimension(100,30));
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

    public void addRegisterController(RegisterViewController registerController){
        jbBack.addActionListener(registerController);
        jbRegister.addActionListener(registerController);
    }
}