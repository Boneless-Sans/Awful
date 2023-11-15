package com.boneless.code.neighborhood;

import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Painter extends JFrame {
    private int xPos;
    private int yPos;
    private String facingDirection = "east";

    private static JPanel[][] panels;
    private static Color color = Color.blue;

    public interface PainterListener {
        void onPainterMove(int x, int y, String facingDirection);
    }

    private PainterListener painterListener;

    public void setPainterListener(PainterListener painterListener) {
        this.painterListener = painterListener;
    }

    public Painter() {
        this(true);
    }

    public Painter(boolean addButtons) {
        int sizeX = Integer.parseInt(JsonFile.read("painter.json", "default", "x"));
        int sizeY = Integer.parseInt(JsonFile.read("painter.json", "default", "y"));

        setTitle("NeighborhoodRunner");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panels = new JPanel[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                panels[x][y] = new JPanel();
            }
        }

        addPanelToGrid();

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        if (addButtons) {
            addButtons();
        }

        setVisible(true);
    }

    private void addPanelToGrid() {
        JPanel grid = new JPanel(new GridLayout(panels.length, panels[0].length));
        int gapSize = 5;

        for (JPanel[] row : panels) {
            for (JPanel p : row) {
                p.setBorder(BorderFactory.createEmptyBorder(gapSize, gapSize, gapSize, gapSize));
                grid.add(p);
            }
        }

        add(grid, BorderLayout.CENTER);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton upButton = new JButton("Up");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        buttonPanel.add(upButton);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLeft();
            }
        });
        buttonPanel.add(leftButton);

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLeft();
                turnLeft();
            }
        });
        buttonPanel.add(downButton);

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLeft();
                turnLeft();
                turnLeft();
            }
        });
        buttonPanel.add(rightButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void move() {
        clearPreviousTileColor(xPos, yPos);

        switch (facingDirection) {
            case "north":
                yPos--;
                break;
            case "east":
                xPos++;
                break;
            case "south":
                yPos++;
                break;
            case "west":
                xPos--;
                break;
            default:
                System.out.println("Invalid direction");
                return;
        }

        if (isValidCoordinate(xPos, yPos)) {
            onPainterMove(xPos, yPos, facingDirection);
        } else {
            System.out.println("Invalid move");
            // Reverse the invalid move
            switch (facingDirection) {
                case "north":
                    yPos++;
                    break;
                case "east":
                    xPos--;
                    break;
                case "south":
                    yPos--;
                    break;
                case "west":
                    xPos++;
                    break;
            }
        }

        printPositionAndDirection();
    }

    void turnLeft() {
        switch (facingDirection) {
            case "north":
                facingDirection = "west";
                break;
            case "east":
                facingDirection = "north";
                break;
            case "south":
                facingDirection = "east";
                break;
            case "west":
                facingDirection = "south";
                break;
            default:
                System.out.println("Invalid direction");
        }

        printPositionAndDirection();
    }

    private void printPositionAndDirection() {
        System.out.println("Painter moved to: (" + xPos + ", " + yPos + ")");
        System.out.println("Facing direction: " + facingDirection);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int x = 0; x < panels.length; x++) {
                for (int y = 0; y < panels[0].length; y++) {
                    g.setColor(panels[x][y].getBackground());
                    g.fillRect(x * 50, y * 50, 50, 50);
                }
            }

            // Draw the painter as a filled oval at its current position
            g.setColor(color);
            g.fillOval(xPos * 50, yPos * 50, 50, 50);
        }
    }

    private void onPainterMove(int x, int y, String facingDirection) {
        updateNewTileColor(x, y, color);
        changePanelColor(x, y, color);

        if (painterListener != null) {
            painterListener.onPainterMove(xPos, yPos, facingDirection);
        }

        repaint(); // Trigger repaint on the DrawingPanel
    }

    private void updateNewTileColor(int x, int y, Color color) {
        panels[x][y].setBackground(color);
    }

    private void changePanelColor(int x, int y, Color color) {
        if (isValidCoordinate(x, y)) {
            if (panels[x][y] != null) {
                panels[x][y].setBackground(color);
            } else {
                System.out.println("Panel at coordinates (" + x + ", " + y + ") is null.");
            }
        } else {
            System.out.println("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }

    private void clearPreviousTileColor(int x, int y) {
        if (isValidCoordinate(x, y)) {
            panels[x][y].setBackground(Color.WHITE);
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < panels.length && y >= 0 && y < panels[0].length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a Painter with buttons
            Painter painterWithButtons = new Painter(true);

            // Set a listener to handle painter movements
            painterWithButtons.setPainterListener((x, y, facingDirection) -> {
                System.out.println("Painter moved to: (" + x + ", " + y + ")");
                System.out.println("Facing direction: " + facingDirection);
            });

            // Create a Painter without buttons
            Painter painterWithoutButtons = new Painter(false);
        });
    }
}
