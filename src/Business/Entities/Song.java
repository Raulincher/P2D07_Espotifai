package Business.Entities;

public class Song {

    private String title;
    private String genre;
    private String album;
    private String author;
    private String filePath;

    public Song(String title, String genre, String album, String author, String filePath){
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.author = author;
        this.filePath = filePath;
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

    public String getAuthor() {
        return author;
    }

    public String getFilePath() {
        return filePath;
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

    public void setAuthor(String author) {
        this.author = author;
    }
}
