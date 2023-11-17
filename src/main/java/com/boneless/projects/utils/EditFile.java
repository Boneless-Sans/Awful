package com.boneless.projects.utils;

import java.util.Scanner;

public class EditFile{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Board X size");
        int x = scan.nextInt();
        System.out.println("Board y size");
        int y = scan.nextInt();
        JsonFile.writeln("painter.json", "default", "x", String.valueOf(x));
        JsonFile.writeln("painter.json", "default", "y", String.valueOf(y));
    }
}
