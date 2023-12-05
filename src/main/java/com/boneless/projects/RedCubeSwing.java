package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RedCubeSwing extends JFrame implements ActionListener {

    private double angleX = 45;
    private double angleY = 45;
    private double angleZ = 45;

    public RedCubeSwing() {
        super("Red Cube 3D");

        // Set up the JFrame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for rendering the cube
        CubePanel cubePanel = new CubePanel();
        add(cubePanel, BorderLayout.CENTER);

        // Add an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(exitButton);
        add(panel, BorderLayout.SOUTH);

        // Set the size of the JFrame
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        // Start a timer to update the cube's rotation
        Timer timer = new Timer(30, e -> {
            angleX += 1;
            angleY += 1;
            angleZ += 1;
            cubePanel.repaint();
        });
        timer.start();
    }

    private class CubePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Calculate rotation transformation
            double radianX = Math.toRadians(angleX);
            double radianY = Math.toRadians(angleY);
            double radianZ = Math.toRadians(angleZ);

            int size = 100;

            // Define the cube vertices
            int[][] vertices = {
                    {size, size, size},
                    {-size, size, size},
                    {-size, -size, size},
                    {size, -size, size},
                    {size, size, -size},
                    {-size, size, -size},
                    {-size, -size, -size},
                    {size, -size, -size}
            };

            // Apply rotation to the cube vertices
            for (int i = 0; i < vertices.length; i++) {
                int x = vertices[i][0];
                int y = vertices[i][1];
                int z = vertices[i][2];

                // Rotate around X-axis
                int newY = (int) (y * Math.cos(radianX) - z * Math.sin(radianX));
                int newZ = (int) (y * Math.sin(radianX) + z * Math.cos(radianX));

                y = newY;
                z = newZ;

                // Rotate around Y-axis
                int newX = (int) (x * Math.cos(radianY) + z * Math.sin(radianY));
                newZ = (int) (-x * Math.sin(radianY) + z * Math.cos(radianY));

                x = newX;
                z = newZ;

                // Rotate around Z-axis
                newX = (int) (x * Math.cos(radianZ) - y * Math.sin(radianZ));
                newY = (int) (x * Math.sin(radianZ) + y * Math.cos(radianZ));

                x = newX;
                y = newY;

                vertices[i][0] = x;
                vertices[i][1] = y;
                vertices[i][2] = z;
            }

            // Draw lines connecting the vertices to form the cube
            for (int i = 0; i <4; i++) {
                int next = (i + 1) % 4;
                g.drawLine(vertices[i][0] + getWidth() / 2, vertices[i][1] + getHeight() / 2,
                        vertices[next][0] + getWidth() / 2, vertices[next][1] + getHeight() / 2);
                g.drawLine(vertices[i + 4][0] + getWidth() / 2, vertices[i + 4][1] + getHeight() / 2,
                        vertices[next + 4][0] + getWidth() / 2, vertices[next + 4][1] + getHeight() / 2);
                g.drawLine(vertices[i][0] + getWidth() / 2, vertices[i][1] + getHeight() / 2,
                        vertices[i + 4][0] + getWidth() / 2, vertices[i + 4][1] + getHeight() / 2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Exit the program when the button is clicked
        System.exit(0);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RedCubeSwing::new);
    }
}
