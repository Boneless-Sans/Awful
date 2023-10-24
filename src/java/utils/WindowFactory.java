package src.java.utils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class WindowFactory extends JFrame{
    private String iconImage;
    private String name;
    private int CloseOperation;
    private int width;
    private int height;
    private boolean resizeable;

    public WindowFactory(){
        width = 250;
        height = 250;
    }

    public WindowFactory(String icon, String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.iconImage = icon;
        this.resizeable = false;
    }

    public WindowFactory(String icon, String name, int width, int height, String closeOperation, boolean resizeable){
        this.name = name;
        this.width = width;
        this.height = height;
        this.iconImage = icon;
        this.resizeable = resizeable;
        switch(closeOperation){
            case "nothing":
                CloseOperation = JFrame.DO_NOTHING_ON_CLOSE;
            case "exit":
                CloseOperation = JFrame.EXIT_ON_CLOSE;
            case "hide":
                CloseOperation = JFrame.HIDE_ON_CLOSE;
            case "dispose":
                CloseOperation = JFrame.DISPOSE_ON_CLOSE;
            default:
                CloseOperation = JFrame.EXIT_ON_CLOSE;
        }
    }

    public void make(){
        ImageIcon icon = new ImageIcon(iconImage);

        this.setResizable(resizeable);
        this.setDefaultCloseOperation(CloseOperation);
        this.setIconImage(icon.getImage());
        this.setTitle(name);
        this.setSize(width, height);
        this.setVisible(true);
    }

}
