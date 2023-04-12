package Business;

import Persistance.dao.SongDao;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SongManager {

    private final SongDao songDao;


    public SongManager(SongDao songDao) {
        this.songDao = songDao;
    }


    public void SimpleAudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        Clip clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



}
