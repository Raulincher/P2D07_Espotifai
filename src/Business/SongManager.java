package Business;

import Persistance.dao.SongDao;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class SongManager {

    private final SongDao songDao;
    private Clip myClip;

    public SongManager(SongDao songDao) {
        this.songDao = songDao;
    }

    public void getSong(){
        try {
            String filePath = "files/music/prueba.wav";

            File file = new File(filePath);

            if (file.exists()) {
                myClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                myClip.open(ais);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + filePath);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }
    }

    public boolean simpleAudioPlayer()
    {
        boolean stopped = true;
        if(myClip.isRunning()){
            myClip.stop();
            stopped = false;
        }else{
            myClip.start();
        }

        return stopped;
    }

    public void loopAudio(){
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
        System.out.println("loop on");
    }

    public void moveForward(){
        System.out.println("1 cancion delante");
    }

    public void moveBackward(){

        System.out.println("1 cancion atras");

    }

    public void loopList(){

        System.out.println("loopeo lista");
    }

}
