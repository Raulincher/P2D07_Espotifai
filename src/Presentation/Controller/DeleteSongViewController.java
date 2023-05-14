package Presentation.Controller;

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
import javax.swing.event.DocumentEvent;


public class DeleteSongViewController implements ActionListener, DocumentListener, ListSelectionListener {
    private final DeleteSongView deleteSongView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;

    public DeleteSongViewController(DeleteSongView deleteSongView, MainView mainView, SongManager songManager, UserManager userManager) {
        this.deleteSongView = deleteSongView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
    }

    public void runDeleteSongView() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DeleteSongView.BTN_BUSCADOR -> {
                String input = deleteSongView.getjtfBuscador().toString();
            }
            //case DeleteSongView.BTN_DELETE -> {
                /*String songToDelete = deleteSongView.getInput().getText();

                if (songManager.songExists(songToDelete)) {
                    if (songManager.deleteSong(songToDelete)) {
                        deleteSongView.showPopUps("Song Deleted Successfully");
                        mainView.showMainMenuCard();
                    }
                }
                else {
                    deleteSongView.showPopUps("This song doesn't exist!");
                }*/
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
    public void valueChanged(ListSelectionEvent e) {
        String songTitle = deleteSongView.selectDeleteSong();

        if (deleteSongView.confirmationDeletePopUp(songTitle) == 0) {
            if (songManager.deleteSong(songTitle)) {
                deleteSongView.showPopUps("Song Deleted Successfully");
                //deleteSongView.updateTable(songTitle);
                mainView.showDeleteSongCard();
                //deleteSongView.fillDeleteTable(songManager.listSongs(true, userManager.currentUsername()));
                //mainView.showMainMenuCard();
            }
        }
        else {
            deleteSongView.showPopUps("Song NOT deleted");
        }
    }
}
