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

        return button;
    }

}
