package src.code.u2l9.part7;

import java.util.Scanner;

public class FoodTruckRunner {
    public static void main(String[] args) {

        // Creates a Scanner object to get input from a user
        Scanner input = new Scanner(System.in);

        Dessert cookie = new Dessert("Oatmeal",5.99);

        System.out.println("What Flavor Do You Want?");
        cookie.setFlavor(input.nextLine());

        System.out.println(cookie.getFlavor() + " is $" + cookie.getPrice());

        input.close();
    }
}