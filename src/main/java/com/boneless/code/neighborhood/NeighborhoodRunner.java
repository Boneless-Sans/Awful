package com.boneless.code.neighborhood;

import com.boneless.code.neighborhood.Painter;

import java.awt.*;
import java.io.FileReader;

public class NeighborhoodRunner {
    public static void main(String[] args){
        PainterListener lisa = new PainterListener();
        lisa.addPaintBucket(5,5,10);
        lisa.addPaintBucket(4,4,10);
        for(int i = 0;i < 5;i++){
            lisa.addPaintBucket(7,i + 3,10);
        }
        System.out.println(lisa.getX());

    }
}
