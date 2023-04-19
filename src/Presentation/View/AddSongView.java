package Presentation.View;

import Presentation.Controller.AddSongViewController;

import javax.swing.*;

public class AddSongView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";


    public void addAddSongController(AddSongViewController addSongController){
        //set action command

    }

    public void configureAddSongView() {
        JLabel jLogo = new JLabel("add song");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);

        add(jLogo);
        add(jback);
    }
}
