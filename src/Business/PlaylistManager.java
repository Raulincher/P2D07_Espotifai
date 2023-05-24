package Business;

import Business.Entities.Playlist;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class PlaylistManager {
    private final PlaylistDao playlistDao;
    private String currentPlaylist;

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

        ArrayList<String> playlistNames = new ArrayList<>();

        if (playlistDao.obtainAllPlaylists() == null) {
            playlistNames = null;
        } else {
            ArrayList<Playlist> playlists = playlistDao.obtainAllPlaylists();
            if (byUsername) {
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
        }
        return playlistNames;
    }

    public ArrayList<String> obtainSongsInPlaylist(String playlistName) {
        if (playlistDao.obtainAllSongsInPlaylist(playlistName) == null) {
            return null;
        } else {
            return playlistDao.obtainAllSongsInPlaylist(playlistName);
        }
    }

    public void addSongToPlaylist(String songName, String playlistName) {
        playlistDao.addSongToPlaylistDAO(songName,playlistName);
    }

    public boolean checkIfSongInPlaylist(String songName, String playlistName) {
        boolean inPlaylist = playlistDao.searchSongInPlaylist(songName, playlistName);
        return inPlaylist;
    }

    public void setCurrentPlaylist(String currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public String getCurrentPlaylist() {
        return currentPlaylist;
    }

    public boolean deletePlaylist(String playListName) {
        return playlistDao.deletePlaylistFromDAO(playListName);
    }

    public void deleteSongFromPlaylists(String songTitle) {
        playlistDao.deleteSongFromPlaylistsDAO(songTitle);
    }
}
