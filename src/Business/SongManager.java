package Business;

import Business.Entities.Song;
import Persistance.dao.SongDao;
import Presentation.Controller.GeneralSongListViewController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class SongManager {

    private final SongDao songDao;
    private Clip myClip;
    private String filePath;
    private File file;
    private Song song;
   // private Song songSelected;

    public SongManager(SongDao songDao) {
        this.songDao = songDao;
    }

    public void getSong(){
        try {
            File music = new File("files/music/");
            File[] files = music.listFiles();
            assert files != null;
            String filePath = "files/music/" + files[0].getName();

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

    public boolean simpleAudioPlayer() {
        boolean stopped = true;
        if(myClip.isRunning()){
            myClip.stop();
            stopped = false;
        }else{
            myClip.start();
        }

        return stopped;
    }

    public void loopAudio() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
        System.out.println("loop on");
    }

    public void moveForward() {
        System.out.println("1 cancion delante");
    }

    public void moveBackward() {
        System.out.println("1 cancion atras");
    }

    public void loopList() {
        System.out.println("loopeo lista");
    }

    public boolean fileSongSelector() {
        JFileChooser fileChooser = new JFileChooser();
        String[] aux;
        int response = fileChooser.showOpenDialog(null);
        boolean fileFormatCorrect = false;

        if(response == JFileChooser.APPROVE_OPTION) {
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            String fileName = file.getName();
            aux = fileName.split("\\.");

            if(aux[1].equals("wav")){
                //file.renameTo(new File("files/music/" + fileName));
                filePath = "files/music/" + fileName;
                //errorInUpload = fileName;
                fileFormatCorrect = true;
            }else{
                //error;
                System.out.println("error, solo .wav");
            }
        }

        return fileFormatCorrect;
    }

    public boolean isEmpty(String songName, String artist, String album, String genre) {
        boolean emptyField = false;

        if (songName.isEmpty() || artist.isEmpty() || album.isEmpty() || genre.isEmpty()) {
            emptyField = true;
        }
        return emptyField;
    }
    public boolean addSong(String songName, String artist, String album, String genre, String username) {
        boolean songSaved = true;
        // Guardar fitxer
        file.renameTo(new File(filePath));
        // Guardar a base de dades
        song = new Song(songName, artist, album, genre, filePath, username);

        try {
            songDao.saveSong(song);
            songSaved = true;
        } catch (SQLException e) {
            songSaved = false;
        }

        return songSaved;
    }


    public boolean songExists(String songName) {
        // Si troba la cançó EN EL DAO
        if (songDao.songInDatabase(songName) ) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteSong(String name) {
        boolean deletedOk = false;

        // Si troba la cançó EN EL DAO
        String filePath = songDao.deleteSong(name);
        file = new File(filePath);
        if (file.delete()) {
            deletedOk = true;
        }
        return deletedOk;
    }

    public ArrayList<Song> listSongs () {
        return songDao.readAllSongsSQL();
    }

}