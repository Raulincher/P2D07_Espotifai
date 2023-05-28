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

public class AddSongViewController implements ActionListener {
    private final AddSongView addSongView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;

    public AddSongViewController(AddSongView addSongView, MainView mainView, SongManager songManager, UserManager userManager) {
        this.addSongView = addSongView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case AddSongView.BTN_ADD_FILE:
                if (!songManager.fileSongSelector()) {
                    addSongView.showPopUps("Error, the song should be in .wav format!");
                } else {
                    addSongView.addFileName(songManager.obtainFilePath());
                }
                break;

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
