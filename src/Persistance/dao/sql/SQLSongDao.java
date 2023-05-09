package Persistance.dao.sql;

import Business.Entities.Song;
import Business.Entities.User;
import Persistance.dao.SongDao;
import Persistance.dao.UserNotFoundException;

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
                String author = song.getAuthor();
                String filePath = song.getFilePath();

                String register = "INSERT INTO song (title, genre, album, author, filePath) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, title);
                preparedStmt.setString (2, genre);
                preparedStmt.setString (3, album);
                preparedStmt.setString (4, author);
                preparedStmt.setString (5, filePath);
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
                    String author = resultSet.getString("author");
                    //System.out.println(author);
                    String filePath = resultSet.getString("filePath");
                    //System.out.println(filePath);
                    Song song = new Song(title, genre, album, author, filePath);
                    //System.out.println("******");
                    songList.add(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error llegint les cançons de la base de dades: " + e.getMessage());
        }
        return songList;
    }

}
