package Business.Entities;

public class Playlist {


    private String username;
    private String title;

    public Playlist(String username, String title){
        this.username = username;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

}
