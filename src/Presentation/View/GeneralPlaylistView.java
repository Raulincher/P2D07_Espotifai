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
    public static final String BTN_NEW_PLAYLIST = "BTN_NEW_PLAYLIST";
    private JButton jNewPlaylist;
    private DefaultTableModel myPlaylistsModel;
    private DefaultTableModel otherPlaylistsModel;
    private JTable myPlaylistsTable;
    private JTable otherPlaylistsTable;
    private JLabel jlYourPlaylists;
    private JLabel jlOthersPlaylists;
    private JTextField jMyPlaylistBuscador;
    private JTextField jOthersPlaylistBuscador;
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
        //set action command
        jNewPlaylist.addActionListener(generalPlaylistViewController);
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

        // Textfields per buscar + imatge create List
        jMyPlaylistBuscador = utils.textField();
        jOthersPlaylistBuscador = utils.textField();

        Icon newPlaylist = new ImageIcon(String.valueOf(AssetsFiles.CREATE_LIST_BUTTON_IMG));
        jNewPlaylist = utils.buttonImg(newPlaylist);
        jNewPlaylist.setActionCommand(BTN_NEW_PLAYLIST);
        jNewPlaylist.setPreferredSize(new Dimension(100,50));
        jNewPlaylist.setMaximumSize(new Dimension(100,50));

        // My Playlists
        Dimension dimension = new Dimension(300,220);
        myPlaylistsTable = new JTable(myPlaylistsModel);
        JScrollPane scrollMyPlaylists = createSongListTable(myPlaylistsTable);
        scrollMyPlaylists.setPreferredSize(dimension);
        scrollMyPlaylists.setMinimumSize(dimension);
        scrollMyPlaylists.setMaximumSize(dimension);

        // Others
        otherPlaylistsTable = new JTable(otherPlaylistsModel);
        JScrollPane scrollOthersPlaylists = createSongListTable(otherPlaylistsTable);
        scrollOthersPlaylists.setPreferredSize(dimension);
        scrollOthersPlaylists.setMinimumSize(dimension);
        scrollOthersPlaylists.setMaximumSize(dimension);

        // Jlables
        jlYourPlaylists = new JLabel(MY_PLAYLISTS);
        jlYourPlaylists.setForeground(Color.GREEN);
        jlYourPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        jlOthersPlaylists = new JLabel(OTHERS_PLAYLISTS);
        jlOthersPlaylists.setForeground(Color.GREEN);
        jlOthersPlaylists.setFont(new Font("Gotham", Font.BOLD, 27));

        Insets columnSpacing = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 20;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.WEST;

        center.add(jlYourPlaylists, constraints);
        constraints.gridy++;
        center.add(jMyPlaylistBuscador, constraints);
        constraints.gridx++;
        center.add(jNewPlaylist, constraints);
        constraints.gridx--;
        constraints.gridy++;
        center.add(scrollMyPlaylists, constraints);

        // DESPLACEM A LA DRETA
        constraints.gridx = 1000;
        constraints.gridy = 0;
        constraints.insets = columnSpacing;

        center.add(jlOthersPlaylists, constraints);
        constraints.gridy++;
        constraints.weighty = 15;
        constraints.weightx = 20;
        center.add(jOthersPlaylistBuscador, constraints);
        constraints.gridy++;
        center.add(scrollOthersPlaylists, constraints);
        constraints.gridy++;

        add(center, BorderLayout.CENTER);


        //South
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
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

    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

}
