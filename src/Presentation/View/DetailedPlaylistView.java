package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DetailedPlaylistViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class DetailedPlaylistView extends JPanel {

    // Preparem tots els atributs
    private final Utils utils;

    // Preparem els strings per quan es premi un botó
    public static final String BTN_BACK = "BTN_BACK";

    // Preparem els elements de Swing
    private JButton jback;

    /**
     * Funció que servirà com a constructor de la DetailedPlaylistView
     *
     * @param utils, per usar tots els seus mètodes
     */
    public DetailedPlaylistView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar els diferents listeners
     *
     * @param detailedPlaylistViewController, controller de la DetailedPlaylistView
     */
    public void addDetailedPlaylistController(DetailedPlaylistViewController detailedPlaylistViewController){

        jback.addActionListener(detailedPlaylistViewController);
    }

    /**
     * Funció que servirà per a configurar tots els elements
     * de Swing de la vista
     *
     * No tindrà ni param ni return
     */
    public void configureDetailedPlaylistView() {

        JLabel jLogo = new JLabel("detailed playlist");

        jback = utils.buttonText("back");
        jback.setActionCommand(BTN_BACK);

        add(jLogo);
        add(jback);
    }
}
