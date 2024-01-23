package org.example;

public class Person {
    private String name;
    private String town;
    private int age;

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name=" + name +
                ". Town=" + town +
                ". Age=" + age;
    }
}
