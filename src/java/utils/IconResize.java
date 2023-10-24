package src.java.utils;

import javax.swing.*;
import java.awt.*;

public class IconResize {
    private ImageIcon imageIcon;
    private String icon;
    private int width;
    private int height;

    public IconResize() {
        icon = "default.png";
        width = 100;
        height = 100;
    }
    public IconResize(String icon, int width, int height) {
        //must run before being called!!
        ImageIcon originalIcon = new ImageIcon(icon);
        Image image = originalIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
    }
    public void set(String icon, int width, int height) {
        //must run before being called!!
        ImageIcon originalIcon = new ImageIcon(icon);
        Image image = originalIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
    }
    public IconResize(String icon) {
        //must run before being called!!
        ImageIcon originalIcon = new ImageIcon(icon);
        Image image = originalIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
    }

    public ImageIcon getImage() {
        return imageIcon;
    }
}
