package src.java;

import src.java.utils.FileReaderSaver;
import src.java.utils.IconResize;
import src.java.utils.NormalButtons;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Fish extends JFrame {
    private static String userProfile = System.getProperty("user.home") + "/AppData/fish.txt";
    public static void main(String[] args){
        //System.out.println(FileReaderSaver.check("C:/fish.txt"));
        ImageIcon icon = new ImageIcon("src/resource/assets/le_fishe.png");
        if(!FileReaderSaver.check(userProfile)){
            new Fish();
        }else{
            JFrame frame = new JFrame("Fuck off, no more");
            frame.setSize(300,120);
            frame.setLayout(new FlowLayout());
            frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            frame.setIconImage(icon.getImage());
            int count = Integer.parseInt(FileReaderSaver.read(userProfile).trim());

            switch(count){
                case 1:
                    frame.add(new JLabel("No really, fuck off"));
                    count++;
                    break;
                case 2:
                    frame.add(new JLabel("No more fish for you."));
                    count++;
                    break;
                case 3:
                    frame.add(new JLabel("you really cant win anymore"));
                    count++;
                    break;
                default:
                    frame.setTitle("go away");
                    break;
            }
            FileReaderSaver.save(count, userProfile);
            frame.setVisible(true);
        }
    }
    public Fish(){
        setSize(350,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("CONGRATULATIONS!");
        setResizable(false);

        ImageIcon icon = new ImageIcon("src/resource/assets/icon.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new FlowLayout());

        JLabel mainText = new JLabel("Congratulations, you are our lucky winner!");
        mainText.setVerticalAlignment(JLabel.NORTH);

        IconResize fishImage = new IconResize("le_fishe.png", 340,200);
        JLabel fish = new JLabel();
        fish.setIcon(fishImage.getImage());

        JLabel hyperlink = new JLabel("<html><a href=\"http://www.fish.com\">01101110 01101001 01100111 01100111 01100101 01110010</a></html>");
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try{
                    Desktop.getDesktop().browse(new URI("https://shorturl.at/STV01"));
                }catch (IOException | URISyntaxException e){
                    e.printStackTrace();
                }
            }
        });

        NormalButtons.set();

        panel.add(mainText);
        panel.add(fish);
        panel.add(hyperlink);

        JPanel buttonsPanel = new JPanel();
        //buttonsPanel.setBackground(Color.RED); //color for debugging
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,20,15,20));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        JButton viewStatistics = new JButton("View Black Crime Statistics");
        viewStatistics.setFocusable(false);
        viewStatistics.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Desktop.getDesktop().browse(new URI("https://www.uscourts.gov/courts/scd/cases/2-15-472/exhibits/GX3.pdf"));
                }catch(IOException | URISyntaxException a){
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

        setLocationRelativeTo(null);
        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.SOUTH, buttonsPanel);
        setVisible(true);
        FileReaderSaver.save("1", userProfile);
    }
}
