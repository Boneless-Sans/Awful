package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private int padding = 1; // Number of tiles for padding

    private BufferedImage buffer; // Offscreen image for double buffering

    public Painter() {
        this(0, 0, "east", false); // Call the parameterized constructor with default values and use tile gaps
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Painter(int initialX, int initialY, String initialDirection, boolean useTileGaps) {
        initializeBoardSize();
        initializeTileColors();

        setTitle("Painter");

        int frameWidth = tileSize * (boardWidth + 2 * padding);
        int frameHeight = tileSize * (boardHeight + 2 * padding);

        setSize(frameWidth, frameHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setInitialPosition(initialX, initialY, initialDirection);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

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
        playerX = Math.max(padding, Math.min(initialX + padding, boardWidth + padding - 1));
        playerY = Math.max(padding, Math.min(initialY + padding, boardHeight + padding - 1));
        facingDirection = initialDirection;
    }

    public void savePlayerPosition() {
        // Save player position to a temp file
        // Implement based on your requirements
    }

    public void move() {
        // Implement movement logic based on the facing direction
        if ("north".equals(facingDirection) && playerY > padding) {
            playerY--;
        } else if ("south".equals(facingDirection) && playerY < boardHeight + padding - 1) {
            playerY++;
        } else if ("west".equals(facingDirection) && playerX > padding) {
            playerX--;
        } else if ("east".equals(facingDirection) && playerX < boardWidth + padding - 1) {
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
        String tileKey = (playerX - padding) + "," + (playerY - padding);
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
        // Draw to the offscreen image first
        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Draw the board with tiles, painted tiles, and player on the offscreen image
        for (int y = 0; y < boardHeight + 2 * padding; y++) {
            for (int x = 0; x < boardWidth + 2 * padding; x++) {
                int xPos = x * tileSize;
                int yPos = y * tileSize;

                // Draw the tile
                bufferGraphics.setColor(tileColors.get("default"));
                bufferGraphics.fillRect(xPos, yPos, tileSize, tileSize);

                // Draw the painted tile
                String tileKey = (x - padding) + "," + (y - padding);
                if (paintedTiles.containsKey(tileKey)) {
                    bufferGraphics.setColor(paintedTiles.get(tileKey));
                    bufferGraphics.fillRect(xPos, yPos, tileSize, tileSize);
                }

                // Draw the painter as a red square
                if (x == playerX && y == playerY) {
                    bufferGraphics.setColor(painterColor);
                    bufferGraphics.fillRect(xPos, yPos, tileSize, tileSize);
                }
            }
        }

        // Copy the offscreen image to the screen
        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Use the default constructor
            Painter painter = new Painter();

            // Alternatively, use the parameterized constructor
            // Painter painter = new Painter(2, 2, "DOWN", false);

            // Example movements and painting
            painter.move();
            painter.turnLeft();
            painter.move();
            painter.paint(Color.BLUE); // Paint the tile below the player in blue

            // Retrieve and print the facing direction
            System.out.println("Facing Direction: " + painter.getFacingDirection());
        });
    }
}
