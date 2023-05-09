package Persistance.dao;

import Business.Entities.Song;

import java.sql.SQLException;

public interface SongDao {

    void saveSong(Song song) throws SQLException;
}
