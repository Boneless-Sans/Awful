package com.boneless.code.neighborhood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorPicker extends JFrame implements KeyListener {
    private Color[] color = {
            Color.red,
            Color.orange,
            Color.yellow,
            Color.green,
            Color.blue,
            new Color(255,0,255),
            new Color(139,69,19),
            Color.black,
            Color.white,
            Color.gray,
            Color.lightGray,
            Color.darkGray
    };
    public ColorPicker(){
        setSize(500,500);
        setTitle("Color Picker");

        System.out.println(color.length);
        setVisible(true);
    }
    public void setColor(Color[] color){
        this.color = color;
    }
    public Color getColor(){
        return color[1];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
