package Presentation.View;

import Presentation.Controller.FooterController;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;

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
        JPanel footer = new JPanel();
        JLabel jLogo = new JLabel("song name");

        jplay = utils.buttonText("play");
        jForward = utils.buttonText("forward");
        jBackward = utils.buttonText("backward");
        jRepeat = utils.buttonText("repeatSong");
        jRepeatList = utils.buttonText("repeatList");

        jplay.setActionCommand(BTN_PLAY);
        jForward.setActionCommand(BTN_FORWARD);
        jBackward.setActionCommand(BTN_BACKWARD);
        jRepeat.setActionCommand(BTN_REPEAT);
        jRepeatList.setActionCommand(BTN_REPEAT_LIST);

        footer.add(jLogo);
        footer.add(jplay);
        footer.add(jForward);
        footer.add(jBackward);
        footer.add(jRepeat);
        footer.add(jRepeatList);
        return footer;
    }

    public void stop(){
        jplay.setText("stop");
    }

    public void start(){
        jplay.setText("play");
    }


}
