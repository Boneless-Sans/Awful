package src.java;

import src.java.utils.AssetLoader;
//import src.java.utils.IconResize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    private static JFrame frame;  // Declare the selector window as a class variable
    private static boolean assetsDetected = true;  // Flag to check if assets are detected
    private static JLabel assetsDirLabel; // Label to display the assets directory


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Application Launcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allow the selector window to be closed
            frame.setSize(300, 200); // Increased height to accommodate the label
            frame.setLayout(new FlowLayout());

            InputStream assetStream = AssetLoader.loadAsset("src/resource/assets/check_mark.png");

            // Create a list of application names by inspecting the JAR file
            ArrayList<String> applications = getAvailableApplications();

            JComboBox<String> appList = new JComboBox<>(applications.toArray(new String[0]));

            JButton launchButton = new JButton("Launch");

            launchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedApp = (String) appList.getSelectedItem();
                    if (selectedApp != null) {
                        // Launch the selected application in a new thread
                        launchApplication(selectedApp);
                    }
                }
            });

            // Check if assets are not detected and display the message
            if (!assetsDetected) {
                displayAssetsNotDetectedMessage();
            }
            //IconResize icon = new IconResize("src/resource/assets/check_mark.png");
            //JLabel label = new JLabel(icon.getImage());

            frame.add(new JLabel("Select an application to launch:"));
            frame.add(appList);
            frame.add(launchButton);
            frame.setResizable(false);
            frame.setVisible(true);
            //frame.add(label);
        });
    }

    private static void displayAssetsNotDetectedMessage() {
        JLabel notDetectedLabel = new JLabel("Assets Not Detected");
        notDetectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notDetectedLabel.setFont(notDetectedLabel.getFont().deriveFont(Font.PLAIN, 18f));
        notDetectedLabel.setOpaque(true);

        // Check for assets and update the label accordingly
        checkForAssets();

        JLabel assetsDirInfoLabel = new JLabel("Assets directory: " + (assetsDetected ? "./src/resource/assets" : "Not found"));
        assetsDirInfoLabel.setFont(assetsDirInfoLabel.getFont().deriveFont(Font.PLAIN, 12f));
        assetsDirInfoLabel.setForeground(Color.BLACK); // Set text color to black

        Timer timer = new Timer(500, new ActionListener() {
            private boolean isRed = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRed) {
                    notDetectedLabel.setForeground(Color.RED);
                    notDetectedLabel.setBackground(Color.WHITE);
                } else {
                    notDetectedLabel.setForeground(Color.WHITE);
                    notDetectedLabel.setBackground(Color.RED);
                }
                isRed = !isRed;
            }
        });

        timer.start();

        frame.add(notDetectedLabel, 0); // Add the message at the top
        frame.add(assetsDirInfoLabel); // Add the assets directory info
        frame.revalidate();
    }

    private static ArrayList<String> getAvailableApplications() {
        ArrayList<String> appList = new ArrayList<>();
        try {
            // Get the package name based on the Main class's package
            String packageName = Main.class.getPackage().getName();
            JarFile jarFile = new JarFile(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            Enumeration<JarEntry> entries = jarFile.entries();

            // Check if assets are not detected and set the flag
            checkForAssets();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(packageName.replace(".", "/")) && !entryName.equals(Main.class.getName().replace(".", "/") + ".class")) {
                    String className = entryName.substring(0, entryName.length() - 6).replace("/", ".");
                    appList.add(className);
                }
            }

            jarFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appList;
    }

    private static void checkForAssets() {
        // Check if the assets directory exists
        File assetsDir = new File("./src/resource/assets");
        assetsDetected = assetsDir.exists() && assetsDir.isDirectory();
    }
    private static void launchApplication(String appName) {
        // Launch the selected application in a separate thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Load the selected class and execute its main method in a new thread
                Class<?> appClass = Class.forName(appName);
                appClass.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
                StringWriter stackTraceWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stackTraceWriter));
                showBuildFailedWindow("Failed to run the selected application.\n" + e.getMessage(), stackTraceWriter.toString());
            }
        });
    }

    private static void showBuildFailedWindow(String errorMessage, String stackTrace) {
        SwingUtilities.invokeLater(() -> {
            JFrame errorFrame = new JFrame("Build Failed");
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel contentPanel = new JPanel(new BorderLayout()); // Create a panel for the entire content

            // Set the background color for the content panel to red
            contentPanel.setBackground(Color.RED);

            JLabel buildFailedLabel = new JLabel("BUILD FAILED");
            buildFailedLabel.setHorizontalAlignment(SwingConstants.CENTER);
            buildFailedLabel.setForeground(Color.RED); // Initial text color red
            buildFailedLabel.setFont(buildFailedLabel.getFont().deriveFont(Font.PLAIN, 18f));

            JTextArea errorTextArea = new JTextArea(errorMessage);
            errorTextArea.setWrapStyleWord(true);
            errorTextArea.setLineWrap(true);
            errorTextArea.setForeground(Color.BLACK); // Set text color to black
            errorTextArea.setFont(errorTextArea.getFont().deriveFont(Font.PLAIN, 14f));
            errorTextArea.setEditable(false);

            JTextArea stackTraceTextArea = new JTextArea(stackTrace);
            stackTraceTextArea.setWrapStyleWord(true);
            stackTraceTextArea.setLineWrap(true);
            stackTraceTextArea.setForeground(Color.RED); // Set text color to red
            stackTraceTextArea.setFont(stackTraceTextArea.getFont().deriveFont(Font.PLAIN, 12f));
            stackTraceTextArea.setEditable(false);

            JScrollPane errorScrollPane = new JScrollPane(errorTextArea);
            JScrollPane stackTraceScrollPane = new JScrollPane(stackTraceTextArea);

            contentPanel.add(buildFailedLabel, BorderLayout.NORTH);
            contentPanel.add(errorScrollPane, BorderLayout.CENTER);
            contentPanel.add(stackTraceScrollPane, BorderLayout.SOUTH);

            errorFrame.add(contentPanel);

            // Calculate the preferred size based on the content
            Dimension preferredSize = new Dimension(600, 400);
            errorTextArea.setPreferredSize(preferredSize);
            stackTraceTextArea.setPreferredSize(preferredSize);

            errorFrame.pack(); // Resize the frame based on the preferred size

            // Flashing white and red background
            Timer timer = new Timer(500, new ActionListener() {
                private boolean isRed = true;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isRed) {
                        contentPanel.setBackground(Color.WHITE);
                        buildFailedLabel.setForeground(Color.RED);
                    } else {
                        contentPanel.setBackground(Color.RED);
                        buildFailedLabel.setForeground(Color.WHITE);
                    }
                    isRed = !isRed;
                }
            });

            timer.start();

            errorFrame.setVisible(true);
        });
    }
}