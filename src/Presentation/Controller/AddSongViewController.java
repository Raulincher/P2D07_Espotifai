package Presentation.Controller;

import Business.SongManager;
import Presentation.View.AddSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongViewController implements ActionListener {
    private final AddSongView addSongView;
    private final MainView mainView;
    private final SongManager songManager;

    public AddSongViewController(AddSongView addSongView, MainView mainView, SongManager songManager) {
        this.addSongView = addSongView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case AddSongView.BTN_ADD_FILE:
                if (!songManager.fileSongSelector()) {
                    // Pop up
                    addSongView.showPopUps("Error, the song should be in .wav format!");
                }
                break;
            case AddSongView.BTN_ADD_SONG:
                // Guardar dades
                String songName = addSongView.getJtfSongName().getText();
                String artist = addSongView.getJtfArtist().getText();
                String album = addSongView.getJtfAlbum().getText();
                String genre = addSongView.getJtfGenre().getText();

                if (songManager.isEmpty(songName, artist, album, genre)) {
                    addSongView.showPopUps("Error, empty field, can't upload the song!");
                } else {
                    songManager.addSong(songName, artist, album, genre);
                }
                // Algun camp buit

                // Fitxer buit


                break;

        }
    }


}
