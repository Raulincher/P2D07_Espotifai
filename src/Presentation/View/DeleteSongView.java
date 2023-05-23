package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.DeleteSongViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class DeleteSongView extends JPanel {
    private static final String[] COLUMN_NAMES = {"Songs", "Artist", "Genre"};

    // Preparem tots els atributs
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;

    // Preparem els strings en cas de prémer un botó
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";

    // Preparem els elements de Swing
    private DefaultTableModel deleteTableModel;
    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;

    private TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnHeaders = {"Title", "Artist", "Genre"};

    /**
     * Funció que servirà com a constructor de la DeleteSongView
     *
     * @param headerView, vista per a posar el Header
     * @param utils, per usar tots els seus mètodes
     * @param footerView, vista per a posar el Footer
     */
    public DeleteSongView(HeaderView headerView, Utils utils, FooterView footerView){
        this.utils = utils;
        this.headerView = headerView;
        this.footerView = footerView;
        deleteTableModel = new DefaultTableModel(columnHeaders, 0);
        sorter =  new TableRowSorter<>(deleteTableModel);
    }

    /**
     * Funció que servirà per a connectar amb el controller i per
     * a activar tots els listeners
     *
     * @param deleteSongController, controller de la DeleteSongView
     */
    public void addDeleteSongController(DeleteSongViewController deleteSongController){
        jBuscador.getDocument().addDocumentListener(deleteSongController);
        table.addMouseListener(deleteSongController);
    }

    /**
     * Funció que servirà per a configurar tots els elements de
     * Swing de la vista
     *
     * No tindrà ni param ni return
     */
    public void configureDeleteSongView() {
        // Iniciem el BorderLayout i el configurem
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        // NORTH
        // Creem el JPanel del nord i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        // Afegim el JLabel i el Header
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.DELETE_LABEL));;
        north.add(headerView.configureHeader(logo));
        add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center i el configurem
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        // Declarem el buscador
        jBuscador = utils.textField();
        center.add(utils.panelBuscador(jBuscador),BorderLayout.NORTH);

        // Declarem la JTable
        table = new JTable(deleteTableModel);

        // Configurem el JScrollPane per la la taula i l'afegim
        JScrollPane scrollpane = createSongListTable(table);
        center.add(scrollpane, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);

        // SOUTH
        // Creem el JPanel per al sud i afegim el footer
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Funció que servirà com a getter per recollir la informació
     * que hagi introduït l'usuari
     *
     * @return jBuscador, valor introduït per l'usuari
     */
    public JTextField getjtfBuscador() {
        return jBuscador;
    }

    /**
     * Funció que servirà per a mostrar pop ups en cas que sigui
     * necessari
     *
     * @param error, missatge a mostrar
     */
    public void showPopUps(String error) {
        JOptionPane.showMessageDialog(this,error);
    }

    /**
     * Funció que servirà per a configurar la JTable i
     * afegir el seu respectiu JScrollPane
     *
     * @param table, JTable a configurar
     * @return JScrollPane(table), taula configurada
     */
    public JScrollPane createSongListTable(JTable table){
        // Creem el color gris
        Color gris = new Color(26,26,26);

        // Configurem mides i format de la JTable
        table.setRowHeight(40);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(Color.decode("#00DC00"));

        // Preparem el header de la taula per a que serveixi com a índex
        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        header.setFont(new Font("Gotham", Font.BOLD, 15));
        table.getTableHeader().setDefaultRenderer(header);

        // Preparem la font de la JTable
        table.setFont(new Font("Gotham", Font.BOLD, 15));

        return new JScrollPane(table);
    }

    /**
     * Funció que servirà per a filtrar la cançó que vols esborrar
     *
     * @param deleteSongs, array amb la cançó i les seves dades
     */
    public void fillDeleteTable(ArrayList<String> deleteSongs) {
        deleteTableModel.setRowCount(0);

        // Obrim bucle per a mostrar la informació de la cançó
        for (String s : deleteSongs) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2]};
            deleteTableModel.addRow(rowData);
        }
    }

    /**
     * Funció que servirà per a buscar la cançó desitjada
     *
     * @param query, valor introduït de l'usuari
     * @param sorter, informació per a canviar la taula
     */
    public void search(String query, TableRowSorter<DefaultTableModel> sorter) {
        // Ens assegurem que s'hagi introduït alguna cosa
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        }
        // Busca per la primera columna, pel títol de la cançó
        else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0)); // Search by the first column (title)
        }
    }

    /**
     * Funció que servirà com a getter per a obtenir
     * resposta de la DefaultTableModel
     *
     * @return sorter
     */
    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }

    /**
     * Funció per a obtenir el nom de la cançó que
     * l'usuari desitja eliminar
     *
     * @param point, índex de la fila a la que es troba la song
     * @return songTitle, títol de la cançó
     */
    public String obtainSongNameToDelete(Point point) {
        int indexFila = table.rowAtPoint(point);
        System.out.println(indexFila);

        // Obté la informació a partir de la cançó seleccionada
        String songTitle = (String) table.getValueAt(indexFila, 0);
        return songTitle;
    }

    /**
     * Funció que servirà per a obtenir l'índex de la
     * cançó que l'usuari desitja eliminar
     *
     * @param songTitle, títol de la cançó
     * @return indexSong, índex de la cançó
     */
    public int obtainSongIndexToDelete(String songTitle) {
        int indexSong = 0;

        // Recorrem el bucle fins a trobar la cançó en qüestió
        for (int i = 0; i < deleteTableModel.getRowCount(); i++) {
            if (deleteTableModel.getValueAt(i,0).equals(songTitle)) {
                indexSong = i;
            }
        }
        return indexSong;
    }

    /**
     * Funció que servirà per a mostrar el pop up
     * per a confirmar que es vol esborrar la cançó
     *
     * @param songTitle, títol de la cançó
     * @return result, opció cliquejada per l'usuari
     */
    public int confirmationDeletePopUp(String songTitle) {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to proceed deleting " + songTitle + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

        return result;
    }

    /**
     * Funció per a netejar el searcher un cop
     * s'hagi introduït informació
     *
     * No tindrà param ni return
     */
    public void clearSearcher() {
        jBuscador.setText("");
    }

}