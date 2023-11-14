package com.boneless.code.u2l7.part7;

public class Cookie {
    private String flavor;
    private double price;
    private boolean isChewy;
    public Cookie(){
        this("apple", 1.99, true);
    }
    public Cookie(String flavor, double price, boolean isChewy){
        this.flavor = flavor;
        this.price = price;
        this.isChewy = isChewy;
    }

    public String getFlavor(){
        return flavor;
    }
    public double getPrice(){
        return price;
    }
    public boolean getIsChewy(){
        return isChewy;
    }

}
