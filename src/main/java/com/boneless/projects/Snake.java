package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
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
        addComponentListener(checkResize());
        addKeyListener(this);
        setTitle("Snake");
        setLayout(new GridLayout(boardSize,boardSize));

        boardPanels = new JPanel[boardSize][boardSize];

        for(int i = 0;i < board.length;i++){
            for(int k = 0;k < board[i].length;k++){
                add(boardPanels[i][k] = new Entity(i,k,board));
            }
        }

        Random rand = new Random(); //spawn player
        int randSX = rand.nextInt(2,board.length - 2);
        int randSY = rand.nextInt(2,board[0].length - 2);

        player = new PSnake(randSX, randSY, board);
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
                    case 0 -> boardPanels[i][k].setBackground(Color.white); //void
                    case 1 -> boardPanels[i][k].setBackground(Color.green); //snek :3
                    case 2 -> boardPanels[i][k].setBackground(Color.red); //food
                    default -> boardPanels[i][k].setBackground(Color.cyan); //error
                }
            }
        }
        repaint(); //not sure if this should be used here, just hoping it fixes the white tile issue (seems to work)
    }
    private ComponentAdapter checkResize(){
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension screenLocation = Toolkit.getDefaultToolkit().getScreenSize();
                int size = (getWidth() + getHeight()) / 2;
                setSize(size,size);
                setLocation((screenLocation.width - getWidth()) / 2, (screenLocation.height - getHeight()) / 2);
            }
        };
    }
    private void moveSnake(){
        try {
            player.move();
        }catch (ArrayIndexOutOfBoundsException e) { //crashes game if player is out of bounds, could loop it, but that's lame
            System.exit(0);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        switch (keyChar){
            case 'w' -> player.turn("north");
            case 's' -> player.turn("south");
            case 'a' -> player.turn("west");
            case 'd' -> player.turn("east");
        }
        if(keyChar == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    //Entity's
    private static class PSnake extends Entity{
        private String facingDir;
        private int bodyLength = 1;
        public PSnake(int x, int y, int[][] board){
            super(x,y,board);
            Random rand = new Random();
            switch (rand.nextInt(0,4)){
                case 0 -> facingDir = "north";
                case 1 -> facingDir = "east";
                case 2 -> facingDir = "south";
                case 3 -> facingDir = "west";
            }
        }
        private void move(){
            switch(facingDir){
                case "north" -> {
                    board[x][y] = 0;
                    board[x--][y] = getColor();
                }
                case "east" -> {
                    board[x][y] = 0;
                    board[x][y++] = getColor();
                }
                case "south" -> {
                    board[x][y] = 0;
                    board[x++][y] = getColor();
                }
                case "west" -> {
                    board[x][y] = 0;
                    board[x][y--] = getColor();
                }
            }
        }
        private void turn(String dir){
            if(!Objects.equals(this.facingDir, dir)) {
                facingDir = dir;
                move();
            }else{
                facingDir = dir;
            }
        }
        @Override
        protected int getColor() {
            return 1;
        }
    }
    private static class SnakeBody extends Entity{
        public SnakeBody(int x, int y, int[][] board){
            super(x,y,board);
        }
        @Override
        protected int getColor() {
            return 1;
        }
    }
    private static class Entity extends JPanel{
        protected int x;
        protected int y;
        protected final int[][] board;
        protected Entity(int x, int y, int[][] board){
            this.x = x;
            this.y = y;
            this.board = board;
        }
        protected int getColor(){
            return 0;
        }
    }
    private static class Food extends Entity{
        public Food(int x, int y, int[][] board){
            super(x,y,board);
        }
        @Override
        protected int getColor() {
            return 2;
        }
    }
}
