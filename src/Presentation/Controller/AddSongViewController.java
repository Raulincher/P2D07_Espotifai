package Presentation.Controller;

import Business.SongManager;
import Business.UserManager;
import Presentation.View.AddSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe que controla la vista del Add Song.
 */
public class AddSongViewController implements ActionListener {

    // Preparem atributs
    private final AddSongView addSongView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;


    /**
     * Funció que servirà per com a constructor del FooterController
     *
     * @param addSongView, per detectar els clics dins de add song view
     * @param mainView, per canviar de cards
     * @param songManager, per poder utilitzar la lògica d'algunes funcions de song manager
     * @param userManager, per poder utilitzar la lògica d'algunes funcions de user manager
     *
     */
    public AddSongViewController(AddSongView addSongView, MainView mainView, SongManager songManager, UserManager userManager) {
        this.addSongView = addSongView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
    }


    /**
     * Funció que servirà per detectar si hem premut qualsevol botó de la vista d'add song
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //add file, aquest botó ens permetrà afegir un file desde aquesta vista
            case AddSongView.BTN_ADD_FILE:
                if (!songManager.fileSongSelector()) {
                    addSongView.showPopUps("Error, the song should be in .wav format!");
                } else {
                    addSongView.addFileName(songManager.obtainFilePath());
                }
                break;

            //add song, aquest botó ens permetrà afegir una cançó
            case AddSongView.BTN_ADD_SONG:
                String songName = addSongView.getJtfSongName().getText();
                String artist = addSongView.getJtfArtist().getText();
                String album = addSongView.getJtfAlbum().getText();
                String genre = (String) addSongView.getJcbGenre().getSelectedItem();
                boolean fileExists = addSongView.fileCheckExists();

                if (songManager.isEmpty(songName, artist, album, genre) || !fileExists){
                    addSongView.showPopUps("Error, empty field, can't upload the song!");
                } else {
                    if (!songManager.songExists(songName)) {
                        String username = userManager.currentUsername();
                        ArrayList<String> temps = songManager.timeSong();
                        String flag = temps.get(0);
                        String time = temps.get(1);
                        if (flag.equals("true")) {
                            if (songManager.addSong(songName, artist, album, genre, username, time)) {
                                addSongView.showPopUps("Song saved!");
                                addSongView.clearFields();
                                mainView.showMainMenuCard();
                            } else {
                                addSongView.showPopUps("Error while saving the song!");
                            }
                        } else {
                            addSongView.showPopUps(time);
                        }
                    } else {
                        addSongView.showPopUps("Song previously added!\n" +
                                "Please, add another one.");
                        addSongView.clearFields();
                        mainView.showAddSongCard();
                    }
                }
                break;

        }
    }


}
