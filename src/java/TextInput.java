package src.java;

import src.java.utils.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextInput extends JFrame implements ActionListener{
    JButton button;
    JTextField textField;
    TextInput(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Submit");
        button.addActionListener(this);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.LIGHT_GRAY);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEtchedBorder());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textField.setBackground(Color.LIGHT_GRAY);
        //textField.setCaretColor(Color.BLUE);
        textField.setText("Username");
        textField.setEditable(true);

        this.getContentPane().setBackground(Color.gray);
        this.add(button);
        this.add(textField);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.out.println("Fuck off " + textField.getText());
        }
    }
    public static void main(String[] args){
        new TextInput();
    }
}
