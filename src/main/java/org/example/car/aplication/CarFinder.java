package org.example.car.aplication;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CarFinder {

    private static final double MAX_COLOR_DISTANCE = 64;

    private Set<Car> cars;

    public CarFinder(Collection<Car> cars) {
        setCars(cars);
    }

    public void setCars(Collection<Car> cars) {
        this.cars = (cars != null)
                ? new HashSet<>(cars)
                : Collections.emptySet();
    }

    public Set<Car> getCars() {
        return Collections.unmodifiableSet(cars);
    }

    public Set<Car> findByBrand(CarBrand... brands) {
        Set<CarBrand> setOfBrands = Set.of(brands);

        return cars.stream()
                .filter(c -> setOfBrands.contains(c.brand()))
                .collect(Collectors.toSet());
    }

    public Set<Car> findByColor(Color... colors) {
        Set<Color> setOfColors = Set.of(colors);

        return cars.stream()
                // Оставляем в потоке данных только те машины, чей цвет похож хотя бы на один из множества цветов
                .filter(car -> setOfColors.stream().anyMatch(color -> isColorMatch(car.color(), color)))
                .collect(Collectors.toSet());
    }

    private double getColorDistance(Color firstColor, Color secondColor) {
        int deltaRed = firstColor.getRed() - secondColor.getRed();
        int deltaGreen = firstColor.getGreen() - secondColor.getGreen();
        int deltaBlue = firstColor.getBlue() - secondColor.getBlue();

        return Math.sqrt((deltaRed * deltaRed) + (deltaGreen * deltaGreen) + (deltaBlue * deltaBlue));
    }

    private boolean isColorMatch(Color firstColor, Color secondColor) {
        return getColorDistance(firstColor, secondColor) <= MAX_COLOR_DISTANCE;
    }
}
