package com.example.stepcal.DTO;

import java.io.Serializable;

public class User implements Serializable {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private long phone_no;
    private int age;
    private double height;
    private double weight;
    private String goal;
    private String gender;
    private String activity_level;

    int point;


    public User(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public User(String first_name, String last_name, String email, String password, long phone_no, int age, double height, double weight, String goal, String gender, String activity_level, int point) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone_no = phone_no;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.gender = gender;
        this.activity_level = activity_level;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone_no=" + phone_no +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", goal='" + goal + '\'' +
                ", gender='" + gender + '\'' +
                ", activity_level='" + activity_level + '\'' +
                ", point=" + point +
                '}';
    }
}
