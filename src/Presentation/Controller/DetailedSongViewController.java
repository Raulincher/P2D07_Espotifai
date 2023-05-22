package Presentation.Controller;

import Business.SongManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DetailedSongViewController implements ActionListener {

    private final DetailedSongView detailedSongView;
    private final MainView mainView;
    private final SongManager songManager;


    public DetailedSongViewController(DetailedSongView detailedSongView, MainView mainView,SongManager songManager) {
        this.detailedSongView = detailedSongView;
        this.mainView = mainView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
           //Afegir add SongToList
        }
    }
}
