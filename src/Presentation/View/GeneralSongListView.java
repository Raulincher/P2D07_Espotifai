package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.GeneralSongListViewController;
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

public class GeneralSongListView extends JPanel {

    // Iniciem els strings per quan es premi un botó
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_BUSCADOR = "BTN_BUSCADOR";

    // Preparem els atributs
    private final Utils utils;

    // Preparem els components de Swing
    private JTextField jBuscador;
    private JButton jCerca;
    private JTable table;
    private DefaultTableModel songsTableModel;

    // Preparem l'array de Strings per a les cançons
    private static String[] columnHeaders = {"Title", "Artist", "Genre","Album","Uploaded by"};

    /**
     * Funció que servirà com a constructor de la StatisticsView
     *
     * @param , vista per a posar el Header
     * @param utils, per usar tots els seus mètodes
     * @param , vista per a posar el Footer
     */
    public GeneralSongListView(Utils utils){
        this.utils = utils;
        songsTableModel = new DefaultTableModel(columnHeaders, 0);
    }

    /**
     * Funció que servirà per a vincular amb el controller de GeneralSongListView
     * i per a activar tots els seus listeners
     *
     * @param generalSongListViewController, controller en de la vista
     */
    public void addGeneralSongListController(GeneralSongListViewController generalSongListViewController){
        jCerca.addActionListener(generalSongListViewController);
        table.addMouseListener(generalSongListViewController);
    }

    /**
     * Funció que servirà per a configurar tots els elements de Swing
     * del GeneralSongListView
     *
     * No tindrà param ni return
     */
    public void configureGeneralSonglistView() {
        // Creem el BorderLayout, background i color gris
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Color gris = new Color(26,26,26);

        //NORTH
        // Creem el JPanel i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);

        // Afegim el JLabel amb el Header
        Icon logo = new ImageIcon(String.valueOf(AssetsFiles.MUSIC_LABEL));;
        //north.add(headerView.configureHeader(logo));
        //add(north, BorderLayout.NORTH);

        //CENTER
        // Creem el JPanel del center i el configurem
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.BLACK);
        center.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));

        // Afegim el JButton per al buscador
        Icon buscadorBtn = new ImageIcon(String.valueOf(AssetsFiles.BUSCADOR_BUTTON_IMG));
        jCerca = new JButton(buscadorBtn);
        jCerca.setActionCommand(BTN_BUSCADOR);

        // Afegim el JTextField per a buscar la cançó
        jBuscador = new JTextField();
        center.add(utils.panelBuscador(jBuscador),BorderLayout.NORTH);

        // Afegim una JTable on es veuran totes les cançons
        table = new JTable(songsTableModel);

        // Afegim un JScrollPane per fer scroll dins de la taula
        JScrollPane scrollpane = createSongListTable(table);
        center.add(scrollpane, BorderLayout.CENTER);

        // Ho afegim tot dins del BorderLayout
        add(center, BorderLayout.CENTER);

        // Creem la DefaultTableModel i la configurem
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(songsTableModel);
        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);

        // Configurem el listener en cas que es premi el botó de buscar
        jBuscador.getDocument().addDocumentListener(new DocumentListener() {
            /**
             * Funció que servirà per a insertar la informació nova a la table
             *
             * @param e, update a insertar
             */
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }

            /**
             * Funció que servirà esborrar la informació prèviament buscada
             *
             * @param e, update a esborrar
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }

            /**
             * Funció que servirà per a marcar la update que s'acaba d'afegir
             *
             * @param e, canvi realitzat
             */
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jBuscador.getText(), sorter);
            }
        });

        // SOUTH
        // Creem el JPanel del sud i el configurem
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));

        // Afegim el Footer i ho afegim tot al BorderLayout
        //south.add(footerView.configureFooter());
        //add(south, BorderLayout.SOUTH);

    }

    /**
     * Funció que servirà com a constructor de la taula
     * amb la qual es visualitzaran totes les cançons
     *
     * @param table, JTable amb la SongList creada
     */
    public JScrollPane createSongListTable(JTable table){
        // Creem el color gris
        Color gris = new Color(26,26,26);

        // Configurem les mides i color de la taula
        table.setRowHeight(60);
        table.setGridColor(Color.gray);
        table.setBackground(gris);
        table.setForeground(Color.WHITE);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(Color.decode("#00DC00"));

        // Configurem el header de la taula amb les seves mides i color
        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setForeground(Color.decode("#00DC00"));
        header.setFont(new Font("Gotham", Font.BOLD, 20));
        table.getTableHeader().setDefaultRenderer(header);

        // Definim la font dels continguts de la taula
        table.setFont(new Font("Gotham", Font.BOLD, 20));

        return new JScrollPane(table);
    }

    /**
     * Funció per a retornar el valor introduït del buscador
     *
     * @return jBuscador, informació resultant
     */
    public JTextField getjBuscador() {
        return jBuscador;
    }

    /**
     * Funció per a retornar el la JTable
     *
     * @return table, JTable a retornar
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Funció que servirà per a filtrar la informació
     * que s'hagi introduït per l'usuari
     *
     * @param songs, array amb la informació de totes les songs
     */
    public void fillTable(ArrayList<String> songs) {
        songsTableModel.setRowCount(0);

        // Iniciem un bucle que recorri l'array i estructurem la informació
        for (String s : songs) {
            String[] songInfo = s.split("-");
            Object[] rowData = {songInfo[0], songInfo[1], songInfo[2], songInfo[3], songInfo[4]};
            songsTableModel.addRow(rowData);
        }
    }

    /**
     * Funció que servirà per a buscar la cançó desitjada
     *
     * @param query, valor introduït de l'usuari
     * @param sorter, informació per a canviar la taula
     */
    private void search(String query, TableRowSorter<DefaultTableModel> sorter) {

        // Ens assegurem que s'hagi introduït alguna cosa
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        }
        // Busca per la primera columna, pel títol de la cançó
        else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0));
        }
    }
}
