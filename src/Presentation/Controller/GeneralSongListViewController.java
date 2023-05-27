package Presentation.Controller;

import Business.Entities.Song;
import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.DeleteSongView;
import Presentation.View.DetailedSongView;
import Presentation.View.GeneralSongListView;
import Presentation.View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Classe per controlar la vista del detailedSongView i les interaccions d'aquesta
 */
public class GeneralSongListViewController implements MouseListener {

    // Declarem atributs
    private final GeneralSongListView generalSongListView;
    private final MainView mainView;
    private final SongManager songManager;
    private final DetailedSongView detailedSongView;
    private final PlaylistManager playlistManager;
    private final UserManager userManager;
    private final DetailedSongViewController detailedSongViewController;


    /**
     * Constructor de la classe GeneralSongListView
     * @param generalSongListView, vista de la classe
     * @param mainView, vista que controla totes les vistes del programa
     * @param songManager, manager per obtenir les songs en la general list View
     * @param detailedSongView, vista per poder emplenar les taules de la següent vista amb la informació obtinguda
     * @param playlistManager, manager per obtenir les playlists de l'usuari registrat.
     * @param userManager, manager per obtenir l'usuari actual
     * @param detailedSongViewController, controller de la classe detailed Song View per poder activar el listener de les playlists
     */    public GeneralSongListViewController(GeneralSongListView generalSongListView, MainView mainView, SongManager songManager, DetailedSongView detailedSongView, PlaylistManager playlistManager, UserManager userManager,DetailedSongViewController detailedSongViewController) {
        this.generalSongListView = generalSongListView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.detailedSongView = detailedSongView;
        this.playlistManager = playlistManager;
        this.userManager = userManager;
        this.detailedSongViewController = detailedSongViewController;
    }

    /**
     * Funció que servirà quan els usuaris premin una cançó de la taula, els portarà a la següent vista de la cançó detallada
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String selected;
        JTable table = generalSongListView.getTable();
        int row = table.rowAtPoint(e.getPoint());
        // Condicional per controlar si s'ha pulsat una fila
        if (row != -1) {
            detailedSongView.clearInfo();
            // Trobem el valor pulsat i carguem la següent vista amb els valors
            selected = (String) table.getValueAt(row, 0);
            ArrayList<String> song = songManager.searchSong(selected);
            if (song == null){
                detailedSongView.showPopUp("Sound: file not found");
            }else {
                // Preparem la lyrics de la cançó
                detailedSongView.fillDetailedTable(song);
                String lyrics = songManager.readLyricApi(song.get(2), song.get(0));
                detailedSongView.fillLyriscText(lyrics);
                //Preparem la PopUpMenu amb les playlist i configurem el boto per afegir de cadascuna
                String username = userManager.currentUsername();
                ArrayList<String> playlist = playlistManager.obtainPlaylistNames(true, username);
                detailedSongView.fillPopMenu(playlist);
                detailedSongView.addDetailedSongController(detailedSongViewController);
                detailedSongView.setNameSong(song.get(0));
                playlistManager.setCurrentPlaylist(null);
                mainView.showDetailedSongCard();
            }
        }
    }

    /**
     * Funció per poder obtenir MouseListener
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Funció per poder obtenir MouseListener
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Funció per poder obtenir MouseListener
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Funció per poder obtenir MouseListener
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseExited(MouseEvent e) {}

}
