package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainMenuViewController implements ActionListener{

    // Declarem els atributs
    private final MainMenuView mainMenuView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;
    private final DeleteSongView deleteSongView;
    private final StatisticsView statisticsView;
    private final GeneralSongListView generalSongListView;
    private final GeneralPlaylistView generalPlaylistView;

    public MainMenuViewController(MainMenuView mainMenuView, MainView mainView, SongManager songManager,
                                  UserManager userManager, DeleteSongView deleteSongView,
                                  GeneralSongListView generalSongListView, StatisticsView statisticsView, GeneralPlaylistView generalPlaylistView,PlaylistManager playlistManager) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.deleteSongView = deleteSongView;
        this.statisticsView = statisticsView;
        this.generalSongListView = generalSongListView;
        this.generalPlaylistView = generalPlaylistView;
        this.playlistManager = playlistManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MainMenuView.BTN_BACK -> mainView.showMainCard();
            case MainMenuView.BTN_ADD_SONG -> mainView.showAddSongCard();
            case MainMenuView.BTN_DELETE_SONG -> {
                deleteSongView.fillDeleteTable(songManager.listSongs(true, userManager.currentUsername()));
                mainView.showDeleteSongCard();
            }
            case MainMenuView.BTN_MANAGE -> {
                generalPlaylistView.fillOtherPlaylistsTable(playlistManager.obtainPlaylistNames(false, userManager.currentUsername()));
                generalPlaylistView.fillMyPlaylistsTable(playlistManager.obtainPlaylistNames(true,  userManager.currentUsername()));
                mainView.showGeneralPlaylistCard();
            }
            case MainMenuView.BTN_SONG_LIST -> {
                generalSongListView.fillTable(songManager.listSongs(false, null));
                mainView.showGeneralSongListCard();
            }
            case MainMenuView.BTN_STATISTICS -> {
                Map<String, Integer> genreMap = songManager.createGenreMap();
                statisticsView.setGenreMap(genreMap);
                statisticsView.repaint();
                mainView.showStatisticsCard();
            }

        }
    }

}
