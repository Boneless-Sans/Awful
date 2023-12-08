package com.boneless.projects;

import com.boneless.code.neighborhood.PainterPlus;

import javax.swing.*;

public class GameRunner {
    public static void main(String[] args) {
        PainterPlus lisa = new PainterPlus();
        lisa.paint("red");
        lisa.move();
        lisa.paint("red");
        lisa.addPaintBucket(5,0,10);
        lisa.runToPaint();
        lisa.move();
        lisa.paint("red");
    }
}
