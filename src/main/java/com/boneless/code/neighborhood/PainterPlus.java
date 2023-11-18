package com.boneless.code.neighborhood;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class PainterPlus extends Painter implements KeyListener {
    public PainterPlus(){
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private Color color;
    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case'w':
                while(!Objects.equals(getFacingDirection(), "north")){
                    turnLeft();
                }
                move();
                break;
            case'd':
                while(!Objects.equals(getFacingDirection(), "east")){
                    turnLeft();
                }
                move();
                break;
            case's':
                while(!Objects.equals(getFacingDirection(), "south")){
                    turnLeft();
                }
                move();
                break;
            case'a':
                while(!Objects.equals(getFacingDirection(), "west")){
                    turnLeft();
                }
                move();
                break;
            case'e':
                turnRight();
                break;
            case'q':
                turnLeft();
                break;
            case'c':
                new ColorPicker();
                break;
            case' ':
                paint(color);
                System.out.println("Painted");
                break;
        }
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}