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
                String song = "";
                String register = "INSERT INTO playlist (username, title, songs)  VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, username);
                preparedStmt.setString (2, title);
                preparedStmt.setString (3, song);
                preparedStmt.execute();
                //remoteConnection.close();

            }catch (SQLException e){
                throw new PlaylistNotSavedException("Error while saving the playlist");
            }
        } catch (SQLException e) {
            //Espotifai not found
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
                    ArrayList<String> songNames;
                    if (allSongs.equals("")) {
                        songNames = null;
                    } else {
                        String songs[] = allSongs.split(",");
                         songNames = new ArrayList<>(Arrays.asList(songs));
                    }
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
                        if (allSongs.equals("")) {
                            songsInPlaylist = null;
                        } else {
                            String songs[] = allSongs.split(",");
                            songsInPlaylist = new ArrayList<>(Arrays.asList(songs));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return songsInPlaylist;
    }

    public boolean addSongToPlaylistDAO(String songName, String playlist) {
        boolean saved = false;
        String allSongs = null;
        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    if (title.equals(playlist)) {
                        allSongs = resultSet.getString("songs");
                        if (allSongs.equals("")) {
                            allSongs += songName;
                            System.out.println("hello");
                            System.out.println(allSongs);
                        } else {
                            allSongs += "," + songName;
                        }
                        String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                        PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                        preparedStmt.setString (1, allSongs);
                        preparedStmt.setString(2, playlist);
                        preparedStmt.execute();
                        saved = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return saved;
    }

    public boolean deletePlaylistFromDAO(String playlistName) {
        boolean deleted = false;

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");

                    if (playlistName.equals(title)) {
                        //Guardem el filePath per a poder borrar el fitxer local

                        try {
                            String deleteQuery = "DELETE FROM playlist WHERE title = ?";
                            PreparedStatement preparedStatement = remoteConnection.prepareStatement(deleteQuery);
                            preparedStatement.setString(1, playlistName);
                            int affectedRows = preparedStatement.executeUpdate();
                            if (affectedRows == 0) {
                                System.out.println("Playlist not found in the database");
                            } else {
                                deleted = true;
                            }
                        } catch (SQLException e) {
                            System.out.println("Error eliminating the song");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return deleted;
    }
}
