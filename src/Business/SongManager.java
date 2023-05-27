package Business;

import Business.Entities.Song;
import Persistance.dao.SongDao;
import Persistance.dao.SongLyricsApi;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private String thatSong;
    private String pathComputer;
    private SongLyricsApi songLyricsApi;
    private boolean loopCondition;
    private String actualPlaylist;
    private String nextSongInPlaylist;

    public SongManager(SongDao songDao,SongLyricsApi songLyricsApi) {
        this.songDao = songDao;
        this.songLyricsApi = songLyricsApi;
    }
    public String getPath(String songName){
        return songDao.songPath(songName);
    }

    public int getSongDurationFromDatabase(String songName){return songDao.songDuration(songName);}

    public String songDurationInString(String songName){
        System.out.println(songName + " in managaer");
        return songDao.songDurationInString(songName);
    }

    public int checkMyClip(){
        return myClip.getFramePosition();
    }

    public int clipDuration(){
        int duration = 0;

        System.out.println(actualSong + "clip duration");
        if(actualSong != null){
            duration = getSongDurationFromDatabase(actualSong);
        }
        return duration;
    }

    public String getActualSong() {
        return thatSong;
    }

    public void setActualSong(String song) {
        thatSong = song;
    }

    public void getSong(String songTitle, String playlistName, String nextSong){
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
                assert files != null;
                if(files.length == 0){
                    return;
                }else {
                    actualSong = files[0].getName();
                    setActualSong(actualSong);
                    String filePath = "files/music/" + files[0].getName();
                    file = new File(filePath);
                }
            }else{
                file = new File(getPath(songTitle));
                actualSong = file.getName();
                thatSong = actualSong;
                System.out.println(actualSong);

            }
            if (file.exists()) {
                myClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                myClip.open(ais);

                myClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        if(!loopCondition) {
                            if(playlistName != null){
                                moveForward(playlistName, nextSong);
                            }else{
                                moveForward(null, null);
                            }
                        }
                    }
                });
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

    public boolean simpleAudioPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        boolean stopped = true;
        if(myClip != null) {
            System.out.println("no es nulo");
            if(myClip.isOpen()){
                System.out.println("abierto");

                if (myClip.isRunning()) {
                    myClip.stop();
                    stopped = false;
                } else {
                    myClip.start();
                }
            }else{
                System.out.println("cerrado");
                System.out.println(actualSong);
                String filePath = "files/music/" + actualSong;
                System.out.println(filePath);
                file = new File(filePath);

                if (file.exists()) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);
                    myClip.start();
                }
            }
        }
        return stopped;
    }

    public boolean anyClipRunning(){
        boolean run = false;
        if(myClip != null){
            run = myClip.isRunning();
        }
        return run;
    }

    public boolean isPlaying(String songDelete){
        return !(myClip.isRunning() & actualSong.equals(songDelete));
    }

    public void stopClip() {
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.stop();
                myClip.close();
            }
        }
    }

    public void loopAudio() {
        if(myClip != null) {
            loopCondition = true;
            myClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void moveForward(String playlistName, String nextSong){
        int auxPos = 0;
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.close();
            }
            if(playlistName == null){
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
                    setActualSong(actualSong);
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
            }else{
                try {
                    String songPath = getPath(nextSong);
                    System.out.println("song path in manager" + songPath);
                    String[] split = songPath.split("/");
                    actualSong = split[2];
                    thatSong = split[2];
                    System.out.println(actualSong + "actual song in manager");

                    File file = new File(songPath);

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

    }

    public void moveBackward(String playlistName, String previousSong){
        int auxPos = 0;
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.close();
            }
            if(playlistName == null) {
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
                    setActualSong(actualSong);
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
            }else{
                try {
                    String songPath = getPath(previousSong);
                    String[] split = songPath.split("/");
                    actualSong = split[2];
                    thatSong = split[2];

                    File file = new File(songPath);

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
    }

    public void loopList() {
        if(myClip != null) {

            System.out.println("loopeo lista");
        }
    }

    public boolean fileSongSelector() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("wav audios", "wav"));
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
                filePath = "files/music/" + fileName;
                fileFormatCorrect = true;
            }
        }

        return fileFormatCorrect;
    }

    public String obtainFilePath() {
        return file.getName();
    }

    public void endSong(){
        if(myClip != null){
            myClip.close();
        }
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
            getSong(songTitle, null, null);
            return songSelected;
        }
    }

    /**
     * Funció per a crear un mapa String-Integer que retornarà
     * tots els gèneres i el seu valor màxim agregats
     *
     * @return genreMap, Mapa amb els valors necessaris per les statistics
     */
    public Map<String, Integer> createGenreMap() {
        Map<String, Integer> genreMap = new HashMap<>();

        try {
            // Llegim les cançons
            ArrayList<Song> songs = songDao.readAllSongsSQL();

            // Obrim un bucle a través de l'Array
            for (Song song : songs) {
                String genre = song.getGenre();

                // Un cop agafat el nom ens assegurem que no el tinguem ja agregat al mapa
                if (genreMap.containsKey(genre)) {
                    // Si ja el tenim sumem 1 al valor del gènere
                    int count = genreMap.get(genre);
                    genreMap.put(genre, count + 1);
                }
                // En cas contrari l'afegim al Mapa
                else {
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
        String name = this.actualSong.substring(0, actualSong.lastIndexOf(".wav"));
        String artist = songDao.songArtist(name);
        String lyrics = readLyricApi(artist,name);
        ArrayList<String> lyricsSong = new ArrayList<>();
        lyricsSong.add(name);
        lyricsSong.add(lyrics);
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


    //Borrem la cançó de la base de dades i retornem l'array de cançons per treure-les de les playlists
    public ArrayList<String> obtainSongsByUser(String userName) {
        return songDao.filterSongsByUser(userName);
    }

    public ArrayList<String> deleteSongsByUsername(ArrayList<String> songsByUser) {
        return songDao.deleteSongsByUsername(songsByUser);
    }

    public void deletePaths(ArrayList<String> pathsToDelete) {
        int i = 0;

        while(i < pathsToDelete.size()) {
            file = new File(pathsToDelete.get(i));
            file.delete();

            i++;
        }
    }
}



