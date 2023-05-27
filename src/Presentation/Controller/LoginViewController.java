package Presentation.Controller;

import Business.UserManager;
import Persistance.dao.UserNotFoundException;
import Presentation.View.LoginView;
import Presentation.View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe per controlar les interaccions de la vista LoginView
 */
public class LoginViewController implements ActionListener {

    // Declare atributs
    private final MainView mainView;
    private final LoginView loginView;
    private final UserManager userManager;


    /**
     * Constructor de la classe LoginViewController
     *
     * @param mainView, vista que controla totes les vistes del programa
     * @param loginView, vista de la classe
     * @param userManager, manager per operar funcions del user
     */
    public LoginViewController(MainView mainView, LoginView loginView, UserManager userManager) {
        this.mainView = mainView;
        this.loginView = loginView;
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

        switch(e.getActionCommand()){
            case LoginView.BTN_LOGIN :
                // Agafem els parametres escrits per l'usuari
                String username = loginView.getJUsername().getText();
                String password = new String(loginView.getJPassword().getPassword());
                data.add(username);
                data.add(password);

                // Controlem si algun del parametre està buit
                if (userManager.isEmpty(data)) {
                    loginView.showPopUps("It's Empty!");
                } else {
                    // Comprovem si l'usuari existeix
                    try {
                        boolean userExists = userManager.userExistence(data.get(0), data.get(0), data.get(1));
                        if (userManager.login(data)) {

                            userManager.setUser(username, username, password);
                            mainView.showMainMenuCard();
                        }else{
                            if(!userExists){
                                loginView.showPopUps("User not found!");

                            }else{
                                loginView.showPopUps("Incorrect password!");
                            }
                        }
                    } catch (UserNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                loginView.clearInfo();
                break;
            case LoginView.BTN_BACK:
                // Configurem el boto Back
                mainView.showMainCard();
                break;
        }
    }
}
