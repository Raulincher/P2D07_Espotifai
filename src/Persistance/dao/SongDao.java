package Persistance.dao;

import Business.Entities.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDao {

    void saveSong(Song song) throws SQLException;
    ArrayList<Song> readAllSongsSQL();
}
