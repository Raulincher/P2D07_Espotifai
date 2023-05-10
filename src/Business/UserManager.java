package Business;

import Business.Entities.User;
import Persistance.dao.UserDao;
import Persistance.dao.UserNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class UserManager {

    private final UserDao userDao;
    private User user;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    public boolean checkIfPasswordsEqual(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }


    public boolean errorInMail(String email){
        boolean error = false;
        Pattern patt1 = Pattern.compile(emailRegex);

        if(!patt1.matcher(email).matches()){
            error = true;
        }

        return error;
    }

    public boolean errorInPassword(String password){
        boolean error = false;
        Pattern patt2 = Pattern.compile(passwordRegex);

        if(!patt2.matcher(password).matches()){
            error = true;
        }

        return error;
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

    public boolean login(ArrayList<String> data) throws UserNotFoundException {
        User user = new User(data.get(0),data.get(1));
        System.out.println("pepe");
        System.out.println(data.get(0));
        System.out.println(data.get(1));

        return userDao.login(user);
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

    public String currentUsername() {
        return user.getUsername();
    }
}
