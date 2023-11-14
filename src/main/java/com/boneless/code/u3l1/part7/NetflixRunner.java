package com.boneless.code.u3l1.part7;

public class NetflixRunner {
    public static void main(String[] args){
        String[] countries = new String[20];
        double[] fees = new double[20];

        Netflix user = new Netflix(countries, fees);

        System.out.println(user.toString());
    }
}
