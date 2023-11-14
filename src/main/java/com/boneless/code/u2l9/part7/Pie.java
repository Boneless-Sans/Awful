package com.boneless.code.u2l9.part7;

public class Pie {
    private String flavor;
    private String answer;
    private double price;
    private int Diameter;

    public Pie(){

    }
    public Pie(String flavor, double price){
        this.flavor = flavor;
        this.price = price;
    }
    public Pie(String flavor, double price, int Diameter){
        this.flavor = flavor;
        this.price = price;
        this.Diameter = Diameter;
    }

    public void setFlavor(String flavor){
        this.flavor = flavor;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public void setDiameter(int Diameter){
        this.Diameter = Diameter;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getFlavor(){return flavor;}

    public double getPrice(){return price;}

    public int getDiameter(){return Diameter;}

    public String getAnswer(){return answer;}
}
