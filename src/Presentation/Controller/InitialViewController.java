package Presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Presentation.View.InitialView;
import Presentation.View.MainView;

public class InitialViewController implements ActionListener {

    // Preparem atributs
    private final MainView mainView;

    /**
     * Funció que servirà per com a constructor del InitialViewController
     *
     * @param mainView, per poder fer el canvi de cards
     *
     */
    public InitialViewController(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Funció que servirà per detectar si hem premut qualsevol botó de la vista incial
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //login case, ens mostra la card del login
            case InitialView.BTN_LOGIN -> mainView.showLoginCard();

            //register case, ens mostra la card del register
            case InitialView.BTN_REGISTER -> mainView.showRegisterCard();
        }
    }


}
