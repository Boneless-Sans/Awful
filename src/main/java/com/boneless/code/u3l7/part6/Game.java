package com.boneless.code.u3l7.part6;

/*
 * Represents a video game
 */
public class Game {

    private String title;    // The title of a game
    private int copies;      // The number of copies sold

    /*
     * Sets title and copies to the specified values
     */
    public Game(String title, int copies) {
        this.title = title;
        this.copies = copies;
    }

    /*
     * Returns the title of the game
     */
    public String getTitle() {
        return title;
    }

    /*
     * Returns the number of copies sold
     */
    public int getCopies() {
        return copies;
    }

    /*
     * Returns a String containing the title and number of copies sold
     */
    public String toString() {
        return title + ": " + copies + " copies sold";
    }

}