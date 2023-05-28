package Presentation.Controller;

import Business.UserManager;
import Presentation.View.MainView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe per controlar la vista del Register i les interaccions d'aquesta
 */
public class RegisterViewController implements ActionListener {

    // Declare atributs
    private final MainView mainView;
    private final RegisterView registerView;
    private final UserManager userManager;


    /**
     * Constructor de la classe RegisterViewController
     *
     * @param mainView, vista que controla totes les vistes del programa
     * @param registerView, vista de la classe
     * @param userManager, manager per operar funcions del user
     */
    public RegisterViewController(MainView mainView, RegisterView registerView, UserManager userManager) {
        this.mainView = mainView;
        this.registerView = registerView;
        this.userManager = userManager;
    }

    /**
     * Funció que servirà com a resposta quan els usuaris premin els botóns
     * i s'activin els listeners
     *
     * @param e l'esdeveniment a tramitar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> data = new ArrayList<>();

        switch (e.getActionCommand()) {
            //back case, per retornar a la vista inicial
            case RegisterView.BTN_BACK:
                mainView.showMainCard();
                break;
            //register case, boto que ens permetrar tramitar el procés de registre
            case RegisterView.BTN_REGISTER:
                String username = registerView.getJtfUsername().getText();
                String email = registerView.getJtfEmail().getText();
                String password = new String(registerView.getJtfPassword().getPassword());
                String repeatPassword = new String(registerView.getJtfRepeatPassword().getPassword());

                data.add(username);
                data.add(email);
                data.add(password);
                data.add(repeatPassword);

                if (userManager.isEmpty(data)) {
                    registerView.emptyFields();
                } else {
                    if (userManager.checkIfPasswordsEqual(password,repeatPassword)) {

                        boolean errorInMail = userManager.errorInMail(email);
                        if(errorInMail){
                           registerView.mailError();
                        }else{
                            boolean errorInPassword = userManager.errorInPassword(password);
                            if(errorInPassword){
                                registerView.passwordError();
                            }else{
                                if(userManager.userExistence(username, email, password)){
                                    registerView.userExistence();
                                }else{
                                    userManager.register(username, email, password);
                                    userManager.setUser(username, email, password);
                                    mainView.showMainMenuCard();
                                }
                            }
                        }
                    } else {
                        registerView.differentPassword();
                    }
                }
                break;
        }
    }
}