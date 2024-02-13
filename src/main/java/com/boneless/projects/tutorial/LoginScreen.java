package com.boneless.projects.tutorial;

import com.boneless.projects.GameRunner;
import com.boneless.projects.utils.JsonFile;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame{
    public LoginScreen() {
        init();
        initUI();
    }

    private void init() {
        setSize(240, 250);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(false);
        setVisible(true);
    }

    private void initUI() {
        JLabel usernameText = new JLabel("Username:");
        usernameText.setFont(new Font("Arial", Font.PLAIN, 15));
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(150,30));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel passwordText = new JLabel("Password:");
        passwordText.setFont(new Font("Arial", Font.PLAIN, 15));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150,30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));

        SystemUI.set();
        JButton login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.addActionListener(e -> {
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            checkCreds(usernameField.getText(), password);
        });
        login.setFocusable(false);

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.addActionListener(e -> {
            System.exit(0);
        });
        exit.setFocusable(false);

        add(usernameText);
        add(usernameField);
        add(passwordText);
        add(passwordField);
        add(login);
        add(exit);
    }

    private void checkCreds(String username, String password){
        if(!username.isEmpty() && !password.isEmpty() && JsonFile.checkCredentials("users.json",username,password)){
            dispose();
            SwingUtilities.invokeLater(GameRunner::new);
        }else{
            JOptionPane.showMessageDialog(null,"Invalid Username or Password");
        }

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
