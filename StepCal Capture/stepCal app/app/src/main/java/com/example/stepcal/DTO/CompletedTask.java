package com.example.stepcal.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class CompletedTask implements Serializable {
    private String email;
    private double calorie_burn;
    private double calorie_intake;
    private String date;

    public CompletedTask(){

    }

    public CompletedTask(String email, double calorie_burn, double calorie_intake, String date) {
        this.email = email;
        this.calorie_burn = calorie_burn;
        this.calorie_intake = calorie_intake;
        this.date = date;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCalorie_burn() {
        return calorie_burn;
    }

    public void setCalorie_burn(double calorie_burn) {
        this.calorie_burn = calorie_burn;
    }

    public double getCalorie_intake() {
        return calorie_intake;
    }

    public void setCalorie_intake(double calorie_intake) {
        this.calorie_intake = calorie_intake;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CompletedTask{" +
                "email='" + email + '\'' +
                ", calorie_burn=" + calorie_burn +
                ", calorie_intake=" + calorie_intake +
                ", date='" + date + '\'' +
                '}';
    }
}
