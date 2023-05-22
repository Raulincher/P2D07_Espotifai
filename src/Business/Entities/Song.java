package Business.Entities;

public class Song {

    private String title;
    private String genre;
    private String album;
    private String artist;
    private String filePath;
    private String username;
    private String time;

    public Song(String title, String genre, String album, String artist, String filePath, String username, String time){
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.filePath = filePath;
        this.username = username;
        this.time = time;
    }

    public String getTile() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
