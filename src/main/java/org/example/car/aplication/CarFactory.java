package org.example.car.aplication;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public List<Car> create() {
        List<Car> cars = new ArrayList<>();
        Database database = new Database("jdbc:sqlite:cars.db");

        database.getConnection(connection -> {
            try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = //statement.executeQuery("SELECT * FROM Cars");
                        statement.executeQuery("""
                                SELECT * FROM Cars
                                """);
                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String brand = resultSet.getString("brand");
                    String model = resultSet.getString("CarName");
                    int year = resultSet.getInt("Year");
                    double price = resultSet.getInt("Price");

                    Car tmp = new Car(new UUID(id, 0L), CarBrand.BMW, model, year, new BigDecimal(price), getRandomColor());
                    cars.add(tmp);
                    //   System.out.println("ID: " + id + " / Brand: " + brand);
                }
            } catch (SQLException exception) {
                System.out.println("Ошибка при запросе к базе данных: " + exception.getMessage());
            }
        });

        return cars;

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