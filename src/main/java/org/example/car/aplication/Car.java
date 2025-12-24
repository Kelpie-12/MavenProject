package org.example.car.aplication;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record Car(
        UUID uniqueId,
        CarBrand brand,
        String model,
        int releaseYear,
        BigDecimal price,
        Color color
) {
    public static final int MIN_RELEASE_YEAR = 1900;

    public Car {
        Objects.requireNonNull(uniqueId, "Car unique id must not be null");
        Objects.requireNonNull(brand, "Car brand must not be null");
        Objects.requireNonNull(model, "Car model must not be null");

        if (model.isBlank())
            throw new IllegalArgumentException("Car model must not be empty");

        if (releaseYear < MIN_RELEASE_YEAR)
            throw new IllegalArgumentException("Minimal car release year is " + MIN_RELEASE_YEAR);

        if (price.compareTo(BigDecimal.ZERO) < 1)
            throw new IllegalArgumentException("Incorrect car price: " + price);

        Objects.requireNonNull(color, "Car color must not be null");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID uniqueId;
        private CarBrand brand;
        private String model;
        private int releaseYear;
        private BigDecimal price;
        private Color color;

        private Builder() {}

        public Builder withUniqueId(UUID uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public Builder withBrand(CarBrand brand) {
            this.brand = brand;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = BigDecimal.valueOf(price);
            return this;
        }

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(uniqueId, brand, model, releaseYear, price, color);
        }
    }

}
