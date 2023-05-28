package Business;

import Business.Entities.User;
import Persistance.dao.UserDao;
import Persistance.dao.UserNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * Classe que ens permet controlar la classe User
 */
public class UserManager {

    // Preparem atributs
    private final UserDao userDao;
    private User user;
    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    /**
     * Funció que servirà per com a constructor del SongManager
     *
     * @param userDao, interface que comunica la clase que parla amb la base de dades, així podrem gestionar les dades dels usuaris
     *
     * no return
     */
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * Funció que servirà per comprobar si les contrasenyes introduides son iguals
     *
     * @param password, string amb la password introduida
     * @param repeatedPassword, string amb la repeticio de la password introduida
     *
     * @return boolean que envia la flag amb la igualtat
     */
    public boolean checkIfPasswordsEqual(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    /**
     * Funció que servirà per comprobar si hi ha errors a l'email
     *
     * @param email, string amb l'email introduit
     *
     * @return error, boolean amb la comprobació del mail
     */
    public boolean errorInMail(String email){
        boolean error = false;
        Pattern patt1 = Pattern.compile(emailRegex);

        if(!patt1.matcher(email).matches()){
            error = true;
        }

        return error;
    }

    /**
     * Funció que servirà per comprobar si hi ha errors a la contrasenya
     *
     * @param password, string amb la contrasenya
     *
     * @return error, boolean amb la comprobació de la contrasenya
     */
    public boolean errorInPassword(String password){
        boolean error = false;
        Pattern patt2 = Pattern.compile(passwordRegex);

        if(!patt2.matcher(password).matches()){
            error = true;
        }

        return error;
    }

    /**
     * Funció que servirà per fer el registre de l'usuari introduit
     *
     * @param username, string amb l'username
     * @param email, string amb l'email
     * @param password, string amb la contrasenya
     *
     * no return
     */
    public void register(String username, String email, String password){
        user = new User(username,email,password);
        userDao.register(user);
    }

    /**
     * Funció que servirà per guardar l'usuari a ram quan entri al programa
     *
     * @param username, string amb l'username
     * @param email, string amb l'email
     * @param password, string amb la contrasenya
     *
     * no return
     */
    public void setUser(String username, String email, String password) {
        User user = new User(username, email, password);
        this.user = user;
    }


    /**
     * Funció que servirà per agafar l'usuari guardat a ram
     *
     * @return user, usuari guardat a ram
     *
     * no param
     */
    public User getUser(){
        return user;
    }


    /**
     * Funció que servirà per poder fer log in de l'usuari
     *
     * @param data, arraylist de tipus string amb totes les dades d'un user
     *
     * @return boolean amb el resultat de log in
     */
    public boolean login(ArrayList<String> data) throws UserNotFoundException {
        User user = new User(data.get(0),data.get(0), data.get(1));

        return userDao.login(user);
    }

    /**
     * Funció que servirà per poder fer log out de l'usuari (borrar l'user de ram)
     *
     * no param ni return
     */
    public void logout(){

        user = null;
    }

    /**
     * Funció que servirà per poder comprobar que els camps no siguin empty
     *
     * @param data, arraylist de tipus string amb totes les dades d'un user
     *
     * @return boolean amb el resultat de la comprobació
     */
    public boolean isEmpty(ArrayList<String> data) { //data = info del view
        boolean empty = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isEmpty()) {
                empty = true;
            }
        }

         return empty;
    }

    /**
     * Funció que servirà per comprobar si un user existeix a la BBDD
     *
     * @param username, string amb l'username
     * @param email, string amb l'email
     * @param password, string amb la contrasenya
     *
     * @return boolean amb el resultat de la comprobació
     */
    public boolean userExistence(String username, String email, String password){
        User user = new User(username, email, password);
        return userDao.userExists(user);
    }

    /**
     * Funció que servirà per poder fer un delete del user a la BBDD
     *
     * no param ni return
     */
    public void delete() throws UserNotFoundException {
        userDao.delete(user);
    }
    /**
     * Funció que servirà per poder agafar el nom d'usuari del use ractual
     *
     * @return string amb el nom d'usuari
     *
     * no param
     */
    public String currentUsername() {
        if (user == null) {
            return "";
        } else {
            return user.getUsername();
        }
    }
}
