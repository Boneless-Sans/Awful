package src.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JTextField classNameField;

    public Main() {
        setTitle("Java File Runner");
        setSize(300, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        classNameField = new JTextField();
        JButton runButton = new JButton("Run");

        mainPanel.add(classNameField, BorderLayout.CENTER);
        mainPanel.add(runButton, BorderLayout.EAST);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaFile();
            }
        });

        add(mainPanel);
    }

    private void runJavaFile() {
        String className = classNameField.getText();
        if (className.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a class name.");
            return;
        }

        String javaFileName = className + ".java";
        File javaFile = new File(javaFileName);

        if (!javaFile.exists()) {
            JOptionPane.showMessageDialog(this, "Java file not found: " + javaFileName);
            return;
        }

        try {
            // Run the Java file using the system's default editor.
            Desktop.getDesktop().edit(javaFile);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while opening the Java file.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main gui = new Main();
            gui.setVisible(true);
        });
    }
}
