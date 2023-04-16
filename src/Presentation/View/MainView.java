package Presentation.View;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {

    private final InitialView initialView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final TestView testView;


    private static final String CARD_MAIN = "CARD_MAIN";
    private static final String CARD_LOGIN = "CARD_LOGIN";
    private static final String CARD_REGISTER = "CARD_REGISTER";
    private static final String CARD_TEST = "CARD_TEST";


    private final CardLayout cardManager;

    public MainView(InitialView initialView, RegisterView registerView, LoginView loginView, TestView testView) {
        this.initialView = initialView;
        this.registerView = registerView;
        this.loginView = loginView;
        this.testView = testView;


        cardManager = new CardLayout();
        getContentPane().setLayout(cardManager);
        configureWindow();

        configureInitial();
        configureLogin();
        configureRegister();
        configureTestView();
    }

    private void configureWindow(){
        setTitle("Espotifai");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLocationRelativeTo(null);
    }

    private void configureInitial(){
        this.initialView.configureInitialView();
        this.getContentPane().add(initialView, CARD_MAIN);
    }

    private void configureLogin(){
        this.loginView.configureLoginView();
        this.getContentPane().add(loginView, CARD_LOGIN);
    }

    private void configureRegister(){
        this.registerView.configureRegisterView();
        this.getContentPane().add(registerView, CARD_REGISTER);
    }

    private void configureTestView(){
        this.testView.configureTestView();
        this.getContentPane().add(testView, CARD_TEST);
    }

    public void showMainCard(){
        cardManager.show(getContentPane(), CARD_MAIN);
    }

    public void showLoginCard(){
        cardManager.show(getContentPane(), CARD_LOGIN);
    }

    public void showRegisterCard(){
        cardManager.show(getContentPane(), CARD_REGISTER);
    }
    public void showTestCard(){
        cardManager.show(getContentPane(), CARD_TEST);
    }


    public void start() {
        showMainCard();
        setVisible(true);
    }
}
