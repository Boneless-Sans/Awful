package com.boneless.projects;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMover {
    public static void main(String[] args) {
        String sourceDirectory = System.getProperty("user.dir"); // Current working directory

        try {
            movePhotos(sourceDirectory);
            System.out.println("Photos moved successfully to 'out' directory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void movePhotos(String sourceDirectory) throws IOException {
        Path sourcePath = Paths.get(sourceDirectory);
        Path outPath = Paths.get(sourceDirectory, "out");

        if (!Files.exists(outPath)) {
            Files.createDirectories(outPath);
        }

        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".heic") || fileName.endsWith(".mp4") ||
                fileName.endsWith(".mov") || fileName.endsWith(".mp3") || fileName.endsWith(".avi")) {
                    Path targetPath = Paths.get(outPath.toString(), fileName);
                    Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
