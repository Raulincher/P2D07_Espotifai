package Presentation.Controller;

import Business.Entities.Song;
import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.DeleteSongView;
import Presentation.View.DetailedSongView;
import Presentation.View.GeneralSongListView;
import Presentation.View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GeneralSongListViewController implements ActionListener, MouseListener {

    private final GeneralSongListView generalSongListView;
    private final MainView mainView;
    private SongManager songManager;
    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;
    private UserManager userManager;

    private DetailedSongViewController detailedSongViewController;


    public GeneralSongListViewController(GeneralSongListView generalSongListView, MainView mainView, SongManager songManager, DetailedSongView detailedSongView, PlaylistManager playlistManager, UserManager userManager,DetailedSongViewController detailedSongViewController) {
        this.generalSongListView = generalSongListView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.detailedSongView = detailedSongView;
        this.playlistManager = playlistManager;
        this.userManager = userManager;
        this.detailedSongViewController = detailedSongViewController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case GeneralSongListView.BTN_BACK:
                mainView.showMainMenuCard();
                break;
            case GeneralSongListView.BTN_BUSCADOR:
                mainView.showMainMenuCard();
                break;
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        String selected;
        JTable table = generalSongListView.getTable();
        int row = table.rowAtPoint(e.getPoint());
        if (row != -1) {
            detailedSongView.clearInfo();
            selected = (String) table.getValueAt(row, 0);
            ArrayList<String> song = songManager.searchSong(selected);
            detailedSongView.fillDetailedTable(song);
            String  lyrics = songManager.readLyricApi(song.get(2),song.get(0));
            if (lyrics == null){
                detailedSongView.fillLyriscText("Song not found");
            } else {
                detailedSongView.fillLyriscText(lyrics);
            }
            //Preparem la PopUpMenu amb les playlist i configurem el boto per afegir de cadascuna
            String username = userManager.currentUsername();
            ArrayList<String> playlist = playlistManager.obtainPlaylistNames(true,username);
            detailedSongView.fillPopMenu(playlist);
            detailedSongView.addDetailedSongController(detailedSongViewController);
            detailedSongView.setNameSong(song.get(0));
            mainView.showDetailedSongCard();
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
