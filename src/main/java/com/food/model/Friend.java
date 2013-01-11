package com.food.model;

public class Friend extends Model {
	
	private String name;

    private int age;

    public Friend() {
        super();
    }

    public Friend(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Friend [age=" + age + ", id=" + id + ", name=" + name + "]";
    }

}
