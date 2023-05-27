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


public class FooterController implements ActionListener {

    private final FooterView footerView;
    private final SongManager songManager;
    private final PlaylistManager playlistManager;
    private String lastSong;

    public FooterController(FooterView footerView, SongManager songManager, PlaylistManager playlistManager) {
        this.footerView = footerView;
        this.songManager = songManager;
        this.playlistManager = playlistManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case FooterView.BTN_PLAY -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                if(playlistName == null){
                    songManager.setLoopListCondition(false);
                    String actualS = songManager.getActualSong();
                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    boolean stop = false;
                    try {
                        stop = songManager.simpleAudioPlayer();
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    int check = songManager.checkMyClip();
                    System.out.println(lastSong + "sin lista");
                    System.out.println(actualS + "sin lista");
                    if(lastSong == null){
                        System.out.println("entro en nulo sin playlists");
                        if(footerView.progressBarThread != null) {
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, true);
                        }
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                        lastSong = actualS;

                    }else if(lastSong.equals(actualS)){
                        System.out.println("entro en igual sin playlists");
                        footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false);
                        lastSong = actualS;
                    }else{
                        System.out.println("entro en diferente sin playlists");
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                        lastSong = actualS;
                    }

                    if (stop) {
                        footerView.stop();

                    } else {
                        footerView.start();
                    }

                }else {
                    ArrayList<String> playListSongs = playlistManager.obtainSongsInPlaylist(playlistName);
                    String actualSongInPlaylist = playlistManager.getClickedSong();
                    String actualS;
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

                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    int check = songManager.checkMyClip();

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
                    actualS = actualS + "playlist";
                    System.out.println(lastSong);
                    System.out.println(actualS);

                    if(lastSong == null){
                        if(footerView.progressBarThread != null) {
                            System.out.println("entro en reset de bar playlist");
                            footerView.iterateProgressBar(songManager.clipDuration(), 0, false, true);
                        }
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                        lastSong = actualS + "playlist";
                    }else if(lastSong.equals(actualS)){
                        footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false);
                        lastSong = actualS + "playlist";
                    }else{
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false);
                        lastSong = actualS + "playlist";

                    }


                }
            }

            case FooterView.BTN_REPEAT -> songManager.loopAudio();
            case FooterView.BTN_BACKWARD -> {
                if(footerView.progressBarThread != null) {
                    String playlistName = playlistManager.getCurrentPlaylist();
                    String actualS;
                    if (playlistName == null) {
                        songManager.setLoopListCondition(false);
                        songManager.moveBackward(null, null);
                        actualS = songManager.getActualSong();

                    } else {
                        String previousSong = playlistManager.getPreviousSongInPlaylist(playlistName);
                        songManager.moveBackward(playlistName, previousSong);
                        actualS = songManager.getActualSong();
                    }

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

            case FooterView.BTN_FORWARD -> {
                if(footerView.progressBarThread != null) {

                    String playlistName = playlistManager.getCurrentPlaylist();
                    String actualS;
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
            case FooterView.BTN_REPEAT_LIST -> {
                songManager.loopList();
            }
            case FooterView.BTN_STOP -> {
                if(footerView.progressBarThread != null) {
                    footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
                    songManager.endSong();
                    footerView.start();
                }
            }
            case FooterView.BTN_LYRICS -> {
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
