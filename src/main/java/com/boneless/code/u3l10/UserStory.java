package com.boneless.code.u3l10;

import com.boneless.code.util.StupidFileReader;

import java.util.Scanner;

public class UserStory {
    // Instance variables to store master data
    private String[] masterArtists;
    private String[] masterAlbums;
    private String[] masterGenres;

    // Flag to control program execution
    private boolean doRun = true;

    // Scanner for user input
    private final Scanner scan = new Scanner(System.in);

    // Default constructor to initialize master data from default files
    public UserStory() {
        this("artists.txt", "albums.txt", "genre.txt");
    }

    // Parameterized constructor to initialize master data from specified files
    public UserStory(String artistsFile, String albumsFile, String genreFile) {
        // Initialize master data arrays using StupidFileReader
        masterArtists = StupidFileReader.toStringArray(artistsFile);
        masterAlbums = StupidFileReader.toStringArray(albumsFile);
        masterGenres = StupidFileReader.toStringArray(genreFile);
    }

    // Method to initialize and control user interaction
    public void init() {
        while (doRun) {
            System.out.println("What do you want to search by?\nArtist, or Genre?");
            // Get user input for search type
            String searchInput = scan.nextLine();

            // Check if the input is valid and corresponds to "artist"
            if (checkSearchInput(searchInput) && searchInput.equalsIgnoreCase("artist")) {
                // Call method to search by artist
                searchByArtist();
                // Set flag to exit the loop
                doRun = false;
            }
            // Check if the input is valid and corresponds to "genre"
            else if (checkSearchInput(searchInput) && searchInput.equalsIgnoreCase("genre")) {
                System.out.println("What genre do you want?\nRock, Hip Hop, Funk, Jazz, Pop, Classical, Folk, Reggae, Latin");
                // Get user input for genre
                String input = scan.nextLine();
                // Check if the genre input is valid
                if (checkGenreInput(input)) {
                    // Call method to search by genre
                    searchByGenre(input);
                    // Set flag to exit the loop
                    doRun = false;
                }
            } else {
                System.out.println("Invalid Choice.\n");
            }
        }
    }

    // Method to search and display songs by a specific artist
    private void searchByArtist() {
        System.out.println("Enter the artist you want to search for:");
        // Get user input for the artist to search for
        String selectedArtist = scan.nextLine();

        System.out.println("List of " + Character.toUpperCase(selectedArtist.charAt(0)) + selectedArtist.substring(1) + " Artists\n--------------------");
        boolean found = false;
        // Iterate through the master data to find and print songs by the specified artist
        for (int i = 0; i < masterArtists.length; i++) {
            if (masterArtists[i].equalsIgnoreCase(selectedArtist)) {
                System.out.println("Artist: " + masterArtists[i] + ", Album: " + masterAlbums[i]);
                found = true;
            }
        }

        // Display a message if no results are found for the specified artist
        if (!found) {
            System.out.println("No results found for artist: " + selectedArtist);
        }
    }

    // Method to search and display songs by a specific genre
    private void searchByGenre(String selectedGenre) {
        System.out.println("List of " + Character.toUpperCase(selectedGenre.charAt(0)) + selectedGenre.substring(1) + " Albums\n--------------------");
        // Iterate through the master data to find and print songs by the specified genre
        for (int i = 0; i < masterGenres.length; i++) {
            if (masterGenres[i].equalsIgnoreCase(selectedGenre)) {
                System.out.println("Artist: " + masterArtists[i] + ", Album: " + masterAlbums[i]);
            }
        }
    }

    // Method to check if the genre input is valid
    private boolean checkGenreInput(String input) {
        return input.equalsIgnoreCase("rock") || input.equalsIgnoreCase("hip hop") ||
                input.equalsIgnoreCase("funk") || input.equalsIgnoreCase("jazz") ||
                input.equalsIgnoreCase("pop") || input.equalsIgnoreCase("classical") ||
                input.equalsIgnoreCase("reggae") || input.equalsIgnoreCase("latin") ||
                input.equalsIgnoreCase("folk");
    }

    // Method to check if the search input is valid
    private boolean checkSearchInput(String input) {
        return input.equalsIgnoreCase("artist") || input.equalsIgnoreCase("genre");
    }

    // Method to create a string representation of the object
    public String toString() {
        StringBuilder result = new StringBuilder();
        // Iterate through the master data to create a string representation
        for (int i = 0; i < masterArtists.length; i++) {
            result.append("Artist: ").append(masterArtists[i]).append(", Album: ").append(masterAlbums[i])
                    .append(", Genre: ").append(masterGenres[i]).append("\n");
        }
        return result.toString();
    }
    //User interaction text and user input logic done by dante
    //Searching done by kevin
}
