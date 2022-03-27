package com.company;

import java.util.HashMap;

public class CountRowsUsingHashMap {
    public static void main(String[] args)
    {
        HashMap hashMap = new HashMap();
        hashMap.put("Ala", 18);
        hashMap.put("Basia", 22);
        hashMap.put("Kasia", 28);
        hashMap.put("Klaudia", 21);

        System.out.println("Ilość elementów w hashmapie: " + hashMap.size());
    }
}
