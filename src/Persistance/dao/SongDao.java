package Persistance.dao;

import Business.Entities.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDao {
    /**
     * Funció que servirà per guardar una song a BBDD
     *
     * @param song, song per guardar
     */
    void saveSong(Song song) throws SQLException;

    /**
     * Funció que servirà per obtenir totes les cançons de la BBDD
     *
     * @return songList, arraylist de tipus string amb totes les songs de la BBDD
     */
    ArrayList<Song> readAllSongsSQL();

    /**
     * Funció que servirà per comprobar que la cançó estigui ja a BBDD
     *
     * @param songName, nom de la song
     *
     * @return exist, boolean amb la existencia de la cançó dins la base de dades
     */
    boolean songInDatabase(String songName);

    /**
     * Funció que servirà per borrar una cançó de la BBDD
     *
     * @param name, nom de la song
     *
     * @return exist, String amb el path de la cançó, si s'ha esborrat retornara un ""
     */
    String deleteSong(String name);

    /**
     * Funció que servirà per agafar el path relatiu d'un arxiu d'audio
     *
     * @param songName, nom de la song
     *
     * @return exist, String amb el path de la cançó, si no existeis retornará un ""
     */
    String songPath(String songName);

    /**
     * Funció per buscar l'artista d'una cançó determinada
     *
     * @param songName, String amb el nom d ela cançó a buscar
     * @return String amb el nom de l'artista
     */
    String songArtist(String songName);

    /**
     * Funció que servirà per agafar la duració d'una cançó en format int
     *
     * @param songName, nom de la song
     *
     * @return duration, int amb el temps total de cançó
     */
    int songDuration(String songName);

    /**
     * Funció que servirà per agafar la duració d'una cançó en format String
     *
     * @param songName, nom de la song
     *
     * @return time, string amb el temps total de cançó
     */
    String songDurationInString(String songName);

    /**
     * Funció que servirà per agafar les cançons d'un user en concret
     *
     * @param userName, nom d'usuari
     *
     * @return songsByUser, arraylist d'Strings amb el title de les cançons
     */
    ArrayList<String> filterSongsByUser(String userName);

    /**
     * Funció que servirà per poder borrar totes les songs d'un user
     *
     * @param songsByUser, nom de les cançons
     *
     * @return filePaths, arraylist d'Strings amb el path de les cançons esborrades
     */
    ArrayList<String> deleteSongsByUsername(ArrayList<String> songsByUser);
}
