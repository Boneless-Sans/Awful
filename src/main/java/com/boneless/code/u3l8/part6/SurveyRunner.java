package com.boneless.code.u3l8.part6;

public class SurveyRunner {
    public static void main(String[] args) {

        MusicSurvey responses = new MusicSurvey("hours.txt", "effects.txt");

        double minHours = responses.findMinHours();
        double maxHours = responses.findMaxHours();

        System.out.println("Minimum hours listened: " + minHours);
        System.out.println("Maximum hours listened: " + maxHours);

    }
}