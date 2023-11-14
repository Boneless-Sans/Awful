package com.boneless.code.u2l7.part6;

public class Dessert {
    private String flavor;
    private double price;
    public Dessert(){
        this("apple", 1.99);
    }
    public Dessert(String flavor, double price){
        this.flavor = flavor;
        this.price = price;
    }

    public String getFlavor(){
        return flavor;
    }
    public double getPrice(){
        return price;
    }
}
