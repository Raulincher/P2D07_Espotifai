package Persistance.dao.sql;

import Business.Song;
import Persistance.dao.SongDao;

import java.sql.*;

public class SQLSongDao implements SongDao {

    private final Connection remoteConnection;
    public SQLSongDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void SaveSong(Song song) {
        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String title = song.getTile();
                String genre = song.getGenre();
                String album = song.getAlbum();
                String author = song.getAuthor();
                String register = "INSERT INTO song (title, genre, album, author) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, title);
                preparedStmt.setString (2, genre);
                preparedStmt.setString (3, album);
                preparedStmt.setString (4, author);

                preparedStmt.execute();
                remoteConnection.close();

            }catch (SQLException e){
                System.err.println("Song already exists");
            }
        } catch (SQLException e) {
            System.err.println("Espotifai not found");
        }
    }
}
