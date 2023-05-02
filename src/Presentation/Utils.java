package Presentation;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Utils extends JFrame {

    private static JButton jLogOut;
    private static JButton jDeleteAcc;

    public static final String BTN_LOGOUT = "BTN_LOGOUT";

    public static final String BTN_BACK = "BTN_BACK";

    public static final String BTN_DELETEACC = "BTN_DELETEACC";


    public static JPanel header(JPanel north, JButton jBack, Icon labelImg) {
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(40, 0, 0, 0));

        JLabel usedLabel = new JLabel(labelImg);
        usedLabel.setLayout(new GridLayout(4, 1, 0, 50));
        north.add(usedLabel);

        Icon loginBtn2 = new ImageIcon(String.valueOf(AssetsFiles.BACK_LITTLEBUTTON_IMG));
        jBack.setIcon(loginBtn2);
        jBack.setActionCommand(BTN_BACK);
        jBack.setBackground(Color.decode("#00000000"));

        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGOUT_LITTLEBUTTON_IMG));
        jLogOut = new JButton(loginBtn);
        jLogOut.setActionCommand(BTN_LOGOUT);
        jLogOut.setBackground(Color.decode("#00000000"));

        Icon loginBtn3 = new ImageIcon(String.valueOf(AssetsFiles.DELETEACC_LITTLEBUTTON_IMG));
        jDeleteAcc = new JButton(loginBtn3);
        jDeleteAcc.setActionCommand(BTN_DELETEACC);
        jDeleteAcc.setBackground(Color.decode("#00000000"));

        north.add(jBack);
        north.add(jLogOut);
        north.add(jDeleteAcc);

        return north;
    }
}
