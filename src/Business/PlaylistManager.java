package Business;

import Business.Entities.Playlist;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;
import java.util.ArrayList;

public class PlaylistManager {

    // Preparem els atributs
    private final PlaylistDao playlistDao;
    private String currentPlaylist;
    private String clickedSong;

    /**
     * Constructor de la classe PlaylistManager
     *
     * @param playlistDao, interface que ens permet connectar amb l'apartat de playlists dins la BBDD
     */
    public PlaylistManager(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * Funció que ens serveix per guardar playlists dins la BBDD
     *
     * @param username, nom de la persona que crea la playlist
     * @param title, titol de la playlist
     *
     * @return boolean que indica s'hi la playlist s'ha creat
     */
    public boolean savePlaylist(String username, String title) {
        Playlist playlist = new Playlist(username, title, null);

        try {
            playlistDao.savePlaylist(playlist);
            return true;
        } catch (PlaylistNotSavedException e) {
            return false;
        }
    }

    /**
     * Funció que ens serveix per obtenir els noms de totes les playlists
     *
     * @param byUsername, boolean que indica si la playlist es nostra
     * @param username, nom de l'usuari actual
     *
     * @return playlistNames, arraylist d'Strings on s'emmagatzemen el noms
     */
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

    /**
     * Funció que ens serveix per obtenir les cançons d'una playlist
     *
     * @param playlistName, el nom de la playlist
     *
     * @return Arraylist de tipus String on guardem totes les cançons d'una playlist
     */
    public ArrayList<String> obtainSongsInPlaylist(String playlistName) {
        if (playlistDao.obtainAllSongsInPlaylist(playlistName) == null) {
            return null;
        } else {
            return playlistDao.obtainAllSongsInPlaylist(playlistName);
        }
    }

    /**
     * Funció que ens permet afegir cançons a una playlist
     *
     * @param songName, nom de la canço a afegir
     * @param playlistName, el nom de la playlist
     *
     * no return
     */
    public void addSongToPlaylist(String songName, String playlistName) {
        playlistDao.addSongToPlaylistDAO(songName,playlistName);
    }


    /**
     * Funció que ens serveix per poder comprobar si una cançó ja existeix a una playlist
     *
     * @param songName, el nom de la cançó
     * @param playlistName, el nom de la playlist
     *
     * @return inPlaylist, boolean que indica la existencia de la cançó
     */
    public boolean checkIfSongInPlaylist(String songName, String playlistName) {
        boolean inPlaylist = playlistDao.searchSongInPlaylist(songName, playlistName);
        return inPlaylist;
    }

    /**
     * Funció que ens serveix per poder agafar la próxima cançó dins d'una playlist
     *
     * @param playListName, el nom de la playlist
     * @param loopCondition, boolean que indica si la llista pot reproduir-se en bucle
     *
     * @return song, string amb el title de la cançó
     */
    public String getNextSongInPlaylist(String playListName, boolean loopCondition){
        String song;
        String actualSong = this.clickedSong;
        int songPosition = 0;
        ArrayList<String> allTitles = obtainSongsInPlaylist(playListName);

        for(int i = 0; i < allTitles.size(); i++){
            if(actualSong.equals(allTitles.get(i))){
                songPosition = i;
            }
        }

        if(songPosition == allTitles.size() - 1){
            if(loopCondition){
                song = allTitles.get(0);

            }else{
                song = allTitles.get(allTitles.size() - 1);
            }

        }else{
            song = allTitles.get(songPosition + 1);
        }
        clickedSong = song;
        return song;
    }


    /**
     * Funció que ens serveix per poder agafar l'anterior cançó dins d'una playlist, si estem a la primera sempre s'agafarà ella mateixa
     *
     * @param playListName, el nom de la playlist
     *
     * @return song, string amb el title de la cançó
     */
    public String getPreviousSongInPlaylist(String playListName){
        String song;
        String actualSong = this.clickedSong;
        int songPosition = 0;
        ArrayList<String> allTitles = obtainSongsInPlaylist(playListName);

        for(int i = 0; i < allTitles.size(); i++){
            if(actualSong.equals(allTitles.get(i))){
                songPosition = i;
            }
        }

        if(songPosition == 0){
            song = allTitles.get(0);
        }else{
            song = allTitles.get(songPosition - 1);
        }
        clickedSong = song;

        return song;
    }

    /**
     * Funció que ens fer set del nom de la playlist actual
     *
     * @param currentPlaylist, el nom de la playlist actual
     *
     * no return
     */
    public void setCurrentPlaylist(String currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    /**
     * Funció que ens serveix per poder agafar el nom de l'actual playlist
     *
     * @return currentPlaylist, string amb el nom de la playlist actual
     *
     * no param
     */
    public String getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     * Funció que ens fer set del nom de la cançó actual de la playlist
     *
     * @param clickedSong, el nom de la cançó clicada
     *
     * no return
     */
    public void setClickedSong(String clickedSong){this.clickedSong = clickedSong;}

    /**
     * Funció que ens serveix per poder agafar el nom de l'actual cançó dins la playlist
     *
     * @return clickedSong, string amb el nom de la cançó actual
     *
     * no param
     */
    public String getClickedSong(){return this.clickedSong;}


    /**
     * Funció que ens serveix per poder esborrar una playlist
     *
     * @param playListName, el nom de la playlist
     *
     * @return boolean que indica si el proces ha sigut correcte
     */
    public boolean deletePlaylist(String playListName) {
        return playlistDao.deletePlaylistFromDAO(playListName);
    }

    /**
     * Funció que ens serveix per poder esborrar una cançó de la playlist seleccionada
     *
     * @param songTitle, el nom de la cançó
     *
     * no return
     */
    public void deleteSongFromPlaylists(String songTitle) {
        playlistDao.deleteSongFromPlaylistsDAO(songTitle);
    }

    /**
     * Funció que ens serveix per poder esborrar una cançó desde fora de la playlist
     *
     * @param playlistName, el nom de la playlist
     * @param songTitle, el nom de la cançó
     *
     * no return
     */
    public void deleteSongFromPlaylist(String playlistName, String songTitle) {
        playlistDao.deleteSongFromPlaylistDAO(playlistName,songTitle);
    }

    /**
     * Funció que ens serveix per poder esborrar les cançons y les playlist d'un usuari que ha sigut esborrat
     *
     * @param songsByUser, arraylist de les cançons de l'usuari a esborrar
     * @param username, el nom de l'usuari
     *
     * no return
     */
    public void deletePlaylists(ArrayList<String> songsByUser, String username) {
        playlistDao.deleteSongsOrPlaylistByUser(songsByUser,username);
    }
}
