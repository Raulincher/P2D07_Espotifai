package Presentation.View;

import Business.Entities.Song;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Controller.GeneralSongListViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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

    public GeneralSongListView(Utils utils, HeaderView headerView, FooterView footerView){
        this.utils = utils;
        this.footerView = footerView;
        this.headerView = headerView;
    }

    public void addGeneralSongListController(GeneralSongListViewController generalSongListViewController){
        jCerca.addActionListener(generalSongListViewController);
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
        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jCerca = new JButton(backBtn);
        jCerca.setActionCommand(BTN_BUSCADOR);
        jBuscador = new JTextField();
        jBuscador.setPreferredSize(new Dimension(300, 40));
        jBuscador.setMinimumSize(new Dimension(300,40));
        panelBuscador.add(jBuscador);
        panelBuscador.add(jCerca);

        center.add(panelBuscador,BorderLayout.NORTH);


        //CANVIAR PER LA LLISTA DE CANÇONS
        ArrayList<Song> songList = new ArrayList<>();
        Song song = new Song("Hola", "Pop","2001","TRE", "HOLA");
        songList.add(song);
        song = new Song("Adeu", "Pop","2001","Wer", "hOLE");
        songList.add(song);

        String[] columnNames = {"Song", "Artist"};
        Object[][] data = new Object[songList.size()][2];

        for (int i = 0; i < songList.size(); i++) {
            Song song1 = songList.get(i);
            data[i][0] = song1.getTile();
            data[i][1] = song1.getAuthor();
        }

        JTable table = new JTable(data, columnNames);

        TableColumn columna;
        columna = table.getColumnModel().getColumn(0);
        columna.setPreferredWidth(600);
        columna.setMaxWidth(600);
        columna.setMinWidth(600);
        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);

        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        //header.setBorder(BorderFactory.createLineBorder(Color.gray));
        header.setFont(new Font("Gotham", Font.BOLD, 20));
        table.getTableHeader().setDefaultRenderer(header);

        table.setFont(new Font("Gotham", Font.BOLD, 20));

        JScrollPane scrollpane = new JScrollPane(table);
        //scrollpane.setBackground(gris);
        //scrollpane.getVerticalScrollBar().setBackground(Color.BLACK);
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));

        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

    }

    public JTextField getjBuscador() {
        return jBuscador;
    }
}
