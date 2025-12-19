package org.example.car.aplication;

import java.awt.*;
import java.util.Objects;

public record Car(CarBrand brand, String model, int year, double price, Color colour) {

    public static final int MIN_YEAR = 1900;

    public Car {

        Objects.requireNonNull(brand, "Бренд должен быть указан");

        Objects.requireNonNull(model, "Модель должна быть указана");

        if (model.isBlank()) {
            throw new IllegalArgumentException("Модель должна быть указана");
        }

        if (year < MIN_YEAR) {
            throw new IllegalArgumentException("Минимальный год");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Неверная цена");
        }
        Objects.requireNonNull(colour, "Цвет должен быть указан");


    }

    public  static  Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private CarBrand brand;
        private String model;
        private int year;
        private double price;
        private Color colour;

        public Builder setBrand(CarBrand brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setColour(Color colour) {
            this.colour = colour;
            return this;
        }


        private Builder() {

        }


        public Car build() {
            return new Car(brand, model, year, price, colour);
        }
    }

}
