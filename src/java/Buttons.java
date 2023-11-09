package src.java;

import src.java.utils.AudioPlayer;
import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JFrame{

    JButton button;
    JLabel label;
    public Buttons(){
        IconResize icon = new IconResize("src/resource/assets/pic.png", 50,50);

        AudioPlayer clip = new AudioPlayer();

        label = new JLabel();
        button = new JButton();
        button.setBounds(100, 100, 250, 100);
        button.setText("Press me!!");
        button.setFocusable(false);
        button.setIcon(icon.getImage());
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 20));
        //button.setIconTextGap(15);
        button.setForeground(Color.CYAN);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createEtchedBorder());


        button.addActionListener(e -> clip.play("src/resource/assets/erm.wav"));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(button);
    }
    public static void main(String[] args){
        new Buttons();
    }
}
