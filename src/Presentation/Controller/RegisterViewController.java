package Presentation.Controller;

import Presentation.View.LoginView;
import Presentation.View.MainView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterViewController implements ActionListener {

    private final MainView mainView;
    private final RegisterView registerView;

    public RegisterViewController(MainView mainView, RegisterView registerView) {
        this.mainView = mainView;
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
