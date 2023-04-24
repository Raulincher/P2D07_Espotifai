package Persistance.dao;

import Business.Entities.User;

public interface UserDao {
    void register(User user);
    boolean userExists(User user);
    void delete(User user);
    boolean login(User user);
}
