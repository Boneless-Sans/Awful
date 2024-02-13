package com.boneless.projects;

public class SpinningChar{
    @SuppressWarnings("BusyWait")
    public static void main(String[] args){
        int delayTime = 100;
        while(true){
            try {
                System.out.println("-");
                Thread.sleep(delayTime);
                clearTerminal();
                System.out.println("\\");
                Thread.sleep(delayTime);
                clearTerminal();
                System.out.println("|");
                Thread.sleep(delayTime);
                clearTerminal();
                System.out.println("/");
                Thread.sleep(delayTime);
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