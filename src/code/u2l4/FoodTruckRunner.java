package src.code.u2l4;

import src.code.util.ConstructorsHelper;

public class FoodTruckRunner {
    public static void main(String[] args) {
        Dessert cookie = new Dessert("Vanilla", 1.5);
        ConstructorsHelper.printConstructors(cookie);
        System.out.println(cookie.getFlavor());
        System.out.println(cookie.getPrice());
    }
}