package Business;

import Business.Entities.User;
import Persistance.dao.UserDao;

public class UserManager {

    private final UserDao userDao;

    User user = new User("pepe", "prueba1234", "pepe@gmail.com");



    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public void Register(User user){
        userDao.Register(user);
    }

    public boolean Login(User user){
        return userDao.Login(user);
    }

    public boolean UserExistence(){
        return userDao.userExists(user);
    }

    public void Delete(){
        userDao.Delete(user);
    }
}
