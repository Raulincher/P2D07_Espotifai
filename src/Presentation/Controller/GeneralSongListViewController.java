package Presentation.Controller;

import Presentation.View.DetailedSongView;
import Presentation.View.GeneralSongListView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralSongListViewController implements ActionListener {

    private final GeneralSongListView generalSongListView;
    private final MainView mainView;


    public GeneralSongListViewController(GeneralSongListView generalSongListView, MainView mainView) {
        this.generalSongListView = generalSongListView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GeneralSongListView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }


}
