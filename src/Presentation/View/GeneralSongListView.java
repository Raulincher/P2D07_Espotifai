package Presentation.View;

import Business.Entities.Song;
import Presentation.AssetsFiles;
import Presentation.Controller.GeneralSongListViewController;
import Presentation.SongTableModel;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class GeneralSongListView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";
    public static final String BTN_SONG = "BTN_SONG";
    private final Utils utils;
    private HeaderView headerView;
    private FooterView footerView;
    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;
    private SongTableModel songTableModel;

    public GeneralSongListView(Utils utils, HeaderView headerView, FooterView footerView,SongTableModel songTableModel ){
        this.utils = utils;
        this.footerView = footerView;
        this.headerView = headerView;
        this.songTableModel = songTableModel;
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
        JPanel panelBuscador = new JPanel();
        panelBuscador.setBackground(Color.BLACK);
        Icon buscadorBtn = new ImageIcon(String.valueOf(AssetsFiles.BUSCADOR_BUTTON_IMG));
        jCerca = new JButton(buscadorBtn);
        jCerca.setActionCommand(BTN_BUSCADOR);
        jBuscador = new JTextField();
        jBuscador.setPreferredSize(new Dimension(300, 40));
        jBuscador.setMinimumSize(new Dimension(300,40));
        panelBuscador.add(jBuscador);
        panelBuscador.add(jCerca);

        //Taula ListSong
        center.add(panelBuscador,BorderLayout.NORTH);
        JScrollPane scrollpane = createSongListTable();

        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));

        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

    }


    public JScrollPane createSongListTable(){
        Color gris = new Color(26,26,26);

        DefaultTableModel model = songTableModel.getAllSongsTableModel();
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

    public JTextField getjBuscador() {
        return jBuscador;
    }

    public JTable getTable() {
        return table;
    }




}
