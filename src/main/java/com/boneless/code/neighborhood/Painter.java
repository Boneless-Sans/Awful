package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Painter extends JFrame {

    private int tileSize = 50; // Size of each tile
    private int boardWidth, boardHeight;
    private int playerX, playerY;
    private String facingDirection = "east"; // Initial direction (east)

    private Map<String, Color> tileColors = new HashMap<>();
    private Map<String, Color> paintedTiles = new HashMap<>();

    // Use a red square as a placeholder for the painter image
    private Color painterColor = Color.RED;

    public Painter() {
        this(0, 0, "east"); // Call the parameterized constructor with default values
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Painter(int initialX, int initialY, String initialDirection) {
        initializeBoardSize();
        initializeTileColors();

        setTitle("Painter");
        setSize(tileSize * boardWidth, tileSize * boardHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setInitialPosition(initialX, initialY, initialDirection);
        setVisible(true);
    }

    private void initializeBoardSize() {
        // Assuming JSON file structure like {"default": {"x": 10, "y": 10}}
        boardWidth = Integer.parseInt(JsonFile.read("painter.json", "default", "x"));
        boardHeight = Integer.parseInt(JsonFile.read("painter.json", "default", "y"));
    }

    private void initializeTileColors() {
        tileColors.put("default", Color.WHITE); // Default color
        // Add more colors as needed
    }

    private void setInitialPosition(int initialX, int initialY, String initialDirection) {
        playerX = Math.max(0, Math.min(initialX, boardWidth - 1));
        playerY = Math.max(0, Math.min(initialY, boardHeight - 1));
        facingDirection = initialDirection;
    }

    public void savePlayerPosition() {
        // Save player position to a temp file
        // Implement based on your requirements
    }

    public void move() {
        // Implement movement logic based on the facing direction
        if ("north".equals(facingDirection) && playerY > 0) {
            playerY--;
        } else if ("south".equals(facingDirection) && playerY < boardHeight - 1) {
            playerY++;
        } else if ("west".equals(facingDirection) && playerX > 0) {
            playerX--;
        } else if ("east".equals(facingDirection) && playerX < boardWidth - 1) {
            playerX++;
        }

        repaint();
        savePlayerPosition();
    }

    public void turnLeft() {
        // Implement logic to turn left
        // Update the facingDirection accordingly
        if ("north".equals(facingDirection)) {
            facingDirection = "west";
        } else if ("west".equals(facingDirection)) {
            facingDirection = "south";
        } else if ("south".equals(facingDirection)) {
            facingDirection = "east";
        } else if ("east".equals(facingDirection)) {
            facingDirection = "north";
        }

        repaint();
    }

    public void turnRight() {
        // Implement logic to turn right
        // Update the facingDirection accordingly
        if ("north".equals(facingDirection)) {
            facingDirection = "east";
        } else if ("east".equals(facingDirection)) {
            facingDirection = "south";
        } else if ("south".equals(facingDirection)) {
            facingDirection = "west";
        } else if ("west".equals(facingDirection)) {
            facingDirection = "north";
        }

        repaint();
    }

    public void paint(Color color) {
        // Paint the tile below the player with the specified color
        String tileKey = playerX + "," + playerY;
        if (!paintedTiles.containsKey(tileKey)) {
            paintedTiles.put(tileKey, color);
            repaint();
        }
    }

    public String getFacingDirection() {
        return facingDirection;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the board with tiles, painted tiles, and player
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                int xPos = x * tileSize;
                int yPos = y * tileSize;

                // Draw the tile
                g.setColor(tileColors.get("default"));
                g.fillRect(xPos, yPos, tileSize, tileSize);

                // Draw the painted tile
                String tileKey = x + "," + y;
                if (paintedTiles.containsKey(tileKey)) {
                    g.setColor(paintedTiles.get(tileKey));
                    g.fillRect(xPos, yPos, tileSize, tileSize);
                }

                // Draw the painter as a red square
                if (x == playerX && y == playerY) {
                    g.setColor(painterColor);
                    g.fillRect(xPos, yPos, tileSize, tileSize);
                }
            }
        }
    }
}
