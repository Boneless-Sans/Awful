package com.boneless.projects;

import com.boneless.code.neighborhood.PainterPlus;

import javax.swing.*;
import java.awt.*;

public class GameRunner {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500,500);

        JPanel panel = new JPanel();
        panel.setBackground(Color.red);

        frame.add(panel, BorderLayout.CENTER);

        frame.add(createBorder(), BorderLayout.NORTH);
        frame.add(createBorder(), BorderLayout.EAST);
        frame.add(createBorder(), BorderLayout.SOUTH);
        frame.add(createBorder(), BorderLayout.WEST);

        frame.setVisible(true);
    }
    private static JPanel createBorder(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(50,50));
        return panel;
    }
}
