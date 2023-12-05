package com.boneless.code.u3l10;

import com.boneless.code.u3l7.part7.ScreenTime;
import com.boneless.code.util.StupidFileReader;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class UserStory {
    private static String[] artists;
    private static String[] albums;
    private static String[] genre;
    private static boolean doRun = true;
    private static HashMap<String, String> map = new HashMap<String, String>();
    private static Scanner scan = new Scanner(System.in);
    public UserStory(){
        this.artists = StupidFileReader.toStringArray("artists.txt");
        this.albums = StupidFileReader.toStringArray("albums.txt");
        this.genre = StupidFileReader.toStringArray("genre.txt");
    }
    public UserStory(String artists, String albums, String genre){
        this.artists = StupidFileReader.toStringArray(artists);
        this.albums = StupidFileReader.toStringArray(albums);
        this.genre = StupidFileReader.toStringArray(genre);
    }
    public static void init(){
        while(doRun){
            System.out.println("What genre do you want?\nRock, Hip Hip, Funk, Jazz, Pop, Classical, Folk, Reggae, Latin");
            String input = scan.nextLine().toLowerCase();
            if (input.equals("rock") || input.equals("hip hop") || input.equals("funk") || input.equals("jazz") || input.equals("pop") ||
                    input.equals("classical") || input.equals("reggae") || input.equals("latin") || input.equals("folk")) {
                chooseArtist(input);
                doRun = false;
            }else {
                System.out.println("Invalid Choice");
            }
        }
    }
    private static void chooseArtist(String genre){
        System.out.println("What Arist would you like for " + genre + "?");
        String input = scan.nextLine().toLowerCase();
        System.out.println(printArtists(genre));

    }
    private static String printArtists(String genre){
        String result = "Artists\n----------------------\n";
        for(String out : artists){
            result += out + "\n";
        }
        return result;
    }

}
