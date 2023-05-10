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
    private SongTableModelController songTableModelController;
    public MainMenuViewController(MainMenuView mainMenuView, MainView mainView, SongTableModelController songTableModelController) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.songTableModelController = songTableModelController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MainMenuView.BTN_BACK -> mainView.showMainCard();
            case MainMenuView.BTN_ADD_SONG -> mainView.showAddSongCard();
            case MainMenuView.BTN_DELETE_SONG -> {
                songTableModelController.createDeleteJTable();
                mainView.showDeleteSongCard();
            }
            case MainMenuView.BTN_MANAGE -> mainView.showGeneralPlaylistCard();
            case MainMenuView.BTN_SONG_LIST -> mainView.showGeneralSongListCard();
            case MainMenuView.BTN_STATISTICS -> mainView.showStatisticsCard();

        }
    }

}
