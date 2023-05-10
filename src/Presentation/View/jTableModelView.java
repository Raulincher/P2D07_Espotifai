package Presentation.View;

import Business.Entities.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class jTableModelView extends DefaultTableModel {
    private String[] columnNames;
    private ArrayList<Song> listSongShow;
    private Object[][] data;
    private int columns;

    public jTableModelView(String[] columnNames, ArrayList<Song> listSongShow, int columns){
        this.columnNames = columnNames;
        this.listSongShow = listSongShow;
        this.columns = columns;
        this.data = new Object[this.listSongShow.size()][this.columns];
    }


    /*public DefaultTableModel createJtable(){


    }*/


}
