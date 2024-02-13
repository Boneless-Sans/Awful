package com.boneless.projects.tutorial;

import com.boneless.projects.utils.AudioPlayer;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import java.awt.*;

public class Buzzer extends JFrame {
    public Buzzer(){
        setSize(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        SystemUI.set();

        JButton correct = new JButton("Correct");
        correct.addActionListener(e -> {
            AudioPlayer.play("correct.wav");
        });

        JButton incorrect = new JButton("Incorrect");
        incorrect.addActionListener(e -> {
            AudioPlayer.play("incorrect.wav");
        });

        JButton both = new JButton("Both");
        both.addActionListener(e -> {
            AudioPlayer.play("correct.wav");
            AudioPlayer.play("incorrect.wav");
        });

        correct.setFocusable(false);
        incorrect.setFocusable(false);

        add(correct);
        add(incorrect);
        //add(both);
        setVisible(true);
    }
    public static void main(String[] args){
        new Buzzer();
    }
}
