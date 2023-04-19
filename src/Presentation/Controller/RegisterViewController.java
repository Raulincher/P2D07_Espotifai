package Presentation.Controller;

import Business.UserManager;
import Presentation.View.MainView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterViewController implements ActionListener {
    private final MainView mainView;
    private final RegisterView registerView;
    private UserManager userManager;

    public RegisterViewController(MainView mainView, RegisterView registerView, UserManager userManager) {
        this.mainView = mainView;
        this.registerView = registerView;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> data = new ArrayList<>();

        switch (e.getActionCommand()) {
            case RegisterView.BTN_BACK:
                mainView.showMainCard();
                break;
            case RegisterView.BTN_REGISTER:
                String username = registerView.getJtfUsername().getText();
                String email = registerView.getJtfEmail().getText();
                String password = Arrays.toString(registerView.getJtfPassword().getPassword());
                password = password.substring(1,password.length()-1);
                String repeatPassword = Arrays.toString(registerView.getJtfRepeatPassword().getPassword());
                repeatPassword = repeatPassword.substring(1, repeatPassword.length()-1);

                data.add(username);
                data.add(email);
                data.add(password);
                data.add(repeatPassword);

                if (userManager.IsEmpty(data)) {
                    registerView.emptyFields();
                } else {
                    if (userManager.checkIfPasswordsEqual(password,repeatPassword)) {
                        //  userManager.Register(username, email, password);
                    } else {
                        registerView.differentPassword();
                    }
                }

                break;
        }
    }
}