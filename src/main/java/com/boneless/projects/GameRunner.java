package com.boneless.projects;

import com.boneless.projects.utils.SystemUI;

import javax.swing.*;

public class GameRunner {
    public static void main(String[] args) {
        SystemUI.set();
        JFrame frame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        frame.setVisible(true);
    }
}
