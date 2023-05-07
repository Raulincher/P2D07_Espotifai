package Presentation.View;

import Business.Entities.Song;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedSongView extends JPanel {
    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";
    private JButton jback = new JButton("back");
    private JButton jlogout = new JButton("logout");
    private JButton jdelete = new JButton("delete account");


    public DetailedSongView(Utils utils){
        this.utils = utils;
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController){
        //set action command
        jback.addActionListener(detailedSongViewController);
        jlogout.addActionListener(detailedSongViewController);
        jdelete.addActionListener(detailedSongViewController);
    }

    public void configureDetailedSongView() {

        JLabel jLogo = new JLabel("detailed song");

        jback = utils.buttonText("back");
        //JLabel jLogo = new JLabel("detailed song");
        //JButton jback = new JButton("back");
        //jback.setActionCommand(BTN_BACK);
        setLayout(new BorderLayout());

        //North
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(50, 0, 0, 0));

        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.LOGO_LABEL));
        JLabel logolabel = new JLabel(logo);
        logolabel.setLayout(new GridLayout(4,1,0,50));

        Icon backBtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_LITTLEBUTTON_IMG));
        jback = new JButton(backBtn);
        jback.setActionCommand(BTN_BACK);
        jback.setBackground(Color.decode("#00000000"));
        //jback.setPreferredSize(new Dimension(200,100));

        Icon logoutBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGOUT_LITTLEBUTTON_IMG));
        jlogout = new JButton(logoutBtn);
        jlogout.setActionCommand(BTN_LOGOUT);
        jlogout.setBackground(Color.decode("#00000000"));
       // jlogout.setPreferredSize(new Dimension(200,100));

        Icon deleteBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETEACC_LITTLEBUTTON_IMG));
        jdelete = new JButton(deleteBtn);
        jdelete.setActionCommand(BTN_DELETE);
        jdelete.setBackground(Color.decode("#00000000"));
       // jdelete.setPreferredSize(new Dimension(200,100));

        north.add(logolabel);
        north.add(jback);
        north.add(jlogout);
        north.add(jdelete);
        add(north, BorderLayout.NORTH);

        //Taula Song
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);

        String[] columnNames = {"", ""};
        Object[][] data = {
                {"Song", "song.getTile()"},
                {"Genre", "song.getGenre()"},
                {"Artist", "song.getAuthor()"},
                {"Album", "song.setAlbum()"},
                {"Uploaded by", "Prova_by"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollpane = new JScrollPane(table);

        center.add(scrollpane, BorderLayout.CENTER);
        add(center);

    }
}
