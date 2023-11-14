package com.boneless.code.u2l5.part7;

public class Pie {
    private String flavor;
    private double price;
    private int diameter;
    public Pie(){
        flavor = "apple";
        price = 9.99;
        diameter = 12;
    }
    public Pie(String flavor, double price, int diameter){
        this.price = price;
        this.flavor = flavor;
        this.diameter = diameter;
    }
    public String getFlavor(){
        return flavor;
    }
    public double getPrice(){
        return price;
    }
    public int getDiameter(){
        return diameter;
    }
}
