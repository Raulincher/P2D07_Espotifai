package Presentation.Controller;

import Business.SongManager;
import Presentation.View.AddSongView;
import Presentation.View.DeleteSongView;
import Presentation.View.InitialView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSongViewController implements ActionListener {
    private final DeleteSongView deleteSongView;
    private final MainView mainView;
    private final SongManager songManager;


    public DeleteSongViewController(DeleteSongView deleteSongView, MainView mainView, SongManager songManager) {
        this.deleteSongView = deleteSongView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DeleteSongView.BTN_DELETE -> {
                String input = deleteSongView.getInput().getText();
                boolean error = songManager.deleteSong(input);
                if(error){
                    System.out.println("song doesn't exists");
                }
            }
        }
    }

}
