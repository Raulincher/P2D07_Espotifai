package Persistance.dao;

import Business.Entities.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDao {

    void saveSong(Song song) throws SQLException;
    ArrayList<Song> readAllSongsSQL();

    boolean songInDatabase(String songName);

    String deleteSong(String name);

    String songPath(String songName);

}
