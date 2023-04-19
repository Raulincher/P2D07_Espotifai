package Presentation.Controller;

import Presentation.View.InitialView;
import Presentation.View.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuViewController implements ActionListener{

    private final MainMenuView mainMenuView;

    public MainMenuViewController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
