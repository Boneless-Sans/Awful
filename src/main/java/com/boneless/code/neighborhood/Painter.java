package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Painter extends JFrame {

    private int tileSize = 50; // Size of each tile
    private int boardWidth, boardHeight;
    private int playerX, playerY;
    private int padding = 1; // Number of tiles for padding

    private double scale = Double.parseDouble(JsonFile.read("painter.json", "default", "scale"));
    private String facingDirection = "east"; // Initial direction (east)
    private Map<String, Image> tileImages = new HashMap<>();
    private Map<String, Color> paintedTiles = new HashMap<>();
    private BufferedImage painterImage;

    private double scaleFactor = 1.5; // Default scaling factor
    private BufferedImage buffer; // Offscreen image for double buffering

    private final int scaledTileSize = (int) (scaleFactor * tileSize);

    public Painter() {
        this(0, 0, "east"); // Call the parameterized constructor with default values and use tile gaps
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Painter(int initialX, int initialY, String initialDirection) {
        initializeBoardSize();
        initializeTileImages();
        initializePainterImage();
        initializeTileSize();

        setTitle("Painter");

        int frameWidth = (int) (scaleFactor * tileSize * boardWidth);
        int frameHeight = (int) (scaleFactor * tileSize * boardHeight) + 35;

        setSize(frameWidth, frameHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setResizable(false);

        setInitialPosition(initialX, initialY, initialDirection);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        setVisible(true);
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
                tileImages.put("default", ImageIO.read(defaultImageStream));
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
        // Retrieve the scale factor from the JSON file
        String scaleString = JsonFile.read("painter.json", "default", "scale");
        try {
            scale = Double.parseDouble(scaleString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        scaleFactor = scale; // Set scaleFactor to the retrieved scale
        tileSize = (int) (scaleFactor * tileSize); // Scale the tile size
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
        // Draw to the offscreen image first
        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Set rendering hints for better quality
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate scaled tile size
        int scaledTileSize = (int) (scaleFactor * tileSize);

        // Draw the board with tiles, painted tiles, and player on the offscreen image
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                int xPos = x * scaledTileSize;
                int yPos = y * scaledTileSize;

                // Draw the tile
                if (tileImages.containsKey("default")) {
                    bufferGraphics.drawImage(tileImages.get("default"), xPos, yPos, scaledTileSize, scaledTileSize, null);
                }

                // Draw the painted tile
                String tileKey = x + "," + y;
                if (paintedTiles.containsKey(tileKey)) {
                    bufferGraphics.setColor(paintedTiles.get(tileKey));
                    bufferGraphics.fillRect(xPos, yPos, scaledTileSize, scaledTileSize);
                }

                // Draw the painter image with rotation
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

        // Copy the offscreen image to the screen
        g.drawImage(buffer, 0, getInsets().top, this);
    }

    private void drawRotatedImage(Graphics g, Image image, int x, int y, double angle) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotate the image around its center
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        transform.rotate(angle, image.getWidth(this) / 2.0, image.getHeight(this) / 2.0);

        g2d.drawImage(image, transform, this);
        g2d.dispose();
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
