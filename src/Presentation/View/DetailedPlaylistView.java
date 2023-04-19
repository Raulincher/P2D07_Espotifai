package Presentation.View;

import Presentation.Controller.DetailedPlaylistViewController;

import javax.swing.*;

public class DetailedPlaylistView extends JPanel {


    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback = new JButton("back");


    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){
        //set action command
        jback.addActionListener(detailedPlaylistViewController);
    }

    public void configureDetailedPlaylistView() {

        JLabel jLogo = new JLabel("detailed playlist");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
