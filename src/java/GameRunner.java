package src.java;

import java.awt.Font;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class GameRunner {
    public static void main(String[] args){

        ImageIcon image = new ImageIcon("src/resource/assets/main.png");
        Border border = BorderFactory.createLineBorder(Color.GREEN,3);
        JLabel label = new JLabel();

        label.setText("^STINKY^");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); // centers text over image
        label.setVerticalTextPosition(JLabel.BOTTOM); //sets text top of image
        label.setForeground(new Color(123, 92, 0));
        label.setFont(new Font("Comic Sans MC", Font.PLAIN,200));
        //label.setIconTextGap(50); //sets text gap to image
        label.setBackground(Color.BLACK); //sets background color
        label.setOpaque(true); //display background color
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER); //sets vertical position for icon
        label.setHorizontalAlignment(JLabel.CENTER); //same as ^^
        // label.setBounds(100,100,500,500); //sets POS and dimensions


        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setSize(720,720);
        //frame.setLayout(null);
        frame.add(label);
        frame.pack();

    }
}
