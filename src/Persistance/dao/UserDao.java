package Persistance.dao;

import Business.Entities.User;

public interface UserDao {
    /**
     * Funció que servirà per guardar un user a BBDD
     *
     * @param user, user per guardar
     */
    void register(User user);

    /**
     * Funció que servirà per comprobar que un user estigui ja a BBDD, (només comprobant el username)
     *
     * @param user, user a comprobar
     *
     * @return error, boolean amb la existencia del user dins la base de dades
     */
    boolean userExists(User user);

    /**
     * Funció que servirà per borrar un user de la BBDD
     *
     * @param user, user a esborrar
     *
     */
    void delete(User user) throws UserNotFoundException;

    /**
     * Funció que servirà per comprobar si un user existeix
     *
     * @param user, user per comprobar
     *
     * @return error, boolean amb la existencia del user dins la base de dades
     */
    boolean login(User user) throws UserNotFoundException;
}
