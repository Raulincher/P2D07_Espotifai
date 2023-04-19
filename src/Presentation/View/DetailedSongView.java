package Presentation.View;

import Presentation.Controller.DetailedSongViewController;

import javax.swing.*;

public class DetailedSongView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController){
        //set action command

    }

    public void configureDetailedSongView() {

        JLabel jLogo = new JLabel("detailed song");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
