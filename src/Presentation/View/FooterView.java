package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.FooterController;
import Presentation.ProgressBarThread;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

public class FooterView extends JPanel {

    // Preparem atributs
    private final Utils utils;

    // Preparem strings dels botons
    public static final String BTN_PLAY = "BTN_PLAY";
    public static final String BTN_FORWARD = "BTN_FORWARD";
    public static final String BTN_BACKWARD = "BTN_BACKWARD";
    public static final String BTN_REPEAT = "BTN_REPEAT";
    public static final String BTN_REPEAT_LIST = "BTN_REPEAT_LIST";
    public static final String BTN_LYRICS = "BTN_LYRICS";

    // Preparem JButtons
    private JButton jplay;
    private JButton jForward;
    private JButton jBackward;
    private JButton jRepeat;
    private JButton jRepeatList;
    private JButton jLyrics;
    private ProgressBarThread progressBarThread;

    //private String songName;

    public JProgressBar jProgressBar;

    /**
     * Funció que servirà per com a constructor del FooterView
     *
     * @param utils, per a utilitzar els seus mètodes
     */
    public FooterView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar els listeners
     *
     * No tindrà param ni return
     */
    public void addFooterController(FooterController footerController){
        jplay.addActionListener(footerController);
        jForward.addActionListener(footerController);
        jBackward.addActionListener(footerController);
        jRepeat.addActionListener(footerController);
        jRepeatList.addActionListener(footerController);
        jLyrics.addActionListener(footerController);
        //songName = footerController.getActualSong();
    }

    /**
     * Funció que servirà per crear els botons del footer
     * i donar'ls-hi format
     *
     * No tindrà param ni return
     */
    public void initializeButtons(){
        // Creem el color de fons
        Color gris = new Color(26,26,26);

        // Creem les icones
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        Icon forwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_NEXTBUTTON_IMG));
        Icon backwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_OLDBUTTON_IMG));
        Icon repeatBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEATBUTTON_IMG));
        Icon repeatListBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEAT_PLAYLIST_BUTTON_IMG));
        Icon lyricsBtn = new ImageIcon(String.valueOf(AssetsFiles.LYRICS_IMG));

        // Activem els JButtons i els hi donem forma
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

        // Creem la barra de progrés
        jProgressBar = utils.progressBar(10,1);
        jProgressBar.setValue(0);

        // Activem les comandes d'acció en cas que es premi
        jplay.setActionCommand(BTN_PLAY);
        jForward.setActionCommand(BTN_FORWARD);
        jBackward.setActionCommand(BTN_BACKWARD);
        jRepeat.setActionCommand(BTN_REPEAT);
        jRepeatList.setActionCommand(BTN_REPEAT_LIST);
        jLyrics.setActionCommand(BTN_LYRICS);
    }

    /**
     * Funció de tipus JPanel que servirà per a acabar de
     * donar-li forma al footer i retornar-lo
     *
     * @return border, JPanel del footer
     */
    public JPanel configureFooter() {


        // Creem el color de fons
        Color gris = new Color(26,26,26);

        // Creem les icones
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        Icon forwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_NEXTBUTTON_IMG));
        Icon backwardBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_OLDBUTTON_IMG));
        Icon repeatBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEATBUTTON_IMG));
        Icon repeatListBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_REPEAT_PLAYLIST_BUTTON_IMG));
        Icon lyricsBtn = new ImageIcon(String.valueOf(AssetsFiles.LYRICS_IMG));

        // Activem els JButtons i els hi donem forma
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

        // Creem la barra de progrés
        jProgressBar = utils.progressBar(10,1);
        jProgressBar.setValue(0);

        // Activem les comandes d'acció en cas que es premi
        jplay.setActionCommand(BTN_PLAY);
        jForward.setActionCommand(BTN_FORWARD);
        jBackward.setActionCommand(BTN_BACKWARD);
        jRepeat.setActionCommand(BTN_REPEAT);
        jRepeatList.setActionCommand(BTN_REPEAT_LIST);
        jLyrics.setActionCommand(BTN_LYRICS);
        // Creem JPanel general i li afegim el BorderLayout
        JPanel border = new JPanel();
        border.setLayout(new BorderLayout());

        // Iniciem el color gris
        //Color gris = new Color(26,26,26);

        // Creem els altres sub-JPanels
        JPanel footerN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));;
        JPanel footerS = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Posem background a cada footer
        footerN.setBackground(gris);
        footer.setBackground(gris);
        footerS.setBackground(gris);

        // Agafem el nom de la cançó
        JLabel jLogo = utils.label("Song Name");

        // Configurem la part NORTH del BorderLayout
        footerN.add(jLogo);
        border.add(footerN, BorderLayout.NORTH);

        // Configurem la part CENTER del BorderLayout
        footer.add(jRepeat);
        footer.add(jBackward);
        footer.add(jplay);
        footer.add(jForward);
        footer.add(jRepeatList);
        border.add(footer, BorderLayout.CENTER);

        // Configurem la part SOUTH del BorderLayout
        footerS.add(jLyrics);
        footerS.add(jProgressBar);
        border.add(footerS, BorderLayout.SOUTH);
        Dimension dimension = new Dimension(10,10);

        return border;
    }
    /**
     * Funció que servirà per a canviar d'icona del play
     * en cas que es premi el botó i deixar-ho en pause
     *
     * No tindrà param ni return
     */
    public void stop(){
        Icon pauseBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PAUSEBUTTON_IMG));
        jplay.setIcon(pauseBtn);
    }


    public void iterateProgressBar(int maxValue){
        jProgressBar.setMaximum(maxValue);
        jProgressBar.setMinimum(0);
        progressBarThread = new ProgressBarThread(jProgressBar);

        progressBarThread.setPlaying(true);

        // Start the playback thread in a separate thread
        Thread thread = new Thread(progressBarThread);
        thread.start();
    }

    /**
     * Funció que servirà per a canviar d'icona del pause
     * en cas que es premi el botó i deixar-ho en play
     *
     * No tindrà param ni return
     */
    public void start(){
        Icon playBtn = new ImageIcon(String.valueOf(AssetsFiles.FOOT_PLAYBUTTON_IMG));
        jplay.setIcon(playBtn);
    }

    /**
     * Funció que servirà per crear els pop up dels lyrics
     * de la cançó que s'estigui escoltant
     *
     * @param lyrics, lyrics de la cançó
     * @param title, títol de la cançó
     */
    public void showPopUpLyrics(String lyrics,String title) {
        // Creem el color de background
        Color gris = new Color(26, 26, 26);

        // Creem el JDialog dels lyrics i el configurem
        JDialog dialog = new JDialog((Frame) null, "Lyrics: " + title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setBackground(gris);

        // Creem un JTextArea per a veure els lyrics i evitem que sigui editable
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Configurem tot el format del JTextArea
        textArea.setBackground(gris);
        textArea.setForeground(Color.decode("#00DC00"));
        textArea.setFont(new Font("Gotham", Font.BOLD, 15));

        // Configurem com es representaran els lyrics amb un StringBuilder
        String[] lines = lyrics.split("\n");
        StringBuilder centeredText = new StringBuilder();

        // Afegim línia per línia els lyrics en un bucle
        for (String line : lines) {
            centeredText.append(String.format("%" + 10 + "s%s\n", "", line));
        }

        // L'afegim en el JTextArea i configurem la posició
        textArea.setText(centeredText.toString());
        textArea.setCaretPosition(0);

        // Afegim un JScrollPane per poder baixar i pujar pel TextArea amb comoditat
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setBackground(gris);

        // Posem el Layout, afegim el ScrollPane i acabem de configurar el JDialog
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Funció que servirà per a mostrar pop ups
     *
     * @param error, missatge en qüestió
     */
    public void showPopUpError(String error){
        JOptionPane.showMessageDialog(this,error);
    }


}
