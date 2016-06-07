package com.example.rod.exampleit;

/**
 * Created by rod on 6/3/16.
 */
public class User {

    int id;
    String name;
    int age;
    float height;


    public User(){

    }

    public User(int theId, String theName, int theAge, float theHeight){
        this.id = theId;
        this.name = theName;
        this.age = theAge;
        this.height = theHeight;
    }

    public User(String theName, int theAge, float theHeight){
        this.name = theName;
        this.age = theAge;
        this.height = theHeight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
