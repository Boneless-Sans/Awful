package com.boneless.code.u3l7.part7;

import com.boneless.code.util.StupidFileReader;

/*
 * Manages data about screen time
 */
public class ScreenTime {

    private int[] socialNetworkTimes;    // The 1D array of the time spent on social media apps

    /*
     * Reads the data from the specified filename
     * to initialize socialNetworkTimes
     */
    public ScreenTime(String filename) {
        socialNetworkTimes = StupidFileReader.toIntArray(filename);
    }

    /*
     * Calculates and returns the average amount of
     * time spent on social networking apps
     */
    public int calcAverageTime() {
        int total = 0;
        int index = 0;
        for(int time : socialNetworkTimes){
            total += socialNetworkTimes[index];
            index++;
        }

        return total / socialNetworkTimes.length;
    }
    public String toString(){
        String result = "Screen Time?\n------------\n";

        for(int time : socialNetworkTimes){
            result += time + "\n";
        }

        return result;
    }
}