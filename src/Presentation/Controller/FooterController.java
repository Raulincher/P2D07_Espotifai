package Presentation.Controller;

import Business.PlaylistManager;
import Business.SongManager;
import Presentation.View.FooterView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe per controlar la vista del Footer i les interaccions d'aquesta
 */
public class FooterController implements ActionListener {

    // Preparem atributs
    private final FooterView footerView;
    private final SongManager songManager;
    private final PlaylistManager playlistManager;
    private String lastSong;

    /**
     * Funció que servirà per com a constructor del FooterController
     *
     * @param footerView, per a utilitzar alguns del seus atributs com la barra del footer
     * @param songManager, per a utilitzar els seus mètodes, aquí tractarem tota la cançó
     * @param playlistManager, per a utilitzar els seus mètodes, aquí tractarem tot el tema de reproduir playlist
     *
     */
    public FooterController(FooterView footerView, SongManager songManager, PlaylistManager playlistManager) {
        this.footerView = footerView;
        this.songManager = songManager;
        this.playlistManager = playlistManager;

    }
    /**
     * Funció que servirà per parar el thread quan volguem fer logout o delete account.
     * Es necesari per un bon funcionament de la barra de reproducció
     *
     * No tindrà param ni return
     */
    public void outOfTheProgram(){
        if(footerView.progressBarThread != null){
            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, true);
        }
    }

    /**
     * Funció que servirà per reiniciar la barra de reproduccio desde el play de la detailed view.
     * Es necesari per un bon funcionament de la barra de reproducció.
     * Ja que cada vegada que prenem el play de la detailed song list es reinicia la canço actual
     *
     * No tindrà param ni return
     */

    public void controlBarFromDetailed(){
        if(footerView.progressBarThread != null){
            footerView.iterateProgressBar(0, 0, false, true);
        }
        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
        footerView.jProgressBar.setMaximum(songManager.clipDuration());

        int minutes = songManager.clipDuration() / 60;
        int seconds = songManager.clipDuration() % 60;

        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);

        String time = minutesString + ":" + secondsString;

        footerView.setSongTotalTime(time);

    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol boto de la vista del footer
     * @param e l'esdeveniment a tramitar
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //case btn play
            case FooterView.BTN_PLAY -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                //dos sistemes per fer play al reproductor, desde playlist i desde la song list general
                if(playlistName == null){
                    songManager.setLoopListCondition(false);
                    String actualS = songManager.getActualSong();
                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    //detectem si hem premut el botó de pause paer canviar la img més endavant
                    if(actualS != null && !actualS.equals(" ")) {
                        boolean stop = false;
                        try {
                            stop = songManager.simpleAudioPlayer();
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                            ex.printStackTrace();
                        }

                        if (stop) {
                            footerView.stop();

                        } else {
                            footerView.start();
                        }

                        //aquí comprovem si la cançó anterior és la mateixa, és diferent o és la primera cançó que es reprodueix
                        int check = songManager.checkMyClip();
                        if (lastSong == null) {
                            //si es la primera cançó primer farem reset de la barra en cas que sigui necessari
                            if (footerView.progressBarThread != null) {
                                footerView.iterateProgressBar(songManager.clipDuration(), 0, false, true);
                            }
                            //aqui iniciem la barra de progrés de la cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                            lastSong = actualS;

                        } else if (lastSong.equals(actualS)) {
                            //si la cançó és la mateixa pausarem el thread conservat el frame actual de la cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false);
                            lastSong = actualS;
                        } else {
                            //si les cançons són diferents farem reset i iniciarem el procés amb la nova cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                            lastSong = actualS;
                        }
                    }


                }else {
                    ArrayList<String> playListSongs = playlistManager.obtainSongsInPlaylist(playlistName);
                    String actualSongInPlaylist = playlistManager.getClickedSong();
                    String actualS;
                    //aquí comprovem si hem premut la cançó a la playlist o pel contrari volem que es reprodueixi la primera
                    if(actualSongInPlaylist != null){
                        actualS = songManager.getPath(actualSongInPlaylist);
                        String[] auxSplit = actualS.split("/");
                        actualS = auxSplit[2];
                        songManager.getSong(actualSongInPlaylist);
                    }else{
                        actualS = songManager.getPath(playListSongs.get(0));
                        String[] auxSplit = actualS.split("/");
                        actualS = auxSplit[2];
                        songManager.getSong(actualS);
                    }
                    if(actualS != null && !actualS.equals(" ")) {
                        footerView.setActualSong(actualS);
                        footerView.jProgressBar.setMinimum(0);
                        footerView.jProgressBar.setMaximum(songManager.clipDuration());
                        footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                        int check = songManager.checkMyClip();
                        //detectem si hem premut el botó de pause per canviar la img més endavant
                        boolean stop = false;
                        try {
                            stop = songManager.simpleAudioPlayer();
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                            ex.printStackTrace();
                        }

                        if (stop) {
                            footerView.stop();
                        } else {
                            footerView.start();
                        }
                        //afegim un playlist al final de la song guardada per així diferenciar-les de les normals
                        actualS = actualS + "playlist";

                        //aquí comprovem si la cançó anterior és la mateixa, és diferent o és la primera cançó que es reprodueix
                        if (lastSong == null) {
                            //si és la primera cançó primer farem reset de la barra en cas que sigui necessari
                            if (footerView.progressBarThread != null) {
                                footerView.iterateProgressBar(songManager.clipDuration(), 0, false, true);
                            }
                            //aquí iniciem la barra de progrés de la cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                            lastSong = actualS + "playlist";
                        } else if (lastSong.equals(actualS)) {
                            //si la cançó és la mateixa pausarem el thread conservat el frame actual de la cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false);
                            lastSong = actualS + "playlist";
                        } else {
                            //si les cançons són diferents farem reset i iniciarem el procés amb la nova cançó
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                            lastSong = actualS + "playlist";

                        }
                    }
                }
            }
            //repeat case, aquest esencialment crida un metode dins de songmanager per fer un bucle a la canço actual
            case FooterView.BTN_REPEAT -> songManager.loopAudio();
            //backward case, far retrocedir la cançó en 1. Si ens trobem al principi reiniciarem la mateixa cançó
            case FooterView.BTN_BACKWARD -> {
                if(footerView.progressBarThread != null) {
                    String playlistName = playlistManager.getCurrentPlaylist();
                    String actualS;
                    //aqui diferenciem si hem d'anar cap endarrere a la llista general o per el contrari anar cap endarrere dins la nostra playlist
                    if (playlistName == null) {
                        songManager.setLoopListCondition(false);
                        songManager.moveBackward(null, null);
                        actualS = songManager.getActualSong();

                    } else {
                        String previousSong = playlistManager.getPreviousSongInPlaylist(playlistName);
                        songManager.moveBackward(playlistName, previousSong);
                        actualS = songManager.getActualSong();
                    }

                    //com sempre que pulsem aquest reiniciarem la cançó, ho farem també amb la barra de reproducció
                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.iterateProgressBar(0, 0, false, true);
                    footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    footerView.setSongActualTime("00:00");

                    lastSong = actualS;

                }
            }

            //backward case, far avançar la cançó en 1. Si ens trobem al final de la zona general de cançons aquesta ens portará cap a la primera canço, en cambi
            //a la zona de playlist si, el boto de loop no ha sigut premut, ens tornara a reproduir la cançó actual, es a dir l'ultima.
            case FooterView.BTN_FORWARD -> {
                if(footerView.progressBarThread != null) {

                    String playlistName = playlistManager.getCurrentPlaylist();
                    String actualS;
                    //aqui diferenciem si hem d'anar cap endavant a la llista general o per el contrari anar cap endavant dins la nostra playlist
                    if (playlistName == null) {
                        songManager.setLoopListCondition(false);
                        songManager.moveForward(null, null);
                        actualS = songManager.getActualSong();

                    } else {
                        boolean loopCondition = songManager.isLoopListCondition();
                        String nextSong = playlistManager.getNextSongInPlaylist(playlistName, loopCondition);
                        songManager.moveForward(playlistName, nextSong);
                        actualS = songManager.getActualSong();
                    }

                    //com sempre que pulsem aquest reiniciarem la cançó, ho farem també amb la barra de reproducció
                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.iterateProgressBar(0, 0, false, true);
                    footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    footerView.setSongActualTime("00:00");


                    lastSong = actualS;

                }

            }
            //case repeat list, aquest metode activa la posibilitat de fer loop a la llista actual,
            // el que ens permetra poder avançar desde el final cap a l'inici prement el boto de forward
            case FooterView.BTN_REPEAT_LIST -> {
                songManager.loopList();
            }
            //case stop
            case FooterView.BTN_STOP -> {
                //sempre que la barra estigui activa significara que podem pausar
                if(footerView.progressBarThread != null) {
                    //aquest stop parara la nostra cançó, la reiniciará i fará reset a la barra de reproducció
                    footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                    songManager.endSong();
                    footerView.start();
                }
            }
            //lyrics case
            case FooterView.BTN_LYRICS -> {
                //en cas que aquest botó es premi es mostrarán les lyrics de la cançó que s'està reproduïnt, sempre que la lyric existeixi
                ArrayList<String> lyrics = songManager.searchLyrics();
                if (lyrics == null){
                    footerView.showPopUpError("Song not found");
                } else {
                    footerView.showPopUpLyrics(lyrics.get(1),lyrics.get(0));
                }
            }

        }
    }
}
