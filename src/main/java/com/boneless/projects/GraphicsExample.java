package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GraphicsExample extends JFrame {

    public GraphicsExample() {
        setTitle("Bouncing Overlay Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create a custom panel and add it to the frame
        BouncingOverlayPanel bouncingOverlayPanel = new BouncingOverlayPanel();
        add(bouncingOverlayPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Custom panel for drawing with bouncing overlay
    private static class BouncingOverlayPanel extends JPanel {

        private BufferedImage backgroundImage;
        private BufferedImage overlayImage;
        private int overlayX = 150; // Initial X position of the overlay
        private int overlayY = 150; // Initial Y position of the overlay
        private int deltaX = 1; // Initial X direction of movement
        private int deltaY = 1; // Initial Y direction of movement

        public BouncingOverlayPanel() {
            // Load the background and overlay images
            try {
                backgroundImage = loadImage("assets/images/tile.png");
                overlayImage = loadImage("assets/main.png");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set up a timer for animation (moving the bouncing overlay)
            Timer timer = new Timer(16, e -> moveOverlay());
            timer.start();
        }

        private void moveOverlay() {
            // Move the overlay by the specified delta values
            overlayX += deltaX;
            overlayY += deltaY;

            // Check for collisions with the edges and reverse direction if needed
            if (overlayX < 0 || overlayX + overlayImage.getWidth() > getWidth()) {
                deltaX = -deltaX; // Reverse X direction
            }
            if (overlayY < 0 || overlayY + overlayImage.getHeight() > getHeight()) {
                deltaY = -deltaY; // Reverse Y direction
            }

            // Repaint the panel to update the display
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Cast to Graphics2D for more advanced drawing options
            Graphics2D g2d = (Graphics2D) g;

            // Draw the tiled background image
            if (backgroundImage != null) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the number of tiles needed to cover the panel
                int numTilesX = (panelWidth / backgroundImage.getWidth()) + 1;
                int numTilesY = (panelHeight / backgroundImage.getHeight()) + 1;

                // Draw the tiles
                for (int i = 0; i < numTilesX; i++) {
                    for (int j = 0; j < numTilesY; j++) {
                        int xPos = i * backgroundImage.getWidth();
                        int yPos = j * backgroundImage.getHeight();
                        g2d.drawImage(backgroundImage, xPos, yPos, this);
                    }
                }
            }

            // Draw the bouncing overlay image on top
            if (overlayImage != null) {
                g2d.drawImage(overlayImage, overlayX, overlayY, this);
            }

            // Draw additional objects or layers as needed
            // ...
        }

        private BufferedImage loadImage(String path) throws IOException {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
                if (inputStream != null) {
                    return ImageIO.read(inputStream);
                } else {
                    throw new IOException("Could not open input stream for resource: " + path);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(GraphicsExample::new);
    }
}
