import Business.Song;
import Business.User;
import Persistance.dao.SongDao;
import Persistance.dao.UserDao;
import Persistance.dao.sql.DatabaseConnector;
import Persistance.dao.sql.SQLSongDao;
import Persistance.dao.sql.SQLUserDao;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
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




        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        sc = new Scanner(System.in);

        while(true){

            System.out.println("1. pause");
            int c = sc.nextInt();
            if(c == 1){
                break;
            }

        }

        //to delete user
        //userDao.Delete(user1);
        //userDao.Delete(user2);
    }



    public void SimpleAudioPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
