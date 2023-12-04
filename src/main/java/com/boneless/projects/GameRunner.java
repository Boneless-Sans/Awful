package com.boneless.projects;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class GameRunner {
    static String[] buttonNames = {
            "name1",
            "name2",
            "name3",
            "name4",
            "name5",
            "name6",
            "name7",
            "name8",
            "name9",
            "name10"
    };
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        for(String buttonName : buttonNames){
            JButton button = new JButton(buttonName);
            button.setName(buttonName);
            frame.add(button);
        }
        frame.setVisible(true);
    }
}
