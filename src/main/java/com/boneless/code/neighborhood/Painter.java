package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

class ColorGrid {
    private Map<String, Color> backgroundColors = new HashMap<>();
    private Map<String, Color> paintColors = new HashMap<>();

    public Color getBackgroundColor(int x, int y) {
        String key = getKey(x, y);
        Color color = backgroundColors.get(key);
        return color != null ? color : Color.WHITE;
    }

    public void setBackgroundColor(int x, int y, Color color) {
        String key = getKey(x, y);
        backgroundColors.put(key, color);
    }

    public Color getPaintColor(int x, int y) {
        String key = getKey(x, y);
        Color color = paintColors.get(key);
        return color != null ? color : null;
    }

    public void setPaintColor(int x, int y, Color color) {
        String key = getKey(x, y);
        paintColors.put(key, color);
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }
}

public class Painter extends JFrame {
    private int tileSize = 50; // Size of each tile
    private int boardWidth, boardHeight;
    private int playerX, playerY;
    private int paintCount;
    private double scale = 1.0;
    private String facingDirection = "east"; // Initial direction (east)
    private Map<String, Image> tileImages = new HashMap<>();
    private ColorGrid colorGrid = new ColorGrid();
    private BufferedImage painterImage;
    private double scaleFactor = 1.5; // Default scaling factor
    private BufferStrategy bufferStrategy;
    private int paintCanX, paintCanY;
    private int paintBucketAmount = 0;
    private Map<String, PaintBucket> paintBuckets = new HashMap<>();
    private Set<String> toRemove = new HashSet<>();

    public Painter() {
        init(0, 0, "east");
    }

    public Painter(int xCord, int yCord, String facingDirection) {
        init(xCord, yCord, facingDirection);
    }
    private void init(int x, int y, String facingDirection) {
        initializeBoardSize();
        initializeTileImages();
        initializePainterImage();
        initializeTileSize();

        setTitle("Painter");
        setBackground(Color.BLACK);

        int frameWidth = (int) (scaleFactor * tileSize * boardWidth) + getInsets().left + getInsets().right;
        int frameHeight = (int) (scaleFactor * tileSize * boardHeight) + getInsets().top + getInsets().bottom + 35;

        setSize(frameWidth, frameHeight);


        setSize(frameWidth, frameHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setInitialPosition(x, y, facingDirection);
        setLayout(new BorderLayout());

        // Set up a timer to periodically update the frame
        int delay = 100; // delay in milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //move(); // Update the frame at each timer tick
                //repaint(); // Ensure the frame gets repainted after the move
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();

        setVisible(true); // Make the frame visible after setting it up
    }
    private void initializeBoardSize() {
        // Assuming JSON file structure like {"default": {"x": 10, "y": 10}}
        boardWidth = Integer.parseInt(JsonFile.read("painter.json", "default", "x"));
        boardHeight = Integer.parseInt(JsonFile.read("painter.json", "default", "y"));
        boardWidth *= scale;
        boardHeight *= scale;
    }

    private void initializeTileImages() {
        try {
            InputStream defaultImageStream = getClass().getResourceAsStream("/assets/images/tile.png");

            if (defaultImageStream != null) {
                ImageIcon icon = new ImageIcon(ImageIO.read(defaultImageStream));
                tileImages.put("default", icon.getImage());
            } else {
                System.err.println("Error loading image resources.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePainterImage() {
        try {
            painterImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/painter.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInitialPosition(int initialX, int initialY, String initialDirection) {
        playerX = Math.max(0, Math.min(initialX, boardWidth - 1));
        playerY = Math.max(0, Math.min(initialY, boardHeight - 1));
        facingDirection = initialDirection;
    }

    private void initializeTileSize() {
        String scaleString = JsonFile.read("painter.json", "default", "scale");
        try {
            scale = Double.parseDouble(scaleString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        scaleFactor = scale;
        tileSize = (int) (scaleFactor * tileSize);
    }

    public void move() {
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
    }

    public boolean canMove() {
        int nextX = playerX;
        int nextY = playerY;

        // Calculate the next position based on the facing direction
        if ("north".equals(facingDirection)) {
            nextY--;
        } else if ("south".equals(facingDirection)) {
            nextY++;
        } else if ("west".equals(facingDirection)) {
            nextX--;
        } else if ("east".equals(facingDirection)) {
            nextX++;
        }

        // Check if the next position is within the bounds of the board
        return nextX >= 0 && nextX < boardWidth && nextY >= 0 && nextY < boardHeight;
    }

    public boolean canMove(String direction) {
        int nextX = playerX;
        int nextY = playerY;

        // Calculate the next position based on the provided direction
        if ("north".equals(direction)) {
            nextY--;
        } else if ("south".equals(direction)) {
            nextY++;
        } else if ("west".equals(direction)) {
            nextX--;
        } else if ("east".equals(direction)) {
            nextX++;
        }

        // Check if the next position is within the bounds of the board
        return nextX >= 0 && nextX < boardWidth && nextY >= 0 && nextY < boardHeight;
    }

    public int getX() {
        return playerX - 1;
    }

    public int getY() {
        return playerY - 1;
    }

    public boolean isFacingNorth() {
        return "north".equals(facingDirection);
    }

    public boolean isFacingSouth() {
        return "south".equals(facingDirection);
    }

    public boolean isFacingWest() {
        return "west".equals(facingDirection);
    }

    public boolean isFacingEast() {
        return "east".equals(facingDirection);
    }

    public boolean isOnBucket() {
        return false;
    }

    public boolean isOnPaint() {
        return true;
    }
    public boolean isOnPaintBucket() {
        for (Map.Entry<String, PaintBucket> entry : paintBuckets.entrySet()) {
            PaintBucket paintBucket = entry.getValue();
            if (playerX == paintBucket.getX() && playerY == paintBucket.getY()) {
                return true;
            }
        }
        return false;
    }
    public boolean hasPaint() {
        if (paintCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getMyPaint(){
        return paintCount;
    }
    public void takePaint() {
        for (Map.Entry<String, PaintBucket> entry : paintBuckets.entrySet()) {
            PaintBucket paintBucket = entry.getValue();
            if (playerX == paintBucket.getX() && playerY == paintBucket.getY()) {
                paintCount++;
                paintBucket.decreaseAmount();

                if (paintBucket.getAmount() == 0) {
                    // Mark the paint bucket for removal
                    toRemove.add(entry.getKey());
                }
                break; // Exit the loop once a matching paint bucket is found
            }
        }

        // Remove paint buckets marked for removal
        for (String keyToRemove : toRemove) {
            paintBuckets.remove(keyToRemove);
        }

        repaint();
    }
    public void turnLeft() {
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
    public int getBoardWidth(){
        return boardWidth;
    }
    public int getBoardHeight(){
        return boardHeight;
    }
    private Color stringToColor(String color){
        String initColor = JsonFile.read("colors.json", "colors", color);
        String[] split = initColor.split(",");
        int red = Integer.parseInt(split[0]);
        int green = Integer.parseInt(split[1]);
        int blue = Integer.parseInt(split[2]);
        return new Color(red,green,blue);
    }

    public void paint(String color) {
        // Set the paint color in the color grid
        colorGrid.setPaintColor(playerX, playerY, stringToColor(color));

        // Check if the current position has a paint bucket
        String key = getKey(playerX, playerY);
        if (paintBuckets.containsKey(key)) {
            // Decrement the paint amount in the associated paint bucket
            PaintBucket paintBucket = paintBuckets.get(key);
            if (paintBucket.getAmount() > 0) {
                paintBucket.decreaseAmount();
                if (paintBucket.getAmount() == 0) {
                    // Remove the paint bucket when its amount reaches 0
                    paintBuckets.remove(key);
                }
            }
        }

        // Repaint the component to update the display
        repaint();
    }
    public void paintOld(Color color) {
        // Set the paint color in the color grid
        colorGrid.setPaintColor(playerX, playerY, color);

        // Repaint the component to update the display
        repaint();
    }
    public String getFacingDirection() {
        return facingDirection;
    }
    @Override
    public void paint(Graphics g) {
        if (bufferStrategy == null) {
            createBufferStrategy(2); // Using double buffering
            bufferStrategy = getBufferStrategy();
        }

        do {
            do {
                Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                super.paint(g2d);

                // Draw everything directly on the graphics object
                drawTilesAndPaint(g2d);
                drawPaintCan(g2d, paintCanX, paintCanY);
                drawPlayer(g2d);

                bufferStrategy.show();
                g2d.dispose();
            } while (bufferStrategy.contentsRestored());
        } while (bufferStrategy.contentsLost());
    }
    private void drawTilesAndPaint(Graphics2D g2d) {
        int scaledTileSize = (int) (scaleFactor * tileSize);

        // Create a buffered image for the entire paint layer
        BufferedImage paintLayer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D paintLayerGraphics = paintLayer.createGraphics();

        // Draw tiles and paint to the paint layer
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                int xPos = (int) (x * scaledTileSize * scaleFactor);
                int yPos = (int) (y * scaledTileSize * scaleFactor) + getInsets().top;

                String tileType = getTileType(x, y);
                Image tileImage = tileImages.get(tileType);

                if (tileImage != null) {
                    // Draw the tile image to the paint layer
                    paintLayerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    paintLayerGraphics.drawImage(tileImage, xPos, yPos, scaledTileSize, scaledTileSize, null);
                } else {
                    Color tileBackgroundColor = colorGrid.getBackgroundColor(x, y);

                    // Check if there is a paint bucket at the current position
                    String key = getKey(x, y);
                    if (!paintBuckets.containsKey(key)) {
                        paintLayerGraphics.setColor(tileBackgroundColor);
                        paintLayerGraphics.fillRect(xPos, yPos, scaledTileSize, scaledTileSize);
                    }
                }

                Color paintColor = colorGrid.getPaintColor(x, y);
                if (paintColor != null) {
                    // Draw the paint color to the paint layer
                    paintLayerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                    paintLayerGraphics.setColor(paintColor);
                    paintLayerGraphics.fillRect(xPos, yPos, scaledTileSize, scaledTileSize);
                }
            }
        }

        // Draw the entire paint layer to the main graphics
        g2d.drawImage(paintLayer, 0, 0, null);

        // Draw paint cans separately after background tiles
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                String tileType = getTileType(x, y);
                if ("paint_bucket".equals(tileType)) {
                    drawPaintCan(g2d, x, y);
                }
            }
        }

        // Dispose of the paint layer graphics
        paintLayerGraphics.dispose();
    }


    private void drawPlayer(Graphics2D g2d) {
        int scaledTileSize = (int) (scaleFactor * tileSize);
        int xPos = (int) (playerX * scaledTileSize * scaleFactor);
        int yPos = (int) (playerY * scaledTileSize * scaleFactor) + getInsets().top;

        // Draw player
        double rotationAngle = getRotationAngle();
        int imageX = xPos + scaledTileSize / 2 - painterImage.getWidth() / 2;
        int imageY = yPos + scaledTileSize / 2 - painterImage.getHeight() / 2;
        drawRotatedImage(g2d, painterImage, imageX, imageY, rotationAngle);
    }
    private double getRotationAngle() {
        switch (facingDirection) {
            case "north":
                return Math.PI;
            case "south":
                return 0.0;
            case "west":
                return Math.PI / 2;
            case "east":
                return -Math.PI / 2;
            default:
                return 0.0;
        }
    }
    private void drawPaintCan(Graphics2D g2d, int x, int y) {
        System.out.println("Drawing paint can at (" + x + ", " + y + ")");

        int scaledTileSize = (int) (scaleFactor * tileSize);
        int xPos = (int) (x * scaledTileSize * scaleFactor);
        int yPos = (int) (y * scaledTileSize * scaleFactor) + getInsets().top;

        try {
            // Load the paint can image
            InputStream paintCanStream = getClass().getResourceAsStream("/assets/images/paint_can.png");
            if (paintCanStream != null) {
                Image paintCanImage = ImageIO.read(paintCanStream);

                // Check if the current position has a paint bucket
                String key = getKey(x, y);
                PaintBucket paintBucket = paintBuckets.get(key);

                if (paintBucket != null) {
                    int paintAmount = paintBucket.getAmount();

                    if (paintAmount > 0) {
                        Composite originalComposite = g2d.getComposite(); // Store the original composite

                        // Draw the paint can image
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Set full opacity
                        g2d.drawImage(paintCanImage, xPos, yPos, scaledTileSize, scaledTileSize, null);

                        // Set font and color for drawing text
                        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                        g2d.setColor(Color.BLACK);

                        // Draw the paint amount text
                        String text = Integer.toString(paintAmount);
                        int textX = xPos + scaledTileSize / 2 - g2d.getFontMetrics().stringWidth(text) / 2;
                        int textY = yPos + scaledTileSize / 2 + g2d.getFontMetrics().getAscent() / 2;
                        g2d.drawString(text, textX, textY);

                        g2d.setComposite(originalComposite); // Restore the original composite
                    } else {
                        // Paint bucket is empty, remove it from the map
                        paintBuckets.remove(key);
                    }
                } else {
                    System.out.println("No paint bucket found at (" + x + ", " + y + ")");
                }
            } else {
                System.err.println("Error loading paint can image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Drawing paint can completed");
    }


    private String getTileType(int x, int y) {
        String key = getKey(x, y);

        // Check if there is a paint bucket at the specified location
        if (paintBuckets.containsKey(key)) {
            return "paint_bucket";
        }

        // Your logic to determine the type of tile at position (x, y)
        // Return the type as a string (e.g., "default" for regular tiles)

        return "default"; // Change this based on your logicM
    }

    private void drawRotatedImage(Graphics g, Image image, int x, int y, double angle) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(x + image.getWidth(null) / 2, y + image.getHeight(null) / 2);
        g2d.rotate(angle);
        g2d.drawImage(image, -image.getWidth(null) / 2, -image.getHeight(null) / 2, null);
        g2d.dispose();
    }
    public void addPaintBucket(int x, int y, int initialPaintAmount) {
        PaintBucket paintBucket = new PaintBucket(x, y, initialPaintAmount);
        String key = getKey(x, y);

        // Check if there is already a paint bucket at the specified location
        if (paintBuckets.containsKey(key)) {
            // Merge the new paint bucket with the existing one
            PaintBucket existingBucket = paintBuckets.get(key);
            existingBucket.merge(paintBucket);
        } else {
            // Add the new paint bucket to the map
            paintBuckets.put(key, paintBucket);
        }

        // Repaint the component to update the display
        repaint();
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }
    private String getKey(int x, int y, int paintAmount) {
        return x + "," + y + "," + paintAmount;
    }


}