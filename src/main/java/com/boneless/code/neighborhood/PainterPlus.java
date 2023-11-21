package com.boneless.code.neighborhood;

import java.awt.*;

public class PainterPlus extends Painter{

    public void moveNum(int num){
        while(num > 0){
            move();
            num = num - 1;
        }
    }
    public void turnRight(){
        turnLeft();
        turnLeft();
        turnLeft();
    }
    public void turnAround(){
        turnLeft();
        turnLeft();
    }
    public void moveNorth(){
        while(!isFacingNorth()){
            turnLeft();
        }
        while(isFacingNorth() && canMove()){
            move();
        }
    }
    public void moveEast(){
        while(!isFacingEast()){
            turnLeft();
        }
        while(isFacingEast() && canMove()){
            move();
        }
    }
    public void moveSouth(){
        while(!isFacingSouth()){
            turnLeft();
        }
        while(isFacingSouth() && canMove()){
            move();
        }
    }
    public void moveWest(){
        while(!isFacingWest()){
            turnLeft();
        }
        while(isFacingWest() && canMove()){
            move();
        }
    }
    public void takeAllPaint(){
        while(isOnBucket()){
            takePaint();
        }
    }
    public void moveFast(){
        while(canMove()){
            move();
        }
    }
    public void paintToEmpty(Color color){
        while(hasPaint() && canMove()){
            paint(color);
            move();
        }
    }
    public void runToPaint(){
        while(!isOnBucket() && canMove()){
            move();
        }
        takeAllPaint();
    }
    public void paintDonut(Color color){
        paint(color);
        move();
        paint(color);
        turnRight();
        move();
        paint(color);
        move();
        paint(color);
    }
    public void paintDashes(Color color){
        while(hasPaint()){
            paint(color);
            move();
            paint(color);
            move();
            if(canMove()){
                move();
            }
        }
    }
    public void paintLine(Color color, int move){
        int set = move;
        set = set - 1;
        while (canMove() && move > set){
            while(isOnBucket()){
                takePaint();
            }
            if(hasPaint()){
                paint(color);
            }
            move();
            move = move - 1;
            if(hasPaint() && !isOnPaint() && !canMove()){
                paint(color);
            }
        }
    }
    public void newLineR(){
        turnRight();
        if(canMove()){
            move();
        }
        turnRight();
    }
    public void newLineL(){
        turnLeft();
        if(canMove()){
            move();
        }
        turnLeft();
    }
    public void moveCords(int xCord, int yCord){
        xCord = xCord - getX();
        yCord = yCord - getY();
        if(xCord > 1){
            xCord = xCord - 1;
        }
        if(yCord > 1){
            yCord = yCord - 1;
        }
        if(isFacingEast()){
            moveNum(xCord);
        }else{
            while(!isFacingEast()){
                turnLeft();
            }
        }
        turnRight();
        moveNum(yCord);
    }
    public void goCorner(){
        while(!isFacingEast()){
            turnLeft();
        }
        moveFast();
        turnRight();
        moveFast();
    }
}
