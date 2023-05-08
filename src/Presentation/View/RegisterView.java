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


    // JTextFields
    private JTextField jtfUsername;
    private JTextField jtfEmail;
    private JPasswordField jtfPassword;
    private JPasswordField jtfRepeatPassword;

    // JButtons
    private JButton jbBack;
    private JButton jbRegister;


    private JButton jSubmitRegister;

    private final Utils utils;

    public RegisterView(Utils utils){
        this.utils = utils;
    }

    public void configureRegisterView() {
        setBackground(Color.black);
        setLayout(new BorderLayout());

        jSubmitRegister = new JButton("Submit");

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


        JLabel label1 = new JLabel(USERNAME_LABEL);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfUsername = new JTextField();
        jtfUsername.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfUsername.setPreferredSize(new Dimension(300, 40));
        jtfUsername.setMinimumSize(new Dimension(300,40));

        JLabel label2 = new JLabel(EMAIL_LABEL);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfEmail = new JTextField();
        jtfEmail.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfEmail.setPreferredSize(new Dimension(300, 40));
        jtfEmail.setMinimumSize(new Dimension(300,40));

        JLabel label3 = new JLabel(PASSWORD_LABEL);
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfPassword = new JPasswordField();
        jtfPassword.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfPassword.setPreferredSize(new Dimension(300, 40));
        jtfPassword.setMinimumSize(new Dimension(300,40));

        JLabel label4 = new JLabel(REPEAT_PASSWORD_LABEL);
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfRepeatPassword = new JPasswordField();
        jtfRepeatPassword.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfRepeatPassword.setPreferredSize(new Dimension(300, 40));
        jtfRepeatPassword.setMinimumSize(new Dimension(300,40));

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
        jbBack = new JButton(backBtn);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.setBackground(Color.decode("#00000000"));

        Icon registerBtn = new ImageIcon(String.valueOf(AssetsFiles.REGISTER_BUTTON_IMG));
        jbRegister = new JButton(registerBtn);
        jbRegister.setActionCommand(BTN_REGISTER);
        jbRegister.setBackground(Color.decode("#00000000"));
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


    public void addRegisterController(RegisterViewController registerController) {
        jbBack.addActionListener(registerController);
        jbRegister.addActionListener(registerController);
    }
}