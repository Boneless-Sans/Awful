package com.boneless.code.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StupidFileReader {

    // Read data from a file in the "resources/data/code" directory
    public static String[] toStringArray(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                StupidFileReader.class.getResourceAsStream("/data/code/" + fileName)))) {

            return reader.lines().toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    // Read data from a file in the "resources/data/code" directory and return it as an array of integers
    public static int[] toIntArray(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                StupidFileReader.class.getResourceAsStream("/data/code/" + fileName)))) {

            return reader.lines().mapToInt(Integer::parseInt).toArray();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    public static double[] toDoubleArray(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                StupidFileReader.class.getResourceAsStream("/data/code/" + fileName)))) {

            return reader.lines().mapToDouble(Double::parseDouble).toArray();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return new double[0];
        }
    }
}
