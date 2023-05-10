package Presentation.View;

import Business.Entities.Song;
import Business.SongManager;
import Business.UserManager;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TableView;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedSongView extends JPanel {
    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";
    private JButton jback = new JButton("back");
    private JButton jlogout = new JButton("logout");
    private JButton jdelete = new JButton("delete account");
    private HeaderView headerView;
    private FooterView footerView;
    private SongManager songManager;


    public DetailedSongView(Utils utils,HeaderView headerView,FooterView footerView, SongManager songManager){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        this.songManager = songManager;
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController){
        //set action command
        //jback.addActionListener(detailedSongViewController);
        //jlogout.addActionListener(detailedSongViewController);
        //jdelete.addActionListener(detailedSongViewController);
    }

    public void configureDetailedSongView() {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //North
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.SONG_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        //Taula Song
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Song song = songManager.getSelectedSong();
        /*if (song == null){
            song = new Song("Prova", "pop","album","author","file");
        }*/
        String[] columnNames = {"", ""};
        Object[][] data = {
                {"Song", "song.getTile()"},
                {"Genre", "song.getGenre()"},
                {"Artist", "song.getAuthor()"},
                {"Album", "song.getAlbum()"},
                {"Uploaded by", "Prova_by"}
        };
        JTable table = new JTable(data, columnNames);

        TableColumn columna;
        columna = table.getColumnModel().getColumn(0);
        columna.setPreferredWidth(300);
        columna.setMaxWidth(300);
        columna.setMinWidth(300);
        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(gris);
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setForeground(Color.decode("#00DC00"));
        renderer.setFont(new Font("Gotham", Font.BOLD, 35));
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);

        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
        renderer2.setForeground(Color.white);
        renderer2.setFont(new Font("Gotham", Font.BOLD, 20));
        table.getColumnModel().getColumn(1).setCellRenderer(renderer2);

        table.setFont(new Font("Gotham", Font.BOLD, 35));
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
}
