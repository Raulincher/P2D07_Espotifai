package Business;

import Business.Entities.Playlist;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;
import java.util.ArrayList;

public class PlaylistManager {
    private final PlaylistDao playlistDao;
    private String currentPlaylist;
    private String clickedSong;

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

    public String getNextSongInPlaylist(String playListName){
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
            song = allTitles.get(0);
        }else{
            song = allTitles.get(songPosition + 1);
        }
        clickedSong = song;
        return song;
    }

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

    public void setCurrentPlaylist(String currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public String getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setClickedSong(String clickedSong){this.clickedSong = clickedSong;}

    public String getClickedSong(){return this.clickedSong;}

    public boolean deletePlaylist(String playListName) {
        return playlistDao.deletePlaylistFromDAO(playListName);
    }

    public void deleteSongFromPlaylists(String songTitle) {
        playlistDao.deleteSongFromPlaylistsDAO(songTitle);
    }

    public void deleteSongFromPlaylist(String playlistName, String songTitle) {
        playlistDao.deleteSongFromPlaylistDAO(playlistName,songTitle);
    }
}
