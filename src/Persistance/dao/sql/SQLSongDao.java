package Persistance.dao.sql;

import Business.Entities.Song;
import Persistance.dao.SongDao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe per poder obtenir, afegir i eliminar valors de la classe Song a la base de dades.
 */
public class SQLSongDao implements SongDao {

    // Preparem els atributs
    private final Connection remoteConnection;

    /**
     * Funció que servirà per com a constructor del SQLSongDao
     *
     * @param remoteConnection, connexió remota amb la BBDD
     */
    public SQLSongDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    /**
     * Funció que servirà per guardar una song a BBDD
     *
     * @param song, song per guardar
     */
    public void saveSong(Song song) throws SQLException {
        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String title = song.getTile();
                String genre = song.getGenre();
                String album = song.getAlbum();
                String author = song.getArtist();
                String filePath = song.getFilePath();
                String username = song.getUsername();
                String time = song.getTime();

                String register = "INSERT INTO song (title, genre, album, author, username, filePath, time) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, title);
                preparedStmt.setString (2, genre);
                preparedStmt.setString (3, album);
                preparedStmt.setString (4, author);
                preparedStmt.setString (5, username);
                preparedStmt.setString(6, filePath);
                preparedStmt.setString(7, time);
                //preparedStmt.setBlob (5, (Blob) songFile);
                preparedStmt.execute();
                //remoteConnection.close();

            } catch (SQLException e) {
                if (e.getSQLState().equals("23505")) {
                    System.err.println("Song already exists");
                } else {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Espotifai not found");
        }
    }


    /**
     * Funció que servirà per obtenir totes les cançons de la BBDD
     *
     * @return songList, arraylist de tipus string amb totes les songs de la BBDD
     */
    public ArrayList<Song> readAllSongsSQL() {
        ArrayList<Song> songList = new ArrayList<>();

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM song");
            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                   // System.out.println(title);
                    String genre = resultSet.getString("genre");
                    //System.out.println(genre);
                    String album = resultSet.getString("album");
                    //System.out.println(album);
                    String artist = resultSet.getString("author");
                    String username = resultSet.getString("username");
                    //System.out.println(author);
                    String filePath = resultSet.getString("filePath");
                    String time = resultSet.getString("time");
                    //System.out.println(filePath);
                    Song song = new Song(title, genre, album, artist, filePath, username,time);
                    //System.out.println("******");
                    songList.add(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return songList;
    }

    /**
     * Funció que servirà per comprobar que la cançó estigui ja a BBDD
     *
     * @param songName, nom de la song
     *
     * @return exist, boolean amb la existencia de la cançó dins la base de dades
     */
    public boolean songInDatabase(String songName) {
        boolean exist = false;

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM song");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");

                    if (songName.equals(title)) {
                        exist = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return exist;
    }

    /**
     * Funció que servirà per agafar el path relatiu d'un arxiu d'audio
     *
     * @param songName, nom de la song
     *
     * @return exist, String amb el path de la cançó, si no existeis retornará un ""
     */
    public String songPath(String songName) {
        String exist = "";

        try {
            Statement statement = remoteConnection.createStatement();
            String queryExists = "SELECT filePath FROM song WHERE title = ?";
            PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
            prepared.setString (1, songName);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                exist = rs.getString("filePath");
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return exist;
    }


    /**
     * Funció per buscar l'artista d'una cançó determinada
     *
     * @param songName, String amb el nom d ela cançó a buscar
     * @return String amb el nom de l'artista
     */
    public String songArtist(String songName) {
        String exist = "";

        try {
            remoteConnection.createStatement();
            String queryExists = "SELECT author FROM song WHERE title = ?";
            PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
            prepared.setString (1, songName);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                exist = rs.getString("author");
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return exist;
    }

    /**
     * Funció que servirà per borrar una cançó de la BBDD
     *
     * @param songName, nom de la song
     *
     * @return exist, String amb el path de la cançó, si s'ha esborrat retornara un ""
     */
    public String deleteSong(String songName) {
        String filePath = null;

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM song");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");

                    if (songName.equals(title)) {
                        //Guardem el filePath per a poder borrar el fitxer local
                        filePath = resultSet.getString("filePath");

                        try {
                            String deleteQuery = "DELETE FROM song WHERE title = ?";
                            PreparedStatement preparedStatement = remoteConnection.prepareStatement(deleteQuery);
                            preparedStatement.setString(1, songName);
                            int affectedRows = preparedStatement.executeUpdate();
                            if (affectedRows == 0) {
                                System.out.println("Song not found in the database");
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
        return filePath;
    }

    /**
     * Funció que servirà per agafar la duració d'una cançó en format int
     *
     * @param songName, nom de la song
     *
     * @return duration, int amb el temps total de cançó
     */
    public int songDuration(String songName){

        int duration = 0;
        String time ="";
        songName = "files/music/" + songName;

        try {
            String queryExists = "SELECT * FROM song WHERE filePath = ?";
            PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
            prepared.setString (1, songName);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                time = rs.getString("time");
                String[] timeSplit = time.split(":");
                int minutes = Integer.parseInt(timeSplit[0]);
                int seconds = Integer.parseInt(timeSplit[1]);
                duration = (minutes * 60) + seconds;
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return duration;
    }

    /**
     * Funció que servirà per agafar la duració d'una cançó en format String
     *
     * @param songName, nom de la song
     *
     * @return time, string amb el temps total de cançó
     */
    public String songDurationInString(String songName){

        String time ="";
        songName = "files/music/" + songName;

        try {
            String queryExists = "SELECT * FROM song WHERE filePath = ?";
            PreparedStatement prepared = remoteConnection.prepareStatement(queryExists);
            prepared.setString (1, songName);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                time = rs.getString("time");
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }

        return time;
    }

    /**
     * Funció que servirà per agafar les cançons d'un user en concret
     *
     * @param userName, nom d'usuari
     *
     * @return songsByUser, arraylist d'Strings amb el title de les cançons
     */
    public ArrayList<String> filterSongsByUser(String userName) {
        ArrayList<String> songsByUser = new ArrayList<>();

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM song");
            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String username = resultSet.getString("username");

                    // Si son del user, les afegeixo al array
                    if (userName.equals(username)) {
                        songsByUser.add(resultSet.getString("title"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
            songsByUser = null;
        }

        return songsByUser;
    }

    /**
     * Funció que servirà per poder borrar totes les songs d'un user
     *
     * @param songNames, nom de les cançons
     *
     * @return filePaths, arraylist d'Strings amb el path de les cançons esborrades
     */
    //Retorna un arraylist dels paths i borrem les cançons de la Base de Dades
    public ArrayList<String> deleteSongsByUsername(ArrayList<String> songNames) {
        ArrayList<String> filePaths = new ArrayList<>();

        try {
            Statement statement = remoteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM song");

            if (!resultSet.next()) {
                System.out.println("Base de dades buida");
            } else {
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");

                    for (int i = 0; i < songNames.size(); i++) {
                        if (songNames.get(i).equals(title)) {
                            //Guardem el filePath per a poder borrar el fitxer local
                            filePaths.add(resultSet.getString("filePath"));

                            try {
                                String deleteQuery = "DELETE FROM song WHERE title = ?";
                                PreparedStatement preparedStatement = remoteConnection.prepareStatement(deleteQuery);
                                preparedStatement.setString(1, songNames.get(i));
                                int affectedRows = preparedStatement.executeUpdate();
                                if (affectedRows == 0) {
                                    System.out.println("Song not found in the database");
                                }
                            } catch (SQLException e) {
                                System.out.println("Error eliminating the song");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
            filePaths = null;
        }
        return filePaths;
    }

}
