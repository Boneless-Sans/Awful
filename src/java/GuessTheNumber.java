package src.java;

import src.java.utils.AudioPlayer;
import src.java.utils.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class GuessTheNumber {
    private int number;
    private double hardNumber;
    private boolean gameMode;

    public GuessTheNumber(){
        //gameplay loop
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(500,500);

        JPanel mainScreen = new JPanel();
        mainScreen.setLayout(null);
        JLabel welcomeText = new JLabel("Welcome! Please Pick a Difficulty.");
        JButton buttonHard = new JButton("Hard");
        JButton buttonEasy = new JButton("Easy");

        buttonEasy.setFocusable(false);
        buttonHard.setFocusable(false);

        buttonEasy.setBackground(Color.GRAY);
        buttonHard.setBackground(Color.GRAY);

        buttonEasy.setFont(new Font("Arial", Font.ITALIC,20));
        buttonHard.setFont(new Font("Arial", Font.ITALIC,20));

        buttonEasy.setBounds(150,250,100,50);
        buttonHard.setBounds(250,250,100,50);

        welcomeText.setFont(new Font("Arial", Font.PLAIN, 25));
        welcomeText.setBounds(50,0,500,100);
        mainScreen.setBackground(Color.LIGHT_GRAY);
        mainScreen.setSize(new Dimension(500,500));

        buttonEasy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {AudioPlayer.play("hover.wav");}

        });
        buttonHard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {AudioPlayer.play("hover.wav");}
        });
        buttonEasy.addActionListener(e -> {
            AudioPlayer.play("select.wav");
            new GuessTheNumber();
        });
        buttonHard.addActionListener(e -> {
            AudioPlayer.play("select.wav");
            new GuessTheNumber();
        });

        mainScreen.add(welcomeText);
        mainScreen.add(buttonEasy);
        mainScreen.add(buttonHard);

        frame.add(mainScreen);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
