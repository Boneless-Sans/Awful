package src.java;

import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;

public class Fish extends JFrame {
    public static void main(String[] args){new Fish();}

    public Fish(){
        setSize(500,500);
        setTitle("CONGRATULATIONS!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel panelText = new JPanel(null);

        panelText.add(new JLabel("Congratulations, you are our lucky winner!"));

        IconResize icon = new IconResize("le_fishe.png", 10,10);
        icon.set("le_fishe.png", 10,10);
        JLabel fish = new JLabel(icon.getImage());

        add(panelText);
        add(fish);
        //pack();
        setVisible(true);
    }
}
