package src.code.u2l5.part7;

import src.code.util.ConstructorsHelper;

public class FoodTruckRunner {
    public static void main(String[] args){
        Pie pumpkinPie = new Pie();
        ConstructorsHelper.printConstructors(pumpkinPie);
        System.out.println(pumpkinPie.getFlavor());
        System.out.println(pumpkinPie.getDiameter() + "\"");
        System.out.println("$" + pumpkinPie.getPrice());

    }
}
