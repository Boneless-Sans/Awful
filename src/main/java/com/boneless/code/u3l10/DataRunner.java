package com.boneless.code.u3l10;

import java.util.Scanner;

public class DataRunner {
    public static void main(String[] args) {
        UserStory user = new UserStory("artists.txt","albums.txt","genres.txt");
        user.init();
    }
}