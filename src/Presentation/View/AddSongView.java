package Presentation.View;

import Presentation.Controller.AddSongViewController;

import javax.swing.*;

public class AddSongView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";

    private JButton jback = new JButton("back");
    private JButton jfile = new JButton("add file");



    public void addAddSongController(AddSongViewController addSongController){
        jback.addActionListener(addSongController);
        jfile.addActionListener(addSongController);
    }

    public void configureAddSong() {
        JLabel jLogo = new JLabel("add song");
        jback.setActionCommand(BTN_BACK);
        jfile.setActionCommand(BTN_ADD_FILE);

        add(jLogo);
        add(jback);
        add(jfile);
    }
}
