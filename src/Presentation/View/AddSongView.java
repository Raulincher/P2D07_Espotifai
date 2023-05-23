package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.AddSongViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.io.File;

import static javax.swing.BorderFactory.createEmptyBorder;

public class AddSongView extends JPanel {
    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";

    private JLabel jlFilename;
    private JButton jadd;
    private JButton jfile;
    private JTextField jtfSongName;
    private JTextField jtfArtist;
    private JTextField jtfAlbum;
    private JTextField jtfGenre;
    private JFileChooser fcAddFile;
    private JComboBox<String> jcbGenre;

    public AddSongView(Utils utils){
        this.utils = utils;
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
        //north.add(headerView.configureHeader(addSongImg));
        north.setBackground(Color.black);
        //add(north, BorderLayout.NORTH);

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
        //jtfGenre = utils.textField();

        String[] options = {"Pop", "Reggaeton", "Techno", "Rock",
                            "Heavy metal", "Rap", "Balada", "Classical"};
        jcbGenre = new JComboBox<>(options);
        jcbGenre.setSelectedIndex(-1);
        jcbGenre.setFont(new Font("Gotham", Font.BOLD, 20));
        jcbGenre.setPreferredSize(new Dimension(300, 40));

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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV files (*.wav)", "wav");
        fcAddFile.setFileFilter(filter);
        fcAddFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
        center.add(jcbGenre, constraints);
        constraints.gridy++;
        center.add(addFileLabel, constraints);
        constraints.gridy++;
        constraints.gridheight = 4;
        center.add(jfile, constraints);

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        //south.add(footerView.configureFooter());
        //add(south, BorderLayout.SOUTH);

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

    public JComboBox<String> getJcbGenre() {
        return jcbGenre;
    }

    public JTextField getJtfArtist() {
        return jtfArtist;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    public void setJlFilename(String filename) {
        jlFilename = new JLabel(filename);
    }

    public void addFileName(String filename) {
        //JLabel label = new JLabel(filename);
        setJlFilename(filename);
        jlFilename.setForeground(Color.BLACK);
        jfile.setIcon(null);
        jfile.setBackground(Color.WHITE);
        jfile.setForeground(Color.WHITE);
        jfile.add(jlFilename);
        jfile.setVerticalTextPosition(SwingConstants.NORTH);
        jfile.setHorizontalTextPosition(SwingConstants.CENTER);
        jfile.setOpaque(true);
        int newWidth = 320;
        int newHeight = 160;
        Dimension dimension = new Dimension(newWidth,newHeight);
        jfile.setPreferredSize(dimension);
    }

    public void clearFields() {
        jtfSongName.setText("");
        jtfAlbum.setText("");
        jtfArtist.setText("");
        jcbGenre.setSelectedIndex(-1);
        ImageIcon addFileBtnAux = new ImageIcon(String.valueOf(AssetsFiles.ADD_SONG_FILE_BUTTON_IMG));
        int newWidth = 320;
        int newHeight = 160;
        Image addFileBtnImg = addFileBtnAux.getImage();
        Image scaledAddFileBtn = addFileBtnImg.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        Icon addFileBtn = new ImageIcon(scaledAddFileBtn);
        jfile.remove(jlFilename);
        jfile.setIcon(addFileBtn);

    }

    public JFileChooser getFcAddFile() {
        return fcAddFile;
    }
}