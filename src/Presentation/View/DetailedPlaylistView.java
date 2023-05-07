package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DetailedPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class DetailedPlaylistView extends JPanel {

    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;


    public DetailedPlaylistView(Utils utils){
        this.utils = utils;
    }

    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){
        //set action command
        jback.addActionListener(detailedPlaylistViewController);
    }

    public void configureDetailedPlaylistView() {

        JLabel jLogo = new JLabel("detailed playlist");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
