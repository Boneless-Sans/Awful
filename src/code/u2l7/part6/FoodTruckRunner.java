package src.code.u2l7.part6;

public class FoodTruckRunner {
    public static void main(String[] args) {
        Dessert pie = new Dessert("Apple", 9.99);

        System.out.println("Flavor: " + pie.getFlavor());

        System.out.println("Price: $" + pie.getPrice());
    }
}