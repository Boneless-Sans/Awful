package src.java;

import src.java.utils.AudioPlayer;
import src.java.utils.FileReaderSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GuessTheNumber extends JFrame implements ActionListener {
    private int trys;
    private static boolean gameModeHard = false;
    private JFrame mainFrame;
    private JFrame scoreBoard;
    private JTextField input;
    private JLabel hotText;
    private JLabel tryCount;
    private JButton submitButton;
    private JButton scoreboardButton;
    private static JButton settingsButton;
    private JButton resetScore;
    private JButton close;
    private JPanel playPanel;
    private static ImageIcon icon = new ImageIcon("src/resource/assets/icon.png");;
    private final Random rand = new Random();
    private double randNumber = rand.nextInt(1,100);

    private String buttonColors = Arrays.toString(new Color[]{
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.BLACK,
            Color.WHITE,
            Color.LIGHT_GRAY,
            Color.GREEN,
            Color.DARK_GRAY
    });

    public GuessTheNumber(){
        this.mainFrame = new JFrame(); // Move this line to the beginning
        this.input = new JTextField();
        this.hotText = new JLabel("Please pick a number");
        this.submitButton = new JButton("Submit Answer");
        this.playPanel = new JPanel();
        this.scoreboardButton = new JButton("Scoreboard");

        if(gameModeHard){
            float randNumberMath = rand.nextFloat();
            float min = 0.00f;
            float max = 100.99f;
            float moreMath = min + randNumberMath * (max - min);

            DecimalFormat df = new DecimalFormat("#.##");
            String format = df.format(moreMath);

            BigDecimal bigDecimal = new BigDecimal(format);
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);

            double randNumberHard = bigDecimal.doubleValue();

            System.out.println("Hard Mode");
            this.randNumber = randNumberHard;

            mainFrame.setTitle("Guess The Number! (Hard Mode)");
        }else{
            mainFrame.setTitle("Guess The Number! (Easy Mode)");
        }

        mainFrame.setLayout(null);
        mainFrame.setSize(new Dimension(500, 100));
        mainFrame.getContentPane().setBackground(Color.GRAY);
        mainFrame.setIconImage(icon.getImage());

        input.setBounds(150, 6, 150, 50);
        submitButton.setBounds(0, 6, 150, 50);
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 17));
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setBackground(Color.LIGHT_GRAY);
        submitButton.addActionListener(this);

        scoreboardButton.setBounds(0, 6, 150, 50);
        scoreboardButton.setFocusable(false);
        scoreboardButton.setFont(new Font("Arial", Font.PLAIN, 17));
        scoreboardButton.setBorder(BorderFactory.createEtchedBorder());
        scoreboardButton.setBackground(Color.LIGHT_GRAY);
        scoreboardButton.addActionListener(this);

        playPanel.setBounds(300, 6, 150, 50);
        hotText.setFont(new Font("Arial", Font.PLAIN, 15));
        hotText.setBounds(0, 0, 150, 50);
        playPanel.add(hotText);
        playPanel.setLayout(null);

        tryCount = new JLabel("0");
        tryCount.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        tryCount.setForeground(Color.BLACK);
        tryCount.setBounds(462,6,50,50);

        System.out.println(FileReaderSaver.check("Guess_Number.savf"));
        System.out.println(randNumber);

        mainFrame.add(submitButton);
        mainFrame.add(input);
        mainFrame.add(playPanel);
        mainFrame.add(tryCount);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    public GuessTheNumber(int settings){
        this.setSize(new Dimension(500,500));
        ImageIcon icon = new ImageIcon("src/resource/assets/icon_scoreboard.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);

        close = new JButton("Close");
        close.setBounds(200,350,100,50);
        close.addActionListener(this);
        close.setBackground(Color.GRAY);
        close.setFocusable(false);
        close.setFont(new Font("Arial", Font.PLAIN,20));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {AudioPlayer.play("hover.wav");}
        });


        this.add(close);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (randNumber == Double.parseDouble(input.getText())) {
                trys++;
                tryCount.setText(Integer.toString(trys));
                hotText.setText("You Won!");
                hotText.setFont(new Font("Arial", Font.PLAIN, 20));
                hotText.setBounds(30, 0, 150, 50);
                playPanel.setBackground(Color.GREEN);
                submitButton.setText("Scoreboard");
                input.setEnabled(false);
                if(FileReaderSaver.check("Guess_Number.savf")){
                    FileReaderSaver.save("High Score: " + 999, "Guess_Number.savf");
                }
                String extractSave = FileReaderSaver.read("Guess_Number.savf");
                String finalScore = extractSave.replaceAll("[^0-9]", "");
                int savedScore = Integer.parseInt(finalScore);
                if (savedScore > trys) {
                    //FileReaderSaver.delete("Guess_Number.savf");
                    FileReaderSaver.save("High Score: " + trys, "Guess_Number.savf");
                    System.out.println("High Score Beaten!!\nNew High Score: " + finalScore);
                } else if (savedScore == trys) {
                    System.out.println("High Score almost beat!\nHigh Score: " + finalScore + "\nYour Score: " + trys);
                } else if (savedScore < trys) {
                    System.out.println("High Score not Reached!\nHigh Score: " + finalScore + "Your Score: " + trys);
                }
                submitButton.setEnabled(false);
                mainFrame.remove(submitButton);
                mainFrame.add(scoreboardButton);

            } else if (randNumber > Double.parseDouble(input.getText())) {
                trys++;
                tryCount.setText(Integer.toString(trys));
                hotText.setText("Hotter");
                hotText.setFont(new Font("Arial", Font.PLAIN, 20));
                playPanel.setBackground(new Color(251, 139, 35));
                hotText.setBounds(50, 0, 150, 50);
            } else if (randNumber < Double.parseDouble(input.getText())) {
                trys++;
                tryCount.setText(Integer.toString(trys));
                hotText.setText("Colder");
                hotText.setFont(new Font("Arial", Font.PLAIN, 20));
                playPanel.setBackground(new Color(37, 124, 255));
                hotText.setBounds(50, 0, 150, 50);
            }
        }
        if(e.getSource()==scoreboardButton) {
            mainFrame.dispose();
            scoreBoard = new JFrame();
            scoreBoard.setTitle("Scoreboard");
            scoreBoard.setSize(300, 250);
            scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            scoreBoard.setLayout(null);
            ImageIcon iconScoreboard = new ImageIcon("src/resource/assets/icon_scoreboard.png");
            scoreBoard.setIconImage(iconScoreboard.getImage());

            JButton exit = new JButton("Exit");
            exit.addActionListener(a ->{
                System.exit(69);
            });

            int fixedNumber = 0;
            //check to see if it's a hard mode number, if not, convert to an int
            if (randNumber == (int) randNumber) {
                JLabel answerText = new JLabel(Integer.toString(fixedNumber = (int) randNumber));
            } else {
                JLabel answerText = new JLabel(Integer.toString(fixedNumber));
            }

            JLabel answerText = new JLabel("Number: " + Integer.toString(fixedNumber));
            JLabel scoreText = new JLabel("Your Score: " + trys);
            JLabel highScoreText = new JLabel(FileReaderSaver.read("Guess_Number.savf"));
            answerText.setFont(new Font("Arial", Font.ITALIC, 15));
            scoreText.setFont(new Font("Arial", Font.ITALIC, 15));
            highScoreText.setFont(new Font("Arial", Font.ITALIC, 15));
            answerText.setBounds(0,50,120,50);
            scoreText.setBounds(0,100,120,50);
            highScoreText.setBounds(0,150,120,50);

            scoreBoard.add(answerText);
            scoreBoard.add(scoreText);
            scoreBoard.add(highScoreText);
            scoreBoard.add(exit);
            scoreBoard.setVisible(true);
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setTitle("Guess The Number");
        frame.setIconImage(icon.getImage());

        JPanel mainScreen = new JPanel();
        mainScreen.setLayout(null);
        JLabel welcomeText = new JLabel("Welcome! Please Pick a Difficulty.");
        JButton buttonHard = new JButton("Hard");
        JButton buttonEasy = new JButton("Easy");

        JLabel highScore = new JLabel(FileReaderSaver.read("Guess_Number.savf"));
        highScore.setFont(new Font("Arial", Font.ITALIC, 20));
        highScore.setBounds(185,125,200,100);

        //TO-DO: Settings, change color scheme or (harder) custom colors

        buttonEasy.setFocusable(false);
        buttonHard.setFocusable(false);

        buttonEasy.setBackground(Color.GRAY);
        buttonHard.setBackground(Color.GRAY);

        buttonEasy.setFont(new Font("Arial", Font.ITALIC,20));
        buttonHard.setFont(new Font("Arial", Font.ITALIC,20));

        buttonEasy.setBounds(150,250,100,50);
        buttonHard.setBounds(250,250,100,50);

        welcomeText.setFont(new Font("Arial", Font.PLAIN, 25));
        welcomeText.setBounds(50,0,500,100);
        mainScreen.setBackground(Color.LIGHT_GRAY);
        mainScreen.setSize(new Dimension(500,500));

        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Arial", Font.ITALIC,20));
        settingsButton.setBounds(200,350,110,50);
        settingsButton.setFocusable(false);
        settingsButton.setBackground(Color.GRAY);
        AtomicBoolean disabled = new AtomicBoolean(false);

        buttonEasy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {AudioPlayer.play("hover.wav");}

        });
        buttonHard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {AudioPlayer.play("hover.wav");}
        });
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!disabled.get()){
                    AudioPlayer.play("hover.wav");
                }
            }
        });
        buttonEasy.addActionListener(e -> {
            AudioPlayer.play("select.wav");
            gameModeHard = false;
            new GuessTheNumber();
            frame.dispose();
        });
        buttonHard.addActionListener(e -> {
            AudioPlayer.play("select.wav");
            gameModeHard = true;
            new GuessTheNumber();
            frame.dispose();
        });
        settingsButton.addActionListener(e -> {
            AudioPlayer.play("select.wav");
            new GuessTheNumber(1);
            settingsButton.setEnabled(false);
            disabled.set(true);
        });
        mainScreen.add(welcomeText);
        mainScreen.add(buttonEasy);
        mainScreen.add(buttonHard);
        mainScreen.add(highScore);
        mainScreen.add(settingsButton);

        frame.add(mainScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
