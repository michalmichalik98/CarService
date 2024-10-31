package com.example.car_service.model;

public class Vehicle {
    private String registrationNumber;
    private String name;
    private boolean isFixed;
    private String color;
    private int year;

    // Konstruktory
    public Vehicle() {}

    public Vehicle(String registrationNumber, String name, String color, int year) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.isFixed = false; // Domyślnie pojazd nie jest naprawiony
        this.color = color;
        this.year = year;
    }

    // Gettery i Settery
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

