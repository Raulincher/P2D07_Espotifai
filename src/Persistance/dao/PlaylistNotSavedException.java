package Persistance.dao;

/**
 * Exception que salta quan la playlist no es guarda bé
 */
public class PlaylistNotSavedException extends Exception{

        public PlaylistNotSavedException (String message) {
            super(message);
        }

}
