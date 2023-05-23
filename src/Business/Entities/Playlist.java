package Business.Entities;

import java.util.ArrayList;

public class Playlist {

    // Preparem els atributs
    private String username;
    private String title;
    private ArrayList<String> songs;


    /**
     * Funció que servirà com a constructor de la Playlist
     *
     * @param username, nom de l'usuari que crea la Playlist
     * @param title, títol de la Playlist
     */
    public Playlist(String username, String title, ArrayList<String> songs){
        this.username = username;
        this.title = title;
        this.songs = songs;
    }

    /**
     * Funció que servirà com a getter per retornar el username
     *
     * @return username, nom d'usuari en qüestió
     */
    public String getUsername() {
        return username;
    }

    /**
     * Funció que servirà com a getter per retornar el títol de la Playlist
     *
     * @return title, nom de la Playlist en qüestió
     */
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }
}
