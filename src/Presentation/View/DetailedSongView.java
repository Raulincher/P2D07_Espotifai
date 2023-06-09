package Presentation.View;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Utils;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Classe per mostrar la vista de la cançó detallada i les interaccions d'aquesta
 */
public class DetailedSongView extends JPanel {

    // Preparem els atributs
    private final Utils utils;

    // Preparem els strings per quan es premi un botó
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
    private JPopupMenu popupMenuPlaylist;
    private ArrayList<JMenuItem> listItems;
    private String songName;
    private JTextField filterMenuPlaylist;


    /**
     * Funció que servirà com a constructor de la GeneralPlaylistView
     *
     * @param utils, per usar tots els seus mètodes
     */
    public DetailedSongView(Utils utils) {
        this.utils = utils;
        defaultTableModel = new DefaultTableModel(columnHeaders, 0);
        jTextArea = new JTextArea();
        popupMenuPlaylist = new JPopupMenu();
        listItems = new ArrayList<>();
        filterMenuPlaylist = new JTextField();
    }

    /**
     * Funció que servirà per a vincular la view amb el controller
     * i activar tots els listeners
     *
     * @param detailedSongViewController, controller de la DetailedSongView
     */
    public void addDetailedSongController(DetailedSongViewController detailedSongViewController) {
        jPlaylist.addMouseListener(detailedSongViewController);
        jPlay.addActionListener(detailedSongViewController);
        for (JMenuItem item : listItems){
            item.addActionListener(detailedSongViewController);
        }
        filterMenuPlaylist.getDocument().addDocumentListener(detailedSongViewController);
        jPlaylist.addActionListener(detailedSongViewController);
    }

    /**
     * Funció que servirà per a construir la vista amb tots
     * els elements de Swing
     *
     * No tindrà param ni return
     */
    public void configureDetailedSongView() {
        // Creem el BorderLayout i el configurem
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26, 26, 26);

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

        //Creem la PopUpMenu del boto playlist
        createPopUpMenu();

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
             * @param row, enter amb la fila a modificar
             * @param column, enter amb la columna a modificar
             * @return cellComponent, amb la cel·la modificada
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
             * @param row, enter amb la fila a modificar
             * @param column, enter amb la columna a modificar
             * @return cellComponent, amb la cel·la modificada
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
            // Afegim la informació a la taula
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

        // Configurem el JTextArea perquè no sigui editable i li donem format
        jTextArea.setEditable(false);
        jTextArea.setBackground(gris);
        jTextArea.setForeground(Color.decode("#00DC00"));
        jTextArea.setFont(new Font("Gotham", Font.BOLD, 15));

        // Preparem el StringBuilder per a afegir un marge
        String[] lines = lyrics.split("\n");
        StringBuilder centeredText = new StringBuilder();

        // Afegim el marge a la lírica perquè es llegeixi millor
        for (String line : lines) {
            centeredText.append(String.format("%" + 10 + "s%s\n", "", line));
        }

        jTextArea.setText(centeredText.toString());
        jTextArea.setCaretPosition(0);
    }

    /**
     *
     * Funció per crear la Pop Up Menu un cop pulsada la canço que es vol veure en detall des del GeneralSongListViewController
     *
     * @param playlist, ArrayList d'Strings que conté el nom de les playlist de l'usuari loguejat
     */
    public void fillPopMenu(ArrayList<String> playlist){
        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.add(filterMenuPlaylist, BorderLayout.NORTH);
        popupMenuPlaylist.add(filterPanel, 0);
        int countAdd = 0;
        if (playlist != null) {
            for (String play : playlist) {
                JMenuItem menuItem = new JMenuItem(play);
                menuItem.setActionCommand(play);
                listItems.add(menuItem);
                popupMenuPlaylist.add(menuItem);
            }
        }
    }

    /**
     * Funció per personalitzar la PopUpMenu d'afegir cançons a les playlists mostrades
     *
     * No tindrà param ni return
     */
    public void createPopUpMenu(){
        Color gris = new Color(26, 26, 26);
        popupMenuPlaylist.setBackground(gris);
        popupMenuPlaylist.setForeground(Color.WHITE);
        popupMenuPlaylist.setFont(new Font("Gotham", Font.BOLD, 15));
        popupMenuPlaylist.setLightWeightPopupEnabled(false);
        popupMenuPlaylist.setPreferredSize(new Dimension(60,90));
    }

    /**
     * Funció que mostrara per pantalla la popUpMenu
     * @param e, component de MouseEvent per detectar quan l'ha de mostrar
     */
    public void showMenuPopUp(MouseEvent e){
        popupMenuPlaylist.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Funció que servirà per a mostrar pop ups
     *
     * @param error, missatge a mostrar
     */
    public void showPopUp(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    /**
     * Funció per inicialitzar i guardar el nom de la cançó seleccionada i que sesta mostrant els seus detalls per pantalla
     * @param songName, String amb el nom de la cançó
     */
    public void setNameSong (String songName){
        this.songName = songName;
    }

    /**
     * Funció per obtenir el nom de la cançó de la llista seleccionada
     * @return songName, Strign amb el nom de la cançó
     */
    public String getSongName(){
        return songName;
    }

    /**
     * Funció que netjara la informació de la PopUpMenu de les playlist i l'arraylist de items per no acumular cançons
     *
     * No tindrà param ni return
     */
    public void clearInfo() {
        popupMenuPlaylist = null;
        popupMenuPlaylist = new JPopupMenu();
        listItems.clear();
    }
    /**
     * Funció que servirà per a canviar d'icona del play
     * en cas que es premi el botó i deixar-ho en pause
     *
     * No tindrà param ni return
     */
    public void stop(){
        Icon pauseBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PAUSEBUTTON_IMG));
        jPlay.setIcon(pauseBtn);
    }

    /**
     * Funció que servirà per a canviar d'icona del pause
     * en cas que es premi el botó i deixar-ho en play
     *
     * No tindrà param ni return
     */
    public void start(){
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jPlay.setIcon(playBtn);
    }

    /**
     * Funció per netejar el filtre de cerca de la popUpMenu playlists
     */
    public void cleanFilter(){
        filterMenuPlaylist.setText("");
    }

    /**
     * Funció per filtra la popUpMenu de les playlists
     */
    public void filteredPopUpMenu() {
        String searchTerm = filterMenuPlaylist.getText().toLowerCase();
        // Recorrem tots els elements de la JPopupMenu i els filtrem depenen de la busqueda
        for (int i = 0; i < listItems.size(); i++) {
            String text = listItems.get(i).getText().toLowerCase();
            listItems.get(i).setVisible(text.contains(searchTerm));
        }
    }
}