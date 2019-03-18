package nl.fontys.s3;

import java.util.ArrayList;
import java.util.Arrays;

public class Greeting {

    private String name;
    private int age;
//    private ArrayList<Integer> stuff;


    public Greeting() {
    }

    public Greeting(String name, int age) {
        this.name = name;
        this.age = age;
//        this.stuff = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
