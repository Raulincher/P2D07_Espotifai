package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * Classe de la vista statistics la qual fa extend d'un jpanel
 */
public class StatisticsView extends JPanel {

    // Preparem els strings en cas que es premi el botó back
    public static final String BTN_BACK = "BTN_BACK";

    // Preparem els elements de Swing
    private Map<String, Integer> genreMap;

    /**
     * Funció que servirà com a constructor de la StatisticsView
     *
     * No tindrà param ni return
     */
    public StatisticsView(){}

    public void setGenreMap(Map<String, Integer> genreMap) {
        this.genreMap = genreMap;
        repaint();
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

        // CENTER
        // Creem el JPanel del center amb el FlowLayout i el configurem
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setBorder(createEmptyBorder(30, 0, 0, 0));
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
                if (genreMap == null || genreMap.isEmpty()) {
                    return;
                }
                // Preparem les variables i les eines
                super.paintComponent(graphics);
                Graphics2D graph2D = (Graphics2D) graphics;

                // Agafem el gènere amb el màxim valor
                int genreValue = Collections.max(genreMap.values());

                // Calculem les mides de les barres
                int availableHeight = getHeight() - 30; // Restem per la barra guia
                int barHeight = availableHeight / genreMap.size();
                int maxWidth = getWidth();

                // Preparem la fila guia i els seus nombres
                int guideRowY = getHeight() - 20;
                graph2D.setColor(Color.WHITE);
                graph2D.setFont(new Font("Gotham", Font.BOLD, 12));
                for (int i = 1; i <= genreValue; i++) {

                    // Dibuixem l'amplada de la fila
                    // Important recalcar: passem la i a double per a poder fer la operació
                    // i un cop feta la operació, passem el resultat a int
                    int guideRowX = (int) ((double) i / genreValue * maxWidth);
                    graph2D.drawString(String.valueOf(i), guideRowX - 42, guideRowY);
                    graph2D.drawLine(guideRowX -40, guideRowY - 15, guideRowX -40, guideRowY - 20);
                }

                // Iniciem un bucle que recorri tot el mapa
                int y = 0;
                for (Map.Entry<String, Integer> entry : genreMap.entrySet()) {
                    // Agafem gènere i el seu valor
                    String genre = entry.getKey();
                    int count = entry.getValue();

                    // Configurem l'alçada del nom del gènere i nombre segons els gèneres afegits
                    int numAdded = genreMap.size();
                    float valors = 0;
                    if (numAdded == 1) {
                        valors = 20;
                    }
                    else if(numAdded > 1  && numAdded < 4) {
                        valors = y + barHeight/8;
                    }else if (numAdded > 3 && numAdded < 6) {
                        valors = y + barHeight/4;
                    }
                    else if (numAdded > 5 && numAdded < 9) {
                        valors = y + barHeight/2;
                    }
                    // Dibuixem l'amplada de la barra
                    // Important recalcar: passem el count a double per a poder fer la operació
                    // i un cop feta la operació, passem el resultat a int
                    int barWidth = (int) ((double) count / genreValue * maxWidth);

                    // Configurem les mides i color de les barres
                    graph2D.setColor(Color.GREEN);
                    graph2D.fillRect(100, y, barWidth - 140, 20);

                    // Configurem format dels Strings de cada gènere
                    graph2D.setColor(Color.WHITE);
                    graph2D.setFont(new Font("Gotham", Font.BOLD, 12));
                    graph2D.drawString(genre, 5, valors);
                    graph2D.drawString(String.valueOf(count), barWidth - 20, valors);

                    // Avancem la Y per la següent barra
                    y += barHeight;
                }
            }
        };
        // Configurem el gràfic del JPanel
        graphPanel.setPreferredSize(new Dimension(1000, 340));
        graphPanel.setBackground(Color.BLACK);
        center.add(graphPanel);

        // L'afegim al BorderLayout
        add(center, BorderLayout.CENTER);
    }
}