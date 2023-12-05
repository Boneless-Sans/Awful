package com.boneless.code.u3l10;

import com.boneless.code.u3l7.part7.ScreenTime;
import com.boneless.code.util.StupidFileReader;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class UserStory {
    private String[] artists;
    private String[] albums;
    private String[] genre;
    private static boolean doRun = true;
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
        Scanner scan = new Scanner(System.in);
        while(doRun){
            System.out.println("What genre do you want?");
            switch(scan.nextLine().toLowerCase()){
                case"rock":
                    break;
                case"hip hop":
                    break;
                case"funk":
                    break;
                    case"rap"
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }
}
