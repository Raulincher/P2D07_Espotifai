package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class DeleteSongView extends JPanel {

    private final Utils utils;

    private final HeaderView headerView;
    private JButton jback;
    public static final String BTN_BACK = "BTN_BACK";

    public DeleteSongView(Utils utils, HeaderView headerView){
        this.utils = utils;
        this.headerView = headerView;
    }

    public void addDeleteSongController(DeleteSongViewController DeleteSongController){
        //set action command
        //jback.addActionListener(DeleteSongController);
    }

    public void configureDeleteSongView() {
        //JLabel jLogo = new JLabel("delete song");

        // EL JPanel en si de deleteSong
        setBackground(Color.black);
        setLayout(new BorderLayout()); // Fem que sigui border layout

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon deleteSongImg = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        north.add(headerView.configureHeader(deleteSongImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);


        /*jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);

        add(jLogo);
        add(jback);*/
    }
}
