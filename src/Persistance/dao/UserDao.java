package Persistance.dao;

import Business.Entities.User;

public interface UserDao {
    void Register(User user);
    boolean userExists(User user);
    void Delete(User user);
    boolean Login(User user);
}
