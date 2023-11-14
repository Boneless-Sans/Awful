package com.boneless.code.u3l1.part7;

/*
 * Manages data about Netflix subscription fees
 * in different countries
 */
public class Netflix {

    private String[] countries;    // The names of countries
    private double[] fees;         // The subscription fee in each country

    /*
     * Initializes countries to the parameter countries
     * and fees to the parameter fees
     */
    public Netflix(String[] countries, double[] fees) {
        this.countries = countries;
        this.fees = fees;
    }

    /*
     * Returns a String containing the number of countries in the 1D array
     * countries and number of subscription fees in the 1D array fees
     */
    public String toString() {
        return "Number of Countries: " + countries.length + "\nNumber of Fees: " + fees.length;
    }
}