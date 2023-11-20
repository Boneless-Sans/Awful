package com.boneless.projects.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GridDimensions {

    public static void main(String[] args) {
        String[] test = JsonFile.readArray("painter.json", "colors");
        int totalCells = test.length;
        int[] dimensions = findDimensions(totalCells);

        System.out.println("Grid dimensions: " + dimensions[1] + " x " + dimensions[0]);
        System.out.println(test.length);

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int index = 0;
        JPanel panel = new JPanel(new GridLayout(dimensions[0], dimensions[1], 5, 5));
        for(String length : test){
            JButton button = new JButton(Objects.requireNonNull(JsonFile.readArray("painter.json", "colors"))[index]);
            button.setPreferredSize(new Dimension(100,50));
            panel.add(button);
            index++;
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    public static int[] findDimensions(int N) {
        int[] result = new int[2];

        // Start with a square grid and adjust
        int sqrtN = (int) Math.ceil(Math.sqrt(N));

        for (int i = sqrtN; i <= N; i++) {
            if (N % i == 0) {
                result[0] = i;
                result[1] = N / i;
                break;
            }
        }

        return result;
    }
}
