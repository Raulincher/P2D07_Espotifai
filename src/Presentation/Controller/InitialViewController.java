package Presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Presentation.View.InitialView;
import Presentation.View.MainView;

public class InitialViewController implements ActionListener {

    private final MainView mainView;

    public InitialViewController(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case InitialView.BTN_LOGIN -> mainView.showLoginCard();
            case InitialView.BTN_REGISTER -> mainView.showRegisterCard();
            case InitialView.BTN_TEST -> mainView.showTestCard();
        }
    }


}
