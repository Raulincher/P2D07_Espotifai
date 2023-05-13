package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class SongTableModel extends DefaultTableModel {
    private DefaultTableModel deleteTableModel;
    private DefaultTableModel allSongsTableModel;
    private DefaultTableModel searchedSongTableModel;


    public SongTableModel() {
        deleteTableModel = new DefaultTableModel();
        allSongsTableModel = new DefaultTableModel();
        searchedSongTableModel = new DefaultTableModel();
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

    public void buildSearchedSongTableModel(ArrayList<String> information) {
        searchedSongTableModel.addColumn("Song");
        searchedSongTableModel.addColumn("Artist");
        searchedSongTableModel.addColumn("Genre");

        Object[] rowData = {information.get(0), information.get(1), information.get(2)};
        searchedSongTableModel.addRow(rowData);
    }

    public DefaultTableModel getDeleteTableModel() {
        return deleteTableModel;
    }

    public DefaultTableModel getAllSongsTableModel() {
        return allSongsTableModel;
    }

    public DefaultTableModel getSearchedSongTableModel() {
        return searchedSongTableModel;
    }
}
