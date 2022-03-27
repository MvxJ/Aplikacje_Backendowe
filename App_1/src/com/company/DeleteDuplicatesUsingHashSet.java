package com.company;

import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicatesUsingHashSet {
    public static void main(String[] args)
    {
        int[] array = new int[]{10,20,30,20,50,60,70,20,80,90,40, 10};
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }

        System.out.println("Array with duplicates: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }

        System.out.println();
        System.out.print("Array without duplicate: \n");
        System.out.print(set);
    }
}
