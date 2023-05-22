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


    public DetailedSongView(Utils utils, HeaderView headerView, FooterView footerView) {
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        defaultTableModel = new DefaultTableModel(columnHeaders, 0);
        jTextArea = new JTextArea();
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController) {
        //jLyrics.addActionListener(detailedSongViewController);
    }

    public void configureDetailedSongView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26, 26, 26);

        // NORTH
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));
        ;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        //CENTER
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Taula Song
        /*table = new JTable(defaultTableModel);
        JScrollPane scrollpane = createSongListTable(table,gris);
        Icon lyricsBtn = new ImageIcon(String.valueOf(AssetsFiles.LYRICS_IMG));
        JLabel label1 = utils.label("Lyrics");
        jLyrics = utils.buttonImg(lyricsBtn);
        jLyrics.setBackground(gris);
        jLyrics.setActionCommand(BTN_LYRICS);
        JPanel panelEast = new JPanel();
        panelEast.setBackground(Color.BLACK);
        panelEast.setFont(new Font("Gotham", Font.BOLD, 20));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        label1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        jLyrics.setBorder(BorderFactory.createEmptyBorder(15, 35, 0, 0));
        panelEast.add(label1);
        panelEast.add(jLyrics);

        center.add(scrollpane, BorderLayout.CENTER);
        center.add(panelEast, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);*/

        table = new JTable(defaultTableModel);
        JScrollPane scrollpane = createSongListTable(table, gris);
        JScrollPane scrollpane2 = new JScrollPane(jTextArea);
        //JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollpane, scrollpane2);
        center.add(scrollpane, BorderLayout.WEST);
        center.add(scrollpane2, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);

        // SOUTH
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

    }

    private JScrollPane createSongListTable(JTable table, Color gris) {
        table.setRowHeight(45);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(gris);
        table.setSelectionForeground(Color.WHITE);
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(new Font("Gotham", Font.BOLD, 30));
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
}