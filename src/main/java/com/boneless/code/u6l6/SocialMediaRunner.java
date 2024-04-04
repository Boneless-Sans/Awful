package com.boneless.code.u6l6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SocialMediaRunner {
    public static void main(String[] args) {

        // Creates an ArrayList of usernames
        ArrayList<String> friends = new ArrayList<String>();
        friends.add("emilysmith");
        friends.add("liampatel");
        friends.add("sofianguyen");
        friends.add("williamgarcia");
        friends.add("isabellakim");

        // Creates a SocialMedia object
        SocialMedia app = new SocialMedia(friends);

        // Prints the SocialMedia object
        System.out.println("----- Unsorted Friends List -----");
        System.out.println(app + "\n");

        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Call the sortUsers() method and print the updated SocialMedia object.
         * -----------------------------------------------------------------------------
         */
        app.sortUsers();
        System.out.println("this one -> " + app);
        //String[] test = "1";
        //System.out.println(Arrays.toString(test));





    }
}
