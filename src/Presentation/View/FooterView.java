package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.FooterController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class FooterView extends JPanel {

    private final Utils utils;

    public static final String BTN_PLAY = "BTN_PLAY";
    public static final String BTN_FORWARD = "BTN_FORWARD";
    public static final String BTN_BACKWARD = "BTN_BACKWARD";
    public static final String BTN_REPEAT = "BTN_REPEAT";
    public static final String BTN_REPEAT_LIST = "BTN_REPEAT_LIST";

    private JButton jplay;
    private JButton jForward;
    private JButton jBackward;
    private JButton jRepeat;
    private JButton jRepeatList;

    public FooterView(Utils utils){
        this.utils = utils;
    }

    public void addFooterController(FooterController footerController){
        //set action command
        jplay.addActionListener(footerController);
        jForward.addActionListener(footerController);
        jBackward.addActionListener(footerController);
        jRepeat.addActionListener(footerController);
        jRepeatList.addActionListener(footerController);
    }

    public JPanel configureFooter() {
        Color gris = new Color(26,26,26);
        JPanel footer = new JPanel();
        JLabel jLogo = new JLabel("song name");
        footer.setBackground(gris);

        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        Icon forwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_NEXTBUTTON_IMG));
        Icon backwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_OLDBUTTON_IMG));
        Icon repeatBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEATBUTTON_IMG));
        Icon repeatListBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEAT_PLAYLIST_BUTTON_IMG));

        jplay = utils.buttonImg(playBtn);
        jplay.setBackground(gris);
        jForward = utils.buttonImg(forwardBtn);
        jForward.setBackground(gris);
        jBackward = utils.buttonImg(backwardBtn);
        jBackward.setBackground(gris);
        jRepeat = utils.buttonImg(repeatBtn);
        jRepeat.setBackground(gris);
        jRepeatList = utils.buttonImg(repeatListBtn);
        jRepeatList.setBackground(gris);

        jplay.setActionCommand(BTN_PLAY);
        jForward.setActionCommand(BTN_FORWARD);
        jBackward.setActionCommand(BTN_BACKWARD);
        jRepeat.setActionCommand(BTN_REPEAT);
        jRepeatList.setActionCommand(BTN_REPEAT_LIST);

        footer.add(jLogo);
        footer.add(jRepeat);
        footer.add(jBackward);
        footer.add(jplay);
        footer.add(jForward);
        footer.add(jRepeatList);
        return footer;
    }

    public void stop(){
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_OLDBUTTON_IMG));
        jplay.setIcon(playBtn);
    }

    public void start(){
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jplay.setIcon(playBtn);
    }


}
