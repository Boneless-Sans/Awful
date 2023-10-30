package src.code.u2l9.part7;

import java.util.Scanner;

public class FoodTruckRunner {
    public static void main(String[] args) {

        // Creates a Scanner object to get input from a user
        Scanner input = new Scanner(System.in);

        Pie pie = new Pie();

        System.out.println("What kind of pie would you like?");
        pie.setFlavor(input.next());

        System.out.println("What Diameter do you want it?");
        pie.setDiameter(input.nextInt());

        System.out.println("You want a " + pie.getDiameter() + "\" " + pie.getFlavor() + " Pie?");
        System.out.println("Yes / No");
        pie.setAnswer(input.next());

        if(!pie.getAnswer().equalsIgnoreCase("yes") || !pie.getAnswer().equalsIgnoreCase("no")){
            boolean bruh = false;
            while(!bruh){
                System.out.println("Um, what?");
                System.out.println("(Yes or No)");
                pie.setAnswer(input.next());
                if(!pie.getAnswer().equalsIgnoreCase("yes") && !pie.getAnswer().equalsIgnoreCase("no")){
                    bruh = false;
                }else{
                    bruh = true;
                }
            }
        }
        double price = (pie.getDiameter() * 10.99) * .6;
        System.out.println("Your total is: $" + String.format("%.2f", price));


        input.close();
    }
}