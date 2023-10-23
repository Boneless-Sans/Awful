package src.java;

import src.java.utils.AudioPlayer;
import src.java.utils.IconResize;

import javax.swing.*;

public class Drunk extends JFrame {
    JButton button;
    JLabel label;

    public Drunk(){
        IconResize icon = new IconResize();
        icon.set("src/resource/assets/erm.png", 50, 50);
        AudioPlayer clip = new AudioPlayer();

        icon.set("src/resource/assets/icon.png", 100,100);
        label = new JLabel();
        button = new JButton();

        button.setBounds(50,50,250,250);
        button.setText("hella drunk");
        button.setIcon(icon.getImage());

        button.addActionListener(e -> clip.play("src/resource/assets/erm.wav"));

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(button);
        this.setIconImage(icon.getImage().getImage());
    }
}
