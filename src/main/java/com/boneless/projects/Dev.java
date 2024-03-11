package com.boneless.projects;

public class Dev {
    public static void main(String[] args){
        System.out.println(makeOutWord("<<>>","Yay"));
        int number = 0;
        
    }
    public static String makeOutWord(String out, String word) {
        return out.substring(0,2) + word + out.substring(2,4);
    }

}
