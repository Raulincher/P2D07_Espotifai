package Presentation.Controller;

import Business.PlaylistManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DetailedPlaylistViewController implements ActionListener, MouseListener {
    private final DetailedPlaylistView detailedPlaylistView;
    private final MainView mainView;
    private final PlaylistManager playlistManager;
    private final GeneralPlaylistView generalPlaylistView;
    private String whichSongName;

    private final UserManager userManager;

    public DetailedPlaylistViewController(DetailedPlaylistView detailedPlaylistView, MainView mainView,
                                          PlaylistManager playlistManager, GeneralPlaylistView generalPlaylistView,
                                          UserManager userManager) {
        this.detailedPlaylistView = detailedPlaylistView;
        this.mainView = mainView;
        this.playlistManager = playlistManager;
        this.generalPlaylistView = generalPlaylistView;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
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

            case DetailedPlaylistView.BTN_DELETE_SONG_FROM_PLAYLIST -> {
                // Primer elimino la cançó de la base de dades
                String playlistName = detailedPlaylistView.getPlaylistName();
                playlistManager.deleteSongFromPlaylist(playlistName, whichSongName);

                // Em retorno la llista actualitzada amb els noms de les cançons i actualitzar la vista
                detailedPlaylistView.fillSongsInPlaylistTable(playlistManager.obtainSongsInPlaylist(playlistName));
            }

            case DetailedPlaylistView.BTN_SONG_DOWN -> detailedPlaylistView.moveSelectedRowDown();
            case DetailedPlaylistView.BTN_SONG_UP -> detailedPlaylistView.moveSelectedRowUp();
        }
    }

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
