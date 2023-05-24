package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe per controlar la vista del detailedSongView i les interaccions d'aquesta
 */
public class DetailedSongViewController  implements ActionListener, MouseListener {

    // Declarem atributs
    private final DetailedSongView detailedSongView;
    private final SongManager songManager;
    private  final PlaylistManager playlistManager;
    private final UserManager userManager;

    /**
     * Constructor de la classe DetailedSongViewController
     * @param detailedSongView, view de la classe
     * @param songManager, manager per a controlar les songs en la detailed View
     * @param playlistManager, manager per obtenir les playlists en la detailed View
     * @param userManager, manager per obtenir l'usuari loguejat
     */
    public DetailedSongViewController(DetailedSongView detailedSongView,SongManager songManager, PlaylistManager playlistManager, UserManager userManager) {
        this.detailedSongView = detailedSongView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.playlistManager = playlistManager;
    }

    /**
     * Funció que servirà com a resposta quan els usuaris premin el botó
     * i s'activin els listeners
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Consultem el user actual, les playlists que té aquest user i la cançó de la detailedSongView
        String username = userManager.currentUsername();
        ArrayList<String> playlists = playlistManager.obtainPlaylistNames(true, username);
        String songName = detailedSongView.getSongName();

        // Recorrem un bucle per trobar quina playlists de la PopUpMenu ha estat premuda
        for (String playlist: playlists){
            if (e.getActionCommand().equals(playlist)){
                // Un cop trobada, afegim la cançó a la playlist escollida i mostrem Pop Ups en funció si 'sha afegit o no.
                if (playlistManager.addSongToPlaylist(songName, playlist)) {
                    detailedSongView.showPopUp("Song successfully saved in " + playlist + "!");
                } else {
                    detailedSongView.showPopUp("There was an error saving the song.");
                }
            }
        }

        // Controlem el play de la pròpia cançó
        if (e.getActionCommand().equals(DetailedSongView.BTN_PLAYME)){
            songManager.getSong(songName);
            boolean stop = songManager.simpleAudioPlayer();
            if(stop){
                detailedSongView.stop();
            }else{
                detailedSongView.start();
            }
        }
    }

    /**
     * Funció que servirà per mostrar la Pop Up Menu quan els usuaris premin el botó adjunt a aquesta, BTN_PLAYLIST
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        detailedSongView.showMenuPopUp(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
