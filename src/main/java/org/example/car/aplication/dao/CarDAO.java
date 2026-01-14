package org.example.car.aplication.dao;
import org.example.car.aplication.Car;
import org.example.car.aplication.CarBrand;
import org.example.car.aplication.Main;
import org.example.car.aplication.database.DataSource;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarDAO {

    public static void validateTable() throws SQLException {
        try (Connection connection = Main.getDataSource().createConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("""
                CREATE TABLE IF NOT EXISTS `cars` (
                    `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    `unique_id` TEXT NOT NULL,
                    `brand` TEXT NOT NULL,
                    `model` TEXT NOT NULL,
                    `release_year` INTEGER NOT NULL,
                    `price` REAL NOT NULL,
                    `color` TEXT NOT NULL
                )
            """);
        }
    }

    public static List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT id, unique_id, brand, model, release_year, price, color FROM cars";

        try (Connection connection = Main.getDataSource().createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Car car = Car.builder()
                        .withId(resultSet.getLong("id"))
                        .withUniqueId(UUID.fromString(resultSet.getString("unique_id")))
                        .withBrand(CarBrand.valueOf(resultSet.getString("brand")))
                        .withModel(resultSet.getString("model"))
                        .withReleaseYear(resultSet.getInt("release_year"))
                        .withPrice(resultSet.getBigDecimal("price"))
                        .withColor(Color.decode("#" + resultSet.getString("color")))
                        .build();

                cars.add(car);
            }
        }

        return Collections.unmodifiableList(cars);
    }

    public static void addCar(Car car) throws SQLException {
        String sql = """
            INSERT INTO `cars`
                (`unique_id`, `brand`, `model`, `release_year`, `price`, `color`)
            VALUES
                (?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection = Main.getDataSource().createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.uniqueId().toString());
            preparedStatement.setString(2, car.brand().name());
            preparedStatement.setString(3, car.model());
            preparedStatement.setInt(4, car.releaseYear());
            preparedStatement.setBigDecimal(5, car.price());

            String colorAsHex = String.format("%06X", 0xFFFFFF & car.color().getRGB());
            preparedStatement.setString(6, colorAsHex);

            preparedStatement.execute();
        }
    }
}