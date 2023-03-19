import Business.Song;
import Business.User;
import Persistance.dao.SongDao;
import Persistance.dao.UserDao;
import Persistance.dao.sql.DatabaseConnector;
import Persistance.dao.sql.SQLSongDao;
import Persistance.dao.sql.SQLUserDao;

import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);

        Song song1 = new Song("the stage", "HeavyMetal", "The stage", "Avenged Sevenfold");
        User user1 = new User("raulincher", "hola123", "raulincher@gmail.com");


        songDao.SaveSong(song1);
        userDao.register(user1);
        if(userDao.userExists(user1)){
            System.out.println("Users exists");
        }


        //to delete user
        //userDao.Delete(user1);
    }
}
