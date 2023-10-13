package src.java;

import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;

public class Panels {
    public static void main(String[] args){
        IconResize icon = new IconResize("src/resource/assets/icon.png", 100,100);
        icon.set();

        JPanel bluePanel = new JPanel();
        JPanel redPanel = new JPanel();
        JPanel greenPanel = new JPanel();

        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0,0,250,250);

        redPanel.setBackground(Color.RED);
        redPanel.setBounds(250,0,250,250);

        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(0,250,500,250);

        JLabel label = new JLabel();
        label.setText("Hello, World!");
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        label.setIcon(icon.getImage());

        //label.setHorizontalTextPosition(JLabel.BOTTOM); //should set center of the panel, instead it centers the text over the image??

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);
        greenPanel.add(label);
        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);

        //frame.pack();
    }
}
