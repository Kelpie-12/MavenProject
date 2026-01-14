package org.example.car.aplication;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

public class CarFactory {

    private final Random random = new Random();

    public List<Car> create(int count) {
        return Stream.generate(this::generate)
                .limit(count)
                .toList();
    }

    private Car generate() {
        return Car.builder()
                .withUniqueId(UUID.randomUUID())
                .withBrand(getRandomCarBrand())
                .withModel("N/A")
                .withReleaseYear(getRandomReleaseYear())
                .withPrice(getRandomPrice())
                .withColor(getRandomColor())
                .build();
    }

    private CarBrand getRandomCarBrand() {
        CarBrand[] carBrands = CarBrand.values();
        return carBrands[random.nextInt(carBrands.length)];
    }

    private int getRandomReleaseYear() {
        return random.nextInt(Car.MIN_RELEASE_YEAR, 2025);
    }

    private double getRandomPrice() {
        return random.nextDouble(100_000, 10_000_000);
    }

    private Color getRandomColor() {
        return new Color(
                random.nextInt(0, 255),
                random.nextInt(0, 255),
                random.nextInt(0, 255)
        );
    }
}
