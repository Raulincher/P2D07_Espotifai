package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.AddSongViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe de la vista add song la qual extend un jpanel
 */
public class AddSongView extends JPanel {

    // Afegim atributs
    private final Utils utils;

    /**
     * preparem els botons
     */
    public static final String BTN_ADD_FILE = "BTN_ADD_FILE";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";

    // Afegim JFunctions, como buttons
    private JLabel jlFilename;
    private JButton jadd;
    private JButton jfile;
    private JTextField jtfSongName;
    private JTextField jtfArtist;
    private JTextField jtfAlbum;
    private JFileChooser fcAddFile;
    private JComboBox<String> jcbGenre;

    /**
     * Funció que servirà com a constructor del AddSongView
     *
     * @param utils, per a utilitzar els mètodes d'utils
     */
    public AddSongView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar tots els listeners
     *
     * @param addSongController, controller del AddSongView
     */
    public void addAddSongController(AddSongViewController addSongController) {
        jfile.addActionListener(addSongController);
        jadd.addActionListener(addSongController);
    }


    /**
     * Funció de tipus void que ens servirá per configurar la vista
     * de l'add song
     */
    public void configureAddSong() {
        setBackground(Color.black);

        // Fem que sigui border layout
        setLayout(new BorderLayout());

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

    /**
     * Funció que servirà per recollir el valor introduït
     * a song name
     *
     * @return jtfSongName, song name que haurà introduït el propi usuari
     */
    public JTextField getJtfSongName() {
        return jtfSongName;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * a album
     *
     * @return jtfAlbum, album que haurà introduït el propi usuari
     */
    public JTextField getJtfAlbum() {
        return jtfAlbum;
    }

    /**
     * Funció que servirà per recollir el valor escollit
     * a album
     *
     * @return jcbGenre, genere que haurà escollit el propi usuari
     */
    public JComboBox<String> getJcbGenre() {
        return jcbGenre;
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * a artist
     *
     * @return jtfArtist, artista que haurà introduït el propi usuari
     */
    public JTextField getJtfArtist() {
        return jtfArtist;
    }

    /**
     * Funció que servirà per mostrar amb una pop up l'error, en cas d'haver-hi
     *
     * @param error, string amb el missatge d'error
     */
    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    /**
     * Funció que servirà per recollir el valor introduït
     * a file
     *
     * @param filename, nom arxiu
     */
    public void setJlFilename(String filename) {
        jlFilename = new JLabel(filename);
    }

    /**
     * Funció que servirà per afegir el nom de l'arxiu
     *
     * @param filename, nom arxiu
     */
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

    /**
     * Funció que servirà per esborrar els camps
     */
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

    /**
     * Funció que servirà per saber si hi ha file pujat
     *
     * @return boolean que serveix de comprovant.
     */
    public Boolean fileCheckExists() {
        return jlFilename != null;
    }
}