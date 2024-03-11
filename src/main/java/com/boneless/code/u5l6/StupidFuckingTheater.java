package com.boneless.code.u5l6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StupidFuckingTheater extends JFrame {
    public StupidFuckingTheater() throws IOException {
        init();
        setVisible(true);
    }
    private void init() throws IOException {
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        File file = new File("src/main/resources/assets/images/cet.png");
        BufferedImage image = ImageIO.read(file);
        BufferedImage BAWImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Calculate the average of the RGB values
                int average = (red + green + blue) / 3;

                // Set the pixel in the black and white image to the average value
                BAWImage.setRGB(x, y, average);
            }
        }
        ImageIcon blackAndWhiteImageIcon = new ImageIcon(BAWImage);
        JLabel label = new JLabel(blackAndWhiteImageIcon);
        add(label);
    }
    public static void main(String[] args) throws IOException {
        new StupidFuckingTheater();
    }
}
