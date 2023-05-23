package Persistance.dao.sql;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLPlaylistDao implements PlaylistDao {

    private final Connection remoteConnection;
    public SQLPlaylistDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void savePlaylist(Playlist playlist) throws PlaylistNotSavedException {
        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String title = playlist.getTitle();
                String username = playlist.getUsername();
                String song = "hola";
                String register = "INSERT INTO playlist (username, title, songs)  VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, username);
                preparedStmt.setString (2, title);
                preparedStmt.setString (3, song);
                preparedStmt.execute();
                //remoteConnection.close();

            }catch (SQLException e){
                System.out.println("hola");
                throw new PlaylistNotSavedException("Error while saving the playlist");
            }
        } catch (SQLException e) {
            //Espotifai not found
            System.out.println("adeu");
            throw new PlaylistNotSavedException("Error while saving the playlist");
        }
    }

    public ArrayList<Playlist> obtainAllPlaylists() {
        ArrayList<Playlist> playlists = new ArrayList<>();

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
                playlists = null;
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String username = resultSet.getString("username");
                    String allSongs = resultSet.getString("songs");
                    String songs[] = allSongs.split(",");
                    ArrayList<String> songNames = new ArrayList<>(Arrays.asList(songs));
                    Playlist playlist = new Playlist(username, title, songNames);
                    playlists.add(playlist);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return playlists;
    }

    public ArrayList<String> obtainAllSongsInPlaylist(String playlistName) {
        ArrayList<String> songsInPlaylist = new ArrayList<>();

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
                songsInPlaylist = null;
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    if (playlistName.equals(resultSet.getString("title"))) {
                        String allSongs = resultSet.getString("songs");
                        String songs[] = allSongs.split(",");
                        songsInPlaylist = new ArrayList<>(Arrays.asList(songs));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return songsInPlaylist;
    }
}
