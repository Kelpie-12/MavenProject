package org.example.car.aplication;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CarFactory {
    private final Random random = new Random();


    public List<Car> create(int count) {
        return Stream.generate(this::createCar).limit(count).toList();
    }

    private Car createCar() {
        return Car.builder()
                .setBrand(getRandomCarBrand())
                .setModel("N/A")
                .setYear(getRandomYear())
                .setPrice(getRandomPrice())
                .setColour(getRandomColour())
                .build();
    }

    private CarBrand getRandomCarBrand() {
        CarBrand[] brands = CarBrand.values();
        return brands[random.nextInt(brands.length)];
    }

    private int getRandomYear() {
        return random.nextInt(Car.MIN_YEAR, 2025);
    }

    private double getRandomPrice() {
        return random.nextDouble(100000, 1000000);
    }

    private Color getRandomColour() {
        return new Color(
                random.nextInt(0, 255),
                random.nextInt(0, 255),
                random.nextInt(0, 255));
    }
}
