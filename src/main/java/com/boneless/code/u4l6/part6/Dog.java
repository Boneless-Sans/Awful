package com.boneless.code.u4l6.part6;

/*
 * Represents a dog
 */
public class Dog {

    private String name;   // The name of a dog
    private int age;       // The age of a dog

    /*
     * Sets name to the specified name and age to the specified age
     */
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*
     * Returns the name of the dog
     */
    public String getName() {
        return name;
    }

    /*
     * Returns the age of the dog
     */
    public int getAge() {
        return age;
    }

    /*
     * Returns true if the ages are the same, otherwise returns false
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dog)) {
            return false;
        }

        Dog otherDog = (Dog) other;
        return this.age == otherDog.age;
    }

    /*
     * Returns a String containing the dog's name and age
     */
    public String toString() {
        return name + ": " + age + " years old";
    }

}