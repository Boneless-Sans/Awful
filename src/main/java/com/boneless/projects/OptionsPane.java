package com.boneless.projects;

import com.boneless.projects.utils.IconResize;

import javax.swing.*;

public class OptionsPane {
    public static void main(String[] args){
        String[] responses = {
                "Button 1",
                "Button 2",
                "Button 3"
        };
        IconResize icon = new IconResize("src/resource/assets/main.png");
        JOptionPane.showOptionDialog(
                null,
                "Body Text",
                "Hello, Title",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon.getImage(), responses, 0);
    }
}
