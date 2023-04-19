package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.DeleteSongView;
import Presentation.View.DetailedSongView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedSongViewController implements ActionListener {

    private final DetailedSongView detailedSongView;
    private final MainView mainView;


    public DetailedSongViewController(DetailedSongView detailedSongView, MainView mainView) {
        this.detailedSongView = detailedSongView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DetailedSongView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }
}
