package Presentation.View;

import Presentation.Controller.AddSongViewController;
import Presentation.Utils;

import javax.swing.*;

public class AddSongView extends JPanel {

    private final Utils utils;

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";

    private JButton jback;
    private JButton jfile;

    public AddSongView(Utils utils){
        this.utils = utils;
    }


    public void addAddSongController(AddSongViewController addSongController){
        jback.addActionListener(addSongController);
        jfile.addActionListener(addSongController);
    }

    public void configureAddSong() {
        JLabel jLogo = new JLabel("add song");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);
        jfile = utils.buttonText("add file");
        jfile.setActionCommand(BTN_ADD_FILE);

        add(jLogo);
        add(jback);
        add(jfile);
    }
}
