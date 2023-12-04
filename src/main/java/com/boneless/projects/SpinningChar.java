package com.boneless.projects;

public class SpinningChar{
    public static void main(String[] args){
        while(true){
            try {
                System.out.println("-");
                Thread.sleep(100);
                clearTerminal();
                System.out.println("\\");
                Thread.sleep(100);
                clearTerminal();
                System.out.println("|");
                Thread.sleep(100);
                clearTerminal();
                System.out.println("/");
                Thread.sleep(100);
                clearTerminal();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
    private static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}