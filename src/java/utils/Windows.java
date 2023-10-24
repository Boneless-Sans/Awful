package src.java.utils;

import javax.swing.*;
import java.awt.*;

public class Windows extends JFrame {
    public Windows(){
        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Windows(int width, int hight) {
        this.setSize(width,hight);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Windows(int width, int hight, boolean hasLayout) {
        this.setSize(width,hight);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(hasLayout){
            return;
        }else{
            this.setLayout(null);
        }
    }
    public Windows(int width, int hight, String title) {
        this.setSize(width,hight);
        this.setVisible(true);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Windows(int width, int hight, String title, String icon) {
        ImageIcon theIcon = new ImageIcon(icon);
        this.setSize(width,hight);
        this.setVisible(true);
        this.setTitle(title);
        this.setIconImage(theIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
