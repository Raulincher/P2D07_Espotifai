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

        Song song2 = new Song("mama", "HeavyMetal", "The black parade", "My chemical romance");
        User user2 = new User("pepe", "1234567", "pepe@gmail.com");

        songDao.SaveSong(song1);
        userDao.register(user1);

        if(userDao.userExists(user1)){
            System.out.println("User 1 exists");
            songDao.SaveSong(song2);
            userDao.register(user2);
        }


        //to delete user
        //userDao.Delete(user1);
    }
}
