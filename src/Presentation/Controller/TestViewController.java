package Presentation.Controller;

import Business.UserManager;
import Presentation.View.InitialView;
import Presentation.View.MainView;
import Presentation.View.TestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestViewController implements ActionListener {

    private final MainView mainView;
    private final UserManager userManager;


    public TestViewController(MainView mainView, UserManager userManager) {
        this.mainView = mainView;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case TestView.BTN_LOGOUT -> mainView.showMainCard();
            case TestView.BTN_DELETE -> userManager.delete();
        }
    }
}
