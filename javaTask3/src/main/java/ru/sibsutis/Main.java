package ru.sibsutis;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Developer> arrayList = new ArrayList<Developer>();
        List<Developer> linkedList = new LinkedList<Developer>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++)
            arrayList.add(new Developer("Pooper","puk@kak.com","Pooping"));
        long stopTime = System.nanoTime();
        System.out.println("ArrayList.add time:" + ((double)stopTime - startTime) / 1000000000);
/*
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++)
            arrayList.remove(i);
        stopTime = System.nanoTime();
        System.out.println("ArrayList.remove time:" + (stopTime - startTime) / 1000000000);
*/
        startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++)
            linkedList.add(new Developer("Pooper","puk@kak.com","Pooping"));
        stopTime = System.nanoTime();
        System.out.println("LinkedList.add time:" + ((double)stopTime - startTime) / 1000000000);
/*
        startTime = System.nanoTime();
        for (Developer temp: linkedList)
            linkedList.remove(temp);
        stopTime = System.nanoTime();
        System.out.println("LinkedList.remove time:" + (stopTime - startTime) / 1000000000);
*/
    }
}
