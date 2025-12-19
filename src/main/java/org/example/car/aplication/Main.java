package org.example.car.aplication;

import java.awt.*;
import java.util.List;

public class Main {
    static void main(String[] args) {
        //Car car=new Car(
        //        CarBrand.Mersedec,
        //        "cls",
        //        2022,
        //        1000.0,
        //        Color.GREEN
        //);
        Car car = Car.builder()
                .setBrand(CarBrand.Mersedec)
                .setModel("N/A")
                .setYear(2024)
                .setPrice(20)
                .setColour(Color.GREEN)
                .build();

        CarFactory carFactory=new CarFactory();
        List<Car> cars=carFactory.create(10);
        System.out.println(car);
        cars.forEach(System.out::println);


    }
}
