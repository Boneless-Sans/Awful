package com.boneless.code.u4l6.part6;

public class PetRunner {
    public static void main(String[] args) {

        // Creates a 1D array of Dog objects
        Dog[] dogs = {new Dog("Max", 3), new Dog("Bella", 2), new Dog("Duke", 5), new Dog("Luna", 4), new Dog("Rocky", 1)};

        // Creates a PetStore object
        PetStore pets = new PetStore(dogs);

        // Creates two Dog objects to find
        Dog firstDog = new Dog("Charlie", 4);
        Dog secondDog = new Dog("Daisy", 10);

        // Calls the findMatchingDog() method and prints the result
        System.out.println("Match for Charlie: " + pets.findMatchingDog(firstDog) + "\n");
        System.out.println("Match for Daisy: " + pets.findMatchingDog(secondDog));

    }
}