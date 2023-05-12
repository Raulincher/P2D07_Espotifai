package Presentation.Controller;

import Business.SongManager;
import Business.UserManager;
import Presentation.SongTableModel;

import javax.swing.*;
import java.util.ArrayList;

public class SongTableModelController {
    private SongManager songManager;
    private UserManager userManager;
    private SongTableModel songTableModel;

    public SongTableModelController(SongManager songManager, UserManager userManager, SongTableModel songTableModel) {
        this.songManager = songManager;
        this.userManager = userManager;
        this.songTableModel = songTableModel;
    }

    public void createDeleteJTable() {
        songTableModel.buildDeleteTableModel(songManager.listSongs(true, userManager.currentUsername()));
    }

    public void createSearchSongJTable(String titleName) {
        songTableModel.buildSearchedSongTableModel(songManager.searchSong(titleName));
    }

    public void createAllSongsJTable() {
        songTableModel.buildAllSongsTableModel(songManager.listSongs(false, null));
    }
}
