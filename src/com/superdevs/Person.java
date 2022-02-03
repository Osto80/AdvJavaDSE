package com.superdevs;

class Person {
    private final String name;
    private final Gender gender;
    private final Double salary;

    public Person(String name, Gender gender, double salary) {
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person {" +
                " name= '" + name + '\'' +
                ", gender= '" + gender + '\'' +
                ", salary= " + salary +
                '}';
    }

}
