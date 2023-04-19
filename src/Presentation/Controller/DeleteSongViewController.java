package Presentation.Controller;

import Presentation.View.DeleteSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSongViewController implements ActionListener {
    private final DeleteSongView deleteSongView;

    public DeleteSongViewController(DeleteSongView deleteSongView) {
        this.deleteSongView = deleteSongView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
