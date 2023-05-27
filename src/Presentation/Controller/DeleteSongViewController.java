package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.AddSongView;
import Presentation.View.DeleteSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;


public class DeleteSongViewController implements ActionListener, DocumentListener, MouseListener {
    private final DeleteSongView deleteSongView;
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;

    public DeleteSongViewController(DeleteSongView deleteSongView, SongManager songManager,
                                    UserManager userManager, PlaylistManager playlistManager) {
        this.deleteSongView = deleteSongView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.playlistManager = playlistManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DeleteSongView.BTN_BUSCADOR -> {
                String input = deleteSongView.getjtfBuscador().toString();
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        deleteSongView.search(deleteSongView.getjtfBuscador().getText(), deleteSongView.getSorter());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        deleteSongView.search(deleteSongView.getjtfBuscador().getText(), deleteSongView.getSorter());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        deleteSongView.search(deleteSongView.getjtfBuscador().getText(), deleteSongView.getSorter());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String songTitle = deleteSongView.obtainSongNameToDelete(e.getPoint());
        deleteSongView.obtainSongIndexToDelete(songTitle);

        if (deleteSongView.confirmationDeletePopUp(songTitle) == 0) {
            boolean playing = songManager.isPlaying(songTitle);
            System.out.println("Playing: " + playing);
            if (!playing) {
                if (songManager.deleteSong(songTitle)) {
                    deleteSongView.showPopUps("Song Deleted Successfully");
                    ArrayList<String> songsUpdated = songManager.listSongs(true, userManager.currentUsername());
                    deleteSongView.fillDeleteTable(songsUpdated);
                    deleteSongView.clearSearcher();
                    playlistManager.deleteSongFromPlaylists(songTitle);
                } else {
                    deleteSongView.showPopUps("The song couldn't delete!");
                }
            } else {
                deleteSongView.showPopUps("The song is playing. You can't delete!");
            }
        } else {
            deleteSongView.showPopUps("Song NOT deleted");
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
