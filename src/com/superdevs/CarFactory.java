package com.superdevs;

import java.util.Locale;

public class CarFactory {

    public Car createCar(String CarType) {
        return switch (CarType.toLowerCase(Locale.ROOT)) {
            case "volvo" -> new GasolineCar(180, CarBrand.VOLVO);
            case "bmw" -> new GasolineCar(250, CarBrand.BMW);
            case "bmw-e" -> new ElectricCar(240, CarBrand.BMW);
            case "tesla" -> new ElectricCar(250, CarBrand.TESLA);
            case "volkswagen" -> new GasolineCar(210, CarBrand.VOLKSWAGEN);
            case "saab" -> new GasolineCar(165, CarBrand.SAAB);
            default -> throw new IllegalStateException(
                    "Unexpected value: "
                            + CarType.toLowerCase(Locale.ROOT)
                            +". This model is not manufactured here."
            );
        };
    }
}
