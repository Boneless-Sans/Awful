package src.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Python {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> create());
    }

    public static void create() {
        JFrame frame = new JFrame("Encryption/Decryption");
        frame.setSize(200, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTextToNumbers();
            }
        });

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertImageToText();
            }
        });

        frame.add(encryptButton);
        frame.add(decryptButton);

        frame.setVisible(true);
    }

    public static void convertTextToNumbers() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File textFile = fileChooser.getSelectedFile();
            String saveLocation = "output.png";  // You can customize the save location.
            String password = JOptionPane.showInputDialog("Enter the encryption password:");
            String numbers = encryptTextToNumbers(textFile, password);
            convertNumbersToImage(numbers, saveLocation);
        }
    }

    public static void convertImageToText() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Files", "png"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File imageFile = fileChooser.getSelectedFile();
            String saveLocation = "output.txt";  // You can customize the save location.
            String password = JOptionPane.showInputDialog("Enter the decryption password:");
            String data = decryptImageToRGB(imageFile, password);
            String text = decryptText(data);
            writeTextFile(text, saveLocation);
        }
    }

    public static String encryptTextToNumbers(File textFile, String password) {
        try {
            Scanner scanner = new Scanner(textFile);
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }

            String encryptedText = encryptText(text.toString(), password);
            String[] chars = encryptedText.split("");
            List<String> numbers = new ArrayList<>();

            for (String character : chars) {
                int charValue = (int) character.charAt(0);
                numbers.add(Integer.toString(charValue));
            }

            return String.join(" ", numbers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptImageToRGB(File inputFile, String password) {
        try {
            BufferedImage image = ImageIO.read(inputFile);
            int width = image.getWidth();
            int height = image.getHeight();
            int pixelLength = 3;
            List<String> rgbValues = new ArrayList<>();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = new Color(image.getRGB(x, y));
                    rgbValues.add(Integer.toString(color.getRed()));
                    rgbValues.add(Integer.toString(color.getGreen()));
                    rgbValues.add(Integer.toString(color.getBlue()));
                }
            }

            String data = String.join(" ", rgbValues);
            password = password;
            data = decryptWithSeed(data, password.getBytes());

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptText(String text, String password) {
        StringBuilder encryptedText = new StringBuilder();
        int passwordLength = password.length();

        for (int i = 0; i < text.length(); i++) {
            char charValue = (char) (text.charAt(i) ^ password.charAt(i % passwordLength));
            encryptedText.append(charValue);
        }

        return encryptedText.toString();
    }

    public static String decryptText(String text) {
        String[] items = text.split(" ");
        List<String> decryptedText = new ArrayList<>();

        for (String item : items) {
            try {
                int itemValue = Integer.parseInt(item);
                char charValue = (char) itemValue;
                decryptedText.add(Character.toString(charValue));
            } catch (NumberFormatException e) {
                decryptedText.add("");
            }
        }

        return String.join("", decryptedText);
    }

    public static void convertNumbersToImage(String numbers, String saveLocation) {
        String[] data = numbers.split(" ");
        int length = data.length;
        int width = (int) Math.sqrt(length / 3);
        int height = (length / 3) / width + 1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < length - 2; i += 3) {
            int r = Integer.parseInt(data[i]);
            int g = Integer.parseInt(data[i + 1]);
            int b = Integer.parseInt(data[i + 2]);

            int x = (i / 3) % width;
            int y = (i / 3) / width;

            Color color = new Color(r, g, b);
            image.setRGB(x, y, color.getRGB());
        }

        try {
            File outputFile = new File(saveLocation);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decryptWithSeed(String data, byte[] seed) {
        String[] items = data.split(" ");
        List<String> decryptedData = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            byte seedByte = seed[i % seed.length];
            int itemValue = Integer.parseInt(items[i]);
            int decryptedItem = itemValue ^ seedByte;
            decryptedData.add(Integer.toString(decryptedItem));
        }

        return String.join(" ", decryptedData);
    }

    public static void writeTextFile(String text, String saveLocation) {
        try (FileWriter writer = new FileWriter(saveLocation)) {
            writer.write(text);
            System.out.println("Text saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
