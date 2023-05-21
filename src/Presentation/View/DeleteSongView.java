package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
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

public class DeleteSongView extends JPanel {
    private static final String[] COLUMN_NAMES = {"Songs", "Artist", "Genre"};
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";
    private DefaultTableModel deleteTableModel;
    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;
    private JLabel name;
    private JTextField input;
    private JButton delete;
    private Object[][] dataTableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnHeaders = {"Title", "Artist", "Genre"};

    public DeleteSongView(HeaderView headerView, Utils utils, FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        deleteTableModel = new DefaultTableModel(columnHeaders, 0);
        sorter =  new TableRowSorter<>(deleteTableModel);
    }

    public void addDeleteSongController(DeleteSongViewController deleteSongController){
        jBuscador.getDocument().addDocumentListener(deleteSongController);
        table.addMouseListener(deleteSongController);
    }

    public void configureDeleteSongView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        // NORTH
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        // Declarem buscador
        jBuscador = utils.textField();
        center.add(utils.panelBuscador(jBuscador),BorderLayout.NORTH);

        table = new JTable(deleteTableModel);
        JScrollPane scrollpane = createSongListTable(table);
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);

        // SOUTH
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

    public JTextField getjtfBuscador() {
        return jBuscador;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
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


    public void fillDeleteTable(ArrayList<String> deleteSongs) {
        deleteTableModel.setRowCount(0);
        for (String s : deleteSongs) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            deleteTableModel.addRow(rowData);
        }
    }

    public void search(String query, TableRowSorter<DefaultTableModel> sorter) {
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0)); // Search by the first column (title)
        }
    }

    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }

    public String obtainSongNameToDelete(Point point) {
        int indexFila = table.rowAtPoint(point);
        System.out.println(indexFila);
        // Get the data from the selected row
        String songTitle = (String) table.getValueAt(indexFila, 0);
        return songTitle;
    }

    public int obtainSongIndexToDelete(String songTitle) {
        int indexSong = 0;
        for (int i = 0; i < deleteTableModel.getRowCount(); i++) {
            if (deleteTableModel.getValueAt(i,0).equals(songTitle)) {
                indexSong = i;
            }
        }
        return indexSong;
    }

    public int confirmationDeletePopUp(String songTitle) {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to proceed deleting " + songTitle + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

        return result;
    }

    public void clearSearcher() {
        jBuscador.setText("");
    }

}
