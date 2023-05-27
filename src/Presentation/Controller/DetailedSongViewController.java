package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe per controlar la vista del detailedSongView i les interaccions d'aquesta
 */
public class DetailedSongViewController  implements ActionListener, MouseListener, DocumentListener {

    // Declarem atributs
    private final DetailedSongView detailedSongView;
    private final SongManager songManager;
    private  final PlaylistManager playlistManager;
    private final UserManager userManager;
    private final FooterView footerView;

    /**
     * Constructor de la classe DetailedSongViewController
     * @param detailedSongView, view de la classe
     * @param songManager, manager per a controlar les songs en la detailed View
     * @param playlistManager, manager per obtenir les playlists en la detailed View
     * @param userManager, manager per obtenir l'usuari loguejat
     */
    public DetailedSongViewController(DetailedSongView detailedSongView,SongManager songManager, PlaylistManager playlistManager, UserManager userManager, FooterView footerView) {
        this.detailedSongView = detailedSongView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.playlistManager = playlistManager;
        this.footerView = footerView;
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
        if(playlists != null) {
            for (String playlist: playlists){
                if (e.getActionCommand().equals(playlist)) {
                    // Un cop trobada, afegim la cançó a la playlist escollida i mostrem Pop Ups en funció si 'sha afegit o no.
                    if (!playlistManager.checkIfSongInPlaylist(songName, playlist)) {
                        playlistManager.addSongToPlaylist(songName, playlist);
                        detailedSongView.showPopUp("Song successfully saved in " + playlist + "!");
                    } else {
                        detailedSongView.showPopUp("There was an error saving the song.");
                    }
                }
            }
        }

        // Controlem el play de la pròpia cançó i el boto de add Playlist
        switch (e.getActionCommand()){
            case FooterView.BTN_PLAY:
            case DetailedSongView.BTN_PLAYME:
                String actualS = songManager.getActualSong();
                footerView.setActualSong(actualS);
                footerView.jProgressBar.setMinimum(0);
                footerView.jProgressBar.setMaximum(songManager.clipDuration());
                footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                songManager.getSong(songName);
                boolean stop = false;
                try {
                    stop = songManager.simpleAudioPlayer();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);

                if(stop){
                    detailedSongView.stop();
                    footerView.stop();
                }else{
                    detailedSongView.start();
                    footerView.start();
                }
                break;
            case DetailedSongView.BTN_PLAYLIST:
                // Netejem el filtre cada cop que pulsem el boto
                detailedSongView.cleanFilter();
                break;
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

    /**
     * Funció per isntertar el filtre de la PopUpMenu i modificar-la
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        //Filtre per les playlist
        detailedSongView.filteredPopUpMenu();
    }

    /**
     * Funció per eliminar de la vista del PopUpMenu les cançons que no s'assemblen a la filtarda
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        //Filtre per les playlist
        detailedSongView.filteredPopUpMenu();
    }

    /**
     * Funció per actualitzar els canvis del filtre a ala PopUpMenu
     * @param e l'esdeveniment de document a tramitar
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        //Filtre per les playlist
        detailedSongView.filteredPopUpMenu();
    }
}
