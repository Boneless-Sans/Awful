package com.boneless.code.u3l8.part6;

import com.boneless.code.util.aFileReader;

/*
 * Manages data about responses to a survey
 */
public class MusicSurvey {

    private Respondent[] responses;    // The 1D array of Respondent objects

    /*
     * Reads the data from hoursFile and effectsFile to initialize responses
     */
    public MusicSurvey(String hoursFile, String effectsFile) {
        responses = createResponses(hoursFile, effectsFile);
    }

    /*
     * Returns a 1D array of Respondent objects using the data from hoursFile and effectsFile
     */
    public Respondent[] createResponses(String hoursFile, String effectsFile) {
        double[] hoursData = aFileReader.toDoubleArray(hoursFile);
        String[] effectsData = aFileReader.toStringArray(effectsFile);

        Respondent[] tempResponses = new Respondent[hoursData.length];

        for (int index = 0; index < tempResponses.length; index++) {
            tempResponses[index] = new Respondent(hoursData[index], effectsData[index]);
        }

        return tempResponses;
    }

    /*
     * Returns the minimum number of hours a respondent listens to music per day
     */
    public double findMinHours() {
        double minHours = Double.MAX_VALUE;

        for(Respondent response : responses){
            double hoursListened = response.getHours();
            if(hoursListened < minHours){
                minHours = hoursListened;
            }
        }
        return minHours;
    }

    /*
     * Returns the maximum number of hours a respondent listens to music per day
     */
    public double findMaxHours() {
        double maxHours = Double.MIN_VALUE;

        for(Respondent response : responses){
            double hoursListened = response.getHours();
            if(hoursListened > maxHours){
                maxHours = hoursListened;
            }
        }
        return maxHours;
    }

    /*
     * Returns a String containing each respondent's information
     */
    public String toString() {
        String result = "";

        for (Respondent answer : responses) {
            result += answer + "\n";
        }

        return result;
    }

}