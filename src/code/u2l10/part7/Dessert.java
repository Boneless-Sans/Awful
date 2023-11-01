package src.code.u2l10.part7;

public class Dessert {
    private String flavor;
    private double price;

    public Dessert(){
        this.flavor = "cookie";
        this.price = 9.99;
    }
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

    public String toString(){
        return "Flavor: " + flavor + "\nPrice: " + price;
    }
}
