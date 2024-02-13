package com.boneless.code.u4l2.part6;

/*
 * Represents a customer at a store
 */
public class Customer {

    private String name;     // The name of the customer

    /* ----------------------------------- TO DO -----------------------------------
     * ✅ Declare and initialize a static variable called newCustomers to 0.
     * -----------------------------------------------------------------------------
     */
    private static int customerCount = 0;


    /*
     * Sets name to the specified name of a customer
     */
    public Customer(String name) {
        this.name = name;

        /* ----------------------------------- TO DO -----------------------------------
         * ✅ Increase the value of newCustomers by 1.
         * -----------------------------------------------------------------------------
         */
        customerCount++;
    }

    /*
     * Returns the name of the customer
     */
    public static String getCustomerCount(){
        return "New Customers: " + customerCount;
    }
    public String getName() {
        return name;
    }

}