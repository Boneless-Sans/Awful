package com.boneless.code.neighborhood;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class PainterPlus extends Painter implements KeyListener {
    private static Color selectedColor;
    private static boolean toggle = true;
    private final int originalHeight = getHeight();
    private final int originalWidth = getWidth();
    private static final Color[] COLORS = {
            Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue,
            Color.pink, Color.magenta, new Color(139, 69, 19),
            Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black
    };
    public PainterPlus(){
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton button = new JButton();
        button.addActionListener(e -> {
            colorPickerUI();
        });
    }
    private void colorPickerUI() {
        ColorPickerFrame frame = new ColorPickerFrame("Color Picker");
        frame.setVisible(true);
    }
    private static class ColorPickerFrame extends JFrame {

        public ColorPickerFrame(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setSize(500,500);

            JPanel panel = new JPanel(new GridLayout(2, 6, 5, 5));
            for (Color color : COLORS) {
                JButton colorButton = new JButton();
                colorButton.setBackground(color);
                colorButton.setPreferredSize(new Dimension(50, 50));
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedColor = color;
                    }
                });
                panel.add(colorButton);
            }

            add(panel);

            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    // Custom logic when the close button is clicked
                    // Dispose the frame and set toggle to false
                    dispose();
                    toggle = false;
                }
            });
            setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case'w':
                if(toggle) {
                    while (!Objects.equals(getFacingDirection(), "north")) {
                        turnLeft();
                    }
                    move();
                }
                break;
            case'd':
                if(toggle) {
                    while (!Objects.equals(getFacingDirection(), "east")) {
                        turnLeft();
                    }
                    move();
                }
                break;
            case's':
                if(toggle) {
                    while (!Objects.equals(getFacingDirection(), "south")) {
                        turnLeft();
                    }
                    move();
                }
                break;
            case'a':
                if(toggle) {
                    while (!Objects.equals(getFacingDirection(), "west")) {
                        turnLeft();
                    }
                    move();
                }
                break;
            case'e':
                if(toggle) {
                    turnRight();
                }
                break;
            case'q':
                if(toggle) {
                    turnLeft();
                }
                break;
            case'c':
                if (toggle) {
                    colorPickerUI();
                    toggle = false;
                } else {
                    toggle = true;
                }
                break;
            case' ':
                if(toggle) {
                    paint(selectedColor);
                }
                break;
        }
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
            if(toggle){
                System.exit(0);
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}