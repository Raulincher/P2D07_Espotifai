package Presentation.View;

import Presentation.Controller.AddSongViewController;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;

public class GeneralPlaylistView extends JPanel {

    private final Utils utils;


    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;

    public GeneralPlaylistView(Utils utils){
        this.utils = utils;
    }

    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
        //set action command
        jback.addActionListener(generalPlaylistViewController);

    }

    public void configureGeneralPlaylistView() {
        JLabel jLogo = new JLabel("general playlist");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
