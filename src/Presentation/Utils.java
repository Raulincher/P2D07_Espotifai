package Presentation;

import javax.swing.*;
import java.awt.*;

public class Utils extends JFrame {

    public Utils(){}

    public JButton buttonText(String text){
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#00000000"));
        button.setFont(new Font("Helvetica", Font.ITALIC, 20));
        button.setMaximumSize(new Dimension (200,100));
        return button;
    }

    public JButton buttonImg(Icon labelImg){
        JButton button = new JButton(labelImg);
        button.setBackground(Color.decode("#00000000"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        //button.setFocusPainted(false);
        //button.setOpaque(false);
        return button;
    }

    public JLabel label(String text){
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gotham", Font.BOLD, 27));

        return label;
    }

    public JPasswordField passwordField(){
        JPasswordField jPassword = new JPasswordField();
        jPassword.setFont(new Font("Gotham", Font.BOLD, 20));
        jPassword.setPreferredSize(new Dimension(300,40));
        jPassword.setMinimumSize(new Dimension(300,40));
        return jPassword;
    }

    public JTextField textField(){
        JTextField jUsername = new JTextField();
        jUsername.setFont(new Font("Gotham", Font.BOLD, 20));
        jUsername.setPreferredSize(new Dimension(300, 40));
        jUsername.setMinimumSize(new Dimension(300,40));
        return jUsername;
    }

}
