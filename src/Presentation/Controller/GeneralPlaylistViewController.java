package Presentation.Controller;

import Presentation.View.AddSongView;
import Presentation.View.GeneralPlaylistView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralPlaylistViewController implements ActionListener {

    private final GeneralPlaylistView generalPlaylistView;

    public GeneralPlaylistViewController(GeneralPlaylistView generalPlaylistView) {
        this.generalPlaylistView = generalPlaylistView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
