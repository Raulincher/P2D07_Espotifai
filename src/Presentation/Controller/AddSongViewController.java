package Presentation.Controller;

import Presentation.View.AddSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongViewController implements ActionListener {

    private final AddSongView addSongView;

    public AddSongViewController(AddSongView addSongView) {
        this.addSongView = addSongView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
