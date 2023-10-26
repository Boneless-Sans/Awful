package src.code.u2l5.part7;

import src.code.util.ConstructorsHelper;
import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodTruckRunner {
    private JButton cycle;

    static String[] iconPaths = {
            "src/resource/assets/icon.png",
            "src/resource/assets/main.png",
            "src/resource/assets/pic.png"
    };

    static int cycleCount = 1;
    static int fontSize = 10;
    public static void main(String[] args){
        Pie pumpkinPie = new Pie();
        ConstructorsHelper.printConstructors(pumpkinPie);
        System.out.println(pumpkinPie.getFlavor());
        System.out.println(pumpkinPie.getDiameter() + "\"");
        System.out.println("$" + pumpkinPie.getPrice() + "\n");


        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,250);

        JButton cycle = new JButton();
        cycle.setLayout(null);
        cycle.setBounds(125,100,20,20);
        cycle.setText("text");

        JButton upScale = new JButton();
        upScale.setText("↑");
        upScale.setBounds(85,125,50,25);

        JButton downScale = new JButton();
        downScale.setText("↓");
        downScale.setBounds(135,125,50,25);


        IconResize icon = new IconResize("src/resource/assets/pic.png", 50,50);

        JLabel labelFlavor = new JLabel("Flavor: " + pumpkinPie.getFlavor());
        upScale.addActionListener(e -> {
            fontSize++;
            labelFlavor.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
        });
        downScale.addActionListener(e -> {
            fontSize--;
            labelFlavor.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
        });
        //JLabel labelFlavor = new JLabel("Flavor: " + pumpkinPie.getFlavor());

        labelFlavor.setBounds(0,0,255,255);

        labelFlavor.setIcon(icon.getImage());
        cycle.addActionListener(e -> {
            if(cycleCount < iconPaths.length){
                icon.set(iconPaths[cycleCount], 75,75);
                labelFlavor.setIcon(icon.getImage());
                if(cycleCount == 2){
                    cycleCount = 0;
                }else{
                    cycleCount++;
                }
                //System.out.println(cycleCount);
            }
        });


        JPanel pFlavor = new JPanel();
        pFlavor.setBounds(0,0,250,250);
        pFlavor.setBackground(Color.BLUE);
        pFlavor.add(labelFlavor);
        pFlavor.setLayout(null);

        JPanel pSize = new JPanel();
        pSize.setBounds(250,0,250,250);
        pSize.setBackground(Color.GREEN);
        pSize.add(cycle);
        pSize.add(upScale);
        pSize.add(downScale);
        pSize.setLayout(null);

        frame.setVisible(true);
        frame.add(pFlavor);
        frame.add(pSize);
        frame.setLayout(null);
        frame.setSize(550,550);
    }
}
