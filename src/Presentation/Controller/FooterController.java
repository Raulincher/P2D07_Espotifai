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
                boolean stop = false;
                try {
                    stop = songManager.simpleAudioPlayer();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                footerView.iterateProgressBar(songManager.clipDuration());
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
            }

            case FooterView.BTN_FORWARD -> {
                try {
                    songManager.moveForward();
                    String actualS = songManager.getActualSong();
                    footerView.setActualSong(actualS);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                }
            }
            case FooterView.BTN_REPEAT_LIST -> songManager.loopList();
            case FooterView.BTN_STOP -> songManager.endSong();
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

    /*public String getActualSong(){
        return songManager.getActualSong();
    }*/
}
