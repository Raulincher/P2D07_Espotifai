package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;

public class DeleteSongView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;

    public DeleteSongView(HeaderView headerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
    }

    public void addDeleteSongController(DeleteSongViewController DeleteSongController){

    }

    public void configureDeleteSongView() {

        Icon deleteSongImg = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        add(headerView.configureHeader(deleteSongImg));








        // EL JPanel en si de deleteSong
        //setBackground(Color.black);
        //setLayout(new BorderLayout()); // Fem que sigui border layout

        // NORTH
        /*JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon deleteSongImg = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        north.add(headerView.configureHeader(deleteSongImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);*/

    }
}
