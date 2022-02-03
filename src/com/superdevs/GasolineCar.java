package com.superdevs;

public class GasolineCar extends Car{

    protected GasolineCar(int maxSpeed, CarBrand brand) { super( maxSpeed, brand, CarFuelType.GASOLINE ); }

    @Override
    public void engine() {
        System.out.println("Vroom! vroom!");
    }
}
