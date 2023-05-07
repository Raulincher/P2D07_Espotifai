package Business.Entities;

public class User {

    private String username;
    private String password;
    private String email;

    public User(String username,String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
