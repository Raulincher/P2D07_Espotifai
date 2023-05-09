package Persistance.dao.sql;

import Business.Entities.Song;
import Persistance.dao.SongDao;

import java.sql.*;

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

}
