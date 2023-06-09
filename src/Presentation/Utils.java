package Presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Classe per crear variables determinades i poder seguir un format homogeni
 */
public class Utils extends JFrame {

    /**
     * Funció que servirà com a constructor d'utils
     *
     * No tindrà ni param ni return
     */
    public Utils(){}

    /**
     * Funció de tipus JProgressBar que servirà com a constructor
     * de la barra de progrés de la cançó al footer
     *
     * @param max, temps màxim de la cançó
     * @param min, temps mínim de la cançó
     * @return pbar, barra de progrés construïda
     */
    public JProgressBar progressBar(int max, int min){
        JProgressBar pbar;

        // Creem la barra de progrés
        pbar = new JProgressBar();

        // Marquem màxims i mínims
        pbar.setMinimum(min);
        pbar.setMaximum(max);
        return pbar;
    }

    /**
     * Funció de tipus JButton que servirà com a constructor
     * dels botons que tinguin imatge
     *
     * @param labelImg, imatge del botó
     * @return button, JButton construït
     */
    public JButton buttonImg(Icon labelImg){
        // Creem el JButton
        JButton button = new JButton(labelImg);

        // Configurem el format
        button.setBackground(Color.decode("#00000000"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    /**
     * Funció de tipus JLabel que servirà com a constructor dels
     * textos que s'hagin de transformar en un JLabel
     *
     * @param text, text en qüestió
     * @return label, JLabel construït
     */
    public JLabel label(String text){
        // Creem el JLabel
        JLabel label = new JLabel(text);

        // Configurem i li donem format
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gotham", Font.BOLD, 27));
        return label;
    }

    /**
     * Funció de tipus JPasswordField que servirà com a
     * constructor de les zones que necessitin la introducció
     * de contrasenyes
     *
     * @return jPassword, JPasswordField construït
     */
    public JPasswordField passwordField(){
        // Creem el JPasswordField
        JPasswordField jPassword = new JPasswordField();

        // Li donem format
        jPassword.setFont(new Font("Gotham", Font.BOLD, 20));
        jPassword.setPreferredSize(new Dimension(300,40));
        jPassword.setMinimumSize(new Dimension(300,40));
        return jPassword;
    }

    /**
     * Funció de tipus JTextField que servirà com a constructor
     * de les zones que necessitin la introducció de text
     *
     * @return jUsername, JTextField construït
     */
    public JTextField textField(){
        JTextField jUsername = new JTextField();
        jUsername.setFont(new Font("Gotham", Font.BOLD, 20));
        jUsername.setPreferredSize(new Dimension(300, 40));
        jUsername.setMinimumSize(new Dimension(300,40));
        return jUsername;
    }

    /**
     * Funció de tipus JPanel que servirà com a constructor
     * d'un panel per a buscar songs
     *
     * @return panelBuscador, JPanel construït
     */
    public JPanel panelBuscador(JTextField jBuscador) {
        // Creem el JPanel
        JPanel panelBuscador = new JPanel();

        // Li donem format
        panelBuscador.setBackground(Color.BLACK);
        jBuscador.setPreferredSize(new Dimension(300, 40));
        jBuscador.setMinimumSize(new Dimension(300,40));
        panelBuscador.add(jBuscador);
        return panelBuscador;
    }

    /**
     * Funció de tipus JTextField que servirà com a constructor
     * d'un textfield per playlists
     *
     * @return jUsername, textField ja fet
     */
    public JTextField textFieldPlaylists(){
        JTextField jUsername = new JTextField();
        jUsername.setFont(new Font("Gotham", Font.BOLD, 15));
        jUsername.setPreferredSize(new Dimension(300, 30));
        jUsername.setMinimumSize(new Dimension(300,30));
        return jUsername;
    }
}
