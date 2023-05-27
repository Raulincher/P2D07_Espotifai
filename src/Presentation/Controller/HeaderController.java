package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Persistance.dao.UserNotFoundException;
import Presentation.View.HeaderView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HeaderController implements ActionListener {
    private final HeaderView headerView;
    private final UserManager userManager;
    private final SongManager songManager;
    private final PlaylistManager playlistManager;
    private MainView mainView;
    private FooterController footerController;


    public HeaderController(HeaderView headerView, UserManager userManager, SongManager songManager, PlaylistManager playlistManager, FooterController footerController) {
        this.headerView = headerView;
        this.userManager = userManager;
        this.songManager = songManager;
        this.playlistManager = playlistManager;
        this.footerController = footerController;
    }

    public void addMainView(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HeaderView.BTN_BACK:
                mainView.showMainMenuCard();
                break;
            case HeaderView.BTN_DELETEACC:
                try {
                    int resposta = headerView.showPopUps("Are you sure?","Delete Account");
                    if (resposta == 0) {
                        ArrayList<String> songsByUser = songManager.obtainSongsByUser(userManager.currentUsername());
                        if (songsByUser != null) {
                            ArrayList<String> pathsToDelete = songManager.deleteSongsByUsername(songsByUser);
                            songManager.deletePaths(pathsToDelete);
                        }
                        playlistManager.deletePlaylists(songsByUser, userManager.currentUsername());
                        songManager.stopClip();
                        userManager.delete();
                        userManager.logout();
                        footerController.outOfTheProgram();
                        mainView.showMainCard();

                       // userManager.delete();

                    }
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case HeaderView.BTN_LOGOUT:
                int resposta2 = headerView.showPopUps("Are you sure?","Log Out");
                if (resposta2 == 0) {
                    songManager.stopClip();
                    userManager.logout();
                    footerController.outOfTheProgram();
                    mainView.showMainCard();
                }
                break;
        }
    }
}
