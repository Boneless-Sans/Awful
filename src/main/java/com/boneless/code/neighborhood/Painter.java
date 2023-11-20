package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

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
    private int padding = 1; // Number of tiles for padding

    private double scale = 1.0;
    private String facingDirection = "east"; // Initial direction (east)
    private Map<String, Image> tileImages = new HashMap<>();
    private ColorGrid colorGrid = new ColorGrid();
    private BufferedImage painterImage;

    private double scaleFactor = 1.5; // Default scaling factor
    private BufferedImage buffer; // Offscreen image for double buffering

    private final int scaledTileSize = (int) (scaleFactor * tileSize);
    private BufferStrategy bufferStrategy;

    public Painter() {
        initializeBoardSize();
        initializeTileImages();
        initializePainterImage();
        initializeTileSize();

        setTitle("Painter");
        setBackground(Color.BLACK);

        int frameWidth = (int) (scaleFactor * tileSize * boardWidth);
        int frameHeight = (int) (scaleFactor * tileSize * boardHeight) + 35;

        setSize(frameWidth, frameHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setInitialPosition(0, 0, "east");

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
    private void render() {
        do {
            do {
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                paint(g);
                g.dispose();
            } while (bufferStrategy.contentsRestored());

            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
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
        playerX = Math.max(padding, Math.min(initialX, boardWidth - 1 + padding));
        playerY = Math.max(padding, Math.min(initialY, boardHeight - 1 + padding));
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

    public void paint(Color color) {
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
        // Create the buffer strategy after the frame is visible
        if (bufferStrategy == null) {
            createBufferStrategy(2); // Using double buffering
            bufferStrategy = getBufferStrategy();
        }

        // Rendering using BufferStrategy
        do {
            do {
                Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                super.paint(g2d); // Call the super.paint to ensure proper painting

                // Create the first buffer and set rendering hints
                BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics bufferGraphics = buffer.getGraphics();
                bufferGraphics.setColor(getBackground());
                bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

                // Create the second buffer for triple buffering
                BufferedImage buffer2 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics bufferGraphics2 = buffer2.getGraphics();
                bufferGraphics2.setColor(getBackground());
                bufferGraphics2.fillRect(0, 0, getWidth(), getHeight());

                int scaledTileSize = (int) (scaleFactor * tileSize);

                for (int y = 0; y < boardHeight; y++) {
                    for (int x = 0; x < boardWidth; x++) {
                        int xPos = (int) (x * scaledTileSize * scaleFactor);  // Adjusted calculation
                        int yPos = (int) (y * scaledTileSize * scaleFactor);  // Adjusted calculation

                        String tileType = getTileType(x, y);
                        Image tileImage = tileImages.get(tileType);

                        // Draw background to the first buffer
                        if (tileImage != null) {
                            bufferGraphics.drawImage(tileImage, xPos, yPos, scaledTileSize, scaledTileSize, null);
                        } else {
                            Color tileBackgroundColor = colorGrid.getBackgroundColor(x, y);
                            bufferGraphics.setColor(tileBackgroundColor);
                            bufferGraphics.fillRect(xPos, yPos, scaledTileSize, scaledTileSize);
                        }

                        // Draw paint layer to the first buffer
                        Color paintColor = colorGrid.getPaintColor(x, y);
                        if (paintColor != null) {
                            bufferGraphics.setColor(paintColor);
                            bufferGraphics.fillRect(xPos, yPos, scaledTileSize, scaledTileSize);
                        }

                        // Draw player to the first buffer
                        if (x == playerX && y == playerY) {
                            double rotationAngle = 0.0;

                            if ("north".equals(facingDirection)) {
                                rotationAngle = Math.PI;
                            } else if ("south".equals(facingDirection)) {
                                rotationAngle = 0.0;
                            } else if ("west".equals(facingDirection)) {
                                rotationAngle = Math.PI / 2;
                            } else if ("east".equals(facingDirection)) {
                                rotationAngle = -Math.PI / 2;
                            }

                            int imageX = xPos + scaledTileSize / 2 - painterImage.getWidth() / 2;
                            int imageY = yPos + scaledTileSize / 2 - painterImage.getHeight() / 2;
                            drawRotatedImage(bufferGraphics, painterImage, imageX, imageY, rotationAngle);
                        }
                    }
                }

                // Draw the first buffer to the second buffer for triple buffering
                bufferGraphics2.drawImage(buffer, 0, 0, this);

                // Draw the second buffer to the screen
                g2d.drawImage(buffer2, 0, getInsets().top, this);

                // Dispose of the graphics contexts
                bufferGraphics.dispose();
                bufferGraphics2.dispose();

                g2d.dispose();
            } while (bufferStrategy.contentsRestored());

            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }
    private String getTileType(int x, int y) {
        // Your logic to determine the type of tile at position (x, y)
        // Return the type as a string
        return "default"; // Change this based on your logic
    }

    private void drawRotatedImage(Graphics g, Image image, int x, int y, double angle) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(x + image.getWidth(null) / 2, y + image.getHeight(null) / 2);
        g2d.rotate(angle);
        g2d.drawImage(image, -image.getWidth(null) / 2, -image.getHeight(null) / 2, null);
        g2d.dispose();
    }
}