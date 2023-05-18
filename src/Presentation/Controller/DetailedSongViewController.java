package Presentation.Controller;

import Presentation.View.*;

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
        /*switch (e.getActionCommand()) {
            case DetailedSongView.BTN_BACK -> mainView.showGeneralSongListCard();
            case DetailedSongView.BTN_LOGOUT -> mainView.showMainCard();
            case DetailedSongView.BTN_DELETE -> mainView.showMainCard();
        }*/
    }
}
