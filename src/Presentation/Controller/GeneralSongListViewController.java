package Presentation.Controller;

import Business.Entities.Song;
import Business.SongManager;
import Presentation.View.DetailedSongView;
import Presentation.View.GeneralSongListView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralSongListViewController implements ActionListener {

    private final GeneralSongListView generalSongListView;
    private final MainView mainView;
    private final SongManager songManager;


    public GeneralSongListViewController(GeneralSongListView generalSongListView, MainView mainView, SongManager songManager) {
        this.generalSongListView = generalSongListView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case GeneralSongListView.BTN_BACK:
                mainView.showMainMenuCard();
                break;
            case GeneralSongListView.BTN_BUSCADOR:
                String buscador = generalSongListView.getjBuscador().getText();
                mainView.showDetailedSongCard();
                break;
        }
        if (generalSongListView.songShow()){
            String selected = generalSongListView.getSongSelected();
            Song songSelected = songManager.searchSong(selected);
            mainView.showDetailedSongCard();
        }
    }




}
