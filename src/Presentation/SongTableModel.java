package Presentation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class SongTableModel extends DefaultTableModel {
    private DefaultTableModel deleteTableModel;
    private DefaultTableModel allSongsTableModel;
    private DefaultTableModel searchedSongTableModel;
    private JTable deleteTable;
    private JTextField jtfBuscador;


    public SongTableModel() {
        deleteTableModel = new DefaultTableModel();
        allSongsTableModel = new DefaultTableModel();
        searchedSongTableModel = new DefaultTableModel();
    }

    public void buildDeleteTableModel(ArrayList<String> information){
        JPanel panelTable = new JPanel();

        deleteTableModel.addColumn("Song");
        deleteTableModel.addColumn("Artist");
        deleteTableModel.addColumn("Genre");

        for (String s : information) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            deleteTableModel.addRow(rowData);
        }
    }

    public void filtrar() {
        String busqueda = jtfBuscador.getText().toLowerCase();

        // Limpiar datos del modelo
        deleteTableModel.setRowCount(0);

        // Filtrar datos y agregarlos al modelo
        for (int i = 0; i < deleteTableModel.getRowCount(); i++) {
            if (deleteTableModel.getValueAt(i,0).equals(busqueda)) {
                System.out.println(deleteTableModel.getValueAt(1,0));
                 break;
            }
        }
    }


    public void buildAllSongsTableModel(ArrayList<String> information){
        allSongsTableModel.addColumn("Song");
        allSongsTableModel.addColumn("Artist");

        for (String s : information) {
            String[] songInfo = s.split("-");
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

}
