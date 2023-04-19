package Business;

import Business.Entities.User;
import Persistance.dao.UserDao;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    User user = new User("pepe", "prueba1234", "pepe@gmail.com");

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public void Register(User user){
        userDao.Register(user);
    }

    public boolean Login(ArrayList<String> data){
        User user = new User(data.get(0),data.get(1));
        return userDao.Login(user);
    }

    public boolean IsEmpty(ArrayList<String> data) { //data = info del view
        boolean empty = false;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isEmpty()) {
                empty = true;
            }
        }

         return empty;
    }


    public boolean UserExistence(){
        return userDao.userExists(user);
    }

    public void Delete(){
        userDao.Delete(user);
    }
}
