package com.boneless.code.neighborhood;

import javax.swing.*;
import java.util.Scanner;

public class NeighborhoodRunner {
    public static void main(String[] args){
        Painter lisa = new Painter();

        //System.out.println(lisa);
        //lisa.move();
        //System.out.println(lisa);

        Scanner scan = new Scanner(System.in);
        while(true){
            switch (scan.nextLine()){
                case"w":
                    lisa.move();
                    break;
                case"a":
                    lisa.move();
                    break;
                case"s":
                    lisa.move();
                    break;
                case"d":
                    lisa.move();
                    break;
                case"l":
                    lisa.turnLeft();
                default:
                    System.out.println("Invalid move");
                    break;
            }
        }
    }
}
