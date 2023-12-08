package com.boneless.projects;

import com.boneless.projects.utils.IconResize;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckMark extends JFrame implements ActionListener {
    JCheckBox checkBox;
    JButton button;
    IconResize checkMark;
    IconResize xMark;
    CheckMark(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        checkMark = new IconResize("src/main/resource/assets/check_mark.png", 20,20);
        xMark = new IconResize("src/main/resource/assets/x_mark.png", 20,20);

        button = new JButton("Submit");
        button.addActionListener(this);

        checkBox = new JCheckBox();
        checkBox.setText("Delete System32?");
        checkBox.setFocusable(false);
        checkBox.addActionListener(this);
        checkBox.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        checkBox.setIcon(xMark.getImage());
        checkBox.setSelectedIcon(checkMark.getImage());

        this.add(button);
        this.add(checkBox);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.out.println(checkBox.isSelected());
        }
    }
    public static void main(String[] args){
        new CheckMark();
    }
}
