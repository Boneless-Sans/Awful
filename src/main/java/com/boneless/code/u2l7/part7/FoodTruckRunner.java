package com.boneless.code.u2l7.part7;

//import src.java.utils.FileReaderSaver;
import java.io.FileNotFoundException;

public class FoodTruckRunner {
    public FoodTruckRunner() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        Cookie cookie = new Cookie("Chocolate", 7.99, false);

        // ConstructorsHelper.printConstructors(cookie);
        System.out.println("Flavor: " + cookie.getFlavor());

        System.out.println("Price: $" + cookie.getPrice());

        if (cookie.getIsChewy()) {
            System.out.println("The Cookie is Chewy");
        } else {
            System.out.println("The Cookie is not Chewy");
        }

        // Create an instance of FileReaderSaver and use it to save and read files
        //FileReaderSaver.save("this is fucking awful text ON GOD", "src/resource/data/output.txt");
        //System.out.println(FileReaderSaver.read("src/resource/data/output.txt"));
    }
}
