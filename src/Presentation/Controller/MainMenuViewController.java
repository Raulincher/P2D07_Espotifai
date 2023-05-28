package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainMenuViewController implements ActionListener{

    // Declarem els atributs
    private final MainMenuView mainMenuView;
    private final MainView mainView;
    private final SongManager songManager;
    private final UserManager userManager;
    private final PlaylistManager playlistManager;
    private final AddSongView addSongView;
    private final DeleteSongView deleteSongView;
    private final StatisticsView statisticsView;
    private final GeneralSongListView generalSongListView;
    private final GeneralPlaylistView generalPlaylistView;

    /**
     * Funció que servirà per com a constructor del MainMenuViewController
     *
     * @param mainMenuView, per poder detectar els clicks dins la vista
     * @param mainView, per poder fer el canvi de cards
     * @param songManager, per poder agafar informació de cançons
     * @param userManager, per poder agafar informació de users
     * @param deleteSongView, per poder omplir la taula de delete que es mostrarà
     * @param generalSongListView, per poder omplir la taula de songs que es mostrarà
     * @param generalPlaylistView, per poder omplir la taula de playlists que es mostrarà
     * @param playlistManager, per poder agafar informació de playlists
     * @param addSongView, per poder fer el canvi de cards
     * @param statisticsView, per poder repintar las statistics
     *
     */
    public MainMenuViewController(MainMenuView mainMenuView, MainView mainView, SongManager songManager,
                                  UserManager userManager, DeleteSongView deleteSongView,
                                  GeneralSongListView generalSongListView, GeneralPlaylistView generalPlaylistView,
                                  PlaylistManager playlistManager, AddSongView addSongView, StatisticsView statisticsView) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.songManager = songManager;
        this.userManager = userManager;
        this.deleteSongView = deleteSongView;
        this.statisticsView = statisticsView;
        this.generalSongListView = generalSongListView;
        this.generalPlaylistView = generalPlaylistView;
        this.playlistManager = playlistManager;
        this.addSongView = addSongView;
    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol botó de la vista de menu
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //cas add song, que permet mostrar la vista d'afegir cançó
            case MainMenuView.BTN_ADD_SONG -> {
                mainView.showAddSongCard();
            }

            //cas delete song, que permet mostrar la vista d'eliminar cançó
            case MainMenuView.BTN_DELETE_SONG -> {
                deleteSongView.fillDeleteTable(songManager.listSongs(true, userManager.currentUsername()));
                deleteSongView.clearSearcher();
                mainView.showDeleteSongCard();
            }

            //cas manage, que permet mostrar la vista de playlists general
            case MainMenuView.BTN_MANAGE -> {
                generalPlaylistView.fillOtherPlaylistsTable(playlistManager.obtainPlaylistNames(false, userManager.currentUsername()));
                generalPlaylistView.fillMyPlaylistsTable(playlistManager.obtainPlaylistNames(true,  userManager.currentUsername()));
                generalPlaylistView.clearSearcher();
                mainView.showGeneralPlaylistCard();
            }

            //cas song list, que permet mostrar la vista de songs general
            case MainMenuView.BTN_SONG_LIST -> {
                generalSongListView.fillTable(songManager.listSongs(false, null));
                mainView.showGeneralSongListCard();
            }

            //cas statistics, que permet mostrar la vista d'estadístiques
            case MainMenuView.BTN_STATISTICS -> {
                Map<String, Integer> genreMap = songManager.createGenreMap();
                statisticsView.setGenreMap(genreMap);
                statisticsView.repaint();
                mainView.showStatisticsCard();
            }

        }
    }

}
