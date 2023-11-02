package src.java.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileReaderSaver {
    public static void save(Object data, String fileName) {
        if (fileName.indexOf('/') == -1) {
            // No directory provided, use the default directory
            fileName = "src/resource/data/" + fileName;
        }

        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data.toString());
            System.out.println("Data saved to " + file.getAbsolutePath() + " (" + data + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String fileName) {
        if (fileName.indexOf('/') == -1) {
            // No directory provided, use the default directory
            fileName = "src/resource/data/" + fileName;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "File not found.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file.";
        }
    }
    public static boolean delete(String fileName) {
        if (fileName.indexOf('/') == -1) {
            // No directory provided, use the default directory
            fileName = "src/resource/data/" + fileName;
        }

        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                return true; // File was successfully deleted
            } else {
                return false; // Failed to delete the file
            }
        } else {
            return false; // File doesn't exist
        }
    }
    public static boolean check(String fileName){
        if(fileName.indexOf('/') == -1){
            fileName = "src/resource/data" + fileName;
        }
        File file = new File(fileName);
        return file.exists();
    }
}
