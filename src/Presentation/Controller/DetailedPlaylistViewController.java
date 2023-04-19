package Presentation.Controller;

import Presentation.View.DeleteSongView;
import Presentation.View.DetailedPlaylistView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedPlaylistViewController implements ActionListener {
    private final DetailedPlaylistView detailedPlaylistView;

    public DetailedPlaylistViewController(DetailedPlaylistView detailedPlaylistView) {
        this.detailedPlaylistView = detailedPlaylistView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
