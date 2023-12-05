package com.boneless.code.u3l7.part6;

import com.boneless.code.util.StupidFileReader;

/*
 * Manages data about best selling video games
 */
public class Nintendo {

    private Game[] bestSellingGames;   // The 1D array of best selling video games

    /*
     * Reads the data from titlesFile and copiesFile to initialize bestSellingGames
     */
    public Nintendo(String titlesFile, String copiesFile) {
        bestSellingGames = createGames(titlesFile, copiesFile);
    }

    /*
     * Returns a 1D array of Game objects using the data from titlesFile and copiesFile
     */
    public Game[] createGames(String titlesFile, String copiesFile) {
        String[] titlesData = StupidFileReader.toStringArray(titlesFile);
        int[] copiesData = StupidFileReader.toIntArray(copiesFile);
        Game[] tempGames = new Game[titlesData.length];

        for (int index = 0; index < tempGames.length; index++) {
            tempGames[index] = new Game(titlesData[index], copiesData[index]);
        }

        return tempGames;
    }

    /*
     * Returns the 1D array of Game objects
     */
    public Game[] getBestSellingGames() {
        return bestSellingGames;
    }

    /*
     * Returns a String containing the information about each Game object
     */
    public String toString() {
        String result = "";

        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Convert this for loop to an enhanced for loop.
         * -----------------------------------------------------------------------------
         */

        for (Game bestSellingGame : bestSellingGames) {
            result += bestSellingGame + "\n";
        }


        return result;
    }

}