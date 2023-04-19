package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.DetailedSongView;
import Presentation.View.GeneralPlaylistView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralPlaylistViewController implements ActionListener {

    private final GeneralPlaylistView generalPlaylistView;
    private final MainView mainView;

    public GeneralPlaylistViewController(GeneralPlaylistView generalPlaylistView, MainView mainView) {
        this.generalPlaylistView = generalPlaylistView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DetailedSongView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }
}
