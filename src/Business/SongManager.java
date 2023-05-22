package Business;

import Business.Entities.Song;
import Persistance.dao.SongDao;
import Persistance.dao.SongLyricsApi;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SongManager {

    private final SongDao songDao;
    private Clip myClip;
    private String filePath;
    private File file;
    private Song song;
    private String actualSong;
    private String pathComputer;
    private SongLyricsApi songLyricsApi;

    public SongManager(SongDao songDao,SongLyricsApi songLyricsApi) {
        this.songDao = songDao;
        this.songLyricsApi = songLyricsApi;
    }
    public String getPath(String songName){
        return songDao.songPath(songName);
    }

    public void getSong(String songTitle){
        try {
            File music;
            File[] files;
            File file;

            if(myClip != null){
                myClip.close();
            }

            if(songTitle.equals("")){
                music = new File("files/music/");
                files = music.listFiles();
                System.out.println(files);
                assert files != null;
                if(files.length == 0){
                    return;
                }else {
                    actualSong = files[0].getName();
                    String filePath = "files/music/" + files[0].getName();
                    file = new File(filePath);
                }
            }else{
                file = new File(getPath(songTitle));
                actualSong = file.getName();
            }
            assert file != null;
            if (file.exists()) {

                myClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                myClip.open(ais);
                if(!songTitle.equals("")){
                    myClip.start();
                }
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
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.stop();
                stopped = false;
            } else {
                myClip.start();
            }
        }

        return stopped;
    }

    public void loopAudio() {
        if(myClip != null) {
            myClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void moveForward() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        int auxPos = 0;
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.close();
            }
            try {
                File music = new File("files/music/");
                File[] files = music.listFiles();
                assert files != null;
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().equals(actualSong)) {
                        auxPos = i + 1;
                        i = files.length;
                    }
                }
                if (auxPos == files.length) {
                    auxPos = 0;
                }
                String filePath = "files/music/" + files[auxPos].getName();
                actualSong = files[auxPos].getName();
                File file = new File(filePath);

                if (file.exists()) {
                    myClip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);
                    myClip.start();
                } else {
                    throw new RuntimeException("Sound: file not found: " + filePath);
                }
            } catch (LineUnavailableException | UnsupportedAudioFileException | RuntimeException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void moveBackward(){
        int auxPos = 0;
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.close();
            }
            try {
                File music = new File("files/music/");
                File[] files = music.listFiles();
                assert files != null;
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().equals(actualSong)) {
                        auxPos = i - 1;
                        i = files.length;
                    }
                }
                if (auxPos < 0) {
                    auxPos = 0;
                }
                String filePath = "files/music/" + files[auxPos].getName();
                actualSong = files[auxPos].getName();
                File file = new File(filePath);

                if (file.exists()) {
                    myClip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);
                    myClip.start();
                } else {
                    throw new RuntimeException("Sound: file not found: " + filePath);
                }
            } catch (LineUnavailableException | UnsupportedAudioFileException | RuntimeException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loopList() {
        if(myClip != null) {

            System.out.println("loopeo lista");
        }
    }

    public boolean fileSongSelector() {
        JFileChooser fileChooser = new JFileChooser();
        String[] aux;
        int response = fileChooser.showOpenDialog(null);
        boolean fileFormatCorrect = false;

        if(response == JFileChooser.APPROVE_OPTION) {
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            File fileSong = fileChooser.getSelectedFile();
            pathComputer = fileSong.getAbsolutePath();
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

    public String obtainFilePath() {
        return file.getName();
    }

    public boolean isEmpty(String songName, String artist, String album, String genre) {
        boolean emptyField = false;

        if (songName.isEmpty() || artist.isEmpty() || album.isEmpty() || genre.isEmpty()) {
            emptyField = true;
        }
        return emptyField;
    }
    public boolean addSong(String songName, String artist, String album, String genre, String username,String time) {
        boolean songSaved = true;
        // Guardar fitxer
        file.renameTo(new File(filePath));
        // Guardar a base de dades
        song = new Song(songName, genre, album, artist, filePath, username,time);

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

    public ArrayList<String> listSongs(boolean toDelete, String currentUsername) {
        ArrayList<String> information = new ArrayList<>();
        ArrayList<Song> allSongs = songDao.readAllSongsSQL();

        if (!toDelete) {
            for (Song song: allSongs) {
                String songLine = song.getTile() + "-" + song.getArtist() + "-" + song.getGenre() + "-"+ song.getAlbum() + "-" + song.getUsername();
                information.add(songLine);
            }
        } else {
            for (Song song: allSongs) {
                if (song.getUsername().equals(currentUsername)) {
                    String songLine = song.getTile() + "-" + song.getArtist() + "-" + song.getGenre();
                    information.add(songLine);
                }
            }
        }

        return information;
    }

    public ArrayList<String> searchSong(String nameSong){
        ArrayList<String> songSelected = new ArrayList<>();
        ArrayList<Song> listSongs = songDao.readAllSongsSQL();
        boolean in = false;
        String songTitle = "";

        for (Song song1: listSongs){
            if (song1.getTile().equals(nameSong)){
                songSelected.add(song1.getTile());
                songSelected.add(song1.getGenre());
                songSelected.add(song1.getArtist());
                songSelected.add(song1.getAlbum());
                songSelected.add(song1.getUsername());
                songSelected.add(song1.getTime());
                songTitle = song1.getTile();
                in = true;
            }
        }

        if (!in) {
            return null;
        } else {
            getSong(songTitle);
            return songSelected;
        }
    }

    public Map<String, Integer> createGenreMap() {
        Map<String, Integer> genreMap = new HashMap<>();

        try {
            ArrayList<Song> songs = songDao.readAllSongsSQL();

            for (Song song : songs) {
                String genre = song.getGenre();
                if (genreMap.containsKey(genre)) {
                    int count = genreMap.get(genre);
                    genreMap.put(genre, count + 1);
                } else {
                    genreMap.put(genre, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return genreMap;
    }

    public String readLyricApi(String artist, String songName){
        return songLyricsApi.readLyrics(artist,songName);
    }

    public ArrayList<String> searchLyrics(){
        actualSong = actualSong.substring(0, actualSong.lastIndexOf(".wav"));
        ArrayList<String> songActual = searchSong(actualSong);
        String lyrics = readLyricApi(songActual.get(2),songActual.get(0));
        ArrayList<String> lyricsSong = new ArrayList<>();
        lyricsSong.add(0,songActual.get(0));
        lyricsSong.add(1,lyrics);
        return lyricsSong;
    }


    public ArrayList<String> timeSong() {
        ArrayList<String> temps = new ArrayList<>();
        try {
            File file1 = new File(pathComputer);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file1);
            long milisegons = audioInputStream.getFrameLength() * 1000 / (long) audioInputStream.getFormat().getFrameRate();
            long segons = (milisegons / 1000) % 60;
            long minuts = milisegons / (1000 * 60);
            temps.add(0,"true");
            temps.add(1,minuts + ":" + segons);
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException e) {
            temps.add(0,"false");
            temps.add(1,"Unsupported Audio File");
        }
        return temps;
    }
}



