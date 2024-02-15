package com.boneless.code.Test;

public class Robot{
    //Fill in question 3
    private String name;
    private int batteryLevel;
    public Robot(String name, int batteryLevel){
        this.name = name;
        this.batteryLevel = batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel = batteryLevel;
    }
    public String toString(){
        return "Robot[" +
                "name= " + name + " " + ", batteryLevel= " + batteryLevel +
                "]";
    }
    public static void main(String[] args){
        Robot jo = new Robot("Jo", 99);
        Robot hal = jo;
        jo.setBatteryLevel(57);
        System.out.println(hal);
        //Output: Robot[name= Jo , batteryLevel= 57]
    }
}