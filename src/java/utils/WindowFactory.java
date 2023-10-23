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

    public WindowFactory(String aIcon, String aName, int aWidth, int aHeight){
        name = aName;
        width = aWidth;
        height = aHeight;
        iconImage = aIcon;
        resizeable = false;
    }

    public WindowFactory(String aIcon, String aName, int aWidth, int aHeight, String aCloseOperation, boolean aResizeable){
        name = aName;
        width = aWidth;
        height = aHeight;
        iconImage = aIcon;
        resizeable = aResizeable;
        switch(aCloseOperation){
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
