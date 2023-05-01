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
        switch (e.getActionCommand()) {
            case FooterView.BTN_PLAY -> {
                try {
                    Path p = Paths.get("mama.wav");
                    Path folder = p.getParent();
                    System.out.println(folder);
                    System.out.println(p);

                    songManager.SimpleAudioPlayer("files/music/mama.wav");
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
