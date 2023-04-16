package Presentation.View;

import Presentation.Controller.TestViewController;

import javax.swing.*;
import java.awt.*;

public class TestView extends JPanel {

    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";


    private JButton jLogout;
    private JButton jDeleteAccount;


    public void configureTestView() {
        jLogout = new JButton("Log out");
        jDeleteAccount = new JButton("Delete account");

        jLogout.setActionCommand(BTN_LOGOUT);
        jLogout.setFont(new Font("Helvetica", Font.ITALIC, 20));

        jDeleteAccount.setActionCommand(BTN_DELETE);
        jDeleteAccount.setFont(new Font("Helvetica", Font.ITALIC, 20));

        add(jLogout);
        add(jDeleteAccount);
    }


    public void addTestController(TestViewController testController){
        jLogout.addActionListener(testController);
        jDeleteAccount.addActionListener(testController);
    }
}
