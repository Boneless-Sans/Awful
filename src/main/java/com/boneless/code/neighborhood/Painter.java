package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class Painter extends JFrame {
    private int xPos;
    private int yPos;
    private String facingDirection = "east";

    private static JPanel[][] playerLayer;
    private static JPanel[][] drawingLayer;
    private static Color playerColor = Color.white;

    public interface PainterListener {
        void onPainterMove(int x, int y, String facingDirection);
    }

    private PainterListener painterListener;

    public void setPainterListener(PainterListener painterListener) {
        this.painterListener = painterListener;
    }

    public Painter() {
        int sizeX = Integer.parseInt(JsonFile.read("painter.json", "default", "x"));
        int sizeY = Integer.parseInt(JsonFile.read("painter.json", "default", "y"));

        // Limit the frame size to a reasonable maximum
        sizeX = Math.min(sizeX, 20);
        sizeY = Math.min(sizeY, 20);

        setTitle("NeighborhoodRunner");
        setSize(sizeX * 50, sizeY * 50); // Set size based on JSON values
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        playerLayer = new JPanel[sizeX][sizeY];
        drawingLayer = new JPanel[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                playerLayer[x][y] = new JPanel();
                drawingLayer[x][y] = new JPanel();
            }
        }

        addPanelToGrid(playerLayer);
        addPanelToGrid(drawingLayer);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addPanelToGrid(JPanel[][] layer) {
        JPanel grid = new JPanel(new GridLayout(layer.length, layer[0].length));
        int gapSize = 5;

        for (JPanel[] row : layer) {
            for (JPanel p : row) {
                p.setBorder(BorderFactory.createEmptyBorder(gapSize, gapSize, gapSize, gapSize));
                grid.add(p);
            }
        }

        add(grid, BorderLayout.CENTER);
    }

    public void paint(Color color) {
        // Update the color of the current tile in the drawing layer
        updateNewTileColor(xPos, yPos, color, drawingLayer);

        // Repaint the DrawingPanel to reflect the changes
        repaint();
    }

    private void updateNewTileColor(int x, int y, Color color, JPanel[][] layer) {
        layer[x][y].setBackground(color);
    }

    private void clearPreviousTileColor(int x, int y, JPanel[][] layer) {
        if (isValidCoordinate(x, y)) {
            layer[x][y].setBackground(Color.WHITE);
        }
    }

    public void move() {
        // Clear the previous tile color in the player layer
        clearPreviousTileColor(xPos, yPos, playerLayer);

        switch (facingDirection) {
            case "north":
                yPos--;
                break;
            case "east":
                xPos++;
                break;
            case "south":
                yPos++;
                break;
            case "west":
                xPos--;
                break;
            default:
                System.out.println("Invalid direction");
                return;
        }

        if (isValidCoordinate(xPos, yPos)) {
            onPainterMove(xPos, yPos, facingDirection);
        } else {
            System.out.println("Invalid move");
            // Reverse the invalid move
            switch (facingDirection) {
                case "north":
                    yPos++;
                    break;
                case "east":
                    xPos--;
                    break;
                case "south":
                    yPos--;
                    break;
                case "west":
                    xPos++;
                    break;
            }
        }

        printPositionAndDirection();
    }

    private class DrawingPanel extends JPanel {
        private JLabel painterLabel;

        DrawingPanel() {
            try {
                // Load the image from resources using ImageIcon
                InputStream inputStream = getClass().getResourceAsStream("/assets/images/painter.png");
                ImageIcon painterIcon = new ImageIcon(ImageIO.read(inputStream));

                // Create a JLabel with the ImageIcon
                painterLabel = new JLabel(painterIcon);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set layout to null to position the JLabel manually
            setLayout(null);
            add(painterLabel);

            // Set the size of the JLabel
            painterLabel.setBounds(0, 0, 50, 50);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the drawing layer
            drawLayer(g, drawingLayer);

            // Draw the player layer
            drawLayer(g, playerLayer);
        }

        private void drawLayer(Graphics g, JPanel[][] layer) {
            for (int x = 0; x < layer.length; x++) {
                for (int y = 0; y < layer[0].length; y++) {
                    g.setColor(layer[x][y].getBackground());
                    g.fillRect(x * 50, y * 50, 50, 50);
                }
            }
        }

        // Add a method to update the painter's position
        public void updatePainterPosition(int x, int y) {
            // Update the position of the JLabel
            painterLabel.setLocation(x * 50, y * 50);
            repaint(); // Trigger repaint to update the layers
        }
    }

    private void onPainterMove(int x, int y, String facingDirection) {
        updateNewTileColor(x, y, playerColor, playerLayer);
        changePanelColor(x, y, playerColor, playerLayer);
        updateNewTileColor(x, y, Color.WHITE, drawingLayer); // Clear the color in the drawing layer

        if (painterListener != null) {
            painterListener.onPainterMove(xPos, yPos, facingDirection);
        }

        // Update the painter's position on the DrawingPanel
        if (getContentPane().getComponent(1) instanceof DrawingPanel) {
            ((DrawingPanel) getContentPane().getComponent(1)).updatePainterPosition(x, y);
        } else {
            System.out.println("DrawingPanel not found in the content pane.");
        }

        repaint(); // Trigger repaint on the DrawingPanel
    }
    // Add a method to update the painter's position
    public void updatePainterPosition(int x, int y) {
        // Update the position of the JLabel
        painterLabel.setLocation(x * 50, y * 50);
        repaint(); // Trigger repaint to update the layers
    }


    private void changePanelColor(int x, int y, Color color, JPanel[][] layer) {
        if (isValidCoordinate(x, y)) {
            if (layer[x][y] != null) {
                layer[x][y].setBackground(color);
            } else {
                System.out.println("Panel at coordinates (" + x + ", " + y + ") is null.");
            }
        } else {
            System.out.println("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }

    private void printPositionAndDirection() {
        System.out.println("Painter moved to: (" + xPos + ", " + yPos + ")");
        System.out.println("Facing direction: " + facingDirection);
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < playerLayer.length && y >= 0 && y < playerLayer[0].length;
    }

    private double getRotationAngle() {
        switch (facingDirection) {
            case "north":
                return 180;
            case "east":
                return 279; // Flip horizontally
            case "south":
                return 0; // Flip horizontally
            case "west":
                return 90;
            default:
                return 0;
        }
    }

    public void addScannerListener() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            switch (scan.nextLine()) {
                case "w":
                    while (!Objects.equals(getFacingDirection(), "north")) {
                        turnLeft();
                    }
                    move();
                    break;
                case "a":
                    while (!Objects.equals(getFacingDirection(), "west")) {
                        turnLeft();
                    }
                    move();
                    break;
                case "s":
                    while (!Objects.equals(getFacingDirection(), "south")) {
                        turnLeft();
                    }
                    move();
                    break;
                case "d":
                    while (!Objects.equals(getFacingDirection(), "east")) {
                        turnLeft();
                    }
                    move();
                    break;
                case "l":
                    turnLeft();
                    break;
                default:
                    System.out.println("Invalid move");
                    break;
            }
        }
    }

    public void turnLeft() {
        switch (facingDirection) {
            case "north":
                facingDirection = "west";
                break;
            case "east":
                facingDirection = "north";
                break;
            case "south":
                facingDirection = "east";
                break;
            case "west":
                facingDirection = "south";
                break;
            default:
                System.out.println("Invalid direction");
        }

        printPositionAndDirection();
    }

    public String getFacingDirection() {
        return facingDirection;
    }

    public boolean isFacingNorth() {
        return !Objects.equals(facingDirection, "north");
    }

    public boolean isFacingEast() {
        return !Objects.equals(facingDirection, "east");
    }

    public boolean isFacingSouth() {
        return !Objects.equals(facingDirection, "south");
    }

    public boolean isFacingWest() {
        return !Objects.equals(facingDirection, "west");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Painter());
    }
}
