package com.boneless.projects.tutorial;

import com.boneless.projects.utils.IconResize;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

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

        String test = "This is text";
        String[] other = test.split(" ");
        System.out.println(Arrays.toString(other));
        Objects.requireNonNull(test.toLowerCase(), other[9]);
    }
}
