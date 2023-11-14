package com.boneless.code.u3l1.part6;

/*
 * Manages data about pet breeds available for adoption
 */
public class PetStore {

    private String name;      // The name of a pet store
    private String[] breeds;  // The breeds of pets available

    /*
     * Sets name to the specified name and initializes
     * breeds to store the number of specified values
     */
    public PetStore(String name, int numBreeds) {
        this.name = name;
        breeds = new String[numBreeds];
    }

    /*
     * Returns a String containing the number of breeds in the 1D array breeds
     */
    public String toString() {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Return a String containing the text "Number of Breeds: " followed
         * by the length of the 1D array breeds.
         * -----------------------------------------------------------------------------
         */

        return "Number of Breeds: " + breeds.length;
    }

}