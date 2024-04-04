package com.boneless.code.u6l8;

import java.util.ArrayList;

public class FeedbackRunner {
    public static void main(String[] args) {
        ArrayList<String> feedbackMessages = new ArrayList<String>();
        feedbackMessages.add("Thank");
        feedbackMessages.add("you");
        feedbackMessages.add("for");
        feedbackMessages.add("the");
        feedbackMessages.add("help");
        feedbackMessages.add("You");
        feedbackMessages.add("were");
        feedbackMessages.add("very");
        feedbackMessages.add("kind");

        ArrayList<String> relevantWords = new ArrayList<String>();
        relevantWords.add("happy");
        relevantWords.add("unhappy");
        relevantWords.add("sad");
        relevantWords.add("glad");
        relevantWords.add("help");
        relevantWords.add("kind");
        relevantWords.add("fast");
        relevantWords.add("slow");

        // Create a Feedback object
        Feedback feedback = new Feedback(feedbackMessages, relevantWords);

        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Call the removeIrrelevantWords() method and print the result
         * -----------------------------------------------------------------------------
         */
        System.out.println(feedback.removeIrrelevantWords());





    }
}