package Presentation.Controller;

import Presentation.View.DeleteSongView;
import Presentation.View.DetailedPlaylistView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedPlaylistViewController implements ActionListener {
    private final DetailedPlaylistView detailedPlaylistView;
    private final MainView mainView;


    public DetailedPlaylistViewController(DetailedPlaylistView detailedPlaylistView, MainView mainView) {
        this.detailedPlaylistView = detailedPlaylistView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //case DeleteSongView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }
}
