package com.boneless.projects;

import com.boneless.projects.utils.AudioPlayer;
import com.boneless.projects.utils.JsonFile;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Counter extends JFrame {
    public static void main(String[] args){
        new Counter();
    }
    int count = 0;
    private final String fileName = "nutCount.json";
    public Counter(){
        File file = new File("src/main/resources/data/" + fileName);
        try {
            System.out.println("File Exists: " + file.exists());
            if (file.exists()) {
                String test = JsonFile.read(fileName,"data","count");
                System.out.println(test);
            }else{
                Path path = Paths.get("src/main/resources/data/" + fileName);
                boolean test = file.createNewFile();
                String blank = "{}";
                Files.write(path, blank.getBytes());
                System.out.println("file? " + test);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        setSize(500,500);
        setLocationRelativeTo(null);
        addWindowListener(closingWindow());
        setTitle("Counter");
        setLayout(new GridBagLayout());

        SystemUI.set();

        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(150,300));

        Font font = new Font("comic sans ms",Font.PLAIN,100);
        JLabel countLabel = new JLabel(String.valueOf(count));
        countLabel.setFont(font);

        JButton countButton = new JButton("Nut");
        countButton.setFocusable(false);
        countButton.setFont(new Font("comic sans ms",Font.PLAIN,25));
        countButton.setPreferredSize(new Dimension(100,100));
        countButton.addActionListener(e -> {
            count++;
            countLabel.setText(String.valueOf(count));
            AudioPlayer.play("nut.wav");
        });

        panel.add(countLabel);
        panel.add(createEmptyPanel());
        panel.add(countButton);
        panel.add(createEmptyPanel());

        add(panel);

        setVisible(true);
    }
    private JPanel createEmptyPanel(){
        JPanel panel = new JPanel();
        int size = 10;
        panel.setPreferredSize(new Dimension(size,size));
        panel.setBackground(new Color(0,0,0,0));
        panel.setOpaque(true);
        return panel;
    }
    private WindowAdapter closingWindow(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JsonFile.writeln(fileName,"data","user",String.valueOf(count));
                System.exit(0);
            }
        };
    }
}
