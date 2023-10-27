package src.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarOutputStream;

public class Main {
    private static JFrame errorFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Application Launcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLayout(new FlowLayout());

            // Create a list of application names by inspecting the JAR file
            ArrayList<String> applications = getAvailableApplications();

            JComboBox<String> appList = new JComboBox<>(applications.toArray(new String[0]));

            JButton launchButton = new JButton("Launch");

            launchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedApp = (String) appList.getSelectedItem();
                    if (selectedApp != null) {
                        // Compile and run the selected application
                        compileAndRun(selectedApp);
                    }
                }
            });

            frame.add(new JLabel("Select an application to launch:"));
            frame.add(appList);
            frame.add(launchButton);
            frame.setVisible(true);
        });
    }

    private static void compileAndRun(String appName) {
        try {
            // Create a temporary directory for compilation and JAR creation
            File tempDir = createTempDirectory();

            // Compile the selected application
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("javac", "-d", tempDir.getAbsolutePath(), appName + ".java");
            compileProcessBuilder.redirectErrorStream(true); // Redirect error stream to output
            Process compileProcess = compileProcessBuilder.start();
            int compilationResult = compileProcess.waitFor();

            if (compilationResult == 0) {
                // Compilation successful, create a JAR
                createJar(tempDir, appName);

                // Execute the created JAR
                ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", appName + ".jar");
                processBuilder.inheritIO();
                processBuilder.directory(tempDir);
                processBuilder.start();
            } else {
                // Compilation failed; display the error message
                try (InputStream errorStream = compileProcess.getInputStream();
                     InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
                     BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader)) {
                    StringBuilder errorMessage = new StringBuilder();
                    String line;
                    while ((line = errorBufferedReader.readLine()) != null) {
                        errorMessage.append("Compilation error: ").append(line).append("\n");
                    }

                    // Show an error window with flashing "BUILD FAILED" message
                    showBuildFailedWindow(errorMessage.toString());
                }
            }

            // Clean up: delete the temporary directory
            deleteTempDirectory(tempDir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void showBuildFailedWindow(String errorMessage) {
        SwingUtilities.invokeLater(() -> {
            errorFrame = new JFrame("Build Failed");
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            errorFrame.setSize(600, 100);
            errorFrame.setResizable(false);

            JPanel contentPanel = new JPanel(new BorderLayout()); // Create a panel for the entire content

            // Set the background color for the content panel to red
            contentPanel.setBackground(Color.RED);

            JLabel buildFailedLabel = new JLabel("BUILD FAILED");
            buildFailedLabel.setHorizontalAlignment(SwingConstants.CENTER);
            buildFailedLabel.setForeground(Color.RED); // Initial text color red
            buildFailedLabel.setFont(buildFailedLabel.getFont().deriveFont(Font.PLAIN, 18f));

            JTextArea errorTextArea = new JTextArea(errorMessage);
            errorTextArea.setEditable(false);
            errorTextArea.setWrapStyleWord(true);
            errorTextArea.setLineWrap(true);
            errorTextArea.setForeground(Color.BLACK); // Set text color to black
            errorTextArea.setFont(errorTextArea.getFont().deriveFont(Font.PLAIN, 14f));

            contentPanel.add(buildFailedLabel, BorderLayout.NORTH);
            contentPanel.add(errorTextArea, BorderLayout.CENTER);

            errorFrame.add(contentPanel);

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






    private static ArrayList<String> getAvailableApplications() {
        ArrayList<String> appList = new ArrayList<>();
        try {
            // Get the package name based on the Main class's package
            String packageName = Main.class.getPackage().getName();
            JarFile jarFile = new JarFile(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(packageName.replace(".", "/")) && !entryName.equals(Main.class.getName().replace(".", "/") + ".class")) {
                    String className = entryName.substring(0, entryName.length() - 6).replace("/", ".");
                    appList.add(className);
                }
            }

            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appList;
    }

    private static File createTempDirectory() throws IOException {
        File tempDir = File.createTempFile("temp", Long.toString(System.nanoTime()));
        if (!tempDir.delete() || !tempDir.mkdir()) {
            throw new IOException("Could not create temporary directory: " + tempDir);
        }
        return tempDir;
    }

    private static void deleteTempDirectory(File tempDir) {
        File[] files = tempDir.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteTempDirectory(file);
            }
        }
        tempDir.delete();
    }

    private static void createJar(File sourceDir, String appName) throws IOException {
        File jarFile = new File(sourceDir, appName + ".jar");

        try (JarOutputStream out = new JarOutputStream(new FileOutputStream(jarFile))) {
            File[] files = sourceDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    addFileToJar(out, file, "");
                }
            }
        }
    }

    private static void addFileToJar(JarOutputStream out, File file, String parent) {
        try {
            String entryName = parent + file.getName();
            JarEntry entry = new JarEntry(entryName);

            if (file.isDirectory()) {
                entryName += "/";
                entry.setMethod(JarEntry.STORED);
                entry.setSize(0);
                entry.setCrc(0);
            } else {
                entry.setMethod(JarEntry.DEFLATED);
            }

            out.putNextEntry(entry);

            if (!file.isDirectory()) {
                try (FileInputStream in = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }

            out.closeEntry();

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File nestedFile : files) {
                        addFileToJar(out, nestedFile, entryName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
