package src.java;

import src.java.utils.FileReaderSaver;

public class GameRunner {
    public static void main(String[] args){
        FileReaderSaver.save("This is text!", "save.savf");

        System.out.println(FileReaderSaver.read("save.savf"));
    }
}