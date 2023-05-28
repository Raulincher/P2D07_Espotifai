package Presentation.View;

import javax.swing.Timer;

import Presentation.AssetsFiles;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * Classe de la vista General playlist la qual fa extend d'un jpanel
 */
public class GeneralPlaylistView extends JPanel {

    // Preparem els atributs
    private final Utils utils;
    public static final String BTN_NEW_PLAYLIST = "BTN_NEW_PLAYLIST";
    private JButton jNewPlaylist;
    private DefaultTableModel myPlaylistsModel;
    private DefaultTableModel otherPlaylistsModel;
    private JTable myPlaylistsTable;
    private JTable otherPlaylistsTable;
    private JLabel jlYourPlaylists;
    private JLabel jlOthersPlaylists;
    private JTextField jMyPlaylistBuscador;
    private JTextField jOthersPlaylistBuscador;
    private TableRowSorter<DefaultTableModel> myPlaylistSorter;
    private TableRowSorter<DefaultTableModel> otherPlaylistSorter;
    private static String[] myPlaylist = {"Your library:"};
    private static String[] otherPlaylist = {"General library:"};

    private final static String MY_PLAYLISTS = "My playlists";
    private final static String OTHERS_PLAYLISTS = "Others playlists";

    /**
     * Funció que servirà com a constructor de la GeneralPlaylistView
     * @param utils, per usar tots els seus mètodes
     */
    public GeneralPlaylistView(Utils utils){
        this.utils = utils;
        myPlaylistsModel = new DefaultTableModel(myPlaylist, 0);
        otherPlaylistsModel = new DefaultTableModel(otherPlaylist, 0);
        myPlaylistSorter =  new TableRowSorter<>(myPlaylistsModel);
        otherPlaylistSorter =  new TableRowSorter<>(otherPlaylistsModel);
    }

    /**
     * Funció que servirà per a vincular-ho amb el controller
     *
     * @param generalPlaylistViewController, controller en del GeneralPlaylistController
     */
    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
        //set action command
        jMyPlaylistBuscador.getDocument().addDocumentListener(generalPlaylistViewController);
        jOthersPlaylistBuscador.getDocument().addDocumentListener(generalPlaylistViewController);
        jNewPlaylist.addActionListener(generalPlaylistViewController);
        myPlaylistsTable.addMouseListener(generalPlaylistViewController);
        otherPlaylistsTable.addMouseListener(generalPlaylistViewController);
    }

    /**
     * Funció que servirà per a configurar tots els elements Swing
     * de la GeneralPlaylistView
     *
     * No tindrà param ni return
     */
    public void configureGeneralPlaylistView() {
        // Iniciem BorderLayout i el configurem
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //NORTH
        // Creem el JPanel del nord i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        //north.add(headerView.configureHeader(logo));
        //add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center i el configurem
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        // Textfields per buscar + imatge create List
        jMyPlaylistBuscador = utils.textFieldPlaylists();
        jOthersPlaylistBuscador = utils.textFieldPlaylists();

        Icon newPlaylist = new ImageIcon(String.valueOf(AssetsFiles.CREATE_LIST_BUTTON_IMG));
        jNewPlaylist = utils.buttonImg(newPlaylist);
        jNewPlaylist.setActionCommand(BTN_NEW_PLAYLIST);
        jNewPlaylist.setPreferredSize(new Dimension(100,50));
        jNewPlaylist.setMaximumSize(new Dimension(100,50));

        // My Playlists
        Dimension dimension = new Dimension(500,220);
        myPlaylistsTable = new JTable(myPlaylistsModel);
        JScrollPane scrollMyPlaylists = createSongListTable(myPlaylistsTable);
        scrollMyPlaylists.setPreferredSize(dimension);
        scrollMyPlaylists.setMinimumSize(dimension);
        scrollMyPlaylists.setMaximumSize(dimension);

        // Others
        otherPlaylistsTable = new JTable(otherPlaylistsModel);
        JScrollPane scrollOthersPlaylists = createSongListTable(otherPlaylistsTable);
        scrollOthersPlaylists.setPreferredSize(dimension);
        scrollOthersPlaylists.setMinimumSize(dimension);
        scrollOthersPlaylists.setMaximumSize(dimension);

        // Jlables
        jlYourPlaylists = new JLabel(MY_PLAYLISTS);
        jlYourPlaylists.setForeground(Color.GREEN);
        jlYourPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        jlOthersPlaylists = new JLabel(OTHERS_PLAYLISTS);
        jlOthersPlaylists.setForeground(Color.GREEN);
        jlOthersPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 20;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.WEST;

        center.add(jlYourPlaylists, constraints);
        constraints.gridy++;
        center.add(jMyPlaylistBuscador, constraints);
        constraints.gridx++;
        center.add(jNewPlaylist, constraints);
        constraints.gridx--;
        constraints.gridy++;
        center.add(scrollMyPlaylists, constraints);

        // DESPLACEM A LA DRETA
        constraints.gridx = 500;
        constraints.gridy = 0;

        center.add(jlOthersPlaylists, constraints);
        constraints.gridy++;
        center.add(jOthersPlaylistBuscador, constraints);
        constraints.gridy++;
        center.add(scrollOthersPlaylists, constraints);
        constraints.gridy++;

        add(center, BorderLayout.CENTER);

        myPlaylistSorter.setSortsOnUpdates(true);
        myPlaylistsTable.setRowSorter(myPlaylistSorter);

        otherPlaylistSorter.setSortsOnUpdates(true);
        otherPlaylistsTable.setRowSorter(otherPlaylistSorter);

        //South
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        //south.add(footerView.configureFooter());
        //add(south, BorderLayout.SOUTH);
    }

    /**
     * Funció que servirà per crear una taula amb scrollpane
     *
     * @param table, taula a crear
     *
     * @return table, retornem la taula ja montada
     *
     */
    public JScrollPane createSongListTable(JTable table){
        Color gris = new Color(26,26,26);

        table.setRowHeight(40);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(Color.decode("#00DC00"));

        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        header.setFont(new Font("Gotham", Font.BOLD, 20));
        table.getTableHeader().setDefaultRenderer(header);
        table.setFont(new Font("Gotham", Font.BOLD, 20));

        return new JScrollPane(table);
    }

    /**
     * Funció que servirà per omplir la taula de playlist de l'esquerra, que es la del usuari
     *
     * @param playlistsNames, playlist de la BBDD (array de noms)
     *
     */
    public void fillMyPlaylistsTable(ArrayList<String> playlistsNames) {
        if (playlistsNames != null) {
            myPlaylistsModel.setRowCount(0);

            // Obrim bucle per a mostrar la informació de la cançó
            for (String playlistName : playlistsNames) {
                Object[] rowData = {playlistName};
                myPlaylistsModel.addRow(rowData);
            }
        }
    }

    /**
     * Funció que servirà per omplir la taula de playlist de la dreta, que es la de altres usuaris
     *
     * @param playlistsNames, playlist de la BBDD (array de noms)
     *
     */
    public void fillOtherPlaylistsTable(ArrayList<String> playlistsNames) {
        if (playlistsNames != null) {
            otherPlaylistsModel.setRowCount(0);

            // Obrim bucle per a mostrar la informació de la cançó
            for (String playlistName : playlistsNames) {
                Object[] rowData = {playlistName};
                otherPlaylistsModel.addRow(rowData);
            }
        }
    }

    /**
     * Funció que servirà per obtenir quina taula hem clicat, si la nostra o la de altre user
     *
     * @param object, objecte clicat
     *
     * @return table, boolean que indica si es nostra o no
     */
    public int obtainTableClicked(Object object) {
        int table = 0;

        if (object.equals(myPlaylistsTable)) {
            table = 0;
        } else if (object.equals(otherPlaylistsTable)) {
            table = 1;
        }

        return table;
    }


    /**
     * Funció que servirà per obtenir el nom de la playlist clicada
     *
     * @param point, punt on es clica
     * @param tableClicked, taula clicada
     *
     * @return playlistName, String amb el nom de la playlist
     */
    public String obtainPlaylistName(Point point, int tableClicked) {
        String playlistName = null;

        if (tableClicked == 0) {
            int indexFila = myPlaylistsTable.rowAtPoint(point);

            // Obté la informació a partir de la cançó seleccionada
            playlistName = (String) myPlaylistsTable.getValueAt(indexFila, 0);
        } else {
            int indexFila = otherPlaylistsTable.rowAtPoint(point);

            // Obté la informació a partir de la cançó seleccionada
            playlistName = (String) otherPlaylistsTable.getValueAt(indexFila, 0);
        }
        return playlistName;
    }

    /**
     * Funció que servirà per obtenir el buscador de la nostra playlist
     *
     * @return jMyPlaylistBuscador, Jtextfield amb el nostre buscador
     */
    public JTextField getMyPlaylistBuscador() {
        return jMyPlaylistBuscador;
    }

    /**
     * Funció que servirà per obtenir el buscador de la playlist d'altres users
     *
     * @return jOthersPlaylistBuscador, jtextfield amb el buscador
     */
    public JTextField getOtherPlaylistBuscador() {
        return jOthersPlaylistBuscador;
    }

    /**
     * Funció que servirà com a buscador dins la playlist
     *
     * @param query, query amb la info
     * @param sorter, sorter de la taula
     */
    public void search(String query, TableRowSorter<DefaultTableModel> sorter) {
        // Ens assegurem que s'hagi introduït alguna cosa
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        }
        // Busca per la primera columna, pel títol de la cançó
        else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0)); // Search by the first column (title)
        }
    }

    /**
     * Funció que retorna el sorter de la taula amb les playlist del usuari
     *
     * @return myPlaylistSorter
     */
    public TableRowSorter<DefaultTableModel> getMyPlaylistSorter() {
        return myPlaylistSorter;
    }

    /**
     * Funció que retorna el sorter de la taula amb les playlist d'altres usuaris
     *
     * @return otherPlaylistSorter
     */
    public TableRowSorter<DefaultTableModel> getOtherPlaylistSorter() {
        return otherPlaylistSorter;
    }

    /**
     * Funció que neteja el buscador
     */
    public void clearSearcher() {
        jMyPlaylistBuscador.setText("");
        jOthersPlaylistBuscador.setText("");
    }
}
