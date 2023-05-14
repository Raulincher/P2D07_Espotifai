package Presentation;

import Presentation.View.DeleteSongView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DeleteSongTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = {"Songs", "Artist", "Genre"};
    private Object[][] data = null;

    /*public DeleteSongTableModel() {
        super(data, COLUMN_NAMES);

    }*/

    public DeleteSongTableModel createTable() {
        DeleteSongTableModel auxTable = new DeleteSongTableModel();
        auxTable.addColumn("Song");
        auxTable.addColumn("Artist");
        auxTable.addColumn("Genre");

        if(data != null) {
            for (int i = 0; i < data.length; i++) {
                auxTable.addRow(data[i]);
            }
        } else {
            auxTable = null;
        }
        return auxTable;
    }

    public void setObject(ArrayList<String> deleteSongsInfo) {
        data = new Object[deleteSongsInfo.size()][3];

        for (int i = 0; i < deleteSongsInfo.size(); i++) {
            String[] songInfo = deleteSongsInfo.get(i).split("-");
            data[i][0] = songInfo[0];
            data[i][1] = songInfo[1];
            data[i][2] = songInfo[2];
        }
        // Arraylist --> Object[][]
        // DefaultTableModel deleteTableAux = new DefaultTableModel(deleteMatrix, COLUMN_NAMES);
        // deleteTableModel = deleteTableAux;
    }


}
