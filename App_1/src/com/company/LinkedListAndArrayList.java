package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListAndArrayList {
    public static void main(String[] args)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(0, 6);
        linkedList.add(1, 7);
        linkedList.add(2, 8);
        linkedList.add(3, 9);
        linkedList.add(4, 10);

        System.out.println("Array list with deleted items: ");
        for (int i = (arrayList.size()&~1)-1; i>=0; i-=2) {
            arrayList.remove(i);
        }
        System.out.println(arrayList);

        System.out.println("\nLinkedList with deleted items: ");
        for(int i = (linkedList.size()&~1)-1; i>=0; i-=2) {
            linkedList.remove(i);
        }
        System.out.println(linkedList);
    }
}
