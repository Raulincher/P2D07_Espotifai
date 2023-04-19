package Presentation.View;

import Presentation.Controller.AddSongViewController;
import Presentation.Controller.GeneralPlaylistViewController;

import javax.swing.*;

public class GeneralPlaylistView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";

    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
        //set action command

    }

    public void configureGeneralPlaylistView() {
        JLabel jLogo = new JLabel("general playlist");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
