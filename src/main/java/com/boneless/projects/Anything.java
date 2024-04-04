package com.boneless.projects;

import java.util.*;


public class Anything {
    public static void main(String[] args){
        int[] arr = {99, 22, 20, 13, 2, -1, 7, 55};

        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    /*
    repeat (numOfElements - 1) times

set the first unsorted element as the minimum

for each of the unsorted elements

   		if element < minimum

      		set element as new minimum

  	swap minimum with first unsorted position // end of outside loop
     */
    public static void selectionSort(int[] arr){
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.add(arr[0]);
        ArrayList<Integer> list = new ArrayList<>();
        for(int val : arr){
            list.add(val);
        }
        for (Integer integer : list) {
            for (int k = 0; k < sorted.size(); k++) {
                if (integer < sorted.get(0)) {
                    System.out.println("passed: " + integer + " " + sorted.get(k));
                    int heapFix = sorted.get(k);
                    sorted.add(heapFix);
                    sorted.set(0, integer);
                    break;
                }else{
                    System.out.println("failed: " + integer + " " + sorted.get(k));
                    sorted.add(integer);
                    k++;
                }
            }
        }
    }
}
