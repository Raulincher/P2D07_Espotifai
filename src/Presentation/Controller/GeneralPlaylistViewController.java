package Presentation.Controller;

import Business.PlaylistManager;
import Presentation.View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralPlaylistViewController implements ActionListener {

    private final GeneralPlaylistView generalPlaylistView;
    private final MainView mainView;
    //private final PlaylistManager playlistManager;

    public GeneralPlaylistViewController(GeneralPlaylistView generalPlaylistView, MainView mainView) {
        this.generalPlaylistView = generalPlaylistView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GeneralPlaylistView.BTN_NEW_PLAYLIST -> {
            //    String texto = JOptionPane.showInputDialog( "Playlist Name:");
                mainView.showDetailedPlaylistCard();

            }
        }
    }
}
