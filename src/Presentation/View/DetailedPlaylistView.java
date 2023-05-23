package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DetailedPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedPlaylistView extends JPanel {

    // Preparem tots els atributs
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    // Preparem els strings per quan es premi un botó
    public static final String BTN_BACK = "BTN_BACK";

    // Preparem els elements de Swing
    private JButton jback;

    /**
     * Funció que servirà com a constructor de la DetailedPlaylistView
     *
     * @param utils, per usar tots els seus mètodes
     */
    public DetailedPlaylistView(HeaderView headerView, FooterView footerView, Utils utils){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar els diferents listeners
     *
     * @param detailedPlaylistViewController, controller de la DetailedPlaylistView
     */
    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){

        //jback.addActionListener(detailedPlaylistViewController);
    }

    /**
     * Funció que servirà per a configurar tots els elements
     * de Swing de la vista
     *
     * No tindrà ni param ni return
     */
    public void configureDetailedPlaylistView() {
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

        //South
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }
}
