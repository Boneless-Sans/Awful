package com.boneless.code.neighborhood;

import com.boneless.code.neighborhood.Painter;

import java.awt.*;
import java.io.FileReader;

public class NeighborhoodRunner {
    public static void main(String[] args){
        RowPainter bob = new RowPainter(0,0,"east",40);
        bob.paintArea("red", "orange");
    }
}