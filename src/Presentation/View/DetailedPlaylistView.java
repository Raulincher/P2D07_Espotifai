package Presentation.View;

import Presentation.Controller.DetailedPlaylistViewController;

import javax.swing.*;

public class DetailedPlaylistView extends JPanel {


    public static final String BTN_BACK = "BTN_BACK";


    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){
        //set action command

    }

    public void configureDetailedPlaylistView() {

        JLabel jLogo = new JLabel("detailed playlist");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
