package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.DeleteSongTableModel;
import Presentation.SongTableModel;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DeleteSongView extends JPanel {
    private static final String[] COLUMN_NAMES = {"Songs", "Artist", "Genre"};
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    public static final String BTN_DELETE = "BTN_DELETE";
    //public static final String BTN_BUSCADOR = "BTN_BUSCADOR";

    private DefaultTableModel deleteTableModel;
    private JTextField jBuscador;
    //private JButton jCerca;
    private JTable table;
    private JLabel name;
    private JTextField input;
    private JButton delete;
    private Object[][] dataTableModel;
    private static String[] columnHeaders = {"Title", "Artist", "Genre"};

    public DeleteSongView(HeaderView headerView, Utils utils, FooterView footerView, SongTableModel songTableModel){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        deleteTableModel = new DefaultTableModel(columnHeaders, 0);
    }

    public void addDeleteSongController(DeleteSongViewController deleteSongController){
     //   delete.addActionListener(deleteSongController);
        //jCerca.addActionListener(deleteSongController);
    }

    public void configureDeleteSongView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        // NORTH
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.MUSIC_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

            // Buscador
        //Icon buscadorBtn = new ImageIcon(String.valueOf(AssetsFiles.BUSCADOR_BUTTON_IMG));
        //jCerca = new JButton(buscadorBtn);
        //jCerca.setActionCommand(BTN_BUSCADOR);
        jBuscador = new JTextField();
        center.add(utils.panelBuscador(jBuscador),BorderLayout.NORTH);


        table = new JTable(deleteTableModel);
        JScrollPane scrollpane = createSongListTable(table);
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        // SOUTH
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

    public JTextField getjBuscador() {
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
        for (String s : deleteSongs) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            deleteTableModel.addRow(rowData);
        }
    }

}
