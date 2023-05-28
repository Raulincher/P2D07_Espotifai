package Persistance.dao;

import Business.Entities.Playlist;

import java.util.ArrayList;

public interface PlaylistDao {

    /**
     * Funció que servirà per guardar una playlist a BBDD
     *
     * @param playlist, playlist per guardar
     */
    void savePlaylist(Playlist playlist) throws PlaylistNotSavedException;

    /**
     * Funció que servirà per obtenir totes les playlist de la BBDD
     *
     * @return playlists, arraylist de tipus string amb totes les playlist de la BBDD
     */
    ArrayList<Playlist> obtainAllPlaylists();

    /**
     * Funció que servirà per obtenir totes les songs d'una playlist de la BBDD
     *
     * @param playlistName, nom de la playlist
     *
     * @return songsInPlaylist, arraylist de tipus string amb totes les cançons d'una playlist
     */
    ArrayList<String> obtainAllSongsInPlaylist(String playlistName);

    /**
     * Funció que servirà per afegir una cançó a una playlist
     *
     * @param songName, nom de la cançó
     * @param playlist, nom de la playlist
     *
     * @return saved, boolean que ens indica si s'ha pogut guardar
     */
    boolean addSongToPlaylistDAO(String songName, String playlist);

    /**
     * Funció que servirà per esborrar una playlist
     *
     * @param playlistName, nom de la playlist
     *
     * @return deleted, boolean que ens indica si s'ha pogut esborrar
     */
    boolean deletePlaylistFromDAO(String playlistName);

    /**
     * Funció que servirà per esborrar una cançó de totes les playlist
     *
     * @param songTitle, nom de la cançó
     *
     * @return success, boolean que ens indica si s'ha pogut esborrar
     */
    boolean deleteSongFromPlaylistsDAO(String songTitle);

    /**
     * Funció que servirà per buscar una canço a una playlist
     *
     * @param songName, nom de la cançó
     * @param playlist, nom de la playlist
     *
     * @return inPlaylist, boolean que ens indica si esta o no a la playlist
     */
    boolean searchSongInPlaylist(String songName, String playlist);

    /**
     * Funció que servirà per esborrar una cançó d'una playlist
     *
     * @param playlistName, nom de la playlist
     * @param songTitle, nom de la cançó
     *
     * @return success, boolean que ens indica si s'ha pogut esborrar
     */
    boolean deleteSongFromPlaylistDAO(String playlistName, String songTitle);

    /**
     * Funció que servirà per esborrar una cançó pujada per un user de totes les playlist
     *
     * @param songsByUser, totes les cançons d'un user
     * @param username, nom de l'usuari
     *
     */
    void deleteSongsOrPlaylistByUser(ArrayList<String> songsByUser, String username);
}
