package Presentation.View;

import Presentation.Controller.DetailedSongViewController;
import Presentation.Controller.GeneralSongListViewController;

import javax.swing.*;

public class GeneralSongListView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback = new JButton("back");

    public void addGeneralSongListController(GeneralSongListViewController generalSongListViewController){
        //set action command
        jback.addActionListener(generalSongListViewController);

    }

    public void configureGeneralSonglistView() {
        JLabel jLogo = new JLabel("general song list");
        JButton jback = new JButton("back");
        jback.setActionCommand(BTN_BACK);


        add(jLogo);
        add(jback);
    }
}
