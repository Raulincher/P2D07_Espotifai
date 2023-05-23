package Business.Entities;

public class User {

    // Preparem els atributs
    private String username;
    private String password;
    private String email;

    /**
     * Funció que servirà com a constructor de l'usuari
     *
     * @param username, nom de l'usuari
     * @param email, correu de l'usuari
     * @param password, contrasenya de l'usuari
     */
    public User(String username,String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
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
     * Funció que servirà com a getter per retornar la
     * password de l'usuari
     *
     * @return password , password de l'usuari en qüestió
     */
    public String getPassword() {
        return password;
    }

    /**
     * Funció que servirà com a getter per retornar el email
     * de l'usuari
     *
     * @return email, email de l'usuari en qüestió
     */
    public String getEmail() {
        return email;
    }
}
