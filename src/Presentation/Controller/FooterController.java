package Presentation.Controller;

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

    public FooterController(FooterView footerView, SongManager songManager) {
        this.footerView = footerView;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case FooterView.BTN_PLAY -> {
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
                footerView.iterateProgressBar(songManager.clipDuration(), check, stop, false);

                if(stop){
                    footerView.stop();

                }else{
                    footerView.start();
                }
            }

            case FooterView.BTN_REPEAT -> songManager.loopAudio();
            case FooterView.BTN_BACKWARD -> {
                songManager.moveBackward();
                String actualS = songManager.getActualSong();
                footerView.setActualSong(actualS);
                footerView.jProgressBar.setMinimum(0);
                footerView.jProgressBar.setMaximum(songManager.clipDuration());
                int check = songManager.checkMyClip();
                footerView.iterateProgressBar(songManager.clipDuration(), check, false, false);
                footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                footerView.setSongActualTime("00:00");
            }

            case FooterView.BTN_FORWARD -> {
                try {
                    songManager.moveForward();
                    String actualS = songManager.getActualSong();
                    footerView.setActualSong(actualS);
                    footerView.jProgressBar.setMinimum(0);
                    footerView.jProgressBar.setMaximum(songManager.clipDuration());
                    int check = songManager.checkMyClip();
                    footerView.iterateProgressBar(songManager.clipDuration(), check, false, false);
                    footerView.setSongTotalTime(songManager.songDurationInString(actualS));
                    footerView.setSongActualTime("00:00");
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                }
            }
            case FooterView.BTN_REPEAT_LIST -> songManager.loopList();
            case FooterView.BTN_STOP -> {
                footerView.iterateProgressBar(songManager.clipDuration(), 0, true, true);
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
