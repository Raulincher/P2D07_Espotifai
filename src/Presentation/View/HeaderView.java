package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class HeaderView extends JPanel {

    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_DELETEACC = "BTN_DELETEACC";

    private final Utils utils;

    private JButton jLogOut;
    private JButton jDeleteAcc;
    private JButton jBack;

    public HeaderView(Utils utils){
        this.utils = utils;
    }



    public void addHeaderController(HeaderController headerController){
        //set action command
        jLogOut.addActionListener(headerController);
        jDeleteAcc.addActionListener(headerController);
        jBack.addActionListener(headerController);

    }

    public JPanel configureHeader(Icon icon) {
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(40, 0, 0, 0));

        JLabel usedLabel = new JLabel(icon);
        usedLabel.setLayout(new GridLayout(4, 1, 0, 50));
        north.add(usedLabel);

        //Icon menuImg = new ImageIcon(String.valueOf(AssetsFiles.MENU_LABEL));

        Icon loginBtn2 = new ImageIcon(String.valueOf(AssetsFiles.BACK_LITTLEBUTTON_IMG));
        jBack = utils.buttonImg(loginBtn2);
        jBack.setActionCommand(BTN_BACK);


        Icon loginBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGOUT_LITTLEBUTTON_IMG));
        jLogOut = utils.buttonImg(loginBtn);
        jLogOut.setActionCommand(BTN_LOGOUT);

        Icon loginBtn3 = new ImageIcon(String.valueOf(AssetsFiles.DELETEACC_LITTLEBUTTON_IMG));
        jDeleteAcc = utils.buttonImg(loginBtn3);
        jDeleteAcc.setActionCommand(BTN_DELETEACC);

        north.add(jBack);
        north.add(jLogOut);
        north.add(jDeleteAcc);

        return north;
    }


}
