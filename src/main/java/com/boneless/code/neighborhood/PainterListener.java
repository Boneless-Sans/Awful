package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;
import com.boneless.projects.utils.SystemUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class PainterListener extends Painter implements KeyListener {
    private static Color selectedColor;
    private static boolean toggle = true;
    private final int originalHeight = getHeight();
    private final int originalWidth = getWidth();
    private static JPanel preview;
    private static JTextField red;
    private static JTextField green;
    private static JTextField blue;
    private static JLabel error;
    private Painter painter;
    private static final Color[] defaultColors = {
            Color.red,
            Color.orange,
            Color.yellow,
            Color.green,
            Color.cyan,
            Color.blue,
            new Color(255, 105, 180),
            new Color(150, 0, 255),
            new Color(139, 69, 19),
            Color.white,
            new Color(200, 200, 200),
            Color.gray,
            new Color(69, 69, 69),
            Color.black
    };
    private static final Color[] COLORS = JsonFile.readColorArray("painter.json", "colors");

    public PainterListener() {
        super(0, 0, "east");
        init();
    }

    public PainterListener(int xCord, int yCord, String facing) {
        super(xCord, yCord, facing);
        init();
    }
    private void init(){
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
    }

    private static class ColorPickerFrame extends JFrame{
        public ColorPickerFrame(String title) {
            super(title);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(480, 250);
            setLayout(new BorderLayout());
            ImageIcon icon = new ImageIcon("src/main/resources/assets/images/colors.png");
            setIconImage(icon.getImage());

            boolean showCustom = true;
            if (COLORS.length == 0) {
                showCustom = false;
            }

            JPanel selectedColorPreview = new JPanel();
            selectedColorPreview.setBackground(Color.BLACK);
            selectedColorPreview.setPreferredSize(new Dimension(30, 30));

            JPanel defaultPanel = new JPanel(new GridLayout(2, 8, 5, 5));
            for (Color color : defaultColors) {
                JButton colorButton = new JButton();
                colorButton.setBackground(color);
                colorButton.setPreferredSize(new Dimension(60, 60));
                colorButton.addActionListener(e -> selectedColor = color);
                selectedColorPreview.setBackground(selectedColor);
                defaultPanel.add(colorButton);
            }

            JPanel savedColorsPanel = new JPanel(new GridLayout(2, 8, 5, 5));
            // Adjust the preferred size of the savedColorsPanel if necessary
            for (Color color : COLORS) {
                JButton savedColorButton = new JButton();
                savedColorButton.setBackground(color);
                savedColorButton.setPreferredSize(new Dimension(60, 60));
                savedColorButton.addActionListener(e -> selectedColor = color);
                selectedColorPreview.setBackground(selectedColor);
                savedColorsPanel.add(savedColorButton);
            }

            preview = new JPanel(new BorderLayout());
            preview.setPreferredSize(new Dimension(30, 30));
            preview.setBackground(Color.BLACK);

            ImageIcon imageIcon;

            ImageIcon originalIcon = new ImageIcon("src/main/resources/assets/images/barrier.png");
            Image image = originalIcon.getImage();
            Image newImg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImg);

            error = new JLabel(new ImageIcon(imageIcon.getImage()));

            JPanel RGBPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel infoText = new JLabel("Custom RGB");
            infoText.setFont(new Font("Arial", Font.PLAIN, 15));

            red = new JTextField();
            green = new JTextField();
            blue = new JTextField();

            red.setPreferredSize(new Dimension(45, 30));
            red.setFont(new Font("Arial", Font.ITALIC, 15));
            red.setText("0");
            red.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (Character.isDigit(c)) {
                        super.keyTyped(e);
                    } else {
                        e.consume();
                    }
                }
            });
            red.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

            });

            green.setPreferredSize(new Dimension(45, 30));
            green.setFont(new Font("Arial", Font.ITALIC, 15));
            green.setText("0");
            green.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

            });

            blue.setPreferredSize(new Dimension(45, 30));
            blue.setFont(new Font("Arial", Font.ITALIC, 15));
            blue.setText("0");
            blue.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updatePreviewColor();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

            });

            JLabel comma = new JLabel(",");
            comma.setFont(new Font("Arial", Font.PLAIN, 15));

            SystemUI.set();
            JButton submitButton = new JButton("Use");
            submitButton.addActionListener(e -> {
                selectedColor = new Color(Integer.parseInt(red.getText()), Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
            });
            submitButton.setFocusable(false);

            String[] buttons = {
                    "Restart",
                    "Continue",
                    "Don't Show Again"
            };
            JButton saveButton = new JButton("Save");
            saveButton.setFocusable(false);
            saveButton.addActionListener(e -> {
                if (!Boolean.parseBoolean(JsonFile.read("painter.json", "data", "save_color_option"))) {
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
                    switch (input) {
                        case 0:
                            System.exit(0);
                            break;
                        case 2:
                            JsonFile.writeln("painter.json", "data", "save_color_option", "true");
                            break;
                    }
                }
                JsonFile.writeToArray("painter.json", "colors", "new Color( " + red.getText() + "," + green.getText() + "," + blue.getText() + ")");
            });

            RGBPanel.add(preview);
            RGBPanel.add(infoText);

            RGBPanel.add(red);
            RGBPanel.add(new JLabel(","));
            RGBPanel.add(green);
            RGBPanel.add(new JLabel(","));
            RGBPanel.add(blue);

            RGBPanel.add(submitButton);
            RGBPanel.add(saveButton);

            RGBPanel.add(selectedColorPreview);

            JLabel defaultColorsText = new JLabel("Default Colors");
            defaultColorsText.setFont(new Font("Arial", Font.PLAIN, 15));

            JLabel savedColors = new JLabel("Saved Colors");
            savedColors.setFont(new Font("Arial", Font.PLAIN, 15));

            JPanel globalPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0;  // Allow horizontal expansion
            globalPanel.add(defaultColorsText, gbc);

            gbc.gridy = 1;
            gbc.weighty = 1.0;  // Allow vertical expansion
            gbc.anchor = GridBagConstraints.CENTER;
            globalPanel.add(defaultPanel, gbc);

            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weighty = 0.0;  // Reset vertical expansion
            if (showCustom) {
                globalPanel.add(savedColors, gbc);
            }

            gbc.gridy = 3;
            gbc.weighty = 1.0;  // Allow vertical expansion
            gbc.anchor = GridBagConstraints.CENTER;
            if (showCustom) {
                globalPanel.add(savedColorsPanel, gbc);
                setSize(480, 400);
            }

            add(globalPanel, BorderLayout.CENTER);

            add(RGBPanel, BorderLayout.SOUTH);

            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    dispose();
                    toggle = false;
                }
            });
            setVisible(true);
        }

        private static void updatePreviewColor() {
            try{
                if (!red.getText().isEmpty() && !green.getText().isEmpty() && !blue.getText().isEmpty() &&
                        Integer.parseInt(red.getText()) <= 255 &&
                        Integer.parseInt(green.getText()) <= 255 &&
                        Integer.parseInt(blue.getText()) <= 255) {
                    Color color = new Color(Integer.parseInt(red.getText()), Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
                    preview.setBackground(color);
                } else {
                    preview.setBackground(null);
                    preview.add(error, BorderLayout.CENTER);
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid Character in RGB Field");
                preview.setBackground(null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = Character.toLowerCase(e.getKeyChar());
        switch (keyChar) {
            case 'w':
                while (!Objects.equals(getFacingDirection(), "north")) {
                    turnLeft();
                }
                move();
                break;
            case 'd':
                while (!Objects.equals(getFacingDirection(), "east")) {
                    turnLeft();
                }
                move();
                break;
            case 's':
                while (!Objects.equals(getFacingDirection(), "south")) {
                    turnLeft();
                }
                move();
                break;
            case 'a':
                while (!Objects.equals(getFacingDirection(), "west")) {
                    turnLeft();
                }
                move();
                break;
            case 'e':
                turnRight();
                break;
            case 'q':
                turnLeft();
                break;
            case 'r':
                takePaint();
                System.out.println("Player paint count: " + getMyPaint());
                break;
            case'f':
                addPaintBucket(getX(),getY(),1);
                break;
            case ' ':
                paintOld(selectedColor);
                repaint();
                break;
        }
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            if (toggle) {
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