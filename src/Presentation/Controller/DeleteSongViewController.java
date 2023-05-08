package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.DeleteSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSongViewController implements ActionListener {
    private final DeleteSongView deleteSongView;
    private final MainView mainView;


    public DeleteSongViewController(DeleteSongView deleteSongView, MainView mainView) {
        this.deleteSongView = deleteSongView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //case DeleteSongView.BTN_BACK -> mainView.showMainMenuCard();
        }
    }

}
