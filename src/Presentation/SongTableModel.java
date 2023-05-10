package Presentation;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SongTableModel extends DefaultTableModel {
    private DefaultTableModel deleteTableModel;

    public void buildDeleteTableModel(ArrayList<String> information){
        deleteTableModel = new DefaultTableModel();

        deleteTableModel.addColumn("Song");
        deleteTableModel.addColumn("Artist");
        deleteTableModel.addColumn("Genre");

        for (int i = 0; i < information.size(); i++) {
            String[] songInfo = information.get(i).split("-");
            deleteTableModel.addRow(new Object[] {
                    songInfo[0],
                    songInfo[1],
                    songInfo[2]
            });
        }
    }

    public DefaultTableModel getDeleteTableModel() {
        return deleteTableModel;
    }
}
