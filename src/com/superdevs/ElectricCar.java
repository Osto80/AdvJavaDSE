package com.superdevs;

public class ElectricCar extends Car {

    protected ElectricCar (int maxSpeed, CarBrand brand) { super( maxSpeed, brand, CarFuelType.ELECTRICAL );}

    @Override
    public void engine() {
        System.out.println("* slight electrical noise *");
    }
}
