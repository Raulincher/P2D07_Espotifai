package Persistance.dao;

/**
 * Exception que salta quan la el user que busquem no existeix a la base de dades
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException (String message) {
        super(message);
    }
}
