package com.boneless.code.u2l5.part7;

import com.boneless.code.util.ConstructorsHelper;
public class FoodTruckRunner {
    public static void main(String[] args) {
        Pie pumpkinPie = new Pie();
        ConstructorsHelper.printConstructors(pumpkinPie);
        System.out.println(pumpkinPie.getFlavor());
        System.out.println(pumpkinPie.getDiameter() + "\"");
        System.out.println("$" + pumpkinPie.getPrice() + "\n");
    }
}
