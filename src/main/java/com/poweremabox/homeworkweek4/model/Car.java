package com.poweremabox.homeworkweek4.model;

public class Car {

    private String make;
    private String model;
    private String color;
    private Integer engineCc;

    public Car() {
    }

    public Car(String make, String model, String color, Integer engineCc) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.engineCc = engineCc;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getEngineCc() {
        return engineCc;
    }

    public void setEngineCc(Integer engineCc) {
        this.engineCc = engineCc;
    }
}
