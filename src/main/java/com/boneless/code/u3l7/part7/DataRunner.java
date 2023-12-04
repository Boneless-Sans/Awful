package com.boneless.code.u3l7.part7;

public class DataRunner {
    public static void main(String[] args){
        ScreenTime time = new ScreenTime("times.txt");

        System.out.println(time);
        time.calcAverageTime();
        System.out.println(time);
    }
}
