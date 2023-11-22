package com.boneless.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class Main {
    private static JFrame frame;
    private static boolean assetsDetected = true;
    private static JLabel assetsDirLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Application Launcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new FlowLayout());

            //InputStream assetStream = AssetLoader.loadAsset("src/resource/assets/check_mark.png");

            ArrayList<String> applications = getAvailableApplications();

            JComboBox<String> appList = new JComboBox<>(applications.toArray(new String[0]));

            JButton launchButton = new JButton("Launch");

            launchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedApp = (String) appList.getSelectedItem();
                    if (selectedApp != null) {
                        launchApplication(selectedApp);
                    }
                }
            });

            JButton compileButton = new JButton("Compile");

            compileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedApp = (String) appList.getSelectedItem();
                    if (selectedApp != null) {
                        compileApplication(selectedApp);
                    }
                }
            });

            if (!assetsDetected) {
                displayAssetsNotDetectedMessage();
            }

            frame.add(new JLabel("Select an application to launch:"));
            frame.add(appList);
            frame.add(launchButton);
            frame.add(compileButton);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    private static void displayAssetsNotDetectedMessage() {
        JLabel notDetectedLabel = new JLabel("Assets Not Detected");
        notDetectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notDetectedLabel.setFont(notDetectedLabel.getFont().deriveFont(Font.PLAIN, 18f));
        notDetectedLabel.setOpaque(true);

        checkForAssets();

        JLabel assetsDirInfoLabel = new JLabel("Assets directory: " + (assetsDetected ? "./src/resource/assets" : "Not found"));
        assetsDirInfoLabel.setFont(assetsDirInfoLabel.getFont().deriveFont(Font.PLAIN, 12f));
        assetsDirInfoLabel.setForeground(Color.BLACK);

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

        frame.add(notDetectedLabel, 0);
        frame.add(assetsDirInfoLabel);
        frame.revalidate();
    }

    private static ArrayList<String> getAvailableApplications() {
        ArrayList<String> appList = new ArrayList<>();
        try {
            String packageName = Main.class.getPackage().getName();
            JarFile jarFile = new JarFile(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            Enumeration<JarEntry> entries = jarFile.entries();

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
        File assetsDir = new File("./src/resource/assets");
        assetsDetected = assetsDir.exists() && assetsDir.isDirectory();
    }

    private static void launchApplication(String appName) {
        SwingUtilities.invokeLater(() -> {
            try {
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

    private static void compileApplication(String appName) {
        SwingUtilities.invokeLater(() -> {
            try {
                String packageName = Main.class.getPackage().getName();
                JarFile sourceJarFile = new JarFile(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

                String compiledJarPath = "./" + appName + ".jar";

                // Create the compiled directory if it doesn't exist
                File compiledDir = new File("./");
                if (!compiledDir.exists()) {
                    compiledDir.mkdirs();
                }

                JarOutputStream targetJarStream = new JarOutputStream(new FileOutputStream(compiledJarPath));

                Enumeration<JarEntry> entries = sourceJarFile.entries();

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.startsWith(packageName.replace(".", "/")) && !entryName.equals(Main.class.getName().replace(".", "/") + ".class")) {
                        targetJarStream.putNextEntry(new JarEntry(entryName));
                        InputStream entryStream = sourceJarFile.getInputStream(entry);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = entryStream.read(buffer)) != -1) {
                            targetJarStream.write(buffer, 0, bytesRead);
                        }
                        targetJarStream.closeEntry();
                    }
                }

                targetJarStream.putNextEntry(new JarEntry("META-INF/MANIFEST.MF"));
                targetJarStream.write("Manifest-Version: 1.0\n".getBytes());
                targetJarStream.write(("Main-Class: " + appName + "\n").getBytes());
                targetJarStream.closeEntry();

                targetJarStream.close();
                sourceJarFile.close();

                copyAssetsToJar(compiledJarPath);

                JOptionPane.showMessageDialog(frame, "Compilation successful!\nCompiled JAR: " + compiledJarPath);

            } catch (Exception e) {
                e.printStackTrace();
                showBuildFailedWindow("Failed to compile the selected application.\n" + e.getMessage(), "");
            }
        });
    }


    private static void showBuildFailedWindow(String errorMessage, String stackTrace) {
        SwingUtilities.invokeLater(() -> {
            JFrame errorFrame = new JFrame("Build Failed");
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(Color.RED);

            JLabel buildFailedLabel = new JLabel("BUILD FAILED");
            buildFailedLabel.setHorizontalAlignment(SwingConstants.CENTER);
            buildFailedLabel.setForeground(Color.RED);
            buildFailedLabel.setFont(buildFailedLabel.getFont().deriveFont(Font.PLAIN, 18f));

            JTextArea errorTextArea = new JTextArea(errorMessage);
            errorTextArea.setWrapStyleWord(true);
            errorTextArea.setLineWrap(true);
            errorTextArea.setForeground(Color.BLACK);
            errorTextArea.setFont(errorTextArea.getFont().deriveFont(Font.PLAIN, 14f));
            errorTextArea.setEditable(false);

            JTextArea stackTraceTextArea = new JTextArea(stackTrace);
            stackTraceTextArea.setWrapStyleWord(true);
            stackTraceTextArea.setLineWrap(true);
            stackTraceTextArea.setForeground(Color.RED);
            stackTraceTextArea.setFont(stackTraceTextArea.getFont().deriveFont(Font.PLAIN, 12f));
            stackTraceTextArea.setEditable(false);

            JScrollPane errorScrollPane = new JScrollPane(errorTextArea);
            JScrollPane stackTraceScrollPane = new JScrollPane(stackTraceTextArea);

            contentPanel.add(buildFailedLabel, BorderLayout.NORTH);
            contentPanel.add(errorScrollPane, BorderLayout.CENTER);
            contentPanel.add(stackTraceScrollPane, BorderLayout.SOUTH);

            errorFrame.add(contentPanel);

            Dimension preferredSize = new Dimension(600, 400);
            errorTextArea.setPreferredSize(preferredSize);
            stackTraceTextArea.setPreferredSize(preferredSize);

            errorFrame.pack();

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

    private static void copyAssetsToJar(String compiledJarPath) {
        try {
            File assetsDir = new File("./src/resource/assets");
            if (assetsDir.exists() && assetsDir.isDirectory()) {
                try (JarOutputStream targetJarStream = new JarOutputStream(new FileOutputStream(compiledJarPath, true))) {
                    Files.walk(Paths.get(assetsDir.getPath()))
                            .filter(Files::isRegularFile)
                            .forEach(file -> {
                                try {
                                    String entryName = "src/resource/assets/" + assetsDir.toPath().relativize(file).toString();
                                    targetJarStream.putNextEntry(new JarEntry(entryName));
                                    Files.copy(file, targetJarStream);
                                    targetJarStream.closeEntry();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
