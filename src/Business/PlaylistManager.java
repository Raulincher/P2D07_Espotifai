package Business;

import Persistance.dao.PlaylistDao;

public class PlaylistManager {
    private final PlaylistDao playlistDao;

    public PlaylistManager(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }
}
