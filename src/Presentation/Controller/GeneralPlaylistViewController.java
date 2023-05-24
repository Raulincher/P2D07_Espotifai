package Presentation.Controller;

import Business.Entities.Playlist;
import Business.Entities.User;
import Business.PlaylistManager;
import Business.UserManager;
import Presentation.View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GeneralPlaylistViewController implements ActionListener, MouseListener {

    private final GeneralPlaylistView generalPlaylistView;
    private final DetailedPlaylistView detailedView;
    private final MainView mainView;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;

    public GeneralPlaylistViewController(GeneralPlaylistView generalPlaylistView, MainView mainView, PlaylistManager playlistManager,
                                         UserManager userManager, DetailedPlaylistView detailedView) {
        this.generalPlaylistView = generalPlaylistView;
        this.mainView = mainView;
        this.playlistManager = playlistManager;
        this.userManager = userManager;
        this.detailedView = detailedView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GeneralPlaylistView.BTN_NEW_PLAYLIST -> {
                String title = JOptionPane.showInputDialog("Playlist Name:");
                String username = userManager.currentUsername();
                playlistManager.savePlaylist(username, title);
                ArrayList<String> playlistNames = playlistManager.obtainPlaylistNames(true,  userManager.currentUsername());
                generalPlaylistView.fillMyPlaylistsTable(playlistNames);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int tableClicked = generalPlaylistView.obtainTableClicked(e.getSource());
        String playlistName = generalPlaylistView.obtainPlaylistName(e.getPoint(), tableClicked);

        detailedView.fillSongsInPlaylistTable(playlistManager.obtainSongsInPlaylist(playlistName));
        detailedView.definePlaylistName(playlistName);
        //TODO
        playlistManager.setCurrentPlaylist(playlistName);
        if (tableClicked == 0) {
            detailedView.showButton();
        } else {
            detailedView.hideButton();
        }
        //detailedView.configureDetailedPlaylistView();
        mainView.showDetailedPlaylistCard();

        //int index = generalPlaylistView.obtainSongIndexToDelete(songTitle);
/*
        if (generalPlaylistView.confirmationDeletePopUp(songTitle) == 0) {
            if (songManager.deleteSong(songTitle)) {
                deleteSongView.showPopUps("Song Deleted Successfully");
                mainView.showMainMenuCard();
                deleteSongView.clearSearcher();
            }
        }
        else {
            deleteSongView.showPopUps("Song NOT deleted");
        }

 */
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
