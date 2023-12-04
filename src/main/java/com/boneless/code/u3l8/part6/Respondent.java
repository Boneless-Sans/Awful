package com.boneless.code.u3l8.part6;

/*
 * Represents a respondent to a survey
 */
public class Respondent {

    private double hours;      // The number of hours a respondent listens to music per day
    private String effect;     // The effect music has on a respondent's mental health

    /*
     * Sets hours and effect to the specified values
     */
    public Respondent(double hours, String effect) {
        this.hours = hours;
        this.effect = effect;
    }

    /*
     * Returns the number of hours a respondent listens to music per day
     */
    public double getHours() {
        return hours;
    }

    /*
     * Returns the effect music has on the respondent's mental health
     */
    public String getEffect() {
        return effect;
    }

    /*
     * Returns a String containing the hours and effect
     */
    public String toString() {
        return hours + " hours - " + effect;
    }

}