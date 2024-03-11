package com.boneless.code.u6l6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * Represents a social media app
 */
public class SocialMedia {

    private ArrayList<String> friends;      // The list of friends

    /*
     * Initializes friends to the specified list of friends
     */
    public SocialMedia(ArrayList<String> friends) {
        this.friends = friends;
    }

    /*
     * Returns the list of friends
     */
    public ArrayList<String> getFriends() {
        return friends;
    }

    /*
     * Sorts the usernames in the friends list in alphabetical order
     */
    public void sortUsers() {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Use nested loops to traverse the friends list to get the current element
         * (outer loop) and the next element (inner loop). If the result of
         * currentElement.compareTo(nextElement) is greater than 0, set the next index
         * (inner loop variable) to currentElement and the current index (outer loop)
         * variable) to nextElement.
         * -----------------------------------------------------------------------------
         */
        //go through each array list obj, compare and edit accordingly
        int currentIndex = 0;
        int lastCompare = 0;
        ArrayList<String> uh = new ArrayList<>();
        for (String friend : friends) {
            if (friends.get(currentIndex).compareTo(friend) < lastCompare) {
                
            }
        }
    }

    /*
     * Returns a String containing each friends in the friends list
     */
    public String toString() {
        String result = "";

        for (int index = 0; index < friends.size(); index++) {
            result += friends.get(index) + "\n";
        }
        return result;
    }

}
