package Presentation.View;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Presentation.AssetsFiles;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class GeneralPlaylistView extends JPanel {

    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    private static final String BTN_NEW_PLAYLIST = "BTN_NEW_PLAYLIST";
    private JButton jNewPlaylist;

    public GeneralPlaylistView(HeaderView headerView,FooterView footerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
        //set action command
    }


    public void configureGeneralPlaylistView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //North
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.LISTMANAGING_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        //Center
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Taula your lists
        JPanel panelPlaylist = new JPanel();
        panelPlaylist.setBackground(Color.BLACK);
        JTextField jtBuscador = utils.textField();
        Icon newPlaylist = new ImageIcon(String.valueOf(AssetsFiles.CREATE_LIST_BUTTON_IMG));
        jNewPlaylist = utils.buttonImg(newPlaylist);
        jNewPlaylist.setActionCommand(BTN_NEW_PLAYLIST);
        jNewPlaylist.setPreferredSize(new Dimension(100,50));
        jNewPlaylist.setMaximumSize(new Dimension(100,50));
        panelPlaylist.add(jtBuscador);
        panelPlaylist.add(jNewPlaylist);
        center.add(panelPlaylist,BorderLayout.NORTH);

        //CANVIAR PER LA LLISTA DE PLAYLIST your list
        ArrayList<Playlist> playlists = new ArrayList<>();
        Playlist yourList = new Playlist("user34", "Prova1");
        playlists.add(yourList);
        yourList = new Playlist("user45", "prova2");
        playlists.add(yourList);

        String[] columnNames = {""};
        Object[][] data = new Object[playlists.size()][1];

        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist1 = playlists.get(i);
            data[i][0] = playlist1.getTitle();
        }

        JTable table = new JTable(data, columnNames);


        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Gotham", Font.BOLD, 20));

        JScrollPane scrollPaneYourList = new JScrollPane(table);

        //Taula 2
        //CANVIAR PER LA LLISTA DE PLAYList others
        ArrayList<Playlist> playlists1 = new ArrayList<>();
        Playlist othersList = new Playlist("user34", "Prova21");
        playlists1.add(othersList);
        othersList = new Playlist("user45", "prova22");
        playlists1.add(othersList);

        String[] columnNames1 = {""};
        Object[][] data1 = new Object[playlists1.size()][1];

        for (int i = 0; i < playlists1.size(); i++) {
            Playlist playlist2 = playlists.get(i);
            data[i][0] = playlist2.getTitle();
        }

        JTable table1 = new JTable(data1, columnNames1);


        table1.setRowHeight(60);
        table1.setGridColor(Color.gray);
        table1.setBackground(gris);
        table1.setForeground(Color.WHITE);
        table1.setFont(new Font("Gotham", Font.BOLD, 20));

        JScrollPane scrollPaneOthersList = new JScrollPane(table);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPaneYourList, scrollPaneOthersList);
        splitPane.setDividerLocation(150);
        center.add(splitPane,BorderLayout.CENTER);
        add(center,BorderLayout.CENTER);

        //South
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));

        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }
}
