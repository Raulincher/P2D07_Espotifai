package Presentation.View;

import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;

public class DeleteSongView extends JPanel {

    private final Utils utils;

    private JButton jback;
    public static final String BTN_BACK = "BTN_BACK";

    public DeleteSongView(Utils utils){
        this.utils = utils;
    }

    public void addDeleteSongController(DeleteSongViewController DeleteSongController){
        //set action command
        jback.addActionListener(DeleteSongController);
    }

    public void configureDeleteSongView() {
        JLabel jLogo = new JLabel("delete song");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);

        add(jLogo);
        add(jback);
    }
}
