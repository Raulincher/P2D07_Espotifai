package Presentation.View;

import Presentation.Controller.DetailedSongViewController;
import Presentation.Controller.GeneralSongListViewController;
import Presentation.Utils;

import javax.swing.*;

public class GeneralSongListView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback;
    private final Utils utils;

    public GeneralSongListView(Utils utils){
        this.utils = utils;
    }

    public void addGeneralSongListController(GeneralSongListViewController generalSongListViewController){
        //set action command
        jback.addActionListener(generalSongListViewController);

    }

    public void configureGeneralSonglistView() {
        JLabel jLogo = new JLabel("general song list");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
