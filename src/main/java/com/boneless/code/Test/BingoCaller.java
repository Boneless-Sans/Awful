package com.boneless.code.Test;

import java.util.Random;

public class BingoCaller{
    private static final String[] COLUMN_LETTERS = {"B","I","N","G","O"}; //already defined, dont include
    private static boolean[] numbersCalled = new boolean[75]; //already defined, dont include

    public static boolean hasBeenCalled(int num){
        return numbersCalled[num];
    }
    public static int getRandomNumber(String letter){
        Random rand = new Random();
        int randomNumber = 0;
        if (letter.equalsIgnoreCase("b")) {
            randomNumber = rand.nextInt(15) + 1; // Range: 1-15
        } else if (letter.equalsIgnoreCase("i")) {
            randomNumber = rand.nextInt(15) + 16; // Range: 16-30
        } else if (letter.equalsIgnoreCase("n")) {
            randomNumber = rand.nextInt(15) + 31; // Range: 31-45
        } else if (letter.equalsIgnoreCase("g")) {
            randomNumber = rand.nextInt(15) + 46; // Range: 46-60
        } else if (letter.equalsIgnoreCase("o")) {
            randomNumber = rand.nextInt(15) + 61; // Range: 61-75
        }
        return randomNumber;
    }
    public static String makeCall(){
        Random rand = new Random();
        String randLetter = COLUMN_LETTERS[rand.nextInt(COLUMN_LETTERS.length)];
        int randNumber;

        randNumber = getRandomNumber(randLetter);
        while (hasBeenCalled(randNumber)) {
            randNumber = getRandomNumber(randLetter);
        }

        numbersCalled[randNumber - 1] = true;

        return randLetter + randNumber;
    }
    //Fill in blank, question 2
    public class Book{
        private String title;
        private String author;
        private static int totalBooks = 0; //add

        public Book(String title, String author){
            totalBooks++; //add
            this.title = title;
            this.author = author;
        }
    }
}
