package src.code.u2l9.part7;

public class Dessert {
    private String flavor;
    private double price;
    public Dessert(String flavor, double price){
        this.flavor = flavor;
        this.price = price;
    }

    public void setFlavor(String flavor){
        this.flavor = flavor;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public String getFlavor(){
        return flavor;
    }

    public double getPrice(){
        return price;
    }
}
