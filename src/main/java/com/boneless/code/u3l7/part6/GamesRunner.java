package com.boneless.code.u3l7.part6;

public class GamesRunner {
    public static void main(String[] args) {

        // Creates a Nintendo object
        Nintendo topGames = new Nintendo("titles.txt", "copies.txt");

        // Prints the Nintendo object
        System.out.println(topGames);

    }
}