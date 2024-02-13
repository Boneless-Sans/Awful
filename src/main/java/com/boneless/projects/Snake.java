package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake extends JFrame implements KeyListener {
    private boolean runGame = true;
    private final int BOARDSIZE = 10;
    private int[][] board = new int[BOARDSIZE][BOARDSIZE];
    private PSnake player;
    public static void main(String[] args){
        new Snake();
    }
    public Snake(){
        init();
        setVisible(true);
        updateThread();
        startSnakeMovement();
    }
    private void init(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Snake");
        setLayout(new GridLayout(BOARDSIZE,BOARDSIZE,1,1));

        Random rand = new Random();
        int randSX = rand.nextInt(0,board.length);
        int randSY = rand.nextInt(0,board[0].length);

        player = new PSnake();
    }
    @SuppressWarnings({"BusyWait", "CallToPrintStackTrace"})
    private void startSnakeMovement(){
        Thread movementThread = new Thread(() -> {
            while (runGame){
                try {
                    moveSnake();
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        movementThread.start();
    }
    @SuppressWarnings({"BusyWait","CallToPrintStackTrace"})
    private void updateThread(){
        Thread updateThread = new Thread(() -> {
            while (runGame){
                try {
                    Thread.sleep(16);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
    }
    private void moveSnake(){
        player.move();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(keyChar == 'w'){
            player.turn("north");
        }
        if(keyChar == 'a'){
            player.turn("west");
        }
        if(keyChar == 's'){
            player.turn("south");
        }
        if(keyChar == 'd'){
            player.turn("east");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    private static class PSnake extends Entity{
        private String facingDir = "north";
        public PSnake(int x, int y){
            super(x,y);
        }
        public void move(){
            switch(facingDir){
                case "north" -> y++;
                case "east" -> x++;
                case "south" -> y--;
                case "west" -> x--;
            }
        }
        public void turn(String dir){
            facingDir = dir;
        }
        @Override
        protected int getColor() {
            return 1;
        }
    }
    private static class Entity extends JPanel{
        protected int x;
        protected int y;
        protected Entity(int x, int y){
            this.x = x;
            this.y = y;
        }
        protected int getColor(){
            return 0;
        }
    }
    private static class Food extends Entity{
        public Food(int x, int y){
            super(x,y);
        }
        @Override
        protected int getColor() {
            return 2;
        }
    }
}
