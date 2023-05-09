package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DeleteSongView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    public static final String BTN_DELETE = "BTN_DELETE";


    private JLabel name;
    private JTextField input;
    private JButton delete;

    public DeleteSongView(HeaderView headerView, Utils utils, FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    public void addDeleteSongController(DeleteSongViewController deleteSongController){
        delete.addActionListener(deleteSongController);
    }

    public void configureDeleteSongView() {

        // EL JPanel en si de addSong

        setLayout(new BorderLayout()); // Fem que sigui border layout

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon addSongImg = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        north.add(headerView.configureHeader(addSongImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.black);

        Insets lineSpacing = new Insets(20, 0, 20, 0);
        GridBagConstraints deleteBox = new GridBagConstraints();
        deleteBox.gridy = 0;
        deleteBox.gridx = 0;
        deleteBox.anchor = GridBagConstraints.CENTER;
        deleteBox.insets = lineSpacing;

        name = utils.label("Song name");
        input = utils.textField();
        Icon deleteBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETESONG_BUTTON_IMG));
        delete = utils.buttonImg(deleteBtn);
        delete.setActionCommand(BTN_DELETE);

        center.add(name, deleteBox);
        deleteBox.gridy++;
        center.add(input, deleteBox);
        deleteBox.gridy++;
        center.add(delete, deleteBox);
        deleteBox.gridy++;

        add(center, BorderLayout.CENTER);

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

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

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

}
