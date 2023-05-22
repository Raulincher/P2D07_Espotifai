package Business.Entities;

public class Song {

    // Preparem els atributs
    private String title;
    private String genre;
    private String album;
    private String artist;
    private String filePath;
    private String username;
    private String time;

    /**
     * Funció que servirà com a constructor de la Song
     *
     * @param title, títol de la song
     * @param genre, gènere de la song
     * @param album, album de la song
     * @param filePath, path on es guarda la song
     * @param username, usuari que ha pujat la song
     * @param time, temps que dura la song
     */
    public Song(String title, String genre, String album, String artist, String filePath, String username, String time){
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.filePath = filePath;
        this.username = username;
        this.time = time;
    }

    /**
     * Funció que servirà com a getter per retornar el títol de la song
     *
     * @return title, nom de la song
     */
    public String getTile() {
        return title;
    }

    /**
     * Funció que servirà com a getter per retornar gènere de la song
     *
     * @return genre, gènere en qüestió
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Funció que servirà com a getter per retornar l'àlbum de la song
     *
     * @return username, nom d'usuari en qüestió
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Funció que servirà com a getter per retornar l'artist de la song
     *
     * @return artist, artista de la song en qüestió
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Funció que servirà com a getter per retornar la path
     * on es guarda la song
     *
     * @return filePath, path en qüestió
     */
    public String getFilePath() {
        return filePath;
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
     * Funció que servirà com a getter per retornar el temps de
     * la song
     *
     * @return time, temps en qüestió
     */
    public String getTime() {
        return time;
    }
}
