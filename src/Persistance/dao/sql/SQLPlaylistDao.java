package Persistance.dao.sql;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistance.dao.PlaylistDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLPlaylistDao implements PlaylistDao {

    private final Connection remoteConnection;
    public SQLPlaylistDao(Connection remoteConnection){
        this.remoteConnection = remoteConnection;
    }

    public void SavePlaylist(Playlist playlist) {
        try {
            Statement statement = remoteConnection.createStatement();
            statement.executeQuery("USE espotifai");
            try {
                String title = playlist.getTitle();
                String username = playlist.getUsername();
                String register = "INSERT INTO song (title, username) VALUES (?, ?)";
                PreparedStatement preparedStmt = remoteConnection.prepareStatement(register);
                preparedStmt.setString (1, title);
                preparedStmt.setString (2, username);
                preparedStmt.execute();
                remoteConnection.close();

            }catch (SQLException e){
                System.err.println("error in playlist");
            }
        } catch (SQLException e) {
            System.err.println("Espotifai not found");
        }
    }
}
