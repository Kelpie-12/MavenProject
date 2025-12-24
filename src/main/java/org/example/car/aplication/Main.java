package org.example.car.aplication;

import org.example.car.aplication.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
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
        //Car car = Car.builder()
        //        .setBrand(CarBrand.Mersedec)
        //        .setModel("N/A")
        //        .setYear(2024)
        //        .setPrice(20)
        //        .setColour(Color.GREEN)
        //        .build();
//
        //CarFactory carFactory=new CarFactory();
        //List<Car> cars=carFactory.create(10);
        //System.out.println(car);
        //cars.forEach(System.out::println);

        //SwingUtilities.invokeLater(Window::new);
        SwingUtilities.invokeLater(Window::new);




        //    String connectionString = "jdbc:sqlite:cars.db";
        //    try (Connection connection = DriverManager.getConnection(connectionString); Statement statement=connection.createStatement())
        //    {
        //        statement.execute("""
        //                create table if not exists Cars(id integer primary key autoincrement,
        //                brand text not null);
        //                """);
        //        System.out.println("Connect!");

        //    } catch (SQLException e) {
        //        e.printStackTrace();
        //    }
    }
}
