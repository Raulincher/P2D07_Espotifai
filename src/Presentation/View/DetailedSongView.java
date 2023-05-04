package Presentation.View;

import Presentation.Controller.DetailedSongViewController;
import Presentation.Utils;

import javax.swing.*;

public class DetailedSongView extends JPanel {
    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;

    public DetailedSongView(Utils utils){
        this.utils = utils;
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController){
        //set action command
        jback.addActionListener(detailedSongViewController);

    }

    public void configureDetailedSongView() {

        JLabel jLogo = new JLabel("detailed song");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
