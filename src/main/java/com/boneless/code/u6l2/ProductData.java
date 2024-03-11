package com.boneless.code.u6l2;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Represents feedback for a product
 */
public class ProductData {

    private String feedback;    // The feedback for a product

    /*
     * Sets feedback to the specified customer feedback
     */
    public ProductData(String feedback) {
        this.feedback = feedback;
    }

    /*
     * Returns the customer feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /*
     * Returns a String containing each word in feedback on separate lines
     */
    public String textToWords() {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Use the indexOf() and substring() methods in the String class to obtain
         * each word in feedback and concatenating it to a new String on a new line.
         * -----------------------------------------------------------------------------
         */
//        String[] array = feedback.split(" ");
//        StringBuilder builder = new StringBuilder();
//        for(String text : array){
//            builder.append(text).append("\n");
//        }
//        return builder.toString();
        StringBuilder masterIndex = new StringBuilder();
        String[] text = feedback.split(" ");
        for(String txt : text){
            //masterIndex.append()
        }
        return null;
    }

}