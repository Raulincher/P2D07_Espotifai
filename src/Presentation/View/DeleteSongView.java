package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;

public class DeleteSongView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;

    public static final String BTN_DELETE = "BTN_DELETE";


    private JLabel name;
    private JTextField input;
    private JButton delete;

    public DeleteSongView(HeaderView headerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
    }

    public void addDeleteSongController(DeleteSongViewController deleteSongController){
        delete.addActionListener(deleteSongController);
    }

    public void configureDeleteSongView() {

        Icon deleteSongImg = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        add(headerView.configureHeader(deleteSongImg));

        name = utils.label("Song name");

        input = utils.textField();

        Icon deleteBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETESONG_BUTTON_IMG));
        delete = utils.buttonImg(deleteBtn);
        delete.setActionCommand(BTN_DELETE);

        add(name);
        add(input);
        add(delete);

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

    public JTextField getInput() {
        return input;
    }

}
