package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Random;

public class Snake extends JFrame implements KeyListener {
    private boolean runGame = true;
    private final int boardSize = 10;
    private final int[][] board = new int[boardSize][boardSize];
    private JPanel[][] boardPanels;
    private PSnake player;
    private final HashMap<String, Integer> objectList = new HashMap<>();
    public static void main(String[] args){
        new Snake();
    }
    public Snake(){
        objectList.put("air", 0);
        objectList.put("player", 1);
        objectList.put("food", 2);
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
        setLayout(new GridLayout(boardSize,boardSize));

        boardPanels = new JPanel[boardSize][boardSize];

        for(int i = 0;i < board.length;i++){
            for(int k = 0;k < board[i].length;k++){
                add(boardPanels[i][k] = new Entity(i,k));
            }
        }

        Random rand = new Random();
        int randSX = rand.nextInt(0,board.length);
        int randSY = rand.nextInt(0,board[0].length);

        player = new PSnake(randSX, randSY);
        board[randSX][randSY] = 1;
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
                    updateBoard();
                    Thread.sleep(16);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
    }
    private void updateBoard(){
        for(int i = 0;i < board.length;i++){
            for(int k = 0;k < board[i].length;k++){
                switch(board[i][k]){
                    case 0 -> boardPanels[i][k].setBackground(Color.black);
                    case 1 -> boardPanels[i][k].setBackground(Color.green);
                    case 2 -> boardPanels[i][k].setBackground(Color.red);
                    default -> boardPanels[i][k].setBackground(Color.cyan);
                }
            }
        }
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
