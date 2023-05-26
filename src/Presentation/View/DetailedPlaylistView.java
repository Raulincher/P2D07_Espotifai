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
    private JButton jbDeletePlaylist;
    private JButton jbDeleteSong;
    private JButton jbSongUp;
    private JButton jbSongDown;

    private DefaultTableModel songListModel;
    private JTable songListTable;
    public static final String BTN_DELETE_PLAYLIST = "BTN_DELETE_PLAYLIST";
    public static final String BTN_SONG_UP = "BTN_SONG_UP";
    public static final String BTN_SONG_DOWN = "BTN_SONG_DOWN";
    public static final String BTN_DELETE_SONG_FROM_PLAYLIST = "BTN_DELETE_SONG_FROM_PLAYLIST";
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
        jbDeletePlaylist.addActionListener(detailedPlaylistViewController);
        songListTable.addMouseListener(detailedPlaylistViewController);
        jbDeleteSong.addActionListener(detailedPlaylistViewController);
        jbSongDown.addActionListener(detailedPlaylistViewController);
        jbSongUp.addActionListener(detailedPlaylistViewController);
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


        jtArea = new JTextArea();
        jtArea.setBackground(Color.BLACK);
        jtArea.setForeground(Color.GREEN);
        jtArea.setFont(new Font("Gotham", Font.BOLD, 27));
        jtArea.setEditable(false);
        jtArea.setFocusable(false);
        jtArea.setPreferredSize(new Dimension(200, 50));
        north.add(jtArea, BorderLayout.WEST);

        ImageIcon addFileBtnUp = new ImageIcon(String.valueOf(AssetsFiles.SONG_GO_UP_BUTTON_IMG));
        jbSongUp = utils.buttonImg(addFileBtnUp);
        jbSongUp.setActionCommand(BTN_SONG_UP);
        north.add(jbSongUp,BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);

        ImageIcon addFileBtnDown = new ImageIcon(String.valueOf(AssetsFiles.SONG_GO_DOWN_BUTTON_IMG));
        jbSongDown = utils.buttonImg(addFileBtnDown);
        jbSongDown.setActionCommand(BTN_SONG_DOWN);
        north.add(jbSongDown,BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);

        ImageIcon addFileBtnAuxDelete = new ImageIcon(String.valueOf(AssetsFiles.DELETE_SONG_FROM_LIST_BUTTON_IMG));
        jbDeleteSong = utils.buttonImg(addFileBtnAuxDelete);
        jbDeleteSong.setActionCommand(BTN_DELETE_SONG_FROM_PLAYLIST);
        north.add(jbDeleteSong,BorderLayout.NORTH);

        ImageIcon addFileBtnAux = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LIST_BUTTON_IMG));
        jbDeletePlaylist = utils.buttonImg(addFileBtnAux);
        jbDeletePlaylist.setActionCommand(BTN_DELETE_PLAYLIST);
        north.add(jbDeletePlaylist,BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);

        //Center
        JPanel center = new JPanel();
        center.setBackground(Color.black);

        Dimension dimension = new Dimension(700, 250);
        songListTable = new JTable(songListModel);
        JScrollPane scrollMyPlaylists = createSongListTable(songListTable);
        scrollMyPlaylists.setPreferredSize(dimension);
        scrollMyPlaylists.setMinimumSize(dimension);
        scrollMyPlaylists.setMaximumSize(dimension);
        scrollMyPlaylists.setForeground(Color.DARK_GRAY);
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

        table.setRowHeight(50);
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
        if (songsInPlaylist != null) {
            // Obrim bucle per a mostrar la informació de la cançó
            for (String s : songsInPlaylist) {
                Object[] rowData = {s};
                songListModel.addRow(rowData);
            }
        }
    }

    public void definePlaylistName(String playlistName) {
        jtArea.setText(playlistName);
    }

    public void showPopUp(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    public void hideButton() {
        jbDeletePlaylist.setVisible(false);
        jbDeleteSong.setVisible(false);
        jbSongUp.setVisible(false);
        jbSongDown.setVisible(false);
    }

    public void showButton() {
        jbDeletePlaylist.setVisible(true);
        jbDeleteSong.setVisible(true);
        jbSongUp.setVisible(true);
        jbSongDown.setVisible(true);
    }

    public String obtainSongName(Point point) {
        int indexFila = songListTable.rowAtPoint(point);
        String playlistName = (String) songListTable.getValueAt(indexFila, 0);
        return playlistName;
    }

    public String getPlaylistName() {
        return jtArea.getText();
    }

    public void moveSelectedRowUp() {
        int selectedRow = songListTable.getSelectedRow();
        if (selectedRow > 0) {
            songListModel.moveRow(selectedRow, selectedRow, selectedRow - 1);
            songListTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
        }
    }
    public void moveSelectedRowDown() {
        int selectedRow = songListTable.getSelectedRow();
        int rowCount = songListTable.getRowCount();

        if (selectedRow >= 0 && selectedRow < rowCount - 1) {
            songListModel.moveRow(selectedRow, selectedRow, selectedRow + 1);
            songListTable.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
        }
    }
}
