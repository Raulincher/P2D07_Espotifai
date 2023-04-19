package Presentation.View;

import Presentation.Controller.DeleteSongViewController;

import javax.swing.*;

public class DeleteSongView extends JPanel {


    public static final String BTN_BACK = "BTN_BACK";

    public void addDeleteSongController(DeleteSongViewController DeleteSongController){
        //set action command

    }

    public void configureDeleteSongView() {

        JLabel jLogo = new JLabel("delete song");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
