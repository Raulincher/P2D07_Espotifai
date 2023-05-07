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
    private JButton jSubmitLogin;

    private JButton jSubmitBack2 = new JButton();

    private JButton jSubmitBack;
    private JTextField jUsername;


    private JPasswordField jPassword;

    public static final String BTN_LOGIN = "BTN_LOGIN";

    public static final String BTN_BACK = "BTN_BACK";

    private final Utils utils;

    public LoginView(Utils utils){
        this.utils = utils;
    }

    public void configureLoginView() {

        setLayout(new BorderLayout());

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(north, BorderLayout.NORTH);


        ImageIcon labelIcon = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_LABEL));
        Image scaledImageLabel = labelIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIconLabel = new ImageIcon(scaledImageLabel);
        JLabel loginLabel = new JLabel(scaledIconLabel);
        north.add(loginLabel);

        //Utils.header(north, jSubmitBack2);
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.black);
        add(center, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel label1 = new JLabel("USERNAME");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Gotham", Font.BOLD, 20));
        jUsername = new JTextField();
        jUsername.setPreferredSize(new Dimension(300, 40));
        jUsername.setMinimumSize(new Dimension(300,40));

        JLabel label2 = new JLabel("PASSWORD");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Gotham", Font.BOLD, 20));
        jPassword= new JPasswordField();
        jPassword.setPreferredSize(new Dimension(300,40));
        jPassword.setMinimumSize(new Dimension(300,40));

        center.add(label1, constraints);
        constraints.gridy++;
        center.add(jUsername, constraints);
        constraints.gridy++;
        center.add(label2, constraints);
        constraints.gridy++;
        center.add(jPassword, constraints);

        JPanel south = new JPanel();
        south.setBackground(Color.black);
        south.setBorder(createEmptyBorder(0, 0, 100, 0));

        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_BUTTON_IMG));
        jSubmitBack = new JButton(backBtn);
        jSubmitBack.setActionCommand(BTN_BACK);
        jSubmitBack.setBackground(Color.decode("#00000000"));

        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_BUTTON_IMG));
        jSubmitLogin = new JButton(loginBtn);
        jSubmitLogin.setActionCommand(BTN_LOGIN);
        jSubmitLogin.setBackground(Color.decode("#00000000"));

        south.add(jSubmitBack);
        south.add(jSubmitLogin);
        add(south, BorderLayout.SOUTH);
    }

    public void addLoginController(LoginViewController loginController){
        //set action command
        jSubmitBack.addActionListener(loginController);
        jSubmitLogin.addActionListener(loginController);
    }

    public JTextField getJUsername() {
        return jUsername;
    }

    public JPasswordField getJPassword() {
        return jPassword;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }
}