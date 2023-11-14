package com.boneless.projects;

import com.boneless.projects.utils.IconResize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Radio extends JFrame implements ActionListener {

    JRadioButton catButton;
    JRadioButton dogButton;
    JRadioButton ratButton;
    IconResize catIcon;
    IconResize dogIcon;
    IconResize ratIcon;
    Radio(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        catIcon = new IconResize("src/resource/assets/icon.png");
        dogIcon = new IconResize("src/resource/assets/pic.png");
        ratIcon = new IconResize("src/resource/assets/main.png");

        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        ratButton = new JRadioButton("Rat");

        ButtonGroup group = new ButtonGroup();
        group.add(catButton);
        group.add(dogButton);
        group.add(ratButton);

        catButton.setFocusable(false);
        dogButton.setFocusable(false);
        ratButton.setFocusable(false);

        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        ratButton.addActionListener(this);

        catButton.setIcon(catIcon.getImage());
        dogButton.setIcon(dogIcon.getImage());
        ratButton.setIcon(ratIcon.getImage());

        this.add(catButton);
        this.add(dogButton);
        this.add(ratButton);
        this.pack();
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==catButton){
            System.out.println("Cats are better");
            return;
        }
        else if(e.getSource()==dogButton){
            System.out.println("Dogs are better");
            return;
        }
        else if(e.getSource()==ratButton){
            System.out.println("Crazy? I was crazy once. they locked me in a room. A rubber room. A rubber room with rats. The rats made me crazy.");
            return;
        }
    }
    public static void main(String[] args){
        new Radio();
    }
}
