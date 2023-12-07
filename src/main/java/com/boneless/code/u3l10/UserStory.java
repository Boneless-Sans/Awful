package com.boneless.code.u3l10;

import com.boneless.code.util.StupidFileReader;

import java.util.Scanner;

public class UserStory {
    private static String[] masterArtists;
    private static String[] masterAlbums;
    private static String[] masterGenres;
    private static boolean doRun = true;
    private static final Scanner scan = new Scanner(System.in);
    public UserStory(){
        masterArtists = StupidFileReader.toStringArray("artists.txt");
        masterAlbums = StupidFileReader.toStringArray("albums.txt");
        masterGenres = StupidFileReader.toStringArray("genre.txt");
    }
    public UserStory(String artists, String albums, String genre){
        masterArtists = StupidFileReader.toStringArray(artists);
        masterAlbums = StupidFileReader.toStringArray(albums);
        masterGenres = StupidFileReader.toStringArray(genre);
    }
    public static void init(){
        while(doRun){
            System.out.println("What do you want to search by?\nArtist, or Genre?");
            String searchInput = scan.nextLine();
            if(checkSearchInput(searchInput) && searchInput.equalsIgnoreCase("artist")){
                searchByArtist(searchInput);
                doRun = false;
            }else if(checkSearchInput(searchInput) && searchInput.equalsIgnoreCase("genre")){
                System.out.println("What genre do you want?\nRock, Hip Hop, Funk, Jazz, Pop, Classical, Folk, Reggae, Latin");
                String input = scan.nextLine();
                if(checkGenreInput(input)){
                    searchByGenre(scan.nextLine());
                    doRun = false;
                }
            }else{
                System.out.println("Invalid Choice.\n");
            }
        }
    }
    private static void searchByArtist(String selectedArtist){
        System.out.println("List of " + Character.toUpperCase(selectedArtist.charAt(0)) + selectedArtist.substring(1) + " Artists\n--------------------");
        for (int i = 0; i < masterArtists.length; i++) {
            if (masterArtists[i].equalsIgnoreCase(selectedArtist)) {
                System.out.println("Artist: " + masterArtists[i] + ", Album: " + masterAlbums[i]);
            }
        }
    }
    private static void searchByGenre(String selectedGenre) {
        System.out.println("List of " + Character.toUpperCase(selectedGenre.charAt(0)) + selectedGenre.substring(1) + " Albums\n--------------------");
        for (int i = 0; i < masterGenres.length; i++) {
            if (masterGenres[i].equalsIgnoreCase(selectedGenre)) {
                System.out.println("Artist: " + masterArtists[i] + ", Album: " + masterAlbums[i]);
            }
        }
    }
    private static boolean checkGenreInput(String input){
        return input.equalsIgnoreCase("rock") || input.equalsIgnoreCase("hip hop") ||
                input.equalsIgnoreCase("funk") || input.equalsIgnoreCase("jazz") ||
                input.equalsIgnoreCase("pop") || input.equalsIgnoreCase("classical") ||
                input.equalsIgnoreCase("reggae") || input.equalsIgnoreCase("latin") ||
                input.equalsIgnoreCase("folk");
    }
    private static boolean checkSearchInput(String input){
        return input.equalsIgnoreCase("artist") || input.equalsIgnoreCase("genre");
    }
    public String toString(){
        String result = null;
        for(int i = 0;i < masterArtists.length;i++){
            result += "Artist: " + masterArtists[i] + ", Album: " + masterAlbums[i] + ", Genre: " + masterGenres[i] + "\n";
        }
        return result;
    }
}
