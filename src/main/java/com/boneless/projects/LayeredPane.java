package com.boneless.projects;

import javax.swing.*;
import java.awt.*;

public class LayeredPane {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setLayout(null);

        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.RED);
        label1.setBounds(50,50,200,200);

        JLabel label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.GREEN);
        label2.setBounds(100,100,200,200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.BLUE);
        label3.setBounds(150,150,200,200);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,500,500);

        layeredPane.add(label1, Integer.valueOf(0)); //red, top left
        layeredPane.add(label2, Integer.valueOf(1)); //green middle
        layeredPane.add(label3, Integer.valueOf(2)); //blue bottom right
        /*
        Layers with names:
        Default
        Palette
        Modal
        PopUp
        Drag

        really only needed if your code is messy, else just layer it via adding
        or, just use integer.valueOf(num); higher the number, the higher the level
         */

        frame.add(layeredPane);
    }
}
