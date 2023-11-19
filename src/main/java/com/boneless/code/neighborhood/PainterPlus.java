package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;
import com.boneless.projects.utils.NormalButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.ResourceBundle;

public class PainterPlus extends Painter implements KeyListener {
    private static Color selectedColor;
    private static boolean toggle = true;
    private final int originalHeight = getHeight();
    private final int originalWidth = getWidth();
    private static final Color[] COLORS = JsonFile.readColorArray("painter.json", "colors");
    public PainterPlus(){
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/main/resources/assets/images/painter.png");
        setIconImage(icon.getImage());

        JButton button = new JButton();
        button.addActionListener(e -> {
            colorPickerUI();
        });
        colorPickerUI();
    }
    private void colorPickerUI() {
        ColorPickerFrame frame = new ColorPickerFrame("Color Picker");
        frame.setVisible(true);
    }
    private static class ColorPickerFrame extends JFrame {

        public ColorPickerFrame(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500,500);
            setLayout(new BorderLayout());
            ImageIcon icon = new ImageIcon("src/main/resources/assets/images/colors.png");
            setIconImage(icon.getImage());

            JPanel panel = new JPanel(new GridLayout(2, 6, 5, 5));
            for (Color color : COLORS) {
                JButton colorButton = new JButton();
                colorButton.setBackground(color);
                colorButton.setPreferredSize(new Dimension(50, 50));
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedColor = color;
                    }
                });
                panel.add(colorButton);
            }
            JPanel RGBPanel = new JPanel(new FlowLayout());
            JLabel infoText = new JLabel("Custom RGB");
            infoText.setFont(new Font("Arial", Font.PLAIN, 20));

            JTextField red = new JTextField();
            JTextField green = new JTextField();
            JTextField blue = new JTextField();

            red.setPreferredSize(new Dimension(45,30));
            red.setFont(new Font("Arial", Font.ITALIC, 20));

            green.setPreferredSize(new Dimension(45,30));
            green.setFont(new Font("Arial", Font.ITALIC, 20));

            blue.setPreferredSize(new Dimension(45,30));
            blue.setFont(new Font("Arial", Font.ITALIC, 20));

            JLabel comma = new JLabel(",");
            comma.setFont(new Font("Arial", Font.PLAIN, 20));

            NormalButtons.set();
            JButton submitButton = new JButton("Use");
            submitButton.setFocusable(false);

            String[] buttons = {
                    "Restart",
                    "Continue",
                    "Don't Show Again"
            };
            JButton saveButton = new JButton("Save");
            saveButton.setFocusable(false);
            saveButton.addActionListener(e -> {
                int input = JOptionPane.showOptionDialog(
                    null,
                    "Program Restart Required to Use New Color",
                    "Restart to Use New Color",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/main/resource/assets/images/question_mark.png"),
                    buttons,
                    0
                );
                switch(input){
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("exited");
                        break;
                    case 2:
                        JsonFile.writeln("painter.json", "data", "save_color_option","false");
                        break;
                }
            });

            RGBPanel.add(infoText);
            RGBPanel.add(red);
            RGBPanel.add(new JLabel(","));
            RGBPanel.add(green);
            RGBPanel.add(new JLabel(","));
            RGBPanel.add(blue);

            RGBPanel.add(submitButton);
            if(!Boolean.parseBoolean(JsonFile.read("painter.json", "data", "save_color_option"))){
                RGBPanel.add(saveButton);
            }

            add(panel, BorderLayout.CENTER);
            add(RGBPanel, BorderLayout.SOUTH);

            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    // Custom logic when the close button is clicked
                    // Dispose the frame and set toggle to false
                    dispose();
                    toggle = false;
                }
            });
            setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case'w':
                while (!Objects.equals(getFacingDirection(), "north")) {
                    turnLeft();
                }
                move();
                break;
            case'd':
                while (!Objects.equals(getFacingDirection(), "east")) {
                    turnLeft();
                }
                move();
                break;
            case's':
                while (!Objects.equals(getFacingDirection(), "south")) {
                    turnLeft();
                }
                move();
                break;
            case'a':
                while (!Objects.equals(getFacingDirection(), "west")) {
                    turnLeft();
                }
                move();
                break;
            case'e':
                turnRight();
                break;
            case'q':
                turnLeft();
                break;
            case' ':
                paint(selectedColor);
                break;
        }
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
            if(toggle){
                System.exit(0);
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}