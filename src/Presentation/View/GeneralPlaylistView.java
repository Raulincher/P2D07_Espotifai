package Presentation.View;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Presentation.AssetsFiles;
import Presentation.Controller.GeneralPlaylistViewController;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class GeneralPlaylistView extends JPanel {

    // Preparem els atributs
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    // Preparem els strings per quan es premi un botó
    private static final String BTN_NEW_PLAYLIST = "BTN_NEW_PLAYLIST";

    // Preparem els elements Swing
    private JButton jNewPlaylist;
    private DefaultTableModel myPlaylistsModel;
    private DefaultTableModel otherPlaylistsModel;
    private JTable myPlaylistsTable;
    private JTable otherPlaylistsTable;
    private JLabel jlYourPlaylists;
    private JLabel jlOthersPlaylists;

    // Preparem els arrays
    private static String[] myPlaylist = {"Your library:"};
    private static String[] otherPlaylist = {"General library:"};

    private final static String MY_PLAYLISTS = "My playlists";
    private final static String OTHERS_PLAYLISTS = "Others playlists";

    /**
     * Funció que servirà com a constructor de la GeneralPlaylistView
     *
     * @param headerView, vista per a posar el Header
     * @param utils, per usar tots els seus mètodes
     * @param footerView, vista per a posar el Footer
     */
    public GeneralPlaylistView(HeaderView headerView,FooterView footerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        myPlaylistsModel = new DefaultTableModel(myPlaylist, 0);
        otherPlaylistsModel = new DefaultTableModel(otherPlaylist, 0);
    }

    /**
     * Funció que servirà per a vincular-ho amb el controller
     *
     * @param generalPlaylistViewController, controller en del GeneralPlaylistController
     */
    public void addGeneralPlaylistController(GeneralPlaylistViewController generalPlaylistViewController){
    }

    /**
     * Funció que servirà per a configurar tots els elements Swing
     * de la GeneralPlaylistView
     *
     * No tindrà param ni return
     */
    public void configureGeneralPlaylistView() {
        // Iniciem BorderLayout i el configurem
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //NORTH
        // Creem el JPanel del nord i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        // Afegim el Label que desitgem i afegim el Header
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.LISTMANAGING_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center i el configurem
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        // Preparem les mides de les taules
        Dimension dimension = new Dimension(300,220);

        // Creem una JTable de MyPlaylist
        myPlaylistsTable = new JTable(myPlaylistsModel);

        // Afegim un JScrollPane en la JTable i li donem el format correcte
        JScrollPane scrollMyPlaylists = createSongListTable(myPlaylistsTable);
        scrollMyPlaylists.setPreferredSize(dimension);
        scrollMyPlaylists.setMinimumSize(dimension);
        scrollMyPlaylists.setMaximumSize(dimension);

        // Creem una JTable de Other's Playlist
        otherPlaylistsTable = new JTable(otherPlaylistsModel);

        // Afegim un JScrollPane en la JTable i donem el format correcte
        JScrollPane scrollOthersPlaylists = createSongListTable(otherPlaylistsTable);
        scrollOthersPlaylists.setPreferredSize(dimension);
        scrollOthersPlaylists.setMinimumSize(dimension);
        scrollOthersPlaylists.setMaximumSize(dimension);

        // Preparem el JLabel de My Playlist i li donem forma
        jlYourPlaylists = new JLabel(MY_PLAYLISTS);
        jlYourPlaylists.setForeground(Color.GREEN);
        jlYourPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        // Preparem el JLabel de Other's Playlists i li donem forma
        jlOthersPlaylists = new JLabel(OTHERS_PLAYLISTS);
        jlOthersPlaylists.setForeground(Color.GREEN);
        jlOthersPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        // Preparem un GridBagConstraints per a ordenar les taules
        Insets columnSpacing = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 20;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.WEST;

        // Afegim la primera Playlist i el seu JScrollPane
        center.add(jlYourPlaylists, constraints);
        constraints.gridy++;
        center.add(scrollMyPlaylists, constraints);

        // Desplacem a la dreta per afegir l'altra JTable
        constraints.gridx = 1000;
        constraints.gridy = 0;
        constraints.insets = columnSpacing;

        // Afegim la JTable de Other's Playlists i li donem forma
        center.add(jlOthersPlaylists, constraints);
        constraints.gridy++;
        constraints.weighty = 15;
        constraints.weightx = 20;
        center.add(scrollOthersPlaylists, constraints);
        constraints.gridy++;

        // Ho afegim al BorderLayout
        add(center, BorderLayout.CENTER);


        // SOUTH
        // Creem el JPanel de sud i li afegim el Footer
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Funció JScrollPane per donar forma al que usarem per a les
     * dues taules de les Playlists
     *
     * @param table, JTable a la que se li afegirà el JScrollPane
     * @return JScrollPane(table), JTable amb el JScrollPane configurat
     */
    public JScrollPane createSongListTable(JTable table){
        // Creem el color gris
        Color gris = new Color(26,26,26);

        // Assignem mides, color i forma
        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(Color.decode("#00DC00"));

        // Preparem els headers de les JTables amb el seu format
        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        header.setFont(new Font("Gotham", Font.BOLD, 20));

        // Afegim el header i donem format a la font de la JTable
        table.getTableHeader().setDefaultRenderer(header);
        table.setFont(new Font("Gotham", Font.BOLD, 20));

        return new JScrollPane(table);
    }

}
