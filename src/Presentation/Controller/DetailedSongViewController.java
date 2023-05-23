package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.util.ArrayList;

public class DetailedSongViewController  implements ActionListener, MouseListener {

    private final DetailedSongView detailedSongView;
    private final MainView mainView;
    private final SongManager songManager;
    private  final PlaylistManager playlistManager;
    private final UserManager userManager;


    public DetailedSongViewController(DetailedSongView detailedSongView, MainView mainView,SongManager songManager, PlaylistManager playlistManager, UserManager userManager) {
        this.detailedSongView = detailedSongView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.playlistManager = playlistManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userManager.currentUsername();
        ArrayList<String> playlists = playlistManager.obtainPlaylistNames(true, username);
        for (String playlist: playlists){
            if (e.getActionCommand().equals(playlist)){
                //Fer add a la playlist

                String songName = detailedSongView.getSongName();
                if (playlistManager.addSongToPlaylist(songName, playlist)) {
                    detailedSongView.showPopUp("Song successfully saved in " + playlist + "!");
                } else {
                    detailedSongView.showPopUp("There was an error saving the song.");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        detailedSongView.showMenuPopUp(e);
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
