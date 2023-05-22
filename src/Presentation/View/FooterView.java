package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.FooterController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class FooterView extends JPanel {

    private final Utils utils;

    public static final String BTN_PLAY = "BTN_PLAY";
    public static final String BTN_FORWARD = "BTN_FORWARD";
    public static final String BTN_BACKWARD = "BTN_BACKWARD";
    public static final String BTN_REPEAT = "BTN_REPEAT";
    public static final String BTN_REPEAT_LIST = "BTN_REPEAT_LIST";
    public static final String BTN_LYRICS = "BTN_LYRICS";

    private JButton jplay;
    private JButton jForward;
    private JButton jBackward;
    private JButton jRepeat;
    private JButton jRepeatList;
    private JButton jLyrics;
    //private String songName;

    public FooterView(Utils utils){
        this.utils = utils;
    }

    public void addFooterController(FooterController footerController){
        //set action command
        jplay.addActionListener(footerController);
        jForward.addActionListener(footerController);
        jBackward.addActionListener(footerController);
        jRepeat.addActionListener(footerController);
        jRepeatList.addActionListener(footerController);
        jLyrics.addActionListener(footerController);
        //songName = footerController.getActualSong();
    }

    public void initializeButtons(){
        Color gris = new Color(26,26,26);

        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        Icon forwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_NEXTBUTTON_IMG));
        Icon backwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_OLDBUTTON_IMG));
        Icon repeatBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEATBUTTON_IMG));
        Icon repeatListBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEAT_PLAYLIST_BUTTON_IMG));
        Icon lyricsBtn = new ImageIcon(String.valueOf(AssetsFiles.LYRICS_IMG));

        jplay = utils.buttonImg(playBtn);
        jplay.setBackground(gris);
        jForward = utils.buttonImg(forwardBtn);
        jForward.setBackground(gris);
        jBackward = utils.buttonImg(backwardBtn);
        jBackward.setBackground(gris);
        jRepeat = utils.buttonImg(repeatBtn);
        jRepeat.setBackground(gris);
        jRepeatList = utils.buttonImg(repeatListBtn);
        jRepeatList.setBackground(gris);
        jLyrics = utils.buttonImg(lyricsBtn);
        jLyrics.setBackground(gris);

        jplay.setActionCommand(BTN_PLAY);
        jForward.setActionCommand(BTN_FORWARD);
        jBackward.setActionCommand(BTN_BACKWARD);
        jRepeat.setActionCommand(BTN_REPEAT);
        jRepeatList.setActionCommand(BTN_REPEAT_LIST);
        jLyrics.setActionCommand(BTN_LYRICS);

    }

    public JPanel configureFooter() {
        JPanel border = new JPanel();
        border.setLayout(new BorderLayout());

        Color gris = new Color(26,26,26);
        JPanel footerN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));;
        JPanel footerS = new JPanel(new FlowLayout(FlowLayout.CENTER));

        footerN.setBackground(gris);
        footer.setBackground(gris);
        footerS.setBackground(gris);

        JLabel jLogo = utils.label("Song Name");

        footerN.add(jLogo);
        border.add(footerN, BorderLayout.NORTH);

        footer.add(jRepeat);
        footer.add(jBackward);
        footer.add(jplay);
        footer.add(jForward);
        footer.add(jRepeatList);
        border.add(footer, BorderLayout.CENTER);

        footerS.add(jLyrics);
        border.add(footerS, BorderLayout.SOUTH);
        Dimension dimension = new Dimension(10,10);

        return border;
    }

    public void stop(){
        System.out.println("Hola");
        Icon pauseBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PAUSEBUTTON_IMG));
        jplay.setIcon(pauseBtn);
    }

    public void start(){
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jplay.setIcon(playBtn);
    }

    public void showPopUpLyrics(String lyrics,String title) {
        Color gris = new Color(26, 26, 26);
        JDialog dialog = new JDialog((Frame) null, "Lyrics: " + title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setBackground(gris);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(gris);
        textArea.setForeground(Color.decode("#00DC00"));
        textArea.setFont(new Font("Gotham", Font.BOLD, 15));

        String[] lines = lyrics.split("\n");
        StringBuilder centeredText = new StringBuilder();
        for (String line : lines) {
            centeredText.append(String.format("%" + 10 + "s%s\n", "", line));
        }
        textArea.setText(centeredText.toString());
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setBackground(gris);


        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    public void showPopUpError(String error){
        JOptionPane.showMessageDialog(this,error);
    }


}
