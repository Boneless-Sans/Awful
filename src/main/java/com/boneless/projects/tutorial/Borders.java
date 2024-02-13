package com.boneless.projects.tutorial;

import javax.swing.*;
import java.awt.*;

public class Borders {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout()); //use (num1, num2) to set margin from center
        frame.setVisible(true);
        frame.setSize(500,500);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GREEN);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.YELLOW);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.MAGENTA);
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.BLUE);

        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(100,100));
        panel3.setPreferredSize(new Dimension(100,100));
        panel4.setPreferredSize(new Dimension(100,100));
        panel5.setPreferredSize(new Dimension(100,100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);
        //panels can be added to panels
    }
}
