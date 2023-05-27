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
                boolean clipRunning = songManager.anyClipRunning();
                String playlistName = playlistManager.getCurrentPlaylist();
                if(playlistName == null){
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
                    footerView.iterateProgressBar(songManager.clipDuration(), 0, true, clipRunning, false);
                    footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false, false);

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
                        String nextSong = playlistManager.getNextSongInPlaylist(playlistName);
                        actualS = songManager.getPath(actualSongInPlaylist);
                        String[] auxSplit = actualS.split("/");
                        actualS = auxSplit[2];
                        songManager.getSong(actualSongInPlaylist, playlistName, nextSong);
                    }else{
                        actualS = songManager.getPath(playListSongs.get(0));
                        String[] auxSplit = actualS.split("/");
                        actualS = auxSplit[2];
                        String nextSong = playlistManager.getNextSongInPlaylist(playlistName);

                        songManager.getSong(actualS, playlistName, nextSong);
                    }

                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    int check = songManager.checkMyClip();


                    if(lastSong == null){
                        lastSong = actualS;
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, clipRunning, false);
                    }else if(lastSong.equals(actualS)){
                        footerView.iterateProgressBar(songManager.clipDuration(), check, true, false, false);
                    }else{
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true, false);
                        footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false, false);
                        lastSong = actualS;

                    }

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
                }
            }

            case FooterView.BTN_REPEAT -> {
                songManager.loopAudio();
                footerView.iterateProgressBar(songManager.clipDuration(), 20, false, false, true);
            }

            case FooterView.BTN_BACKWARD -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                String actualS;
                if(playlistName == null) {
                    songManager.moveBackward(null, null);
                    actualS = songManager.getActualSong();

                }else{
                    String previousSong = playlistManager.getPreviousSongInPlaylist(playlistName);
                    songManager.moveBackward(playlistName, previousSong);
                    actualS = songManager.getActualSong();
                }

                footerView.setActualSong(actualS);
                footerView.jProgressBar.setMinimum(0);
                footerView.jProgressBar.setMaximum(songManager.clipDuration());
                footerView.iterateProgressBar(0, 0, false, true, false);
                footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false, false);
                footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                footerView.setSongActualTime("00:00");
            }

            case FooterView.BTN_FORWARD -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                String actualS;
                if(playlistName == null) {
                    songManager.moveForward(null, null);
                    actualS = songManager.getActualSong();

                }else{
                    String nextSong = playlistManager.getNextSongInPlaylist(playlistName);
                    songManager.moveForward(playlistName, nextSong);
                    actualS = songManager.getActualSong();
                }
                footerView.setActualSong(actualS);
                footerView.jProgressBar.setMinimum(0);
                footerView.jProgressBar.setMaximum(songManager.clipDuration());
                footerView.iterateProgressBar(0, 0, false, true,false);
                footerView.iterateProgressBar(songManager.clipDuration(), 0, false, false, false);
                footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                footerView.setSongActualTime("00:00");

            }
            case FooterView.BTN_REPEAT_LIST -> {
                String playlistName = playlistManager.getCurrentPlaylist();
                if(playlistName != null) {
                    String nextSong = playlistManager.getNextSongInPlaylist(playlistName);
                    songManager.loopList();
                }
            }
            case FooterView.BTN_STOP -> {
                footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true,false);
                songManager.endSong();
                footerView.start();
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
