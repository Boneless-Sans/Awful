package com.boneless.code.neighborhood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPicker extends JFrame{
    private static final Color[] COLORS = {
            Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue,
            Color.pink, Color.magenta, new Color(139, 69, 19),
            Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black
    };
    private static Color selectedColor;
    private static JFrame frame;

    public static void showColorPicker() {
        frame = new JFrame("Color Picker");
        frame.setSize(500, 200);

        JLabel title = new JLabel("Pick a Color");
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel panel = new JPanel(new GridLayout(2, 6, 10, 10));

        for(Color color : COLORS) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.setPreferredSize(new Dimension(10, 10));
            System.out.println("uh");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//            colorButton.addActionListener(e -> {
//                selectedColor = color;
//            });
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            panel.add(colorButton);
        }
        while (frame.isVisible()) {
            try{
                Thread.sleep(100);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        JButton exit = new JButton("Close");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setFocusable(false);
        exit.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(exit, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    public Color getSelectedColor(){
        return selectedColor;
    }
}