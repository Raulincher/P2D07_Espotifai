package Presentation.View;

import Presentation.Controller.AddSongViewController;

import javax.swing.*;

public class AddSongView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    private JButton jback = new JButton("back");


    public void addAddSongController(AddSongViewController addSongController){
        jback.addActionListener(addSongController);
    }

    public void configureAddSongView() {
        JLabel jLogo = new JLabel("add song");
        jback.setActionCommand(BTN_BACK);

        add(jLogo);
        add(jback);
    }
}
