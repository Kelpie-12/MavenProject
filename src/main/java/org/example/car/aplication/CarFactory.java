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

        /*

INSERT into Cars (Brand,"Year" ,CarName )VALUES ("BMW","2024","M5");
WITH var(value) AS (SELECT last_insert_rowid())
INSERT INTO Color
(R, G, B, CarId)
VALUES(123, 56, 120,(select value from var));
        */

    }

    public List<Car> create() {
        List<Car> cars = new ArrayList<>();
        Database database = new Database("jdbc:sqlite:cars.db");

        database.getConnection(connection -> {
            try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = //statement.executeQuery("SELECT * FROM Cars");
                        statement.executeQuery("""
                                SELECT Cars.Id,CarBrand.BrandName,Cars.CarName,Cars.Year,Cars.Price,Color.R,Color.G,Color.B
                                        from CarBrand, Cars,Color where Color.CarId ==Cars.id and Cars.Brand == CarBrand.BrandName
                                """);
                while (resultSet.next()) {

                    int id = resultSet.getInt("Id");
                   CarBrand brand =  CarBrand.valueOf(resultSet.getString("BrandName"));
                    String model = resultSet.getString("CarName");
                    int year = resultSet.getInt("Year");
                    double price = resultSet.getInt("Price");
                    Color colour=new Color( resultSet.getInt("R"), resultSet.getInt("G"), resultSet.getInt("B"));

                    Car tmp = new Car(new UUID(id, 0L), brand, model, year, new BigDecimal(price), colour);
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