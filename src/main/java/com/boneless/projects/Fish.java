package com.boneless.projects;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boneless.projects.utils.IconResize;
import com.boneless.projects.utils.NormalButtons;


public class Fish {
    private static final String userProfile = System.getProperty("user.home") + "/fish.txt";
    private JFrame frame;

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("src/resource/assets/le_fishe.png");
        try {
            if (!Files.exists(Paths.get(userProfile))) {
                Fish fish = new Fish();
                fish.showCongratulations();
            } else {
                JFrame frame = createFrame(icon);
                int count = getCountFromUserProfile();

                switch (count) {
                    case 1:
                        addLabelToFrame(frame, "No really, fuck off");
                        count++;
                        break;
                    case 2:
                        addLabelToFrame(frame, "No more fish for you.");
                        count++;
                        break;
                    case 3:
                        addLabelToFrame(frame, "you really cant win anymore");
                        count++;
                        break;
                    default:
                        frame.setTitle("go away");
                        break;
                }
                writeCountToUserProfile(count);
                frame.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Fish() {
        try {
            frame = new JFrame("CONGRATULATIONS!");
            frame.setSize(350, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setTitle("CONGRATULATIONS!");
            frame.setResizable(false);

            ImageIcon icon = new ImageIcon("src/resource/assets/icon.png");
            frame.setIconImage(icon.getImage());

            JPanel panel = new JPanel(new FlowLayout());

            JLabel mainText = new JLabel("Congratulations, you are our lucky winner!");
            mainText.setVerticalAlignment(JLabel.NORTH);

            IconResize fishImage = new IconResize("le_fishe.png", 340, 200);
            JLabel fish = new JLabel();
            fish.setIcon(fishImage.getImage());

            JLabel hyperlink = new JLabel("<html><a href=\"http://www.fish.com\">01101110 01101001 01100111 01100111 01100101 01110010</a></html>");
            hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            hyperlink.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://shorturl.at/STV01"));
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });

            NormalButtons.set();

            panel.add(mainText);
            panel.add(fish);
            panel.add(hyperlink);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

            JButton viewStatistics = new JButton("View Black Crime Statistics");
            viewStatistics.setFocusable(false);
            viewStatistics.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.uscourts.gov/courts/scd/cases/2-15-472/exhibits/GX3.pdf"));
                    } catch (IOException | URISyntaxException a) {
                        a.printStackTrace();
                    }
                }
            });

            JButton exit = new JButton("Ok");
            exit.setFocusable(false);
            exit.addActionListener(e -> {
                System.exit(5318008);
            });

            buttonsPanel.add(viewStatistics);
            buttonsPanel.add(Box.createHorizontalGlue());
            buttonsPanel.add(exit);

            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);
            Files.write(Paths.get(userProfile), "1".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    private static JFrame createFrame(ImageIcon icon) {
        JFrame frame = new JFrame("Fuck off, no more");
        frame.setSize(300, 120);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        return frame;
    }

    private static int getCountFromUserProfile() throws IOException {
        return Integer.parseInt(new String(Files.readAllBytes(Paths.get(userProfile))).trim());
    }

    private static void addLabelToFrame(JFrame frame, String labelText) {
        frame.add(new JLabel(labelText));
    }

    private static void writeCountToUserProfile(int count) throws IOException {
        Files.write(Paths.get(userProfile), String.valueOf(count).getBytes());
    }

    private void showCongratulations() {
        frame.setVisible(true);
    }
}
