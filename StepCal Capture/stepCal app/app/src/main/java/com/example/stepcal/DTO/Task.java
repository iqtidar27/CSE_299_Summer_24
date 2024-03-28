package com.example.stepcal.DTO;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class Task {
    private double target_Calorie;
    private Map<String,Double>exercises;


    public Task(){

    }

    public Task(double target_Calorie, Map<String, Double> exercises) {
        this.target_Calorie = target_Calorie;
        this.exercises = exercises;
    }

    public double getTarget_Calorie() {
        return target_Calorie;
    }

    public void setTarget_Calorie(double target_Calorie) {
        this.target_Calorie = target_Calorie;
    }

    public Map<String, Double> getExercises() {
        return exercises;
    }

    public void setExercises(Map<String, Double> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Task{" +
                "target_Calorie=" + target_Calorie +
                ", exercises=" + exercises +
                '}';
    }
}
