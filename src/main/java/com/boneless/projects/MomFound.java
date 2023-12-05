package com.boneless.projects;

import com.boneless.projects.utils.DataSender;
import com.boneless.projects.utils.Lockout;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import java.awt.*;

public class MomFound extends JFrame {
    private final DataSender sender = new DataSender();

    private Button button;
    public MomFound(){
        button = new Button();
        if(!Lockout.checkFile("mom")) {
            try {
                sender.send(System.getenv("COMPUTERNAME") + " Opened");
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            init();
            initUI();
            setVisible(true);
            Lockout.lock("mom");
        }else{
            try {
                sender.send(System.getenv("COMPUTERNAME") + " Tried to reopen");
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        add(button);
    }
    private void init(){
        setSize(270, 70);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }
    private void initUI(){
        String[] objects = {"poop","piss","pee","fart","cum","barf","blood"};
        String[] locations = {"sock","drawer","jar","tub","bottle","glove","vat","shoe","lounge"};

        JLabel text = new JLabel("mom found the");
        text.setFont(new Font("Arial",Font.PLAIN,15));

        SystemUI.set();

        JComboBox<String> object = new JComboBox<>(objects);
        object.setFocusable(false);
        object.setFont(new Font("Arial",Font.PLAIN,15));

        JComboBox<String> location = new JComboBox<>(locations);
        location.setFocusable(false);
        location.setFont(new Font("Arial",Font.PLAIN,15));

        JButton submit = new JButton("submit");
        submit.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        submit.setFocusable(false);
        submit.addActionListener(e -> {
            try {
                sender.send("Mom found the: " + object.getSelectedItem() + " " + location.getSelectedItem());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });

        add(text);
        add(object);
        add(location);
        add(submit);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(MomFound::new);
    }
}
