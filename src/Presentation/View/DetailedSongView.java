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
    private final Utils utils;

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String BTN_LYRICS = "BTN_LYRICS";
    public static final String BTN_PLAYLIST = "BTN_PLAYLIST";
    public static final String BTN_PLAYME = "BTN_PLAYME";
    private JButton jback = new JButton("back");
    private JButton jlogout = new JButton("logout");
    private JButton jdelete = new JButton("delete account");
    private JButton jLyrics;
    private JTextArea jTextArea;
    private HeaderView headerView;
    private FooterView footerView;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private static String[] columnHeaders = {"", ""};
    private JLabel jlLyrics;
    private JLabel jlSong;
    private JLabel jlPlaylist;
    private JLabel jlPlayMe;
    private JButton jPlaylist;
    private JButton jPlay;



    public DetailedSongView(Utils utils, HeaderView headerView, FooterView footerView) {
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        defaultTableModel = new DefaultTableModel(columnHeaders, 0);
        jTextArea = new JTextArea();
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController) {
        jPlaylist.addActionListener(detailedSongViewController);
        jPlay.addActionListener(detailedSongViewController);
    }

    public void configureDetailedSongView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26, 26, 26);

        // NORTH
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.SONG_LABEL));
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        //CENTER

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 50, 80, 50));

        // Table song
        Dimension dimension = new Dimension(400,220);
        table = new JTable(defaultTableModel);
        JScrollPane scrollpane = createSongListTable(table, gris);
        scrollpane.setPreferredSize(dimension);
        scrollpane.setMinimumSize(dimension);
        scrollpane.setMaximumSize(dimension);

        // TextArea
        JScrollPane scrollpane2 = new JScrollPane(jTextArea);
        scrollpane2.setPreferredSize(dimension);
        scrollpane2.setMinimumSize(dimension);
        scrollpane2.setMaximumSize(dimension);

        //Boto playlist
        Icon playlistBtn = new ImageIcon(String.valueOf(AssetsFiles.PLAYLIST_PLUS_ADD));
        jPlaylist = utils.buttonImg(playlistBtn);
        jPlaylist.setBackground(gris);
        jPlaylist.setActionCommand(BTN_PLAYLIST);

        //Boto palySong
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jPlay = utils.buttonImg(playBtn);
        jPlay.setBackground(gris);
        jPlay.setActionCommand(BTN_PLAYME);

        // Jlables
        jlSong = new JLabel("");
        jlSong.setForeground(Color.GREEN);
        jlSong.setFont(new Font("Gotham", Font.BOLD, 27));

        jlLyrics  = new JLabel("LYRICS");
        jlLyrics.setForeground(Color.GREEN);
        jlLyrics.setFont(new Font("Gotham", Font.BOLD, 27));

        jlPlaylist  = new JLabel("ADD to Playlist");
        jlPlaylist.setForeground(Color.GREEN);
        jlPlaylist.setFont(new Font("Gotham", Font.BOLD, 20));

        jlPlayMe  = new JLabel("PLAY ME");
        jlPlayMe.setForeground(Color.GREEN);
        jlPlayMe.setFont(new Font("Gotham", Font.BOLD, 20));

        Insets columnSpacing = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 20;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.WEST;

        center.add(jlSong, constraints);
        constraints.gridy++;
        center.add(scrollpane, constraints);

        // DESPLACEM A LA DRETA
        constraints.gridx = 1000;
        constraints.gridy = 0;
        constraints.insets = columnSpacing;
        center.add(jlLyrics, constraints);

        constraints.gridy++;
        constraints.weighty = 15;
        constraints.weightx = 20;
        center.add(scrollpane2, constraints);

        // Botóns
        //constraints.insets = new Insets(0, 0, 20, 0);
        constraints.gridx++;
        constraints.gridy = 0;
        constraints.weighty = 4;
        constraints.gridheight = 6;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 0, 0);
        center.add(jlPlaylist, constraints);
        constraints.gridx++;
        center.add(jPlaylist, constraints);
        constraints.gridy++;
        constraints.gridx--;
        constraints.insets = new Insets(80, 0, 0, 0);
        center.add(jlPlayMe, constraints);
        constraints.gridx++;
        center.add(jPlay, constraints);

        add(center, BorderLayout.CENTER);

        /*constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0,0,0,0);
        centerTotal.add(center);
        constraints.gridy++;
        centerTotal.add(jlPlaylist);
        centerTotal.add(jPlaylist);*/



        // SOUTH
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

    }

    private JScrollPane createSongListTable(JTable table, Color gris) {
        table.setRowHeight(40);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(gris);
        table.setSelectionForeground(Color.WHITE);
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(new Font("Gotham", Font.BOLD, 25));
                cellComponent.setForeground(Color.decode("#00DC00"));
                return cellComponent;
            }
        });

        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
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

    public void fillDetailedTable(ArrayList<String> song) {
        defaultTableModel.setRowCount(0);
        int countRow = 0;
        String[] row = {"Song", "Genre", "Artist", "Album", "Uploaded by", "Time"};
        for (String s : song) {
            String[] songInfo = s.split("-");
            Object[] rowData = {row[countRow], songInfo[0]};
            defaultTableModel.addRow(rowData);
            countRow++;
        }
    }

    public void fillLyriscText(String lyrics) {
        Color gris = new Color(26, 26, 26);
        jTextArea.setEditable(false);
        jTextArea.setBackground(gris);
        jTextArea.setForeground(Color.decode("#00DC00"));
        jTextArea.setFont(new Font("Gotham", Font.BOLD, 15));

        String[] lines = lyrics.split("\n");
        StringBuilder centeredText = new StringBuilder();
        for (String line : lines) {
            centeredText.append(String.format("%" + 10 + "s%s\n", "", line));
        }

        jTextArea.setText(centeredText.toString());
        jTextArea.setCaretPosition(0);
    }

    public void showPopUp(String error) {
        JOptionPane.showMessageDialog(this,error);
    }
}