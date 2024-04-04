package com.boneless.code.u6l8;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Represents feedback
 */
public class Feedback {

    private ArrayList<String> feedbackMessages; // customer feedback
    private ArrayList<String> relevantWords;    // relevant words to keep

    public Feedback(ArrayList<String> feedbackMessages, ArrayList<String>relevantWords) {
        this.feedbackMessages = feedbackMessages;
        this.relevantWords = relevantWords;
    }


    public ArrayList<String> removeIrrelevantWords() {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Remove all non-relevant words from feedbackMessages and return feedbackMessages
         * -----------------------------------------------------------------------------
         */
        for(int i = 0; i < feedbackMessages.size();i++){
            for(String whiteList : relevantWords){
                if(!whiteList.equals(feedbackMessages.toArray()[i])){
                    feedbackMessages.remove(feedbackMessages.remove(i));
                }
            }
        }


        return feedbackMessages;
    }

}
