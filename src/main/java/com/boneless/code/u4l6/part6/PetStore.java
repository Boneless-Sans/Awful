package com.boneless.code.u4l6.part6;

/*
 * Represents a pet store
 */
public class PetStore {

    private Dog[] dogs;     // The 1D array of Dog objects

    /*
     * Initializes dogs to the specified 1D array of Dog objects
     */
    public PetStore(Dog[] dogs) {
        this.dogs = dogs;
    }

    /*
     * Returns a String containing information about the Dog
     * object that has the same age as the parameter dogToFind
     */
    public String findMatchingDog(Dog dogToFind) {
        StringBuilder result = new StringBuilder();

        for (Dog pet : dogs) {
            if (pet == dogToFind) {
                result.append("Matching dog found!\n").append(pet);
            }
        }

        if (result.isEmpty()) {
            result.append("No matching dog found.");
        }

        return result.toString();
    }

}