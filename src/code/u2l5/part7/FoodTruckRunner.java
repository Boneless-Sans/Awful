package src.code.u2l5.part7;

import src.code.util.ConstructorsHelper;
//import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodTruckRunner {
//    private JButton cycle;
//
//    static String[] iconPaths = {
//            "src/resource/assets/icon.png",
//            "src/resource/assets/main.png",
//            "src/resource/assets/pic.png"
//    };
//
//    static int cycleCount = 1;
//    static int fontSize = 10;
    public static void main(String[] args) {
        Pie pumpkinPie = new Pie();
        ConstructorsHelper.printConstructors(pumpkinPie);
        System.out.println(pumpkinPie.getFlavor());
        System.out.println(pumpkinPie.getDiameter() + "\"");
        System.out.println("$" + pumpkinPie.getPrice() + "\n");
    }
}
