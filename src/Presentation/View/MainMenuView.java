package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Utils;
import Presentation.Controller.MainMenuViewController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class MainMenuView extends JPanel {

    // Afegim els atributs
    private final FooterView footerView;
    private final Utils utils;
    private final HeaderView headerView;

    /**
     * Funció que servirà com a constructor del MainMenuView
     *
     * @param footerView, vista del footer
     * @param utils, per utilitzar els seus mètodes
     * @param headerView, vista del header
     */
    public MainMenuView(FooterView footerView, Utils utils, HeaderView headerView){
        this.footerView = footerView;
        this.headerView = headerView;
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
        //set action command
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

        // NORTH
        // Iniciem el JPanel del nord amb el FlowLayout i li afegim el header amb el seu JLabel
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Icon menuImg = new ImageIcon(String.valueOf(AssetsFiles.MENU_LABEL));
        north.add(headerView.configureHeader(menuImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);


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

        // SOUTH
        // Iniciem el JPanel del sud i el color gris
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();

        // Configurem el JPanel com a footer i l'afegim
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }
}
