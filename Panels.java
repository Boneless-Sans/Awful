import javax.swing.*;
import java.awt.*;

public class Panels {
    public static void main(String[] args){
        JPanel bluePanel = new JPanel();
        JPanel redPanel = new JPanel();

        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0,0,250,250);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);
        frame.add(bluePanel);

        //frame.pack();
    }
}
