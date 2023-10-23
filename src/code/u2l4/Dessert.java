package src.code.u2l4;

public class Dessert {

    private String flavor;    // The flavor of a dessert
    private double price;     // The price of a dessert
    public Dessert() {
        flavor = "plain";
        price = 0.50;
    }
    public Dessert(String aFlavor, double aPrice){
        flavor = aFlavor;
        price = aPrice;
    }
    public String getFlavor(){
        return flavor;
    }
    public double getPrice(){
        return price;
    }
}