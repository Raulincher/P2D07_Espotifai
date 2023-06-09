package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Utils;
import Presentation.Controller.MainMenuViewController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * Classe de la vista Main Menu la qual fa extend d'un jpanel
 */
public class MainMenuView extends JPanel {

    // Afegim els atributs
    private final Utils utils;

    /**
     * Funció que servirà com a constructor del MainMenuView
     * @param utils, per utilitzar els seus mètodes
     */
    public MainMenuView(Utils utils){
        this.utils = utils;
    }

    // Afegim tots els JButtons
    private JButton jSongList;
    private JButton jAddSong;
    private JButton jDeleteSong;
    private JButton jStatistics;
    private JButton jManage;

    // Afegim els Strings de les respostes dels botons
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_SONG_LIST = "BTN_SONG_LIST";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_DELETE_SONG = "BTN_DELETE_SONG";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_MANAGE = "BTN_MANAGE";

    /**
     * Funció que servirà per a connectar amb el seu controller
     * i activar tots els listeners
     *
     * @param mainMenuController, controller del MainMenuView
     */
    public void addMainMenuController(MainMenuViewController mainMenuController){
        jSongList.addActionListener(mainMenuController);
        jAddSong.addActionListener(mainMenuController);
        jDeleteSong.addActionListener(mainMenuController);
        jStatistics.addActionListener(mainMenuController);
        jManage.addActionListener(mainMenuController);
    }

    /**
     * Funció que servirà per a configurar el MainMenu amb
     * tots els seus elements de Swing
     *
     * No té ni param ni return
     */
    public void configureMainMenuView() {
        // Activem el BorderLayout
        setLayout(new BorderLayout());

        // CENTER
        // Iniciem el JPanel del center amb el FlowLayout i el configurem
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(Color.black);
        center.setBorder(createEmptyBorder(50, 0, 0, 0));
        add(center, BorderLayout.CENTER);

        // Creem dos sub-JPanels per a la línia d'adalt dels botons i la d'abaix
        JPanel fila1 = new JPanel();
        fila1.setBackground(Color.black);
        JPanel fila2 = new JPanel();
        fila2.setBackground(Color.black);

        // Creem el botó de Song List
        Icon songListBtn = new ImageIcon(String.valueOf(AssetsFiles.SONGLIST_BUTTON_IMG));
        jSongList = utils.buttonImg(songListBtn);
        jSongList.setActionCommand(BTN_SONG_LIST);

        // Creem el botó d'Add Song
        Icon addSongBtn = new ImageIcon(String.valueOf(AssetsFiles.ADDSONG_BUTTON_IMG));
        jAddSong = utils.buttonImg(addSongBtn);
        jAddSong.setActionCommand(BTN_ADD_SONG);

        // Creem el botó de Delete Song
        Icon deleteSongBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETESONG_BUTTON_IMG));
        jDeleteSong = utils.buttonImg(deleteSongBtn);
        jDeleteSong.setActionCommand(BTN_DELETE_SONG);

        // Creem el botó de Statistics
        Icon statisticsBtn = new ImageIcon(String.valueOf(AssetsFiles.STATISTICS_BUTTON_IMG));
        jStatistics = utils.buttonImg(statisticsBtn);
        jStatistics.setActionCommand(BTN_STATISTICS);

        // Creem el botó de Manage Lists
        Icon manageBtn = new ImageIcon(String.valueOf(AssetsFiles.MANAGELISTS_BUTTON_IMG));
        jManage = utils.buttonImg(manageBtn);
        jManage.setActionCommand(BTN_MANAGE);

        // Afegim els tres botons de la fila superior
        fila1.add(jSongList);
        fila1.add(jAddSong);
        fila1.add(jDeleteSong);

        // Afegim els dos botons de la fila inferior
        fila2.add(jStatistics);
        fila2.add(jManage);

        // Afegim els dos sub-JPanels al JPanel del center
        center.add(fila1);
        center.add(fila2);
    }
}
