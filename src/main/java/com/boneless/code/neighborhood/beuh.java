package com.boneless.code.neighborhood;

import java.awt.*;

public class beuh {
    private int xPos;
    private int yPos;
    private int paintCount;
    private boolean infPaint;
    private boolean canMove;
    private boolean isFacingNorth = false;
    private boolean isFacingEast = true;
    private boolean isFacingSouth = false;
    private boolean isFacingWest = false;
    private PainterListener painterListener;

    public interface PainterListener {
        void onPainterMove(int x, int y);
    }

    public void setPainterListener(PainterListener painterListener) {
        this.painterListener = painterListener;
    }

    public beuh() {
        setDir("east");
        this.xPos = 0;
        this.yPos = 0;
        this.infPaint = false;
    }
    public beuh(int xPos, int yPos, String dir, int paintCount, boolean infPaint){
        setDir(dir);
        this.xPos = xPos;
        this.yPos = yPos;
        this.paintCount = paintCount;
        this.infPaint = infPaint;
    }
    public beuh(int xPos, int yPos, String dir){
        setDir(dir);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void move(){
        switch(getDir()){
            case "north":
                yPos--;
                break;
            case "east":
                xPos++;
                break;
            case "south":
                yPos++;
                break;
            case "west":
                xPos--;
                break;
            default:
                System.out.println("!! WARNING !! VERY INVALID DIR");
        }
        if (painterListener != null) {
            painterListener.onPainterMove(xPos, yPos);
        }
    }
    public void turnLeft(){
        switch(getDir()){
            case "north":
                setDir("north");
                break;
            case "east":
                setDir("east");
                break;
            case "south":
                setDir("south");
                break;
            case "west":
                setDir("west");
                break;
            default:
                System.out.println("!! WARNING !! VERY INVALID DIR");
        }

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

    public void setDir(String dir){
        switch (dir){
            case"north":
                this.isFacingNorth = true;
                this.isFacingEast = false;
                this.isFacingSouth = false;
                this.isFacingWest = false;
                break;
            case"east":
                this.isFacingNorth = false;
                this.isFacingEast = true;
                this.isFacingSouth = false;
                this.isFacingWest = false;
                break;
            case"south":
                this.isFacingNorth = false;
                this.isFacingEast = false;
                this.isFacingSouth = true;
                this.isFacingWest = false;
                break;
            case"west":
                this.isFacingNorth = false;
                this.isFacingEast = false;
                this.isFacingSouth = false;
                this.isFacingWest = true;
                break;
            default:
                System.out.println("\n!! Invalid Direction !!\n");
        }
    }
    public String getDir() {
        if (isFacingNorth) {
            return "north";
        } else if (isFacingEast) {
            return "east";
        } else if (isFacingSouth) {
            return "south";
        } else {
            return "west";
        }
    }

    public boolean canMove(){
        return canMove;
    }
    public int getX(){
        return xPos;
    }
    public int getY(){
        return yPos;
    }

    public String toString(){
        return
                "X Position: " + xPos +
                "\nY Position: " + yPos +
                "\nFacing Direction: " + getDir() +
                "\nRemaining Paint: " + paintCount +
                "\nInfinite Paint: " + infPaint;
    }
}
