package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.AddSongViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class AddSongView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";

    private JButton jback;
    private JButton jfile;

    public AddSongView(Utils utils, HeaderView headerView){
        this.utils = utils;
        this.headerView = headerView;
    }

    public void addAddSongController(AddSongViewController addSongController) {
        //jback.addActionListener(addSongController);
        //jfile.addActionListener(addSongController);
    }

    public void configureAddSong() {
        //JLabel jLogo = new JLabel("add song");

        // EL JPanel en si de addSong
        setBackground(Color.black);
        setLayout(new BorderLayout()); // Fem quesigui border layout

        // NORTH
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon addSongImg = new ImageIcon(String.valueOf(AssetsFiles.ADDSONG_LABEL));
        north.add(headerView.configureHeader(addSongImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new GridBagLayout());

        // CENTER LEFT
        JLabel songNameLabel = new JLabel("Song Name");
        center.setBackground(Color.BLACK);

        add(center, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 2, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        songNameLabel.setForeground(Color.WHITE);
        songNameLabel.setFont(new Font("Gotham", Font.BOLD, 20));
        JTextField jtfSongName = new JTextField();
        jtfSongName.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfSongName.setPreferredSize(new Dimension(300, 40));
        jtfSongName.setMinimumSize(new Dimension(300,40));

        JLabel albumLabel = new JLabel("Album");
        albumLabel.setForeground(Color.WHITE);
        albumLabel.setFont(new Font("Gotham", Font.BOLD, 20));
        JTextField jtfAlbum = new JTextField();
        jtfAlbum.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfAlbum.setPreferredSize(new Dimension(300, 40));
        jtfAlbum.setMinimumSize(new Dimension(300,40));

        JLabel ArtistLabel = new JLabel("Artist");
        ArtistLabel.setForeground(Color.WHITE);
        ArtistLabel.setFont(new Font("Gotham", Font.BOLD, 20));
        JTextField jtfArtist = new JTextField();
        jtfArtist.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfArtist.setPreferredSize(new Dimension(300, 40));
        jtfArtist.setMinimumSize(new Dimension(300,40));

        JLabel genreLabel = new JLabel("Genre");
        genreLabel.setForeground(Color.WHITE);
        genreLabel.setFont(new Font("Gotham", Font.BOLD, 20));
        JTextField jtfGenre = new JTextField();
        jtfGenre.setFont(new Font("Gotham", Font.BOLD, 20));
        jtfGenre.setPreferredSize(new Dimension(300, 40));
        jtfGenre.setMinimumSize(new Dimension(300,40));

        JLabel addFileLabel = new JLabel("Add File");
        addFileLabel.setForeground(Color.WHITE);
        addFileLabel.setFont(new Font("Gotham", Font.BOLD, 20));
        JFileChooser FCaddFile = new JFileChooser();
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


        // DESPLAÇEM A LA DRETA
        constraints.gridx = 100;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 100);

        center.add(genreLabel, constraints);
        constraints.gridy++;
        center.add(jtfGenre, constraints);

        center.add(addFileLabel, constraints);
        constraints.gridy++;
        center.add(FCaddFile, constraints);

        /*jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);
        jfile = utils.buttonText("add file");
        jfile.setActionCommand(BTN_ADD_FILE);

        add(jLogo);
        add(jback);
        add(jfile);*/
    }
}
