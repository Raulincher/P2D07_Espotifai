package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongViewController implements ActionListener {

    private final AddSongView addSongView;
    private final MainView mainView;


    public AddSongViewController(AddSongView addSongView, MainView mainView) {
        this.addSongView = addSongView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case AddSongView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }
}
