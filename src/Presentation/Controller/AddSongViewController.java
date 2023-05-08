package Presentation.Controller;

import Business.SongManager;
import Presentation.View.AddSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongViewController implements ActionListener {
    private final AddSongView addSongView;
    private final MainView mainView;
    private final SongManager songManager;

    public AddSongViewController(AddSongView addSongView, MainView mainView, SongManager songManager) {
        this.addSongView = addSongView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case AddSongView.BTN_BACK -> mainView.showMainMenuCard();
            case AddSongView.BTN_ADD_FILE -> songManager.fileSongSelector();
        }
    }
}
