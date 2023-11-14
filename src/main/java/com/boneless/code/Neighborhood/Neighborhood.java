package com.boneless.code.Neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.awt.*;

public class Neighborhood extends JFrame {
    private JPanel[][] panels;  // Declare a 2D array of JPanels

    public Neighborhood() {
        int sizeX = Integer.parseInt(JsonFile.read("painter.json", "default", "x"));
        int sizeY = Integer.parseInt(JsonFile.read("painter.json", "default", "y"));

        setTitle("NeighborhoodRunner");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panels = new JPanel[sizeX][sizeY];  // Initialize the 2D array of JPanels

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                panels[x][y] = new JPanel();
            }
        }

        addPanelToGrid();  // Call the method to add panels to the grid

        setVisible(true);
    }

    // Method to add all panels to the grid
    private void addPanelToGrid() {
        JPanel grid = new JPanel(new GridLayout(panels.length, panels[0].length));
        int gapSize = 5; // Adjust the gap size as needed

        for (JPanel[] row : panels) {
            for (JPanel p : row) {
                p.setBorder(BorderFactory.createEmptyBorder(gapSize, gapSize, gapSize, gapSize));
                grid.add(p);
            }
        }

        add(grid, BorderLayout.CENTER);
    }

    // Method to change the background color of a specific panel
    public void changePanelColor(int x, int y, Color color) {
        if (isValidCoordinate(x, y)) {
            if (panels[x][y] != null) {
                panels[x][y].setBackground(color);
            } else {
                System.out.println("Panel at coordinates (" + x + ", " + y + ") is null.");
            }
        } else {
            System.out.println("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }

    // Method to check if the coordinates are valid
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < panels.length && y >= 0 && y < panels[0].length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Neighborhood neighborhood = new Neighborhood();
            // Example: Change the color of the panel at coordinates (2, 3) to blue
            neighborhood.changePanelColor(2, 3, Color.BLUE);
        });
    }
}
