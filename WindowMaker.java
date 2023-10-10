import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class WindowMaker extends JFrame{
    WindowMaker(String name, String icon, int r, int g, int b, int width, int hight){
        this.setTitle(name); // sets window name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when closed
        this.setSize(width, hight); // sets window sizes
        this.setResizable(false); //prevents resizing
        this.setVisible(true); //makes the window visable

        ImageIcon image = new ImageIcon(icon);
        this.setIconImage(image.getImage()); //changes window icon

        this.getContentPane().setBackground(new Color(r,g,b)); //Color.BLUE
    }
}
