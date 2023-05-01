package Presentation.View;

import Presentation.Controller.FooterController;
import Presentation.Controller.GeneralPlaylistViewController;

import javax.swing.*;

public class FooterView extends JPanel {

    public static final String BTN_PLAY = "BTN_PLAY";
    private final JButton jplay = new JButton("play");

    public void addFooterController(FooterController footerController){
        //set action command
        jplay.addActionListener(footerController);

    }

    public JPanel configureFooter() {
       JPanel footer = new JPanel();

        JLabel jLogo = new JLabel("song name");
        jplay.setActionCommand(BTN_PLAY);

        footer.add(jLogo);
        footer.add(jplay);

        return footer;
    }


}
