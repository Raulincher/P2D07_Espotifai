package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.AddSongViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class AddSongView extends JPanel {
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";

    private JButton jback;
    private JButton jadd;
    private JButton jfile;

    public AddSongView(Utils utils, HeaderView headerView, FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    public void addAddSongController(AddSongViewController addSongController) {
        //jback.addActionListener(addSongController);
        jfile.addActionListener(addSongController);
    }

    public void configureAddSong() {
        //JLabel jLogo = new JLabel("add song");

        // EL JPanel en si de addSong
        setBackground(Color.black);
        setLayout(new BorderLayout()); // Fem que sigui border layout

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon addSongImg = new ImageIcon(String.valueOf(AssetsFiles.ADDSONG_LABEL));
        north.add(headerView.configureHeader(addSongImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel centerTotal = new JPanel(new GridBagLayout());
        centerTotal.setBackground(Color.BLACK);
        add(centerTotal, BorderLayout.CENTER);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.BLACK);

        // CENTER LEFT

        //center.setBackground(Color.BLACK);
        //add(centerTotal, BorderLayout.CENTER);

        Insets columnSpacing = new Insets(0, 100, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 10, 10, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel songNameLabel = utils.label("Song Name");
        JTextField jtfSongName = utils.textField();

        JLabel albumLabel = utils.label("Album");
        JTextField jtfAlbum = utils.textField();

        JLabel ArtistLabel = utils.label("Artist");
        JTextField jtfArtist = utils.textField();

        JLabel genreLabel = utils.label("Genre");
        JTextField jtfGenre = utils.textField();

        JLabel addFileLabel = utils.label("Add file");
        JFileChooser FCaddFile = new JFileChooser();
        ImageIcon addFileBtnAux = new ImageIcon(String.valueOf(AssetsFiles.ADD_SONG_FILE_BUTTON_IMG));
        int newWidth = 320;
        int newHeight = 160;
        Image addFileBtnImg = addFileBtnAux.getImage();
        Image scaledAddFileBtn = addFileBtnImg.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        Icon addFileBtn = new ImageIcon(scaledAddFileBtn);
        jfile = utils.buttonImg(addFileBtn);
        jfile.setActionCommand(BTN_ADD_FILE);
        FCaddFile.setFont(new Font("Gotham", Font.BOLD, 20));
        FCaddFile.setPreferredSize(new Dimension(500, 300));
        FCaddFile.setMinimumSize(new Dimension(500,300));

        center.add(songNameLabel, constraints);
        constraints.gridy++;
        center.add(jtfSongName, constraints);
        constraints.gridy++;
        center.add(albumLabel, constraints);
        constraints.gridy++;
        center.add(jtfAlbum, constraints);
        constraints.gridy++;
        center.add(ArtistLabel, constraints);
        constraints.gridy++;
        center.add(jtfArtist, constraints);


        // DESPLACEM A LA DRETA
        constraints.gridx = 1000;
        constraints.gridy = 0;
        constraints.insets = columnSpacing;

        center.add(genreLabel, constraints);
        constraints.gridy++;
        center.add(jtfGenre, constraints);
        constraints.gridy++;
        center.add(addFileLabel, constraints);
        constraints.gridy++;
        constraints.gridheight = 4;
        center.add(jfile, constraints);

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

        //JPanel centerPanel = new JPanel();

        //centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //centerPanel.add(center);

        // Botó
        JPanel button = new JPanel();
        button.setBackground(Color.BLACK);
        ImageIcon addSongBtn = new ImageIcon(String.valueOf(AssetsFiles.ADDSONG_BUTTON_IMG));
        jadd = utils.buttonImg(addSongBtn);
        jadd.setActionCommand(BTN_ADD_SONG);
        button.add(jadd);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0,0,0,0);
        centerTotal.add(center);
        constraints.gridy++;
        centerTotal.add(button);




        // center.add(FCaddFile, constraints);

        /*jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);
        jfile = utils.buttonText("add file");
        jfile.setActionCommand(BTN_ADD_FILE);

        add(jLogo);
        add(jback);
        add(jfile);*/
    }
}