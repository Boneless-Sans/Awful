package com.boneless.code.Neighborhood;

import java.awt.*;

public class Painter {
    private int xPos;
    private int yPos;
    private int paintCount;
    private boolean infPaint;
    private boolean canMove;
    private boolean isFacingNorth;
    private boolean isFacingEast;
    private boolean isFacingSouth;
    private boolean isFacingWest;
    private String dir;

    public Painter(){
        this.dir = "west";
        this.xPos = 0;
        this.yPos = 0;
        this.infPaint = true;
    }
    public Painter(int xPos, int yPos, String dir, int paintCount, boolean infPaint){
        this.dir = dir;
        this.xPos = xPos;
        this.yPos = yPos;
        this.paintCount = paintCount;
        this.infPaint = infPaint;
    }

    public void move(){
        xPos++;
        System.out.println("moved");
    }
    public void turnLeft(){
        //
    }
    public void paint(Color color){
        //
    }
    public void takePaint(){
        //
    }
    public void scrapePaint(){
        //
    }
}
