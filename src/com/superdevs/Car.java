package com.superdevs;

public abstract class Car {

    private int maxSpeed;
    private CarBrand brand;
    private CarFuelType type;

    protected Car(int maxRange, CarBrand brand, CarFuelType type){
        this.maxSpeed = maxRange;
        this.brand = brand;
        this.type = type;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public CarFuelType getFuelType() {
        return this.type;
    }

    public abstract void engine();
}
