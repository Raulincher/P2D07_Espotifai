package Business;

import Business.Entities.Playlist;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlaylistManager {
    private final PlaylistDao playlistDao;

    public PlaylistManager(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    public boolean savePlaylist(String username, String title) {
        Playlist playlist = new Playlist(username, title, null);
        try {
            playlistDao.savePlaylist(playlist);
            System.out.println("La llista s'ha guardat!");
            return true;
        } catch (PlaylistNotSavedException e) {
            System.out.println("La llista NO s'ha guardat!");

            return false;
        }
    }

    public ArrayList<String> obtainPlaylistNames (boolean byUsername, String username) {
        ArrayList<Playlist> playlists = playlistDao.obtainAllPlaylists();
        ArrayList<String> playlistNames = new ArrayList<>();

        if(byUsername) {
            for (int i = 0; i < playlists.size(); i++) {
                if (username.equals(playlists.get(i).getUsername())) {
                    playlistNames.add(playlists.get(i).getTitle());
                }
            }
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                if (!(username.equals(playlists.get(i).getUsername()))) {
                    playlistNames.add(playlists.get(i).getTitle());
                }
            }
        }
        return playlistNames;
    }

    public ArrayList<String> obtainSongsInPlaylist(String playlistName) {
        return playlistDao.obtainAllSongsInPlaylist(playlistName);
    }
}
