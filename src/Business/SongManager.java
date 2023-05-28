package Business;

import Business.Entities.Song;
import Persistance.dao.SongDao;
import Persistance.dao.SongLyricsApi;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que ens permet controla la classe Song
 */
public class SongManager {

    // Preparem atributs
    private final SongDao songDao;
    private Clip myClip;
    private String filePath;
    private File file;
    private Song song;
    private String actualSong;
    private String thatSong;
    private String pathComputer;
    private SongLyricsApi songLyricsApi;
    private boolean loopListCondition = false;

    /**
     * Funció que servirà per com a constructor del SongManager
     *
     * @param songDao, interface que comunica la clase que parla amb la base de dades, així podrem gestionar les dades
     * @param songLyricsApi, conexió amb la api per agafar les lletres de les cançons disponibles dins la mateixa
     *
     */
    public SongManager(SongDao songDao,SongLyricsApi songLyricsApi) {
        this.songDao = songDao;
        this.songLyricsApi = songLyricsApi;
    }

    /**
     * Funció que servirà per agafar el path sencer d'una cançó dins la base de dades
     *
     * @param songName, string que utilitzarem per buscar la canço dins la abse de dades
     *
     * @return path, string amb el path de la song
     *
     */
    public String getPath(String songName){
        return songDao.songPath(songName);
    }

    /**
     * Funció que servirà per agafar el temps total d'una cançó en format int
     *
     * @param songName, string que utilitzarem per buscar la canço dins la base de dades
     *
     * @return time, int de la duració de la cançó
     */
    public int getSongDurationFromDatabase(String songName){return songDao.songDuration(songName);}

    /**
     * Funció que servirà per agafar el temps total d'una cançó en format String minutes:seconds
     *
     * @param songName, string que utilitzarem per buscar la canço dins la base de dades
     *
     *
     * @return time, String de la duració de la cançó minutes:seconds format
     */
    public String songDurationInString(String songName){
        return songDao.songDurationInString(songName);
    }

    /**
     * Funció que ens permet veure la posició del frames del clip actual
     *
     * no params
     *
     * @return actualFrame, frame actual en forma d'int
     */
    public int checkMyClip(){
        return myClip.getFramePosition();
    }

    /**
     * Funció que ens permet veure la duració total del clip actual
     *
     * no params
     *
     * @return duration, duració total del clip
     */
    public int clipDuration(){
        int duration = 0;

        if(actualSong != null){
            duration = getSongDurationFromDatabase(actualSong);
        }
        return duration;
    }

    /**
     * Funció que ens permet agafar la cançó actual guardada dins de thatSong
     *
     * no params
     *
     * @return thatSong, String de la cançó actual
     */
    public String getActualSong() {
        return thatSong;
    }

    /**
     * Funció que ens permet cambiar la cançó actual guardada dins de thatSong
     *
     * @param song, string que fara cambiar la variable thatSong
     *
     * no return
     */
    public void setActualSong(String song) {
        thatSong = song;
    }


    /**
     * Funció que ens permet, agafar la cançó que volguem reproduir i a més parar la que ya s'está reproduint per evitar sorolls dobles
     *
     * @param songTitle, string que ens dira quin titol te la cançó que volem reproduir
     *
     * no return
     */
    public void getSong(String songTitle){
        try {
            File music;
            File[] files;
            File file;

            if(myClip != null){
                myClip.close();
            }

            //comprovem el title, si es no hem escollit cap cançó es reproduira la primera dins la vista general
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

            }
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

    /**
     * Funció que ens permet para la cançó que s'está reproduint i a més ens serveix per avisar a la vista, que té que cambiar, amb una flag
     *
     * @return stopped, boolean que indica si la cançó s'ha parat
     *
     * no param
     */
    public boolean simpleAudioPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        boolean stopped = true;
        if(myClip != null) {
            if(myClip.isOpen()){
                if (myClip.isRunning()) {
                    myClip.stop();
                    stopped = false;
                } else {
                    myClip.start();
                }
            }else{
                String filePath = "files/music/" + actualSong;
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

    /**
     * Funció que ens permet detectar si la cançó que volem esborrar s'està reproduint
     *
     * @param songDelete, cançó a comparar amb l'actual
     *
     * @return boolean amb la flag esmentada a la funció
     *
     * no param
     */
    public boolean isPlaying(String songDelete){
        return (myClip.isRunning() & actualSong.equals(songDelete));
    }

    /**
     * Funció que ens permet parar i tancar l'arxiu de so myclip
     *
     * no param ni return
     */
    public void stopClip() {
        if(myClip != null) {
            if (myClip.isRunning()) {
                myClip.stop();
                myClip.close();
            }
        }
    }

    /**
     * Funció que ens permet fer un bucle a la cançó actual
     *
     * no param ni return
     */
    public void loopAudio() {
        if(myClip != null) {
            myClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Funció que ens permet avançar cap a la següent cançó si es posible
     *
     * @param playlistName, nom de la playlist, si existeix
     * @param nextSong, següent cançó de la llista, en cas de ser una playlist
     *
     * no return
     */
    public void moveForward(String playlistName, String nextSong){
        int auxPos = 0;
        //assegurem que el nostre clip no es null
        if(myClip != null) {
            //assegurem que tanquem el clip anterior per evitar sorolls dobles
            if (myClip.isRunning()) {
                myClip.close();
            }
            //detecció en cas de ser una playlist o una cançó per separat
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

    /**
     * Funció que ens permet retrocedir cap a l'anterior cançó si és posible
     *
     * @param playlistName, nom de la playlist, si existeix
     * @param previousSong, anterior cançó de la llista, en cas de ser una playlist
     *
     * no return
     */
    public void moveBackward(String playlistName, String previousSong){
        int auxPos = 0;
        //assegurem que el nostre clip no es null
        if(myClip != null) {
            //assegurem que tanquem el clip anterior per evitar sorolls dobles
            if (myClip.isRunning()) {
                myClip.close();
            }

            //detecció en cas de ser una playlist o una cançó per separat
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

    /**
     * Funció que ens permet activar el camp de loop list, aquí indicarem si una llista pot loopejar quan arriba el final o no
     */
    public void loopList() {
        if(myClip != null) {
            loopListCondition = !loopListCondition;
        }
    }
    /**
     * Funció que ens permet obtenir el camp de loop list
     *
     * @return boolean amb l'estat de la variable loop list
     *
     */
    public boolean isLoopListCondition() {
        return loopListCondition;
    }

    /**
     * Funció que ens permet sobreescriure el camp de loop list
     *
     * @param loopListCondition, boolean que ens marca la condició desitjada
     *
     * no return
     */
    public void setLoopListCondition(boolean loopListCondition) {
        this.loopListCondition = loopListCondition;
    }

    /**
     * Funció que ens permet agafar un fitxer del nostre ordinador per poder guardar-lo localment
     *
     * @return boolean amb la flag de si tot a anat bé amb la selecció
     */
    public boolean fileSongSelector() {
        JFileChooser fileChooser = new JFileChooser();
        //mirem de fer un filtre d'extensions wav
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

            //comprobem que l'extensió sigui correcte
            if(aux[1].equals("wav")){
                filePath = "files/music/" + fileName;
                fileFormatCorrect = true;
            }
        }

        return fileFormatCorrect;
    }

    /**
     * Funció que ens permet agafar el path d'un fitxer en local, el nom sense el path sencer
     *
     * @return string amb el nom en format path de la cançó, exemple -> cançó.wav
     *
     * no param
     */
    public String obtainFilePath() {
        return file.getName();
    }

    /**
     * Funció que ens permet tancar el nostre clip en cas de sortir del programa
     *
     * no param no return
     */
    public void endSong(){
        if(myClip != null){
            myClip.close();
        }
    }

    /**
     * Funció que ens permet comprovar si el camps estan buits
     *
     * @param songName, nom de la cançó escollida per l'usuari
     * @param artist, nom de l'artista escollit per l'usuari.
     * @param album, nom de l'album escollit per l'usuari.
     * @param genre, nom del gènere escollit per l'usuari.
     *
     * @return songSaved, flag que indicarà que algun camp està buit
     */
    public boolean isEmpty(String songName, String artist, String album, String genre) {
        boolean emptyField = false;

        if (songName == null || artist == null || album == null || genre == null) {
            emptyField = true;
        } else {
            if (songName.isEmpty() || artist.isEmpty() || album.isEmpty() || genre.isEmpty()) {
                emptyField = true;
            }
        }
        return emptyField;
    }

    /**
     * Funció que ens permet tcomprovar que a l'hora de guardar una cançó cumplim tots els parametres
     *
     * @param songName, nom de la cançó escollida per l'usuari
     * @param artist, nom de l'artista escollit per l'usuari.
     * @param album, nom de l'album escollit per l'usuari.
     * @param genre, nom del genere escollit per l'usuari.
     * @param username, nom de l'usuari que puja la cançó
     * @param time, temps total de la cançó
     *
     * @return songSaved, flag que indicará que el procés ha estat correcte
     */
    public boolean addSong(String songName, String artist, String album, String genre, String username, String time) {
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


    /**
     * Funció que ens permet comprovar que una cançó existeix
     *
     * @param songName, nom de la cançó
     *
     * @return songExists, flag que indicará que la cançó existeix
     */
    public boolean songExists(String songName) {
        // Si troba la cançó EN EL DAO
        if (songDao.songInDatabase(songName) ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Funció que ens permet esborrar una cançó amb title x
     *
     * @param name, nom de la cançó
     *
     * @return songExists, flag que indicará que la cançó ha sigut esborrada
     */
    public boolean deleteSong(String name) {
        boolean deletedOk = false;

        if (!isPlaying(name)) {
            // Si troba la cançó EN EL DAO
            String filePath = songDao.deleteSong(name);
            file = new File(filePath);
            if (file.delete()) {
                deletedOk = true;
            }
        }

        return deletedOk;
    }

    /**
     * Funció que ens permet obtenir el llistat de les cançons a la base de dades
     *
     * @param toDelete, boolean que indica si es per esborrar
     * @param currentUsername, nom d'usuari actual
     *
     * @return information, arrayList de totes les cançons recollides de la BBDD
     */
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


    /**
     * Funció que ens permet obtenir el llistat de les cançons a partir d'uns parametres de busqueda
     *
     * @param nameSong, nom de la cançó per filtrar
     *
     * @return songSelected, arrayList de les cançons filtrades
     */
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

    /**
     * Funció que ens permet llegir les lyrics d'una cançó, si existeixen a la API
     *
     * @param artist, artista de la cançó
     * @param songName, nom de la cançó
     *
     * @return lyricsSong, arrayList de les lyrics
     */
    public String readLyricApi(String artist, String songName){
        return songLyricsApi.readLyrics(artist,songName);
    }

    /**
     * Funció que ens permet buscar les lyrics d'una cançó
     *
     * @return lyricsSong, arrayList de les lyrics
     */
    public ArrayList<String> searchLyrics(){
        String name = this.actualSong.substring(0, actualSong.lastIndexOf(".wav"));
        String artist = songDao.songArtist(name);
        String lyrics = readLyricApi(artist,name);
        ArrayList<String> lyricsSong = new ArrayList<>();
        lyricsSong.add(name);
        lyricsSong.add(lyrics);
        return lyricsSong;
    }

    /**
     * Funció que ens permet calcular el temps total d'una cançó
     *
     * @return temps, arrayList del temps total de la cançó en format (boolean, minuts:segons)
     */
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
        } catch (UnsupportedAudioFileException | IOException | NullPointerException e) {
            temps.add(0,"false");
            temps.add(1,"Unsupported Audio File");
        }
        return temps;
    }

    /**
     * Funció que ens permet obtenir les cançons pujades per un usuari a la BBDD
     *
     * @param userName, usuari que va pujar la cançó
     *
     * @return songs, array de cançons per treure-les de les playlists
     */
    public ArrayList<String> obtainSongsByUser(String userName) {
        return songDao.filterSongsByUser(userName);
    }

    /**
     * Funció que ens permet borrar les cançons d'un usuari de la base de dades
     *
     * @param songsByUser, arraylist de cançons a esborrar
     *
     * @return filePath, array de paths locals per esborrar els fitxers
     */
    public ArrayList<String> deleteSongsByUsername(ArrayList<String> songsByUser) {
        return songDao.deleteSongsByUsername(songsByUser);
    }

    /**
     * Funció que ens permet borrar les cançons en local
     *
     * @param pathsToDelete, arraylist de paths de cançons a esborrar
     *
     * no return
     */
    public void deletePaths(ArrayList<String> pathsToDelete) {
        int i = 0;

        while(i < pathsToDelete.size()) {
            file = new File(pathsToDelete.get(i));
            file.delete();

            i++;
        }
    }
}



