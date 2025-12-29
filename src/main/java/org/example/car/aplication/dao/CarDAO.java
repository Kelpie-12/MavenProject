package org.example.car.aplication.dao;
import org.example.car.aplication.Car;
import org.example.car.aplication.CarBrand;
import org.example.car.aplication.database.DataSource;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarDAO {

    public static List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT unique_id, brand, model, release_year, price, color FROM cars";

        try (Connection connection = DataSource.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Car car = Car.builder()
                        .withUniqueId(UUID.fromString(resultSet.getString("unique_id")))
                        .withBrand(CarBrand.valueOf(resultSet.getString("brand")))
                        .withModel(resultSet.getString("model"))
                        .withReleaseYear(resultSet.getInt("release_year"))
                        .withPrice(resultSet.getBigDecimal("price"))
                        .withColor(Color.decode(resultSet.getString("color")))
                        .build();

                cars.add(car);
            }
        }

        return Collections.unmodifiableList(cars);
    }
}