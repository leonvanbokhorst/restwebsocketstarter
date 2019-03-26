package nl.fhict.s3.restserver;

public class Greeting {

    private String name;
    private int age;

    public Greeting() {
    }

    Greeting(String name, int age) {
        this.name = name;
        this.age = age;
    }


    String getName() {
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
