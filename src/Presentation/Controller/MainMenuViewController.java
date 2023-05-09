package Presentation.Controller;

import Business.SongManager;
import Presentation.View.GeneralSongListView;
import Presentation.View.InitialView;
import Presentation.View.MainMenuView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuViewController implements ActionListener{

    private final MainMenuView mainMenuView;
    private final MainView mainView;
    final SongManager songManager;

    public MainMenuViewController(MainMenuView mainMenuView, MainView mainView, SongManager songManager) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MainMenuView.BTN_BACK -> mainView.showMainCard();
            case MainMenuView.BTN_ADD_SONG -> mainView.showAddSongCard();
            case MainMenuView.BTN_DELETE_SONG -> mainView.showDeleteSongCard();
            case MainMenuView.BTN_MANAGE -> mainView.showGeneralPlaylistCard();
            case MainMenuView.BTN_SONG_LIST -> mainView.showGeneralSongListCard();
            case MainMenuView.BTN_STATISTICS -> mainView.showStatisticsCard();

        }
    }

}
