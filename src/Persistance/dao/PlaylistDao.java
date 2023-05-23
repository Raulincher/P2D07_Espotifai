package Persistance.dao;

import Business.Entities.Playlist;

import java.util.ArrayList;

public interface PlaylistDao {

    void savePlaylist(Playlist playlist) throws PlaylistNotSavedException;

    ArrayList<Playlist> obtainAllPlaylists();

    ArrayList<String> obtainAllSongsInPlaylist(String playlistName);
    boolean addSongToPlaylistDAO(String songName, String playlist);
    boolean deletePlaylistFromDAO(String playlistName);
}
