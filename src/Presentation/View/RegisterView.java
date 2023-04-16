package Presentation.View;

import Presentation.Controller.RegisterViewController;

import javax.swing.*;

public class RegisterView extends JPanel {
    private JButton jSubmitRegister;

    public void configureRegisterView() {
        jSubmitRegister = new JButton("Submit");
    }


    public void addRegisterController(RegisterViewController registerController){
        jSubmitRegister.addActionListener(registerController);
    }
}
