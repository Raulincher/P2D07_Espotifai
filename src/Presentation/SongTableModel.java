package Presentation;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SongTableModel extends DefaultTableModel {
    private DefaultTableModel deleteTableModel;
    private DefaultTableModel allSongsTableModel;

    public SongTableModel() {
        deleteTableModel = new DefaultTableModel();
        allSongsTableModel = new DefaultTableModel();
    }

    public void buildDeleteTableModel(ArrayList<String> information){
        deleteTableModel.addColumn("Song");
        deleteTableModel.addColumn("Artist");
        deleteTableModel.addColumn("Genre");

        for (int i = 0; i < information.size(); i++) {
            String[] songInfo = information.get(i).split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            deleteTableModel.addRow(rowData);
        }
    }

    public void buildAllSongsTableModel(ArrayList<String> information){
        allSongsTableModel.addColumn("Song");
        allSongsTableModel.addColumn("Artist");

        for (int i = 0; i < information.size(); i++) {
            String[] songInfo = information.get(i).split("-");
            Object[] rowData = {songInfo[0], songInfo[1]};
            allSongsTableModel.addRow(rowData);
        }
    }

    public DefaultTableModel getDeleteTableModel() {
        return deleteTableModel;
    }

    public DefaultTableModel getAllSongsTableModel() {
        return allSongsTableModel;
    }
}
