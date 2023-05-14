package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.GeneralSongListViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class GeneralSongListView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";
    private final Utils utils;
    private HeaderView headerView;
    private FooterView footerView;
    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;
    private DefaultTableModel songsTableModel;
    private static String[] columnHeaders = {"Title", "Artist", "Genre"};

    public GeneralSongListView(Utils utils, HeaderView headerView, FooterView footerView){
        this.utils = utils;
        this.footerView = footerView;
        this.headerView = headerView;
        songsTableModel = new DefaultTableModel(columnHeaders, 0);
    }

    public void addGeneralSongListController(GeneralSongListViewController generalSongListViewController){
        jCerca.addActionListener(generalSongListViewController);
        table.addMouseListener(generalSongListViewController);
    }

    public void configureGeneralSonglistView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //North
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.MUSIC_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        //Center
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Buscador
        Icon buscadorBtn = new ImageIcon(String.valueOf(AssetsFiles.BUSCADOR_BUTTON_IMG));
        jCerca = new JButton(buscadorBtn);
        jCerca.setActionCommand(BTN_BUSCADOR);
        jBuscador = new JTextField();
        center.add(utils.panelBuscador(jBuscador),BorderLayout.NORTH);

        // Taula ListSong
        table = new JTable(songsTableModel);
        JScrollPane scrollpane = createSongListTable(table);
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(songsTableModel);
        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);
        jBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }
        });

        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));

        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

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

    public JTextField getjBuscador() {
        return jBuscador;
    }

    public JTable getTable() {
        return table;
    }

    public void fillTable(ArrayList<String> songs) {
        songsTableModel.setRowCount(0);

        for (String s : songs) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            songsTableModel.addRow(rowData);
        }
    }

    private void search(String query, TableRowSorter<DefaultTableModel> sorter) {

        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0)); // Search by the first column (title)
        }
    }
}
