package Presentation.Controller;

import Presentation.View.DetailedSongView;
import Presentation.View.GeneralSongListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralSongListViewController implements ActionListener {

    private final GeneralSongListView generalSongListView;

    public GeneralSongListViewController(GeneralSongListView generalSongListView) {
        this.generalSongListView = generalSongListView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
