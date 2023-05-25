package Presentation.View;

import Presentation.AssetsFiles;
import Presentation.Controller.HeaderController;
import Presentation.Utils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class HeaderView extends JPanel {

    // Preparem els botons
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_DELETEACC = "BTN_DELETEACC";

    // Afegim atributs
    private final Utils utils;

    // Afegim JButtons
    private JButton jLogOut;
    private JButton jDeleteAcc;
    private JButton jBack;

    /**
     * Funció que servirà com a constructor del HeaderView
     *
     * @param utils, per a utilitzar els mètodes d'utils
     */
    public HeaderView(Utils utils){
        this.utils = utils;
    }

    /**
     * Funció que servirà per a vincular amb el seu controller
     * i activar tots els listeners
     *
     * @param headerController, controller del HeaderView
     */
    public void addHeaderController(HeaderController headerController){
        // Afegim tots els listeners
        jLogOut.addActionListener(headerController);
        jDeleteAcc.addActionListener(headerController);
        jBack.addActionListener(headerController);

    }

    /**
     * Funció de tipus JPanel que servirà per configurar com
     * es veurà el header
     *
     * @param icon, label que canviarà en cada diferent vista
     * @return north, JPanel que composarà el Header
     */
    public JPanel configureHeader(Icon icon) {

        // Creem el botó de back i el configurem
        Icon backbtn = new ImageIcon(String.valueOf(AssetsFiles.BACK_LITTLEBUTTON_IMG));
        jBack = utils.buttonImg(backbtn);
        jBack.setActionCommand(BTN_BACK);

        // Creem el botó de LogOut i el configurem
        Icon logoutBtn = new ImageIcon(String.valueOf(AssetsFiles.LOGOUT_LITTLEBUTTON_IMG));
        jLogOut = utils.buttonImg(logoutBtn);
        jLogOut.setActionCommand(BTN_LOGOUT);

        // Creem el botó de delete account i el configurem
        Icon deleteAccBtn = new ImageIcon(String.valueOf(AssetsFiles.DELETEACC_LITTLEBUTTON_IMG));
        jDeleteAcc = utils.buttonImg(deleteAccBtn);
        jDeleteAcc.setActionCommand(BTN_DELETEACC);
        // Creem el JPanel i el configurem
        JPanel north = new JPanel();
        north.setBackground(Color.black);
        north.setBorder(createEmptyBorder(10, 0, 0, 0));

        // Creem el JLabel, el configurem i l'afegim
        JLabel usedLabel = new JLabel(icon);
        usedLabel.setLayout(new GridLayout(4, 1, 0, 50));
        north.add(usedLabel);

        // Afegim tots els components
        north.add(jBack);
        north.add(jLogOut);
        north.add(jDeleteAcc);

        return north;
    }


    /**
     * Funció que servirà per a mostrar tots els pop ups
     * en cas que es necessiti
     *
     * @param error, avís1 a mostrar
     * @param error2, avís2 a mostrar
     * @return resposta, pop up que avisarà a l'usuari
     */
    public int showPopUps(String error, String error2) {
        int resposta = JOptionPane.showConfirmDialog(this,error, error2, JOptionPane.YES_NO_OPTION);
        return resposta;
    }
}
