package com.boneless.projects.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class ImageTiles {
    public static ImageIcon[] parse(String filename, int tileSizeX, int tileSizeY) {
        try {
            String imagePath = getResourcePath("assets/images/" + filename);
            assert imagePath != null;
            BufferedImage image = ImageIO.read(new File(imagePath));
            int rows = image.getHeight() / tileSizeY;
            int cols = image.getWidth() / tileSizeX;

            ImageIcon[] tiles = new ImageIcon[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    BufferedImage tileImage = image.getSubimage(j * tileSizeX, i * tileSizeY, tileSizeX, tileSizeY);
                    Image scaledImage = tileImage.getScaledInstance(tileSizeX, tileSizeY, Image.SCALE_SMOOTH);
                    ImageIcon tileIcon = new ImageIcon(scaledImage);
                    tiles[i * cols + j] = tileIcon;
                }
            }

            return tiles;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getResourcePath(String relativePath) {
        try {
            return Paths.get(Objects.requireNonNull(ImageTiles.class.getClassLoader().getResource(relativePath)).toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        ImageIcon[] wallTiles = ImageTiles.parse("wall.png", 80, 80);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(4,10,0,0));

//        for (int index = 0; index < wallTiles.length; index++) {
//            frame.add(new JLabel(wallTiles[index]));
//        }
        assert wallTiles != null;
        frame.add(new JLabel(wallTiles[20]));

        frame.pack();
        frame.setVisible(true);
    }
}
