package Presentation.View;
import Presentation.AssetsFiles;
import Presentation.Controller.DetailedSongViewController;
import Presentation.Utils;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DetailedSongView extends JPanel {
    private final Utils utils;
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";
    private JButton jback = new JButton("back");
    private JButton jlogout = new JButton("logout");
    private JButton jdelete = new JButton("delete account");
    private HeaderView headerView;
    private FooterView footerView;
    private DefaultTableModel defaultTableModel;
    private JTable table;

    private static String[] columnHeaders ={"", ""};


    public DetailedSongView(Utils utils,HeaderView headerView,FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        defaultTableModel = new DefaultTableModel(columnHeaders, 0);
    }

    public void addDetailedSongController(DetailedSongViewController detailedSongViewController){
    }

    public void configureDetailedSongView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);
        //North
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.SONG_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        //Taula Song
        table = new JTable(defaultTableModel);
        JScrollPane scrollpane = createSongListTable(table,gris);
        center.add(scrollpane, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(30, 0, 30, 0));

        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);

    }

   private JScrollPane createSongListTable(JTable table, Color gris) {
        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(gris);
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setForeground(Color.decode("#00DC00"));
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);

        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
        renderer2.setForeground(Color.white);
        table.getColumnModel().getColumn(1).setCellRenderer(renderer2);

        table.setFont(new Font("Gotham", Font.BOLD, 35));

        return new JScrollPane(table);
    }

    public void fillDetailedTable(ArrayList<String> song) {
        defaultTableModel.setRowCount(0);
        int countRow = 0;
        String[] row = {"Song","Genre","Artist","Album", "Uploaded by"};
        for (String s : song) {
            String[] songInfo = s.split("-");
            Object[] rowData = {row[countRow],songInfo[0]};
            defaultTableModel.addRow(rowData);
            countRow++;
        }
    }
}
