package Persistance.dao.sql;

import Business.Entities.Song;
import Persistance.dao.SongDao;

import java.sql.*;
import java.util.ArrayList;

public class SQLSongDao implements SongDao {

    private final Connection remoteConnection;
    public SQLSongDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

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

    public String songArtist(String songName) {
        String exist = "";

        try {
            Statement statement = remoteConnection.createStatement();
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


    public int songDuration(String songName){

        int duration = 0;
        String time ="";
        System.out.println("entro");
        System.out.println(songName);
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
                System.out.println(duration);
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }


        return duration;
    }
}
