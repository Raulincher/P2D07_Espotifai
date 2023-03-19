package Business;

public class Song {

    private String title;
    private String genre;
    private String album;
    private String author;

    public Song(String title, String genre, String album, String author){
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.author = author;
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
