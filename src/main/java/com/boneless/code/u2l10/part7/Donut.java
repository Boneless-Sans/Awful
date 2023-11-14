package com.boneless.code.u2l10.part7;

public class Donut extends Dessert{
    private boolean hasSprinkles;
    private String flavor;
    private double price;
    public Donut(){
        super("Plain", 5.99);
        this.hasSprinkles = false;
    }
    public Donut(String flavor, double price, boolean hasSprinkles){
        super(flavor, price);
        this.hasSprinkles = hasSprinkles;
    }
    public void setSize(boolean hasSprinkles){
        this.hasSprinkles = hasSprinkles;
    }
    public boolean gethasSprinkles(){
        return hasSprinkles;
    }
    public String toString(){
        return super.toString() + "\nDoes it Have Sprinkles?: " + hasSprinkles;
    }
}
