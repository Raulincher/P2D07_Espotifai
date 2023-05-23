package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DetailedPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedPlaylistView extends JPanel {

    // Preparem tots els atributs
    private final Utils utils;
    private JLabel jlListName;
    private JButton boto1;
    private JButton boto2;

    private DefaultTableModel songListModel;
    private JTable songListTable;

    private JTextArea jtArea;
    private static String[] columnHeaders = {"Title"};

    // Preparem els strings per quan es premi un botó
    public static final String BTN_BACK = "BTN_BACK";
    // Preparem els elements de Swing
    private JButton jback;

    /**
     * Funció que servirà com a constructor de la DetailedPlaylistView
     *
     * @param utils, per usar tots els seus mètodes
     */
    public DetailedPlaylistView(Utils utils){
        this.utils = utils;
        songListModel = new DefaultTableModel(columnHeaders, 1);
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar els diferents listeners
     *
     * @param detailedPlaylistViewController, controller de la DetailedPlaylistView
     */
    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){
        //jback.addActionListener(detailedPlaylistViewController);
    }

    /**
     * Funció que servirà per a configurar tots els elements
     * de Swing de la vista
     *
     * No tindrà ni param ni return
     */
    public void configureDetailedPlaylistView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //NORTH
        // Creem el JPanel del nord i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        // Afegim el Label que desitgem i afegim el Header
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.LISTMANAGING_LABEL));;
        //north.add(headerView.configureHeader(logo));


        jtArea = new JTextArea();
        jtArea.setBackground(Color.BLACK);
        jtArea.setForeground(Color.GREEN);
        jtArea.setFont(new Font("Gotham", Font.BOLD, 27));
        jtArea.setEditable(false);
        jtArea.setFocusable(false);
        jtArea.setPreferredSize(new Dimension(200, 50));
        north.add(jtArea, BorderLayout.WEST);
        ImageIcon addFileBtnAux = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LIST_BUTTON_IMG));
        boto1 = utils.buttonImg(addFileBtnAux);
        north.add(boto1,BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);

        //Center
        JPanel center = new JPanel();
        center.setBackground(Color.black);


        boto2 = new JButton("Boto2");

        Dimension dimension = new Dimension(700, 350);
        songListTable = new JTable(songListModel);
        JScrollPane scrollMyPlaylists = createSongListTable(songListTable);
        scrollMyPlaylists.setPreferredSize(dimension);
        scrollMyPlaylists.setMinimumSize(dimension);
        scrollMyPlaylists.setMaximumSize(dimension);
        center.add(scrollMyPlaylists, BorderLayout.CENTER);


        add(center,BorderLayout.CENTER);

        //South
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        //south.add(footerView.configureFooter());
        //add(south, BorderLayout.SOUTH);
    }

    public JScrollPane createSongListTable(JTable table){
        Color gris = new Color(26,26,26);

        table.setRowHeight(60);
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

    public void fillSongsInPlaylistTable(ArrayList<String> songsInPlaylist) {
        songListModel.setRowCount(0);
        System.out.println("Aqui: " + songsInPlaylist.get(0));
        // Obrim bucle per a mostrar la informació de la cançó
        for (String s : songsInPlaylist) {
            Object[] rowData = {s};
            songListModel.addRow(rowData);
        }
    }

    public void definePlaylistName(String playlistName) {
        jtArea.setText(playlistName);
    }
}
