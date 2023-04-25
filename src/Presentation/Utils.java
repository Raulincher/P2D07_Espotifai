package Presentation;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Utils extends JFrame {

    private static JButton jLogOut;
    private static JButton jDeleteAcc;

    public static final String BTN_LOGIN = "BTN_LOGIN";

    public static final String BTN_BACK = "BTN_BACK";


    public static JPanel header(JPanel north, JButton jBack) {
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(100, 0, 0, 0));


        Icon loginImg = new ImageIcon(String.valueOf(AssetsFiles.LOGIN_LABEL));
        JLabel loginLabel = new JLabel(loginImg);
        loginLabel.setLayout(new GridLayout(4, 1, 0, 50));
        north.add(loginLabel);

        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGOUT_LITTLEBUTTON_IMG));
        jLogOut = new JButton(loginBtn);
        jLogOut.setActionCommand(BTN_LOGIN);
        jLogOut.setBackground(Color.decode("#00000000"));

        Icon loginBtn2 = new ImageIcon(String.valueOf(AssetsFiles.BACK_LITTLEBUTTON_IMG));
        jBack.setIcon(loginBtn2);
        jBack.setActionCommand(BTN_BACK);
        jBack.setBackground(Color.decode("#00000000"));

        Icon loginBtn3 = new ImageIcon(String.valueOf(AssetsFiles.DELETEACC_LITTLEBUTTON_IMG));
        jDeleteAcc = new JButton(loginBtn3);
        jDeleteAcc.setActionCommand(BTN_LOGIN);
        jDeleteAcc.setBackground(Color.decode("#00000000"));

        north.add(jLogOut);
        north.add(jBack);
        north.add(jDeleteAcc);

        return north;
    }
}
