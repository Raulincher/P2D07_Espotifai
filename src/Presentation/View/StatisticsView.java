package Presentation.View;

import Business.SongManager;
import Presentation.AssetsFiles;
import Presentation.Controller.HeaderController;
import Presentation.Controller.StatisticsViewController;
import Presentation.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static javax.swing.BorderFactory.createEmptyBorder;

public class StatisticsView extends JPanel {

    // Preparem els strings en cas que es premi el botó back
    public static final String BTN_BACK = "BTN_BACK";

    // Preparem els atributs
    private final Utils utils;
    private final HeaderView headerView;
    private final FooterView footerView;
    private final SongManager songManager;

    /**
     * Funció que servirà com a constructor de la StatisticsView
     *
     * @param headerView, vista per a posar el Header
     * @param utils, per usar tots els seus mètodes
     * @param footerView, vista per a posar el Footer
     * @param songManager, per a usar les estadístiques
     */
    public StatisticsView(HeaderView headerView, Utils utils, FooterView footerView, SongManager songManager){
        this.headerView = headerView;
        this.footerView = footerView;
        this.utils = utils;
        this.songManager = songManager;
    }

    /**
     * Funció que servirà per a connectar amb el seu
     * controller i activar els listeners
     *
     * @param statisticsViewController, controller de les Statistics
     */
    public void addStatisticsController(StatisticsViewController statisticsViewController){
        //jback.addActionListener(statisticsViewController);
    }

    /**
     * Funció que servirà per a configurar la StatisticsView
     * i tots els seus components de Swing
     *
     * No tindrà ni param ni return
     */
    public void configureStatisticsView() {
        // Creem el BorderLayout
        setLayout(new BorderLayout());

        // NORTH
        // Creem el JPanel del nord amb el FlowLayout
        JPanel north =  new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Configurem el JPanel i afegim el Label
        Icon statsImg = new ImageIcon(String.valueOf(AssetsFiles.STATISTICS_LABEL));
        north.add(headerView.configureHeader(statsImg));
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        // CENTER
        // Creem el JPanel del center amb el FlowLayout i el configurem
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(Color.BLACK);

        // Creem un nou sub-JPanel que servirà per visualitzar les estadístiques
        JPanel graphPanel = new JPanel() {
            /**
             * Funció amb override que servirà per a dibuixar
             * el gràfic dins del JPanel contínuament
             *
             * @param graphics, per recollir els gràfics que volem dibuixar
             */
            @Override
            protected void paintComponent(Graphics graphics) {
                // Preparem les variables i les eines
                super.paintComponent(graphics);
                Graphics2D graph2D = (Graphics2D) graphics;

                // Agafem el mapa amb els gèneres i el seu nombre
                Map<String, Integer> genreMap = songManager.createGenreMap();

                // Agafem el gènere amb el màxim valor
                int genreValue = Collections.max(genreMap.values());

                // Calculem les mides de les barres
                int barWidth = getWidth() / (genreMap.size() * 2);
                int maxHeight = getHeight();

                // Iniciem un bucle que recorri tot el mapa
                int x = 0;
                for (Map.Entry<String, Integer> entry : genreMap.entrySet()) {
                    // Agafem gènere i el seu valor
                    String genre = entry.getKey();
                    int count = entry.getValue();

                    // Dibuixem l'alçada de la barra
                    // Important recalcar: passem el count a double per a poder fer la operació
                    // i un cop feta la operació, passem el resultat a int
                    int barHeight = (int) ((double) count / genreValue * maxHeight);

                    // Configurem les mides i color de les barres
                    graph2D.setColor(Color.GREEN);
                    graph2D.fillRect(x, getHeight() - barHeight, barWidth, barHeight -25);

                    // Configurem format dels Strings de cada gènere
                    graph2D.setColor(Color.WHITE);
                    graph2D.setFont(new Font("Gotham", Font.BOLD, 12));
                    graph2D.drawString(genre, x, getHeight() - 5);

                    // Avancem la X per la següent barra
                    x += barWidth * 2;
                }
            }
        };
        // Configurem el gràfic del JPanel
        graphPanel.setPreferredSize(new Dimension(500, 300));
        graphPanel.setBackground(Color.BLACK);
        center.add(graphPanel);

        // L'afegim al BorderLayout
        add(center, BorderLayout.CENTER);

        // SOUTH
        // Creem el JPanel del sud, creant el color gris i configurant mides per al Footer
        Color gris = new Color(26,26,26);
        JPanel south = new JPanel();
        south.setBackground(gris);
        south.setBorder(createEmptyBorder(2, 0, 2, 0));
        south.add(footerView.configureFooter());
        add(south, BorderLayout.SOUTH);
    }

}
