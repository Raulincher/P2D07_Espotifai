import Business.Entities.Song;
import Business.Entities.User;
import Business.PlaylistManager;
import Business.SongManager;
import Business.UserManager;
import Persistance.dao.PlaylistDao;
import Persistance.dao.SongDao;
import Persistance.dao.UserDao;
import Persistance.dao.sql.DatabaseConnector;
import Persistance.dao.sql.SQLPlaylistDao;
import Persistance.dao.sql.SQLSongDao;
import Persistance.dao.sql.SQLUserDao;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class Main {


    private static String filePath = "src/Media/mama.wav";
    private static AudioInputStream audioInputStream;
    private static Clip clip;
    private static Scanner sc;
    // to store current position
    Long currentFrame;

    // current status of clip
    String status;



    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        Connection remoteConnection = DatabaseConnector.getInstance();
        UserDao userDao = new SQLUserDao(remoteConnection);
        SongDao songDao = new SQLSongDao(remoteConnection);
        PlaylistDao playlistDao = new SQLPlaylistDao(remoteConnection);

        UserManager userManager = new UserManager(userDao);
        SongManager songManager = new SongManager(songDao);
        PlaylistManager playlistManager = new PlaylistManager(playlistDao);


        Song song1 = new Song("the stage", "HeavyMetal", "The stage", "Avenged Sevenfold");
        User user1 = new User("raulincher", "hola123", "raulincher@gmail.com");

        Song song2 = new Song("mama", "HeavyMetal", "The black parade", "My chemical romance");
        User user2 = new User("pepe", "1234567", "pepe@gmail.com");


        //to delete user
        //userDao.Delete(user1);
        //userDao.Delete(user2);
    }
}
