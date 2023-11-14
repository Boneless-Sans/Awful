package com.boneless.projects;

import com.boneless.projects.utils.IconResize;

import javax.swing.*;

public class OptionsPane {
    public static void main(String[] args){
//        JOptionPane.showMessageDialog(null, "KYS", "Plain Text", JOptionPane.PLAIN_MESSAGE);
//        JOptionPane.showMessageDialog(null, "You really should", "Information", JOptionPane.INFORMATION_MESSAGE);
//        JOptionPane.showMessageDialog(null, "Can you do it pussy?", "Boy do I have a question for you", JOptionPane.QUESTION_MESSAGE);
//        JOptionPane.showMessageDialog(null, "FUCKING DO IT", "WARNING!", JOptionPane.WARNING_MESSAGE);
//        JOptionPane.showMessageDialog(null, "Pussy", "ERROR", JOptionPane.ERROR_MESSAGE);
//        while(true){
//            JOptionPane.showMessageDialog(null, "Your Computer has a VIRUS!!", "VIRUS DETECTED!", JOptionPane.INFORMATION_MESSAGE);
//        }

//        int answer = JOptionPane.showConfirmDialog(null, "yae or nae?", "Choose", JOptionPane.YES_NO_OPTION);
//        System.out.println(answer);
//        String name = JOptionPane.showInputDialog("What is your cock size?");
//        System.out.println(name); //should be used for scores

        String[] responses = {
                "a",
                "Will do",
                "*bruh*"
        };
        IconResize icon = new IconResize("src/resource/assets/main.png");
        JOptionPane.showOptionDialog(null,
                "You are not fucking awesome",
                "kys",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon.getImage(), responses, 0);
    }
}
