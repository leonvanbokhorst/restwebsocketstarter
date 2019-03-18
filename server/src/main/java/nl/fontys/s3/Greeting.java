package nl.fontys.s3;

import java.util.ArrayList;
import java.util.Arrays;

class Greeting {

    private String name;
    private int age;
    private ArrayList<Integer> stuff;

    Greeting(String name, int age) {
        this.name = name;
        this.age = age;
        this.stuff = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    }
}
