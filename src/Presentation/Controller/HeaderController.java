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

    /**
     * Funció que servirà per com a constructor del HeaderController
     *
     * @param headerView, utilitzarem els metodes dins de headerView
     * @param userManager, utilitzarem el metodes dins de userManager, així tractarem tot el tema d'usuaris
     * @param songManager, utilitzarem el metodes dins de songManager, així tractarem tot el tema de cançons
     * @param playlistManager, utilitzarem el metodes dins de playlistManager, així tractarem tot el tema de playlists
     * @param footerController, utilitzarem una funció dins d'aquest metode per així poder fer reset de la barra un cop sortim del nostre compte
     */
    public HeaderController(HeaderView headerView, UserManager userManager, SongManager songManager, PlaylistManager playlistManager, FooterController footerController) {
        this.headerView = headerView;
        this.userManager = userManager;
        this.songManager = songManager;
        this.playlistManager = playlistManager;
        this.footerController = footerController;
    }
    /**
     * Funció que servirà per afegir la mainview dins del nostre headerController
     *
     * @param mainView, utilitzarem els metodes dins de mainview

     */
    public void addMainView(MainView mainView){
        this.mainView = mainView;
    }


    /**
     * Funció que servirà per detectar si hem premut qualsevol botó de la vista del header
     *
     * No tindrà param ni return
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //case back, que ens permetrá regresar a la vista del menu d'opcions
            case HeaderView.BTN_BACK:
                mainView.showMainMenuCard();
                break;
            // case delete account, botó que ens permetrá borrar el compte actual
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
                    }
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            // case logout, botó que ens permetrà sortir del compte actual sense esborrar l'usuari
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
