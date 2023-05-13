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

    private JButton jadd;
    private JButton jfile;
    private JTextField jtfSongName;
    private JTextField jtfArtist;
    private JTextField jtfAlbum;
    private JTextField jtfGenre;
    private JFileChooser fcAddFile;

    public AddSongView(Utils utils, HeaderView headerView, FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    public void addAddSongController(AddSongViewController addSongController) {
        jfile.addActionListener(addSongController);
        jadd.addActionListener(addSongController);
    }

    public void configureAddSong() {
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

        Insets columnSpacing = new Insets(0, 100, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 10, 10, 5);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel songNameLabel = utils.label("Song Name");
        jtfSongName = utils.textField();

        JLabel albumLabel = utils.label("Album");
        jtfAlbum = utils.textField();

        JLabel ArtistLabel = utils.label("Artist");
        jtfArtist = utils.textField();

        JLabel genreLabel = utils.label("Genre");
        jtfGenre = utils.textField();

        JLabel addFileLabel = utils.label("Add file");

        ImageIcon addFileBtnAux = new ImageIcon(String.valueOf(AssetsFiles.ADD_SONG_FILE_BUTTON_IMG));
        int newWidth = 320;
        int newHeight = 160;
        Image addFileBtnImg = addFileBtnAux.getImage();
        Image scaledAddFileBtn = addFileBtnImg.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        Icon addFileBtn = new ImageIcon(scaledAddFileBtn);
        jfile = utils.buttonImg(addFileBtn);
        jfile.setActionCommand(BTN_ADD_FILE);
        fcAddFile = new JFileChooser();
        fcAddFile.setFont(new Font("Gotham", Font.BOLD, 20));
        fcAddFile.setPreferredSize(new Dimension(500, 300));
        fcAddFile.setMinimumSize(new Dimension(500,300));

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
    }

    public JTextField getJtfSongName() {
        return jtfSongName;
    }

    public JTextField getJtfAlbum() {
        return jtfAlbum;
    }

    public JTextField getJtfGenre() {
        return jtfGenre;
    }

    public JTextField getJtfArtist() {
        return jtfArtist;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    public void addFileName(String filename) {
        JLabel label = new JLabel(filename);
        label.setForeground(Color.BLACK);
        jfile.setIcon(null);
        jfile.setBackground(Color.WHITE);
        jfile.setForeground(Color.WHITE);
        jfile.add(label);
        jfile.setVerticalTextPosition(SwingConstants.NORTH);
        jfile.setHorizontalTextPosition(SwingConstants.CENTER);
        jfile.setOpaque(true);
        int newWidth = 320;
        int newHeight = 160;
        Dimension dimension = new Dimension(newWidth,newHeight);
        jfile.setPreferredSize(dimension);
    }

}