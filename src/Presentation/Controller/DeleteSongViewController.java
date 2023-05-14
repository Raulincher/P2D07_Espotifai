package Presentation.Controller;

import Business.SongManager;
import Presentation.SongTableModel;
import Presentation.View.AddSongView;
import Presentation.View.DeleteSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSongViewController implements ActionListener {
    private final DeleteSongView deleteSongView;
    private final MainView mainView;
    private final SongManager songManager;
    private SongTableModelController songTableModelController;
    private SongTableModel songTableModel;


    public DeleteSongViewController(DeleteSongView deleteSongView, MainView mainView, SongManager songManager,
                                    SongTableModelController songTableModelController, SongTableModel songTableModel) {
        this.deleteSongView = deleteSongView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.songTableModelController = songTableModelController;
        this.songTableModel = songTableModel;
    }

    public void runDeleteSongView() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DeleteSongView.BTN_BUSCADOR -> {
                String input = deleteSongView.getjtfBuscador().toString();
                //deleteSongView.searchInTable(input);
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
    }


