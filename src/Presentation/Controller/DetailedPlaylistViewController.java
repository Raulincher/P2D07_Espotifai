package Presentation.Controller;

import Business.PlaylistManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedPlaylistViewController implements ActionListener {
    private final DetailedPlaylistView detailedPlaylistView;
    private final MainView mainView;
    private final PlaylistManager playlistManager;
    private final GeneralPlaylistView generalPlaylistView;

    private UserManager userManager;

    public DetailedPlaylistViewController(DetailedPlaylistView detailedPlaylistView, MainView mainView,
                                          PlaylistManager playlistManager, GeneralPlaylistView generalPlaylistView,
                                          UserManager userManager) {
        this.detailedPlaylistView = detailedPlaylistView;
        this.mainView = mainView;
        this.playlistManager = playlistManager;
        this.generalPlaylistView = generalPlaylistView;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DetailedPlaylistView.BTN_DELETE_PLAYLIST -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                if (playlistManager.deletePlaylist(playlistName)) {
                    detailedPlaylistView.showPopUp("Playlist successfully deleted!");
                    generalPlaylistView.fillOtherPlaylistsTable(playlistManager.obtainPlaylistNames(false, userManager.currentUsername()));
                    generalPlaylistView.fillMyPlaylistsTable(playlistManager.obtainPlaylistNames(true,  userManager.currentUsername()));
                    mainView.showGeneralPlaylistCard();
                } else {
                    detailedPlaylistView.showPopUp("Couldn't delete playlist.");
                }
            }
        }
    }
}
