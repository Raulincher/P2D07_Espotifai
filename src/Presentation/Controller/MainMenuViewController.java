package Presentation.Controller;

import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuViewController implements ActionListener{

    private final MainMenuView mainMenuView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;
    private final DeleteSongView deleteSongView;
    private final GeneralSongListView generalSongListView;

    public MainMenuViewController(MainMenuView mainMenuView, MainView mainView, SongManager songManager,
                                  UserManager userManager, DeleteSongView deleteSongView, GeneralSongListView generalSongListView) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.deleteSongView = deleteSongView;
        this.generalSongListView = generalSongListView;
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
            case MainMenuView.BTN_MANAGE -> mainView.showGeneralPlaylistCard();
            case MainMenuView.BTN_SONG_LIST -> {
                generalSongListView.fillTable(songManager.listSongs(false, null));
                mainView.showGeneralSongListCard();
            }
            case MainMenuView.BTN_STATISTICS -> mainView.showStatisticsCard();

        }
    }

}
