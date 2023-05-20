package Presentation.Controller;

import Business.SongManager;
import Persistance.dao.SongLyricsApi;
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
                boolean stop = songManager.simpleAudioPlayer();
                if(stop){
                    footerView.stop();
                }else{
                    footerView.start();
                }
            }

            case FooterView.BTN_REPEAT -> songManager.loopAudio();
            case FooterView.BTN_BACKWARD -> songManager.moveBackward();
            case FooterView.BTN_FORWARD -> {
                try {
                    songManager.moveForward();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                }
            }
            case FooterView.BTN_REPEAT_LIST -> songManager.loopList();
            case FooterView.BTN_LYRICS -> {
                String nameActualSong = songManager.getActualSong();
                nameActualSong = nameActualSong.substring(0, nameActualSong.lastIndexOf(".wav"));
                ArrayList<String> actualSong = songManager.searchSong(nameActualSong);
                if (actualSong == null){
                    footerView.showPopUpError("Song not found");
                } else {
                    String lyrics = songManager.readLyricApi(actualSong.get(2) ,actualSong.get(0));
                    footerView.showPopUpLyrics(lyrics,actualSong.get(0));
                }
            }
        }
    }
}
