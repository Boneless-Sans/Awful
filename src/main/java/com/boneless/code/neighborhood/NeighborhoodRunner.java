package com.boneless.code.neighborhood;

import com.boneless.code.neighborhood.Painter;

import java.awt.*;
import java.io.FileReader;

public class NeighborhoodRunner {
    public static void main(String[] args){
        PainterListener rob = new PainterListener();
        rob.addPaintBucket(1,1,10);
    }
}