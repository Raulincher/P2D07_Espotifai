package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedSongViewController implements ActionListener {

    private final DetailedSongView detailedSongView;

    public DetailedSongViewController(DetailedSongView detailedSongView) {
        this.detailedSongView = detailedSongView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
