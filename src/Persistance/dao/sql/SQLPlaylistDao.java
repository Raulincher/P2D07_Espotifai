package Persistance.dao.sql;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;
import Persistance.dao.SongDao;

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
                String song = null;
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
                ArrayList<String> songNames = null;
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String username = resultSet.getString("username");
                    String allSongs = resultSet.getString("songs");

                    if (allSongs == null) {
                        songNames = null;
                    } else {
                        String[] songs = allSongs.split(",");
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
                        if (allSongs == null) {
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

    public boolean searchSongInPlaylist(String songName, String playlist) {
        boolean inPlaylist = false;

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
                        String allSongs = resultSet.getString("songs");
                        if (allSongs == null) {
                            inPlaylist = false;
                        } else {
                            String[] songs = allSongs.split(",");
                            for (int i = 0; i < songs.length; i++) {
                                if (songs[i].equals(songName)) {
                                    inPlaylist = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return inPlaylist;
    }
    public boolean addSongToPlaylistDAO(String songName, String playlist) {
        boolean saved = false;
        boolean equals = false;

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
                        if (allSongs == null) {
                            allSongs = songName;
                        } else {
                            allSongs += "," + songName;
                        }
                        String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                        PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                        preparedStmt.setString(1, allSongs);
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

    public boolean deleteSongFromPlaylistsDAO(String songTitle) {
        boolean success = true;

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String playlist = resultSet.getString("title");
                    String allSongs = resultSet.getString("songs");
                    if (allSongs != null) {
                        String[] songs = allSongs.split(",");
                        ArrayList<String> songsPlaylist = new ArrayList<>();

                        for (int i = 0; i < songs.length; i++) {
                            if (!songs[i].equals(songTitle)) {
                                songsPlaylist.add(songs[i]);
                            }
                        }
                        String songSave = null;
                        for (int i = 0; i < songsPlaylist.size(); i++) {
                            if (i == 0) {
                                songSave = songsPlaylist.get(i);
                            } else {
                                songSave += "," + songsPlaylist.get(i);
                            }
                        }
                        String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                        PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                        preparedStmt.setString (1, songSave);
                        preparedStmt.setString(2, playlist);
                        preparedStmt.execute();
                    }
                }
            }
        } catch (SQLException e) {
            success = false;
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return success;
    }

    public boolean deleteSongFromPlaylistDAO(String playlistName, String songTitle) {
        boolean success = true;

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String playlist = resultSet.getString("title");
                    if (playlistName.equals(playlist)) {
                        String allSongs = resultSet.getString("songs");
                        String[] songs = allSongs.split(",");
                        ArrayList<String> songsPlaylist = new ArrayList<>();

                        for (int i = 0; i < songs.length; i++) {
                            if (!songs[i].equals(songTitle)) {
                                songsPlaylist.add(songs[i]);
                            }
                        }

                        String songSave = null;
                        for (int i = 0; i < songsPlaylist.size(); i++) {
                            if (i == 0) {
                                songSave = songsPlaylist.get(i);
                            } else {
                                songSave += "," + songsPlaylist.get(i);
                            }
                        }
                        String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                        PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                        preparedStmt.setString (1, songSave);
                        preparedStmt.setString(2, playlist);
                        preparedStmt.execute();

                    }
                }
            }
        } catch (SQLException e) {
            success = false;
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return success;
    }

    public void deleteSongsOrPlaylistByUser(ArrayList<String> songsByUser, String username) {
        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM playlist");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String usernameFromDataBase = resultSet.getString("username");
                    String title = resultSet.getString("title");
                    String allSongs = resultSet.getString("songs");
                    boolean save = true;

                    //for (int i = 0; i < songsByUser.size(); i++) {
                        // Si la playlist es del user, la borrem
                        if (username.equals(usernameFromDataBase)) {
                            try {
                                String deleteQuery = "DELETE FROM playlist WHERE title = ?";
                                PreparedStatement preparedStatement = remoteConnection.prepareStatement(deleteQuery);
                                preparedStatement.setString(1, title);
                                int affectedRows = preparedStatement.executeUpdate();
                                if (affectedRows == 0) {
                                    System.out.println("Song not found in the database");
                                }
                            } catch (SQLException e) {
                                System.out.println("Error eliminating the song");
                            }
                        } else {
                            // HEM DE LLEGIR LA PLAYLIST I TREURE LES CANÇONS DEL NOSTRE USER
                       //     try {

                                if (allSongs != null) {
                                    String[] songs = allSongs.split(",");
                                    ArrayList<String> songsUpdated = new ArrayList<>();

                                    System.out.println("songsUpdated TOP: " + songsUpdated);

                                    for (int j = 0; j < songs.length; j++) {
                                        System.out.println("---------Song: " + songs[j]);
                                        for (int k = 0; k < songsByUser.size(); k++) {
                                            System.out.println("SongsByUser: " + songsByUser.get(k));
                                            if ((songsByUser.get(k).equals(songs[j]))) {
                                                save = false;
                                            }
                                        }

                                        if (save) {
                                            songsUpdated.add(songs[j]);
                                        }
                                        save = true;
                                    }
                                    System.out.println("songsUpdated: " + songsUpdated);
                                    String songsCorrectFormat = String.join(",", songsUpdated);
                                    System.out.println("Songs correct format: " + songsUpdated);
                                    allSongs = songsCorrectFormat.substring(0);
                                }

                                String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                                preparedStmt.setString(1, allSongs);
                                preparedStmt.setString(2, title);
                                preparedStmt.execute();

                         /*   } catch (SQLException e) {
                            }*/
                        }
                   // }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
    }
}
