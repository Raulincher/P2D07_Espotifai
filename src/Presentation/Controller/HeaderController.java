package Presentation.Controller;

import Business.UserManager;
import Persistance.dao.UserNotFoundException;
import Presentation.View.HeaderView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderController implements ActionListener {
    private final HeaderView headerView;
    private final UserManager userManager;
    private final MainView mainView;

    public HeaderController(HeaderView headerView, UserManager userManager, MainView mainView) {
        this.headerView = headerView;
        this.userManager = userManager;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HeaderView.BTN_BACK:
                mainView.showMainMenuCard();
                break;
            case HeaderView.BTN_DELETEACC:
                try {
                    userManager.delete();
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                }
                mainView.showMainCard();
                break;
            case HeaderView.BTN_LOGOUT:
                userManager.logout();
                mainView.showMainCard();
                break;
        }
    }
}
