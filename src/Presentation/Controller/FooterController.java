package Presentation.Controller;

import Business.SongManager;
import Presentation.View.FooterView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FooterController implements ActionListener {

    private final FooterView footerView;
    private final SongManager songManager;


    public FooterController(FooterView footerView, SongManager songManager) {
        this.footerView = footerView;
        this.songManager = songManager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
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
            case FooterView.BTN_FORWARD -> songManager.moveForward();
            case FooterView.BTN_REPEAT_LIST -> songManager.loopList();
        }
    }
}
