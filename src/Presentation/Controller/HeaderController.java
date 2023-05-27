package Presentation.Controller;

import Business.SongManager;
import Business.UserManager;
import Persistance.dao.UserNotFoundException;
import Presentation.View.HeaderView;
import Presentation.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderController implements ActionListener {
    private final HeaderView headerView;
    private final UserManager userManager;
    private final SongManager songManager;
    private MainView mainView;

    public HeaderController(HeaderView headerView, UserManager userManager, SongManager songManager) {
        this.headerView = headerView;
        this.userManager = userManager;
        this.songManager = songManager;
    }

    public void addMainView(MainView mainView){
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
                    int resposta = headerView.showPopUps("Are you sure?","Delete Account");
                    if (resposta == 0) {
                        userManager.delete();
                        mainView.showMainCard();
                    }
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case HeaderView.BTN_LOGOUT:
                int resposta = headerView.showPopUps("Are you sure?","Log Out");
                if (resposta == 0) {
                    songManager.stopClip();
                    userManager.logout();
                    mainView.showMainCard();
                }
                break;
        }
    }
}
