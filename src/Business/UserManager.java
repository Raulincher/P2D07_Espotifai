package Business;

import Business.Entities.User;
import Persistance.dao.UserDao;
import Persistance.dao.UserNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {

    private final UserDao userDao;
    private User user;

    public boolean checkIfPasswordsEqual(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public void Register(String username, String email, String password){
        user = new User(username,email,password);
        userDao.register(user);
    }

    public void setUser(String username, String email, String password) {
        User user = new User(username, email, password);

        this.user = user;
    }

    public User getUser(){
        return user;
    }

    /* public void Register(User user){
        userDao.Register(user);
    }

    */

    public boolean login(ArrayList<String> data){
        User user = new User(data.get(0),data.get(1));

        try {
            userDao.login(user);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    public void logout(){
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        user = null;

    }

    public boolean isEmpty(ArrayList<String> data) { //data = info del view
        boolean empty = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isEmpty()) {
                empty = true;
            }
        }

         return empty;
    }

    public boolean userExistence(String username, String email, String password){
        User user = new User(username, email, password);
        return userDao.userExists(user);
    }

    public void delete() throws UserNotFoundException {
        userDao.delete(user);
    }
}
