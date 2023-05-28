package Presentation.Controller;

import Business.PlaylistManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe que controla la vista del DetailedPlaylist.
 */
public class DetailedPlaylistViewController implements ActionListener, MouseListener {
    // Preparem atributs
    private final DetailedPlaylistView detailedPlaylistView;
    private final MainView mainView;
    private final PlaylistManager playlistManager;
    private final GeneralPlaylistView generalPlaylistView;
    private String whichSongName;
    private final UserManager userManager;


    /**
     * Funció que servirà per com a constructor del FooterController
     *
     * @param detailedPlaylistView, per detectar els clics dins de delete song view
     * @param mainView, per poder cambiar entre cards
     * @param playlistManager, per poder utilitzar la lógica d'algunes funcions de playlist manager
     * @param generalPlaylistView, per poder omplir amb informació de la vista general de playlists
     * @param userManager, per poder utilitzar la lógica d'algunes funcions de user manager
     *
     */
    public DetailedPlaylistViewController(DetailedPlaylistView detailedPlaylistView, MainView mainView,
                                          PlaylistManager playlistManager, GeneralPlaylistView generalPlaylistView,
                                          UserManager userManager) {
        this.detailedPlaylistView = detailedPlaylistView;
        this.mainView = mainView;
        this.playlistManager = playlistManager;
        this.generalPlaylistView = generalPlaylistView;
        this.userManager = userManager;
    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol boto de la vista de detailed playlist view
     *
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //case delete playlist, serveix per poder esborrar la playlist seleccionada
            case DetailedPlaylistView.BTN_DELETE_PLAYLIST -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                if (playlistManager.deletePlaylist(playlistName)) {
                    detailedPlaylistView.showPopUp("Playlist successfully deleted!");
                    generalPlaylistView.fillOtherPlaylistsTable(playlistManager.obtainPlaylistNames(false, userManager.currentUsername()));
                    generalPlaylistView.fillMyPlaylistsTable(playlistManager.obtainPlaylistNames(true,  userManager.currentUsername()));
                    mainView.showGeneralPlaylistCard();
                } else {
                    detailedPlaylistView.showPopUp("Couldn't delete playlist.");
                }
            }

            //case delete song from playlist, serveix per poder esborrar una song de la playlist escollida
            case DetailedPlaylistView.BTN_DELETE_SONG_FROM_PLAYLIST -> {
                // Primer elimino la cançó de la base de dades
                String playlistName = detailedPlaylistView.getPlaylistName();
                playlistManager.deleteSongFromPlaylist(playlistName, whichSongName);

                // Em retorno la llista actualitzada amb els noms de les cançons i actualitzar la vista
                detailedPlaylistView.fillSongsInPlaylistTable(playlistManager.obtainSongsInPlaylist(playlistName));
            }

            //case song go down, permet baixar una cançó
            case DetailedPlaylistView.BTN_SONG_DOWN -> detailedPlaylistView.moveSelectedRowDown();

            //case song go up, permet pujar una cançó
            case DetailedPlaylistView.BTN_SONG_UP -> detailedPlaylistView.moveSelectedRowUp();
        }
    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol click amb ratolí
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        whichSongName  = detailedPlaylistView.obtainSongName(e.getPoint());
        playlistManager.setClickedSong(whichSongName);
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
