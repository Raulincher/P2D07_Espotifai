package Persistance.dao;

import Business.User;

public interface UserDao {
    void register(User user);
    int userExists(User user);
    void Delete(User user);
    int Login(User user);
}
