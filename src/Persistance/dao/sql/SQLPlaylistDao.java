package Persistance.dao.sql;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistance.dao.PlaylistDao;
import Persistance.dao.PlaylistNotSavedException;
import Persistance.dao.SongDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que implementa els mètodes per communicar-se amb la base de dades de la playlist
 */
public class SQLPlaylistDao implements PlaylistDao {

    // Preparem atributs
    private final Connection remoteConnection;

    /**
     * Funció que servirà per com a constructor del SQLPlaylistDao
     *
     * @param remoteConnection, connexió remota amb la BBDD
     */
    public SQLPlaylistDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    /**
     * Funció que servirà per guardar una playlist a BBDD
     *
     * @param playlist, playlist per guardar
     */
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

    /**
     * Funció que servirà per obtenir totes les playlist de la BBDD
     *
     * @return playlists, arraylist de tipus string amb totes les playlist de la BBDD
     */
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

    /**
     * Funció que servirà per obtenir totes les songs d'una playlist de la BBDD
     *
     * @param playlistName, nom de la playlist
     *
     * @return songsInPlaylist, arraylist de tipus string amb totes les cançons d'una playlist
     */
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

    /**
     * Funció que servirà per buscar una cançó a una playlist
     *
     * @param songName, nom de la cançó
     * @param playlist, nom de la playlist
     *
     * @return inPlaylist, boolean que ens indica si esta o no a la playlist
     */
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

    /**
     * Funció que servirà per afegir una cançó a una playlist
     *
     * @param songName, nom de la cançó
     * @param playlist, nom de la playlist
     *
     * @return saved, boolean que ens indica si s'ha pogut guardar
     */
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

    /**
     * Funció que servirà per esborrar una playlist
     *
     * @param playlistName, nom de la playlist
     *
     * @return deleted, boolean que ens indica si s'ha pogut esborrar
     */
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

    /**
     * Funció que servirà per esborrar una cançó de totes les playlist
     *
     * @param songTitle, nom de la cançó
     *
     * @return success, boolean que ens indica si s'ha pogut esborrar
     */
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

    /**
     * Funció que servirà per esborrar una cançó d'una playlist
     *
     * @param playlistName, nom de la playlist
     * @param songTitle, nom de la cançó
     *
     * @return success, boolean que ens indica si s'ha pogut esborrar
     */
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
    /**
     * Funció que servirà per esborrar una cançó pujada per un user de totes les playlist. Primer mirem si la playlist
     * es de l'usuari, si ho és la borrem sencera, i si no ho és mirem cançó a canço quines s'han de treure.
     * Per a trobar les cançons, fem un bucle que llegeix una playlist, d'aquella playlist llegim totes les songs,
     * i comparem cada una de les songs de la playlist amb cada cançó l'ArrayList<String> songsByUser.
     * Fem aquest procés amb cada una de les playlists que no siguin de l'usuari.
     *
     * @param songsByUser, totes les cançons d'un user
     * @param username, nom de l'usuari
     *
     */
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
                        if (allSongs != null) {
                           String[] songs = allSongs.split(",");
                           ArrayList<String> songsUpdated = new ArrayList<>();

                           for (int j = 0; j < songs.length; j++) {
                               for (int k = 0; k < songsByUser.size(); k++) {
                                   if ((songsByUser.get(k).equals(songs[j]))) {
                                       save = false;
                                   }
                               }
                               if (save) {
                                   songsUpdated.add(songs[j]);
                               }
                               save = true;
                           }
                           String songsCorrectFormat = String.join(",", songsUpdated);
                           allSongs = songsCorrectFormat.substring(0);
                        }

                        String register = "UPDATE playlist SET songs = ? WHERE title = ?";
                        PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                        preparedStmt.setString(1, allSongs);
                        preparedStmt.setString(2, title);
                        preparedStmt.execute();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
    }
}
