package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.SongTableModel;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DeleteSongView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";

    private SongTableModel songTableModel;

    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;
    private JLabel name;
    private JTextField input;
    private JButton delete;

    public DeleteSongView(HeaderView headerView, Utils utils, FooterView footerView, SongTableModel songTableModel){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        this.songTableModel = songTableModel;
    }

    public void addDeleteSongController(DeleteSongViewController deleteSongController){
     //   delete.addActionListener(deleteSongController);
    }

    public void configureDeleteSongView() {

        //NORTH
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.MUSIC_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);


        // CENTER
        JPanel center = new JPanel();
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Buscador
        Icon buscadorBtn = new ImageIcon(String.valueOf(AssetsFiles.BUSCADOR_BUTTON_IMG));
        jCerca = new JButton(buscadorBtn);
        jCerca.setActionCommand(BTN_BUSCADOR);
        jBuscador = new JTextField();
        center.add(utils.panelBuscador(jCerca, jBuscador, BTN_BUSCADOR),BorderLayout.NORTH);

        //Taula ListSong
        JScrollPane scrollpane = createSongListTable();
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        /*DefaultTableModel model = songTableModel.getDeleteTableModel();
        JTable deleteSongsTable = new JTable(model);
        JScrollPane deleteSongsScroll = new JScrollPane(deleteSongsTable);
        center.add(deleteSongsScroll);
        add(center, BorderLayout.CENTER);*/

        // SOUTH
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);


    }

    public JTextField getInput() {
        return input;
    }

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    public JScrollPane createSongListTable(){
        Color gris = new Color(26,26,26);

        DefaultTableModel model = songTableModel.getDeleteTableModel();
        table = new JTable(model);


        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        //table.setSelectionBackground(Color.decode("#8B898B"));
        //table.setSelectionForeground(gris);
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(Color.decode("#00DC00"));

        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        //header.setBorder(BorderFactory.createLineBorder(Color.gray));
        header.setFont(new Font("Gotham", Font.BOLD, 20));
        table.getTableHeader().setDefaultRenderer(header);

        table.setFont(new Font("Gotham", Font.BOLD, 20));
        //scrollpane.setBackground(gris);
        //scrollpane.getVerticalScrollBar().setBackground(Color.BLACK);

        return new JScrollPane(table);
    }

}
