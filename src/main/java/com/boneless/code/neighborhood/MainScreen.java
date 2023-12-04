package com.boneless.code.neighborhood;

import com.boneless.projects.Main;
import com.boneless.projects.utils.NormalButtons;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    public MainScreen(){
        init();
        initUI();
    }
    private void init(){
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
    }
    private void initUI(){
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        NormalButtons.set();
        JButton start = new JButton("Start");

        JButton levelEditor = new JButton("Level Editor");
        levelEditor.setEnabled(false);

        JButton exit = new JButton("Exit");

        mainPanel.add(start);
        mainPanel.add(levelEditor);
        mainPanel.add(exit);
        add(mainPanel);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(MainScreen::new);
    }
}
