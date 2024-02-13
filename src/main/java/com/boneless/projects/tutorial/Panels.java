package com.boneless.projects.tutorial;

import com.boneless.projects.utils.AudioPlayer;
import com.boneless.projects.utils.IconResize;

import javax.swing.*;
import java.awt.*;

public class Panels {
    public static void main(String[] args){
        IconResize icon = new IconResize("src/resource/assets/pic.png", 50,50);

        //AudioPlayer clip = new AudioPlayer();

        JPanel bluePanel = new JPanel();
        JPanel redPanel = new JPanel();
        JPanel greenPanel = new JPanel();

        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0,0,250,250);
        bluePanel.setLayout(null);

        redPanel.setBackground(Color.RED);
        redPanel.setBounds(250,0,250,250);
        redPanel.setLayout(null);

        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(0,250,500,250);
        greenPanel.setLayout(null);

        JLabel label = new JLabel();
        label.setText("Hello, World!");
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        label.setIcon(icon.getImage());
//        label.setVerticalAlignment(JLabel.BOTTOM); //only needed with a border
//        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBounds(10,10,250,250);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);

        greenPanel.add(label);
        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
    }
}
