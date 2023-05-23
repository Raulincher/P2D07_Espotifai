package Presentation.View;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Utils;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedSongView extends JPanel {

    // Preparem els atributs
    private final Utils utils;

    // Preparem els strings per quan es premi un botó
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_PLAYLIST = "BTN_PLAYLIST";
    public static final String BTN_PLAYME = "BTN_PLAYME";

    // Preparem els elements de Swing
    private JTextArea jTextArea;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private static String[] columnHeaders = {"", ""};
    private JLabel jlLyrics;
    private JLabel jlSong;
    private JLabel jlPlaylist;
    private JLabel jlPlayMe;
    private JButton jPlaylist;
    private JButton jPlay;


    /**
     * Funció que servirà com a constructor de la GeneralPlaylistView
     *
     * @param , vista per a posar el Header
     * @param utils, per usar tots els seus mètodes
     * @param , vista per a posar el Footer
     */
    public DetailedSongView(Utils utils) {
        this.utils = utils;
        defaultTableModel = new DefaultTableModel(columnHeaders, 0);
        jTextArea = new JTextArea();
    }

    /**
     * Funció que servirà per a vincular la view amb el controller
     * i activar tots els listeners
     *
     * @param detailedSongViewController, controller de la DetailedSongView
     */
    public void addDetailedSongController(DetailedSongViewController detailedSongViewController) {
        jPlaylist.addActionListener(detailedSongViewController);
        jPlay.addActionListener(detailedSongViewController);
    }

    /**
     * Funció que servirà per a construir la vista amb tots
     * els elements de Swing
     *
     * No tindrà param ni retur n
     */
    public void configureDetailedSongView() {
        // Creem el BorderLayout i el configurem
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26, 26, 26);

        // NORTH
        // Creem el JPanel del nord i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        // Afegim el JLabel corresponent i afegim el Header
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.SONG_LABEL));
        //north.add(headerView.configureHeader(logo));
        //add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center i el configurem
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 50, 80, 50));

        // Afegim les dimensions de la taula
        Dimension dimension = new Dimension(400,220);

        // Creem la JTable
        table = new JTable(defaultTableModel);

        // Creem i ajustem el JScrollPane d'aquesta JTable
        JScrollPane scrollpane = createSongListTable(table, gris);
        scrollpane.setPreferredSize(dimension);
        scrollpane.setMinimumSize(dimension);
        scrollpane.setMaximumSize(dimension);

        // Creem el JScrollPane dins del JTextArea
        JScrollPane scrollpane2 = new JScrollPane(jTextArea);
        scrollpane2.setPreferredSize(dimension);
        scrollpane2.setMinimumSize(dimension);
        scrollpane2.setMaximumSize(dimension);

        // Creem el JButton per afegir a la Playlist
        Icon playlistBtn = new ImageIcon(String.valueOf(AssetsFiles.PLAYLIST_PLUS_ADD));
        jPlaylist = utils.buttonImg(playlistBtn);
        jPlaylist.setBackground(gris);
        jPlaylist.setActionCommand(BTN_PLAYLIST);

        // Creem el JButton per donar-li al play a la cançó en qüestió
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jPlay = utils.buttonImg(playBtn);
        jPlay.setBackground(gris);
        jPlay.setActionCommand(BTN_PLAYME);

        // Afegim el JLabel de Song
        jlSong = new JLabel("SONG");
        jlSong.setForeground(Color.GREEN);
        jlSong.setFont(new Font("Gotham", Font.BOLD, 27));

        // Afegim el JLabel de Lyrics
        jlLyrics  = new JLabel("LYRICS");
        jlLyrics.setForeground(Color.GREEN);
        jlLyrics.setFont(new Font("Gotham", Font.BOLD, 27));

        // Afegim el JLabel per afegir a la Playlist
        jlPlaylist  = new JLabel("ADD to Playlist");
        jlPlaylist.setForeground(Color.GREEN);
        jlPlaylist.setFont(new Font("Gotham", Font.BOLD, 20));

        // Afegim el JLabel de Play Me
        jlPlayMe  = new JLabel("PLAY ME");
        jlPlayMe.setForeground(Color.GREEN);
        jlPlayMe.setFont(new Font("Gotham", Font.BOLD, 20));

        // Preparem el GridBagConstraints per ordenar els diferents components
        Insets columnSpacing = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 20;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.WEST;

        // Afegim el JLabel i la JTable
        center.add(jlSong, constraints);
        constraints.gridy++;
        center.add(scrollpane, constraints);

        // Desplacem a la dreta per afegir el JTextArea
        constraints.gridx = 1000;
        constraints.gridy = 0;
        constraints.insets = columnSpacing;
        center.add(jlLyrics, constraints);

        // Configurem mesures i afegim el JTextArea amb JScrollPane
        constraints.gridy++;
        constraints.weighty = 15;
        constraints.weightx = 20;
        center.add(scrollpane2, constraints);

        // Preparem mesures per afegir l'ultima fila de contingut
        constraints.gridx++;
        constraints.gridy = 0;
        constraints.weighty = 4;
        constraints.gridheight = 6;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 0, 0);

        // Afegim JLabels i JButtons per afegir a la Playlist o donar al play
        center.add(jlPlaylist, constraints);
        constraints.gridx++;
        center.add(jPlaylist, constraints);
        constraints.gridy++;
        constraints.gridx--;
        constraints.insets = new Insets(80, 0, 0, 0);
        center.add(jlPlayMe, constraints);
        constraints.gridx++;
        center.add(jPlay, constraints);

        // Ho afegim tot al BorderLayout
        add(center, BorderLayout.CENTER);

        // SOUTH
        // Creem JPanel del sud i li afegim el Footer
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        //south.add(footerView.configureFooter());
        //add(south, BorderLayout.SOUTH);

    }

    /**
     * Funció que servirà per a construir el JScrollPane  i la JTable
     * de la detailed song
     *
     * @param table, JTable a la qual se li afegirà el JScrollPane
     * @param gris, color de fons
     */
    private JScrollPane createSongListTable(JTable table, Color gris) {
        // Configurem les mesures i color
        table.setRowHeight(40);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(gris);
        table.setSelectionForeground(Color.WHITE);
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            /**
             * Funció amb Override per a la fila esquerra de la JTable
             * i per a donar-li un format
             *
             * @param table, JTable en qüestió
             * @param value
             * @param isSelected, bool per confirmar si s'ha seleccionat
             * @param hasFocus,
             * @param row,
             * @param column
             * @return cellComponent
             */
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(new Font("Gotham", Font.BOLD, 25));
                cellComponent.setForeground(Color.decode("#00DC00"));
                return cellComponent;
            }
        });

        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            /**
             * Funció amb Override per a la fila dreta de la JTable
             * de la cançó seleccionada
             *
             * @param table, JTable en qüestió
             * @param value,
             * @param isSelected, bool per confirmar si la cançó ha sigut seleccionada
             * @param hasFocus,
             * @param row,
             * @param column,
             * @return cellComponent,
             */
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(new Font("Gotham", Font.BOLD, 20));
                cellComponent.setForeground(Color.white);
                return cellComponent;
            }
        });
        return new JScrollPane(table);
    }

    /**
     * Funció per a afegir la cançó seleccionada a la JTable
     * de forma correcta
     *
     * @param song, ArrayList amb totes les cançons afegides
     */
    public void fillDetailedTable(ArrayList<String> song) {
        defaultTableModel.setRowCount(0);
        int countRow = 0;

        // Definim l'array de strings
        String[] row = {"Song", "Genre", "Artist", "Album", "Uploaded by", "Time"};

        // Obrim un bucle que recorri tot l'ArrayList amb la song
        for (String s : song) {
            // Anem afegint la informació a la taula
            String[] songInfo = s.split("-");
            Object[] rowData = {row[countRow], songInfo[0]};
            defaultTableModel.addRow(rowData);
            countRow++;
        }
    }

    /**
     * Funció per a configurar el format de les lyrics que
     * apareixeran al JTextArea
     *
     * @param lyrics, String amb els lyrics de la cançó
     */
    public void fillLyriscText(String lyrics) {
        Color gris = new Color(26, 26, 26);

        // Configurem el JTextArea per a que no sigui editable i li donem format
        jTextArea.setEditable(false);
        jTextArea.setBackground(gris);
        jTextArea.setForeground(Color.decode("#00DC00"));
        jTextArea.setFont(new Font("Gotham", Font.BOLD, 15));

        // Preparem el StringBuilder per a afegir tota la lletra
        String[] lines = lyrics.split("\n");
        StringBuilder centeredText = new StringBuilder();

        // Obrim bucle per a construir les lyrics
        for (String line : lines) {
            centeredText.append(String.format("%" + 10 + "s%s\n", "", line));
        }

        jTextArea.setText(centeredText.toString());
        jTextArea.setCaretPosition(0);
    }

    /**
     * Funció que servirà per a mostrar pop ups
     *
     * @param error, missatge a mostrar
     */
    public void showPopUp(String error) {
        JOptionPane.showMessageDialog(this,error);
    }
}