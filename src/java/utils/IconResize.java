package src.java.utils;

import javax.swing.*;
import java.awt.*;

public class IconResize {
    private String icon;
    private int width;
    private int height;
    private ImageIcon imageIcon;

    public IconResize() {
        icon = "default.png";
        width = 100;
        height = 100;
    }

    public IconResize(String aIcon, int aWidth, int aHeight) {
        icon = aIcon;
        width = aWidth;
        height = aHeight;
    }

    public void set() {
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
