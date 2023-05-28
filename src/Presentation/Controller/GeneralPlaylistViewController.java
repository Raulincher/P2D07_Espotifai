package Presentation.Controller;

import Business.Entities.Playlist;
import Business.Entities.User;
import Business.PlaylistManager;
import Business.UserManager;
import Presentation.View.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GeneralPlaylistViewController implements ActionListener, MouseListener, DocumentListener {

    // Preparem atributs
    private final GeneralPlaylistView generalPlaylistView;
    private final DetailedPlaylistView detailedView;
    private final MainView mainView;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;


    /**
     * Funció que servirà per com a constructor del FooterController
     *
     * @param generalPlaylistView, per a poder veure les accions dins la vista de general playlist
     * @param mainView, per poder cambiar de card dins la vista
     * @param detailedView, per poder pasar alguns parametres dins la vista de detailed
     * @param userManager, per a utilitzar els seus mètodes, aquí tractarem tota l'usuari
     * @param playlistManager, per a utilitzar els seus mètodes, aquí tractarem tot el tema de reproduir playlist
     *
     */
    public GeneralPlaylistViewController(GeneralPlaylistView generalPlaylistView, MainView mainView, PlaylistManager playlistManager,
                                         UserManager userManager, DetailedPlaylistView detailedView) {
        this.generalPlaylistView = generalPlaylistView;
        this.mainView = mainView;
        this.playlistManager = playlistManager;
        this.userManager = userManager;
        this.detailedView = detailedView;
    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol botó de la vista del footer
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //new playlist case, botó per crear playlist
            case GeneralPlaylistView.BTN_NEW_PLAYLIST -> {
                String title = JOptionPane.showInputDialog("Playlist Name:");
                String username = userManager.currentUsername();
                playlistManager.savePlaylist(username, title);
                ArrayList<String> playlistNames = playlistManager.obtainPlaylistNames(true,  userManager.currentUsername());
                generalPlaylistView.fillMyPlaylistsTable(playlistNames);
                generalPlaylistView.clearSearcher();
            }
        }
    }
    /**
     * Funció que servirà quan els usuaris premin una playlist de la taula, els portarà a la següent vista de la playlist detallada
     *
     *  @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int tableClicked = generalPlaylistView.obtainTableClicked(e.getSource());
        String playlistName = generalPlaylistView.obtainPlaylistName(e.getPoint(), tableClicked);

        detailedView.fillSongsInPlaylistTable(playlistManager.obtainSongsInPlaylist(playlistName));
        detailedView.definePlaylistName(playlistName);
        playlistManager.setCurrentPlaylist(playlistName);

        if (tableClicked == 0) {
            detailedView.showButton();
        } else {
            detailedView.hideButton();
        }
        mainView.showDetailedPlaylistCard();
    }

    /**
     * Funció per isntertar el filtre
     *
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == generalPlaylistView.getMyPlaylistBuscador().getDocument()) {
            generalPlaylistView.search(generalPlaylistView.getMyPlaylistBuscador().getText(), generalPlaylistView.getMyPlaylistSorter());
        } else {
            generalPlaylistView.search(generalPlaylistView.getOtherPlaylistBuscador().getText(), generalPlaylistView.getOtherPlaylistSorter());
        }
    }
    /**
     * Funció per eliminar de la vista les playlists que no s'assemblen a la filtarda
     *
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == generalPlaylistView.getMyPlaylistBuscador().getDocument()) {
            generalPlaylistView.search(generalPlaylistView.getMyPlaylistBuscador().getText(), generalPlaylistView.getMyPlaylistSorter());
        } else {
            generalPlaylistView.search(generalPlaylistView.getOtherPlaylistBuscador().getText(), generalPlaylistView.getOtherPlaylistSorter());
        }
    }

    /**
     * Funció per actualitzar els canvis del filtre
     *
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == generalPlaylistView.getMyPlaylistBuscador().getDocument()) {
            generalPlaylistView.search(generalPlaylistView.getMyPlaylistBuscador().getText(), generalPlaylistView.getMyPlaylistSorter());
        } else {
            generalPlaylistView.search(generalPlaylistView.getOtherPlaylistBuscador().getText(), generalPlaylistView.getOtherPlaylistSorter());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
